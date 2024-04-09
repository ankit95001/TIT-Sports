package com.titdevelopercommunity.titsports;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class InterCollegeRegistration extends AppCompatActivity {
    String[] items = {"Volleyball", "Badminton", "Cricket", "Football", "Kabaddi", "Handball", "Athletics", "Chess", "Basketball", "Swimming", "Taekwondo", "Table Tennis", "Karate", "Kho-Kho", "Weight/power Lifting", "Yoga", "Other"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    Button upload_btn;
    EditText phone_no, student_name, enrollment;
    ImageView img;
    String imgURL;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_college_registratin);

        upload_btn = findViewById(R.id.inter_registration_save_button);
        student_name = findViewById(R.id.inter_registration_Name);
        phone_no = findViewById(R.id.inter_registration_phoneNo);
        enrollment = findViewById(R.id.inter_registration_enrollment);
        img = findViewById(R.id.upload_image_inter_registration);

        autoCompleteTextView = findViewById(R.id.inter_registration_autocomplete_txt);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);
        autoCompleteTextView.setAdapter(adapterItems);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        uri = data.getData();
                        img.setImageURI(uri);
                    } else {
                        Toast.makeText(InterCollegeRegistration.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                });
        img.setOnClickListener(view -> {
            Intent iphoto = new Intent(Intent.ACTION_PICK);
            iphoto.setType("image/*");
            activityResultLauncher.launch(iphoto);
        });

        upload_btn.setOnClickListener(view -> {
            if (!phone_no.getText().toString().equals("") && !student_name.getText().toString().equals("") && !enrollment.getText().toString().equals("") && !autoCompleteTextView.getText().toString().equals("")) {
                if(uri==null){
                    Toast.makeText(this, "Select your college id card photo first", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            } else {
                Toast.makeText(InterCollegeRegistration.this, "Update the Required field", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void saveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InterCollegeRegistration.this);
        builder.setCancelable(false);
        builder.setView(R.layout.process_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("InterCollegeRegistration").child(autoCompleteTextView.getText().toString()).child(enrollment.getText().toString());
        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while (!uriTask.isComplete()) ;
            Uri urlImage = uriTask.getResult();
            imgURL = urlImage.toString();
            uploadFiles();
            dialog.dismiss();


        }).addOnFailureListener(
                e -> {
                    Toast.makeText(InterCollegeRegistration.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
    }
    private void uploadFiles() {
        DataClass data = new DataClass(phone_no.getText().toString(), student_name.getText().toString(), enrollment.getText().toString(), imgURL);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("InterCollegeRegistration").child(autoCompleteTextView.getText().toString());
        databaseReference.child(enrollment.getText().toString())
                .setValue(data).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(InterCollegeRegistration.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e ->
                        Toast.makeText(InterCollegeRegistration.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show());

    }
}