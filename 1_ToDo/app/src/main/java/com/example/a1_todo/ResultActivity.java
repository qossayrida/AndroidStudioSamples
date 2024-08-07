package com.example.a1_todo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private int cumulativeScore;
    private static final String PREFS_NAME = "GamePrefs";
    private static final String SCORE_KEY = "cumulativeScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView feedbackTextView = findViewById(R.id.feedback);
        TextView finalScoreTextView = findViewById(R.id.finalScore);
        Button btnReset = findViewById(R.id.btnReset);
        int color = getResources().getColor(android.R.color.holo_red_dark);
        btnReset.setBackgroundTintList(ColorStateList.valueOf(color));

        Intent intent = getIntent();
        String playerChoice = intent.getStringExtra("playerChoice");
        String adversaryChoice = intent.getStringExtra("adversaryChoice");
        cumulativeScore = intent.getIntExtra("cumulativeScore", 0);

        String result = getResult(playerChoice, adversaryChoice);
        feedbackTextView.setText(result);
        finalScoreTextView.setText("Score: " + cumulativeScore);

        btnReset.setOnClickListener(v -> {
            // Save the updated cumulative score to SharedPreferences
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(SCORE_KEY, cumulativeScore);
            editor.apply();

            Intent resetIntent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(resetIntent);
            finish();
        });
    }

    private String getResult(String playerChoice, String adversaryChoice) {
        if (playerChoice.equals(adversaryChoice)) {
            cumulativeScore += 1;
            return "It's a Draw! (1) ";
        } else if ((playerChoice.equals("rock") && adversaryChoice.equals("scissors")) ||
                (playerChoice.equals("paper") && adversaryChoice.equals("rock")) ||
                (playerChoice.equals("scissors") && adversaryChoice.equals("paper"))) {
            cumulativeScore += 3;
            return "You Won! (3) ";
        } else {
            cumulativeScore -= 4;
            return "You Lost! (-4) ";
        }
    }
}
