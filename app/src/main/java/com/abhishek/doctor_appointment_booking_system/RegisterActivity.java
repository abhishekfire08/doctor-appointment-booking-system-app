package com.abhishek.doctor_appointment_booking_system;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etMobile, etPassword;
    RadioGroup rgGender;
    ProgressBar progressRegister;
    TextView tvGoToLogin;
    Button btnRegister;

    LinearLayout dobContainer;
    TextView tvDob;
    ImageView ivCalendar;

    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new MyDatabaseHelper(this);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        rgGender = findViewById(R.id.rgGender);

        dobContainer = findViewById(R.id.dobContainer);
        tvDob = findViewById(R.id.tvDob);
        ivCalendar = findViewById(R.id.ivCalendar);

        progressRegister = findViewById(R.id.progressRegister);

        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        // ✅ DOB CLICK
        dobContainer.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RegisterActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {

                        @SuppressLint("DefaultLocale") String dob = String.format("%02d/%02d/%04d",
                                selectedDay,
                                selectedMonth + 1,
                                selectedYear);

                        tvDob.setText(dob);
                    },
                    year, month, day
            );

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        btnRegister.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String dob = tvDob.getText().toString().trim();

            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            if (fullName.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() ||
                    dob.equals("Select Date of Birth") || selectedGenderId == -1) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender.getText().toString();

            progressRegister.setVisibility(android.view.View.VISIBLE);
            btnRegister.setEnabled(false);

            boolean isInserted = db.insert(fullName, email, mobile, password, gender, dob);

            progressRegister.setVisibility(android.view.View.GONE);
            btnRegister.setEnabled(true);

            if (isInserted) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Registration Failed or Email already exists", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}