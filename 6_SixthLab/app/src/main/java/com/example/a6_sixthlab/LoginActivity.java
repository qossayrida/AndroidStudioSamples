package com.example.a6_sixthlab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;
    TextView textViewStatus;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewStatus = findViewById(R.id.textViewStatus);
        sharedPrefManager = SharedPrefManager.getInstance(this);

        buttonLogin.setOnClickListener(v -> {
            String inputUsername = editTextUsername.getText().toString();
            String inputPassword = editTextPassword.getText().toString();
            String hashedPassword = Hash.hashPassword(inputPassword);

            String storedUsername = sharedPrefManager.readString("userName", "noValue");
            String storedPassword = sharedPrefManager.readString("password", "noValue");

            if (inputUsername.equals(storedUsername) && hashedPassword.equals(storedPassword)) {
                textViewStatus.setText("Login successful");
            } else {
                textViewStatus.setText("Login failed");
            }
        });
    }
}
