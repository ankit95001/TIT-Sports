package com.titdevelopercommunity.titsports;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WhatsappGroup extends Fragment {


    public WhatsappGroup() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    ArrayList<WhatsappDataClass> WhatsappDataClasses;
    WhatsappAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_whatsapp_group, container, false);


        recyclerView=v.findViewById(R.id.contactRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        WhatsappDataClasses=new ArrayList<WhatsappDataClass>();
        adapter=new WhatsappAdapter(getContext(),WhatsappDataClasses);

        recyclerView.setAdapter(adapter);

        getData();



        return v;
    }
    @SuppressLint("NotifyDataSetChanged")
    private void getData() {
        // Show a loading dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.pleasewait_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Reference to the "volunteer details" node in Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("volunteer details");
        reference.addListenerForSingleValueEvent(new ValueEventListener() { // Removed .child() for correct access
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WhatsappDataClasses.clear(); // Clear the list to avoid duplication

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Map each child to the WhatsappDataClass
                    WhatsappDataClass details = snapshot.getValue(WhatsappDataClass.class);
                    if (details != null) {
                        WhatsappDataClasses.add(details);
                    }
                }

                // Dismiss the dialog after data is loaded
                dialog.dismiss();

                // Notify the adapter to update the RecyclerView
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}