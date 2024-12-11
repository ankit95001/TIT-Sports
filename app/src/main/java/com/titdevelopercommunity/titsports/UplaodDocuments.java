package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;


public class UplaodDocuments extends Fragment {

    Button nodal,interCollege;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> Log.d("Ankit", "onActivityResult")
    );
    View v;

    public UplaodDocuments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_uplaod_documents, container, false);

        nodal=v.findViewById(R.id.nodal_registration);
        interCollege=v.findViewById(R.id.inter_registration);

        nodal.setOnClickListener(view -> {
            Intent iNodal = new Intent(v.getContext(), NodalRegistration.class);
            activityResultLauncher.launch(iNodal);
        });
        interCollege.setOnClickListener(view -> {
            Toast.makeText(getContext(), "This action is not allowed.", Toast.LENGTH_SHORT).show();
//            Intent iNodal = new Intent(v.getContext(), InterCollegeRegistration.class);
//            activityResultLauncher.launch(iNodal);
        });



        return v;
    }
}