package com.titdevelopercommunity.titsports;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;

public class WhatsappAdapter extends RecyclerView.Adapter<WhatsappAdapter.WhatsappViewHolder> {

    Context context;
    ArrayList<WhatsappDataClass> whatsappDataClasses;

    public WhatsappAdapter(Context context, ArrayList<WhatsappDataClass> whatsappDataClasses) {
        this.context = context;
        this.whatsappDataClasses = whatsappDataClasses;
    }

    @NonNull
    @Override
    public WhatsappAdapter.WhatsappViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.contact_layout,viewGroup,false);

        return new WhatsappViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsappAdapter.WhatsappViewHolder whatsappViewHolder, int i) {
        WhatsappDataClass whatsappDataClass=whatsappDataClasses.get(i);
        whatsappViewHolder.gameName.setText(whatsappDataClass.getGameName());
        whatsappViewHolder.gameCoordinatorName.setText(whatsappDataClass.getGameCoordinatorName());
        whatsappViewHolder.gameCoordinatorNumber.setOnClickListener(view -> {
            try{
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel: "+whatsappDataClass.getGameCoordinatorNumber()));
                context.startActivity(call);
            }catch (Exception e){
                Log.e("call error", Objects.requireNonNull(e.getMessage()));
            }
        });
        whatsappViewHolder.gameLink.setOnClickListener(view -> {
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(whatsappDataClass.gameLink));
                intentWhatsapp.setPackage("com.whatsapp");
                context.startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(whatsappDataClass.gameLink));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    context.startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(context.getApplicationContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return whatsappDataClasses.size();
    }
    public static class WhatsappViewHolder extends RecyclerView.ViewHolder{
        TextView gameName,gameCoordinatorName;
        ImageView gameCoordinatorNumber,gameLink;
        public WhatsappViewHolder(@NonNull View itemView) {
            super(itemView);
            gameName=itemView.findViewById(R.id.gameName);
            gameCoordinatorName=itemView.findViewById(R.id.gameCoordinatorName);
            gameCoordinatorNumber=itemView.findViewById(R.id.game_call);
            gameLink=itemView.findViewById(R.id.game_whatsapp);

        }
    }
}
