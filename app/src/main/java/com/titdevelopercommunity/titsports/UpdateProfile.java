package com.titdevelopercommunity.titsports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {
    TextView name,email,phone,enrollment;
    Button update;
    FirebaseAuth authProfile;
    String userID,emailId,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name=findViewById(R.id.update_name);
        email=findViewById(R.id.update_email);
        phone=findViewById(R.id.update_phone);
        enrollment=findViewById(R.id.update_enrollment);
        update=findViewById(R.id.update_update);

        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=authProfile.getCurrentUser();
        if(firebaseUser==null){
            Toast.makeText(UpdateProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateProfile.this,Home.class);
            startActivity(intent);
            finish();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfile.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            userID = firebaseUser.getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Details");
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ReadWriteUserDetails details = dataSnapshot.getValue(ReadWriteUserDetails.class);
                    if (details != null) {
                        email.setText(firebaseUser.getEmail());
                        name.setText(details.getName());
                        phone.setText(details.getPhone());
                        enrollment.setText(details.getEnrollment());
                        emailId=firebaseUser.getEmail();
                        password= details.getPassword();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UpdateProfile.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        update.setOnClickListener(view -> {
            AlertDialog.Builder builder= new AlertDialog.Builder(UpdateProfile.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            DatabaseReference reference =FirebaseDatabase.getInstance().getReference("User Details");
            ReadWriteUserDetails details = new ReadWriteUserDetails(emailId,password,name.getText().toString(),enrollment.getText().toString(),phone.getText().toString());
            reference.child(userID).setValue(details).addOnCompleteListener(task -> {
                Toast.makeText(UpdateProfile.this, "Updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            });
        });
    }
}