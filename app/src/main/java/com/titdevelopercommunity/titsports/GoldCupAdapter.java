package com.titdevelopercommunity.titsports;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class GoldCupAdapter extends RecyclerView.Adapter<GoldCupAdapter.GoldCupViewAdapter> {

    private Context context;
    private List<String> images;

    public GoldCupAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public GoldCupViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_view,parent,false);
        return new GoldCupViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoldCupViewAdapter holder, int position) {
        String documentUrl = images.get(position);
        Glide.with(context)
                .load(documentUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache image on disk
                .fitCenter() // Ensure the image fits the view proportionally
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            // Create an Intent to open FullScreenImageActivity
            try {
                Intent intent = new Intent(context, FullScreenImageActivity.class);
                intent.putExtra("image_url", documentUrl); // Pass image URL to FullScreenActivity
                context.startActivity(intent);
            }catch (Exception e){
                Toast.makeText(context, "error"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
    public class GoldCupViewAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GoldCupViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView1);
        }
    }
}