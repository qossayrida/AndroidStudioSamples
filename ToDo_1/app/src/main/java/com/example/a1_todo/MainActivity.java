package com.example.a1_todo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private ImageView adversaryImageView;
    private int cumulativeScore = 0;

    private Button btnRock;
    private Button btnPaper;
    private Button btnScissors;

    private static final String PREFS_NAME = "GamePrefs";
    private static final String SCORE_KEY = "cumulativeScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.score);
        adversaryImageView = findViewById(R.id.adversaryChoice);

        cumulativeScore = 0;
        btnRock = findViewById(R.id.btnRock);
        btnPaper = findViewById(R.id.btnPaper);
        btnScissors = findViewById(R.id.btnScissors);

        btnRock.setOnClickListener(v -> playGame("rock"));
        btnPaper.setOnClickListener(v -> playGame("paper"));
        btnScissors.setOnClickListener(v -> playGame("scissors"));

        // Retrieve the cumulative score from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        cumulativeScore = prefs.getInt(SCORE_KEY, 0);
        scoreTextView.setText("Score: " + cumulativeScore);
    }

    private void playGame(String playerChoice) {
        disableButtons();

        Handler handler = new Handler();
        String adversaryChoice = getAdversaryChoice();

        // Change the adversary choice every 100ms for the first 1.5 seconds
        for (int i = 0; i < 16; i++) {
            handler.postDelayed(() -> showAdversaryChoice(getAdversaryChoice()), i * 100);
        }

        // Show the final adversary choice at 1.5 seconds
        handler.postDelayed(() -> showAdversaryChoice(adversaryChoice), 1500);

        // Proceed to the ResultActivity after 5 seconds
        handler.postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("adversaryChoice", adversaryChoice);
            intent.putExtra("playerChoice", playerChoice);
            intent.putExtra("cumulativeScore", cumulativeScore);
            startActivity(intent);

            enableButtons();
        }, 5000);
    }

    private String getAdversaryChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        Random random = new Random();
        return choices[random.nextInt(choices.length)];
    }

    private void showAdversaryChoice(String choice) {
        int resId = getResources().getIdentifier("ic_" + choice, "drawable", getPackageName());
        adversaryImageView.setImageResource(resId);
        adversaryImageView.setVisibility(View.VISIBLE);
    }

    private void disableButtons() {
        btnRock.setEnabled(false);
        btnPaper.setEnabled(false);
        btnScissors.setEnabled(false);
    }

    private void enableButtons() {
        btnRock.setEnabled(true);
        btnPaper.setEnabled(true);
        btnScissors.setEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieve the cumulative score from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        cumulativeScore = prefs.getInt(SCORE_KEY, 0);
        scoreTextView.setText("Score: " + cumulativeScore);
    }
}
