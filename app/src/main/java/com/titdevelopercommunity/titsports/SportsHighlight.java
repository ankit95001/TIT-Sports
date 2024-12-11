package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SportsHighlight extends Fragment {

    public SportsHighlight() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_sports_highlight, container, false);

        Button gold_cup_highlights=v.findViewById(R.id.gold_cup_highlights);
        Button nodalHighlights = v.findViewById(R.id.nodal_highlights);


        gold_cup_highlights.setOnClickListener(view -> {
            Intent intent = new Intent(v.getContext(), GoldCupHighlights.class);
            startActivity(intent);
        });

        nodalHighlights.setOnClickListener(view -> {
            Intent intent = new Intent(v.getContext(), NodalHighlights.class);
            startActivity(intent);
        });



        return v;
    }

}