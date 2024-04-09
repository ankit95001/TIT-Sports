package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class AboutDeveloper extends Fragment {



    public AboutDeveloper() {
        // Required empty public constructor
    }

    ImageView call,email,whatsapp,message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_about_developer, container, false);



        call=v.findViewById(R.id.about_call);
        email=v.findViewById(R.id.about_email);
        whatsapp=v.findViewById(R.id.about_whatsapp);
        message=v.findViewById(R.id.about_message);
        call.setOnClickListener(view -> {
            Intent icall = new Intent(Intent.ACTION_DIAL);
            icall.setData(Uri.parse("tel: +91 7870793511"));
            startActivity(icall);
        });
        message.setOnClickListener(view -> {
            Intent iMessage=new Intent(Intent.ACTION_SENDTO);
            iMessage.setData(Uri.parse("smsto:"+Uri.encode("+91 7870793511")));
            iMessage.putExtra("sms_body","Enter you query here!");
            startActivity(iMessage);
        });
        email.setOnClickListener(view -> {
            Intent iEmail=new Intent(Intent.ACTION_SEND);
            iEmail.setType("message/rfc822");
            iEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"titdevelopercommunity@gmail.com"});
            iEmail.putExtra(Intent.EXTRA_SUBJECT,"Sports related queries");
            iEmail.putExtra(Intent.EXTRA_TEXT,"Enter your queries here!");
            startActivity(Intent.createChooser(iEmail,"Email via"));
        });
        whatsapp.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/L8hantnBl7o0QPXjeiAfVI";
            try {
                Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                intentWhatsapp.setData(Uri.parse(url));
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
            } catch (Exception e) {
                try {
                    Intent intentWhatsapp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsapp.setData(Uri.parse(url));
                    intentWhatsapp.setPackage("com.whatsapp.w4b");
                    startActivity(intentWhatsapp);
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return v;
    }
}