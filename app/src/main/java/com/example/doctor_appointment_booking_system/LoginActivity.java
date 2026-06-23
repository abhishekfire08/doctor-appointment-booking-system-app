package com.example.doctor_appointment_booking_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvGoToRegister, tvForgotPassword;
    Button btnLogin;
    ProgressBar progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressLogin = findViewById(R.id.progressLogin);

        btnLogin.setOnClickListener(v ->
                Toast.makeText(LoginActivity.this,
                        "Login Button",
                        Toast.LENGTH_SHORT).show());

        tvGoToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,
                    RegisterActivity.class));
            finish();
        });

        tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,
                    ForgotPasswordActivity.class));
            finish();
        });
    }
}