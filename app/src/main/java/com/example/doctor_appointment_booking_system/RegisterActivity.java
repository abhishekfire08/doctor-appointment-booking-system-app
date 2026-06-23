package com.example.doctor_appointment_booking_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etMobile, etPassword, etDob;
    Spinner spinnerGender;
    ProgressBar progressRegister;
    TextView tvGoToLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etDob = findViewById(R.id.etDob);
        spinnerGender = findViewById(R.id.spinnerGender);
        progressRegister = findViewById(R.id.progressRegister);

        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnRegister.setOnClickListener(v ->
                Toast.makeText(RegisterActivity.this,
                        "Register Successful",
                        Toast.LENGTH_SHORT).show());

        tvGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this,
                    LoginActivity.class));
            finish();
        });
    }
}