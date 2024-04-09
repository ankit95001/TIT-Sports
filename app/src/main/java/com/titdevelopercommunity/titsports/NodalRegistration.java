package com.titdevelopercommunity.titsports;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class NodalRegistration extends AppCompatActivity {
    Button upload_btn;
    EditText phone_no, student_name, enrollment;
    ImageView img;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String[] items = {"Volleyball", "Badminton", "Cricket", "Football", "Kabaddi", "Handball", "Athletics", "Chess", "Basketball", "Swimming", "Taekwondo", "Table Tennis", "Karate", "Kho-Kho", "Weight/power Lifting", "Yoga", "Other"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodal_registration);

        upload_btn = findViewById(R.id.yoga_save_button);
        student_name = findViewById(R.id.yoga_Name);
        phone_no = findViewById(R.id.yoga_phoneNo);
        enrollment = findViewById(R.id.yoga_enrollment);
        img = findViewById(R.id.upload_image_yoga);
        autoCompleteTextView = findViewById(R.id.nodal_autocomplete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);
        autoCompleteTextView.setAdapter(adapterItems);






        img.setOnClickListener(view -> {
            String[] mimeTypes =
                    {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                            "application/pdf"};

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(Intent.createChooser(intent, "ChooseFile"), 1);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            img.setImageResource(R.drawable.other);
            TextView textView = findViewById(R.id.yoga_text);
            textView.setText(R.string.file_selected);
            LinearLayout ll = findViewById(R.id.yoga_ll);
            ll.setVisibility(View.GONE);
            Toast.makeText(this, "File Selected!", Toast.LENGTH_SHORT).show();
            upload_btn.setOnClickListener(view -> {

                if (!phone_no.getText().toString().equals("") && !student_name.getText().toString().equals("") && !enrollment.getText().toString().equals("")&& !autoCompleteTextView.getText().toString().equals("")) {
                        UploadFiles(data.getData());
                } else {
                    Toast.makeText(NodalRegistration.this, "Update the Required field", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(NodalRegistration.this, "No item Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void UploadFiles(Uri data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NodalRegistration.this);
        builder.setCancelable(false);
        builder.setView(R.layout.process_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference reference = storageReference.child("nodalRegistration").child(autoCompleteTextView.getText().toString()).child(enrollment.getText().toString());
        reference.putFile(data).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while (!uriTask.isComplete()) ;
            Uri url = uriTask.getResult();

            DataClass dataClass = new DataClass(phone_no.getText().toString(), student_name.getText().toString(), enrollment.getText().toString(), url.toString());
            databaseReference = FirebaseDatabase.getInstance().getReference("nodalRegistration").child(autoCompleteTextView.getText().toString());
            databaseReference.child(enrollment.getText().toString()).setValue(dataClass);
            Toast.makeText(NodalRegistration.this, "File Uploaded", Toast.LENGTH_SHORT).show();
            finish();
            dialog.dismiss();
        }).addOnFailureListener(
                e -> {
                    Toast.makeText(NodalRegistration.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });
    }
}