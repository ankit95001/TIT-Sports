package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Logout extends Fragment {


    public Logout() {
        // Required empty public constructor
    }

    FirebaseAuth mAuth;
    Button btn_logout;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_logout, container, false);

        mAuth=FirebaseAuth.getInstance();
        btn_logout=v.findViewById(R.id.fragment_logout);
        user=mAuth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
        btn_logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        return v;
    }
}