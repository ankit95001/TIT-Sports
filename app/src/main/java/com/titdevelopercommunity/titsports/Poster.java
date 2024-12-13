package com.titdevelopercommunity.titsports;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Poster extends AppCompatActivity {
    private static final String CACHE_PREFS = "PosterCache";
    private static final String CACHE_KEY = "CachedPosterPhotos";
    private static final String TIMESTAMP_KEY = "CachePosterTimestamp";
    private static final long CACHE_EXPIRY = 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds

    RecyclerView galleryRecyclerView;
    GoldCupAdapter galleryAdapter;

    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        galleryRecyclerView = findViewById(R.id.imageListRecyclerPoster);

        // Initialize Firebase Realtime Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Highlights").child("Poster");

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(CACHE_PREFS, Context.MODE_PRIVATE);

        // Check cache or fetch from server
        loadHighlights();
    }
    private void loadHighlights() {
        long lastCacheTime = sharedPreferences.getLong(TIMESTAMP_KEY, 0);
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastCacheTime < CACHE_EXPIRY) {
            // Load from cache
            List<String> cachedPhotos = getCachedPhotos();
            if (cachedPhotos != null && !cachedPhotos.isEmpty()) {
                Log.d("CACHE", "Loaded photos from cache.");
                updateGalleryRecyclerView(cachedPhotos);
                return;
            }
        }

        // Fetch from server if cache is expired or empty
        fetchPhotosFromServer();
    }
    private List<String> getCachedPhotos() {
        String json = sharedPreferences.getString(CACHE_KEY, null);
        if (json == null) return null;

        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void cachePhotos(List<String> photoList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(photoList);
        editor.putString(CACHE_KEY, json);
        editor.putLong(TIMESTAMP_KEY, System.currentTimeMillis());
        editor.apply();
        Log.d("CACHE", "Photos cached successfully.");
    }

    private void fetchPhotosFromServer() {
        List<String> photoList = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot yearSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot photoSnapshot : yearSnapshot.getChildren()) {
                        String photoUrl = photoSnapshot.getValue(String.class);
                        if (photoUrl != null) {
                            photoList.add(photoUrl);
                        }
                    }
                }

                if (photoList.isEmpty()) {
                    Toast.makeText(Poster.this, "No photos found.", Toast.LENGTH_SHORT).show();
                } else {
                    cachePhotos(photoList); // Cache the photos
                    updateGalleryRecyclerView(photoList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FETCH_ERROR", "Failed to fetch photos: " + error.getMessage());
                Toast.makeText(Poster.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateGalleryRecyclerView(List<String> photoList) {
        if (galleryAdapter == null) {
            galleryAdapter = new GoldCupAdapter(this, photoList);
            galleryRecyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3-column grid
            galleryRecyclerView.setAdapter(galleryAdapter);
        } else {
            galleryAdapter.notifyDataSetChanged();
        }
    }
}