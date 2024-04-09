package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {
    TextView textView;
    private ViewPager2 viewPager2;
    ImageView call,message,email,whatsapp;
    private final Handler slideHandler = new Handler();
    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);


        //Contact Us
        call=v.findViewById(R.id.call);
        email=v.findViewById(R.id.email);
        whatsapp=v.findViewById(R.id.whatsapp);
        message=v.findViewById(R.id.message);
        call.setOnClickListener(view -> {
            try{
                Intent icall = new Intent(Intent.ACTION_DIAL);
                icall.setData(Uri.parse("tel: +91 7771003944"));
                startActivity(icall);
            }catch (Exception e){
                Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
            }
        });
        message.setOnClickListener(view -> {
            try{
                Intent iMessage=new Intent(Intent.ACTION_SENDTO);
                iMessage.setData(Uri.parse("smsto:"+Uri.encode("+91 7771003944")));
                iMessage.putExtra("sms_body","Enter you query here!");
                startActivity(iMessage);
            }catch (Exception e){
                Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
            }
        });
        email.setOnClickListener(view -> {
            try{
                Intent iEmail=new Intent(Intent.ACTION_SEND);
                iEmail.setType("message/rfc822");
                iEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"Sports@technocratsgroup.edu.in"});
                iEmail.putExtra(Intent.EXTRA_SUBJECT,"Sports related queries");
                iEmail.putExtra(Intent.EXTRA_TEXT,"Enter your queries here!");
                startActivity(Intent.createChooser(iEmail,"Email via"));
            }catch (Exception e){
                Toast.makeText(getContext(), "App not found", Toast.LENGTH_SHORT).show();
            }
        });
        whatsapp.setOnClickListener(view -> {
            String url = "https://chat.whatsapp.com/FvWpEFJcuCWLj32POqCV5m";
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





        viewPager2 = v.findViewById(R.id.viewPager2);
        List<RoundImageModel> imageList = new ArrayList<>();

        imageList.add(new RoundImageModel(R.drawable.cricket));
        imageList.add(new RoundImageModel(R.drawable.yoga));
        imageList.add(new RoundImageModel(R.drawable.tt));
        imageList.add(new RoundImageModel(R.drawable.taekwondo));
        imageList.add(new RoundImageModel(R.drawable.swimming));
        imageList.add(new RoundImageModel(R.drawable.atheletcs));
        imageList.add(new RoundImageModel(R.drawable.badminton));
        imageList.add(new RoundImageModel(R.drawable.basketball));
        imageList.add(new RoundImageModel(R.drawable.chess));
        imageList.add(new RoundImageModel(R.drawable.volleyball));
        imageList.add(new RoundImageModel(R.drawable.football));
        imageList.add(new RoundImageModel(R.drawable.kabaddi));
        imageList.add(new RoundImageModel(R.drawable.handball));
        imageList.add(new RoundImageModel(R.drawable.karate));
        imageList.add(new RoundImageModel(R.drawable.khokho));
        imageList.add(new RoundImageModel(R.drawable.weight));


        HomeImageAdapter adapter = new HomeImageAdapter(imageList, viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.14f);
        });
        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 2000);
            }
        });

        textView = v.findViewById(R.id.home_notice);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Notice");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m = snapshot.getValue(String.class);
                textView.setText(m);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Notice", "Error is" + error);
            }
        });


        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 2000);
    }
}