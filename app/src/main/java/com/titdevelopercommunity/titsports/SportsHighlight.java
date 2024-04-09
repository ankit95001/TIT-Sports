package com.titdevelopercommunity.titsports;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class SportsHighlight extends Fragment {

    public SportsHighlight() {
        // Required empty public constructor
    }
    private ViewPager2 goldCup,sportsTeam,sportsAchievements,sportsProgram;
    HomeImageAdapter adapterGoldCup,adapterSportsTeam,adapterSportsAchievement,adapterSportsProgram;
    private final Handler slideHandler = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_sports_highlight, container, false);
        goldCup=v.findViewById(R.id.goldCup);
        sportsTeam=v.findViewById(R.id.sportsTeam);
        sportsAchievements=v.findViewById(R.id.sportsAchievement);
        sportsProgram=v.findViewById(R.id.otherSportsProgram);

        List<RoundImageModel> imageListGoldCup = new ArrayList<>();
        List<RoundImageModel> imageListSportsTeam = new ArrayList<>();
        List<RoundImageModel> imageListSportsAchievement = new ArrayList<>();
        List<RoundImageModel> imageListSportsProgram = new ArrayList<>();

        CompositePageTransformer transformer=new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer((page, position) -> {
            float r=1-Math.abs(position);
            page.setScaleY(0.85f+r*0.14f);
        });

        // Achievements image
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.aa));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ab));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ac));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ad));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ae));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.af));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ag));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ah));
        imageListSportsAchievement.add(new RoundImageModel(R.drawable.ai));

        //Set image on adapter
        adapterSportsAchievement=new HomeImageAdapter(imageListSportsAchievement,sportsAchievements);
        sportsAchievements.setAdapter(adapterSportsAchievement);

        //Animation
        sportsAchievements.setOffscreenPageLimit(3);
        sportsAchievements.setClipToPadding(false);
        sportsAchievements.setClipChildren(false);
        sportsAchievements.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        sportsAchievements.setPageTransformer(transformer);
        sportsAchievements.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);
            }
        });

        //Gold Cup
        imageListGoldCup.add(new RoundImageModel(R.drawable.ba));
        imageListGoldCup.add(new RoundImageModel(R.drawable.bb));
        imageListGoldCup.add(new RoundImageModel(R.drawable.bc));
        imageListGoldCup.add(new RoundImageModel(R.drawable.bd));
        imageListGoldCup.add(new RoundImageModel(R.drawable.be));
        imageListGoldCup.add(new RoundImageModel(R.drawable.bf));
        imageListGoldCup.add(new RoundImageModel(R.drawable.bg));
        imageListGoldCup.add(new RoundImageModel(R.drawable.bh));

        //Set image on adapter
        adapterGoldCup=new HomeImageAdapter(imageListGoldCup,goldCup);
        goldCup.setAdapter(adapterGoldCup);

        //Animation
        goldCup.setOffscreenPageLimit(3);
        goldCup.setClipToPadding(false);
        goldCup.setClipChildren(false);
        goldCup.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        goldCup.setPageTransformer(transformer);
        goldCup.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);
            }
        });

        //TIT Sports Team
        imageListSportsTeam.add(new RoundImageModel(R.drawable.ca));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cb));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cc));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cd));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.ce));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cf));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cg));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.ch));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.ci));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cj));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.ck));
        imageListSportsTeam.add(new RoundImageModel(R.drawable.cl));

        //Set image on adapter
        adapterSportsTeam=new HomeImageAdapter(imageListSportsTeam,sportsTeam);
        sportsTeam.setAdapter(adapterSportsTeam);

        //Animation
        sportsTeam.setOffscreenPageLimit(3);
        sportsTeam.setClipToPadding(false);
        sportsTeam.setClipChildren(false);
        sportsTeam.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        sportsTeam.setPageTransformer(transformer);
        sportsTeam.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);
            }
        });

        // Sports Program
        //TIT Sports Team
        imageListSportsProgram.add(new RoundImageModel(R.drawable.da));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.db));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.dc));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.dd));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.de));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.df));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.dg));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.dh));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.di));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.dj));
        imageListSportsProgram.add(new RoundImageModel(R.drawable.dk));

        //Set image on adapter
        adapterSportsProgram=new HomeImageAdapter(imageListSportsProgram,sportsProgram);
        sportsProgram.setAdapter(adapterSportsProgram);

        //Animation
        sportsProgram.setOffscreenPageLimit(3);
        sportsProgram.setClipToPadding(false);
        sportsProgram.setClipChildren(false);
        sportsProgram.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        sportsProgram.setPageTransformer(transformer);
        sportsProgram.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);
            }
        });


        return v;
    }

    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            sportsAchievements.setCurrentItem(sportsAchievements.getCurrentItem()+1);
            goldCup.setCurrentItem(goldCup.getCurrentItem()+1);
            sportsTeam.setCurrentItem(sportsTeam.getCurrentItem()+1);
            sportsProgram.setCurrentItem(sportsProgram.getCurrentItem()+1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,3000);
    }
}