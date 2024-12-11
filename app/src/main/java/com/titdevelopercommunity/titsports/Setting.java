package com.titdevelopercommunity.titsports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Setting extends Fragment {


    public Setting() {
        // Required empty public constructor
    }

    TextView name,email,phone,enrollment,sport;
    Button update;
    FirebaseAuth authProfile;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> Log.d("Ankit", "onActivityResult")
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_setting, container, false);

        name=v.findViewById(R.id.profile_name);
        email=v.findViewById(R.id.profile_email);
        phone=v.findViewById(R.id.profile_phone);
        enrollment=v.findViewById(R.id.profile_enrollment);
        update=v.findViewById(R.id.profile_update);
        sport=v.findViewById(R.id.profile_sport);


        update.setOnClickListener(view -> {
            Intent iNodal = new Intent(v.getContext(), UpdateProfile.class);
            activityResultLauncher.launch(iNodal);
        });

        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=authProfile.getCurrentUser();
        if(firebaseUser==null){
            Toast.makeText(v.getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(),Home.class);
            startActivity(intent);
            requireActivity().finish();
        }else{
            AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            String userID=firebaseUser.getUid();
            DatabaseReference reference =FirebaseDatabase.getInstance().getReference("User Details");
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ReadWriteUserDetails details = dataSnapshot.getValue(ReadWriteUserDetails.class);
                    if(details!=null){
                        email.setText(firebaseUser.getEmail());
                        name.setText(details.getName());
                        phone.setText(details.getPhone());
                        enrollment.setText(details.getEnrollment());
                        sport.setText(details.getSport());
                        dialog.dismiss();
                        }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        return v;
    }
}