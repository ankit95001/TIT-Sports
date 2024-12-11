package com.titdevelopercommunity.titsports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    String[] items = {"Volleyball", "Badminton", "Cricket", "Football", "Kabaddi", "Handball", "Athletics", "Chess", "Basketball", "Swimming", "Taekwondo", "Table Tennis", "Karate", "Kho-Kho", "Weight/power Lifting", "Yoga", "Other"};
    TextInputEditText edtEmail,edtPassword,edtName,edtEnrollment,edtPhone;
    TextView txt;
    Button btn_register;
    FirebaseAuth mAuth;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    String email,password,name,enrollment,phone,sport;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        edtEmail=findViewById(R.id.register_email);
        edtPassword=findViewById(R.id.register_password);
        edtName=findViewById(R.id.register_name);
        edtEnrollment=findViewById(R.id.register_enrollment);
        btn_register=findViewById(R.id.register_button);
        txt=findViewById(R.id.register_login_button);
        edtPhone=findViewById(R.id.register_phone);

        autoCompleteTextView = findViewById(R.id.sport_autocomplete_txt);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);
        autoCompleteTextView.setAdapter(adapterItems);

        txt.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        btn_register.setOnClickListener(view -> {
            AlertDialog.Builder builder= new AlertDialog.Builder(RegisterActivity.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();



            email=String.valueOf(edtEmail.getText());
            password=String.valueOf(edtPassword.getText());
            name=String.valueOf(edtName.getText());
            enrollment=String.valueOf(edtEnrollment.getText());
            phone=String.valueOf(edtPhone.getText());
            sport=String.valueOf(autoCompleteTextView.getText());

            //Validation of mobile number
            String mobileRgx="[6-9][0-9]{9}";
            Matcher mobileMatcher;
            Pattern mobilePattern = Pattern.compile(mobileRgx);
            mobileMatcher=mobilePattern.matcher(phone);

            if(TextUtils.isEmpty(name)){
                edtName.setError("Full name is Required");
                edtName.requestFocus();
                dialog.dismiss();
                return;
            }
            if(TextUtils.isEmpty(phone)){
                edtPhone.setError("Phone number is Required");
                edtPhone.requestFocus();
                dialog.dismiss();
                return;
            }
            if(TextUtils.isEmpty(enrollment)){
                edtEnrollment.setError("Enrollment number is Required");
                edtEnrollment.requestFocus();
                dialog.dismiss();
                return;
            }
            if(TextUtils.isEmpty(email)){
                edtEmail.setError("Phone number is Required");
                edtEmail.requestFocus();
                dialog.dismiss();
                return;
            }
            if(TextUtils.isEmpty(password)){
                edtPassword.setError("Password is Required");
                edtPassword.requestFocus();
                dialog.dismiss();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(RegisterActivity.this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
                edtEmail.setError("Valid Email is Required");
                edtEmail.requestFocus();
                dialog.dismiss();
                return;
            }
            if(!(phone.length()==10)){
                edtPhone.setError("Enter valid Phone number");
                edtPhone.requestFocus();
                dialog.dismiss();
                return;
            }
            if(!mobileMatcher.find()){
                edtPhone.setError("Enter valid Phone number");
                edtPhone.requestFocus();
                dialog.dismiss();
                return;
            }
            if(sport.equals("")){
                autoCompleteTextView.setError("Enter valid Phone number");
                autoCompleteTextView.requestFocus();
                dialog.dismiss();
                return;
            }


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            ReadWriteUserDetails details = new ReadWriteUserDetails(email,password,name,enrollment,phone,sport);
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Details");


                            dialog.dismiss();
                            assert firebaseUser != null;
                            reference.child(firebaseUser.getUid()).setValue(details).addOnCompleteListener(task1 -> {
                                Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            });
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }
}