package com.titdevelopercommunity.titsports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class ForgetPassword extends AppCompatActivity {
    TextInputEditText email;
    TextView back;
    String emailId;
    Button sendEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email=findViewById(R.id.forgot_email);
        back=findViewById(R.id.forgot_text_view);
        sendEmail=findViewById(R.id.email_send_button);

        back.setOnClickListener(view -> {
            Intent intent =new Intent(ForgetPassword.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        mAuth=FirebaseAuth.getInstance();
        sendEmail.setOnClickListener(view -> validateData());
    }
    private void validateData() {
        emailId=String.valueOf(email.getText());
        if(emailId.isEmpty()){
            email.setError("Required");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            email.setError("Enter a valid Email!");
        } else{
            AlertDialog.Builder builder= new AlertDialog.Builder(ForgetPassword.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            forgetPassword(dialog);
        }
    }
    private void forgetPassword(AlertDialog dialog) {
        mAuth.sendPasswordResetEmail(emailId)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        dialog.dismiss();
                        Toast.makeText(ForgetPassword.this, "Password reset link has been sent to your Email!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
                        finish();
                    }
                    else{
                        try{
                            throw Objects.requireNonNull(task.getException());
                        }
                        catch (FirebaseAuthInvalidUserException e){
                            email.setError("User not Exist, Please Register again");
                        }
                        catch(Exception e){
                            Toast.makeText(ForgetPassword.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}