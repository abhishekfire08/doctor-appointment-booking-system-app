package com.example.doctor_appointment_booking_system;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etMobile, etPassword, etDob;
    RadioButton rbMale, rbFemale;
    Button btnSignup;
    TextView tvLogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etDob = findViewById(R.id.etDob);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tvLogin);

        // Date of Birth Click Listener to show Calendar
        etDob.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    SignupActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                        etDob.setText(selectedDate);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        btnSignup.setOnClickListener(view -> {
            String fullName = etFullName.getText().toString();
            String email = etEmail.getText().toString();
            String mobile = etMobile.getText().toString();
            String password = etPassword.getText().toString();
            String dob = etDob.getText().toString();
            String gender = "";

            if (rbMale.isChecked()) {
                gender = "Male";
            } else if (rbFemale.isChecked()) {
                gender = "Female";
            }

            if (fullName.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || gender.isEmpty() || dob.isEmpty()) {
                Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkUserEmail = databaseHelper.checkEmail(email);

                if (!checkUserEmail) {
                    Boolean insert = databaseHelper.insertData(fullName, email, mobile, password, gender, dob);
                    if (insert) {
                        Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}
