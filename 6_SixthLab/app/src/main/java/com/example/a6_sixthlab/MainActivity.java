package com.example.a6_sixthlab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    Button buttonSave;
    Button buttonGoToSecondActivity;
    Button buttonGoToLoginActivity;
    SharedPrefManager sharedPrefManager;
    Intent intentToSecondActivity;
    Intent intentToLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editText_password);
        buttonSave = findViewById(R.id.buttonSave);
        buttonGoToSecondActivity = findViewById(R.id.buttonGoToSecondActivity);
        buttonGoToLoginActivity = findViewById(R.id.buttonGoToLoginActivity);
        sharedPrefManager = SharedPrefManager.getInstance(this);

        intentToSecondActivity = new Intent(MainActivity.this, SecondActivity.class);
        intentToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);

        buttonSave.setOnClickListener(v -> {
            sharedPrefManager.writeString("userName", editTextUserName.getText().toString());
            sharedPrefManager.writeString("password", Hash.hashPassword(editTextPassword.getText().toString()));
            Toast.makeText(MainActivity.this, "Values written to Shared Preferences", Toast.LENGTH_SHORT).show();
        });

        buttonGoToSecondActivity.setOnClickListener(v -> {
            startActivity(intentToSecondActivity);
            finish();
        });

        buttonGoToLoginActivity.setOnClickListener(v -> {
            startActivity(intentToLoginActivity);
            finish();
        });
    }
}
