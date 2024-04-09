package com.titdevelopercommunity.titsports;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edtEmail, edtPassword;
    TextView txt, forgetTxt;
    Button btn_login;
    FirebaseAuth mAuth;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> Log.d("Ankit", "onActivityResult")
    );

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_avtivity);

        mAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.login_email);
        edtPassword = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.login_button);
        txt = findViewById(R.id.login_Register_button);
        forgetTxt = findViewById(R.id.forgot_text_view);


        // Forget Password
        forgetTxt.setOnClickListener(view -> {
            Intent iNodal = new Intent(getApplicationContext(),ForgetPassword.class);
            activityResultLauncher.launch(iNodal);
        });
        //Register
        txt.setOnClickListener(view -> {
            Intent iNodal = new Intent(getApplicationContext(), RegisterActivity.class);
            activityResultLauncher.launch(iNodal);
        });

        //Login

        btn_login.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            String email, password;
            email = String.valueOf(edtEmail.getText());
            password = String.valueOf(edtPassword.getText());

            if (TextUtils.isEmpty(email)) {
                edtEmail.setError("Required");
                dialog.dismiss();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                edtPassword.setError("Required");
                dialog.dismiss();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });


    }
}