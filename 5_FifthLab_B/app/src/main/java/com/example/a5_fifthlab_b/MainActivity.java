package com.example.a5_fifthlab_b;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRotate = findViewById(R.id.button_rotate);
        Button buttonScale = findViewById(R.id.button_scale);
        Button buttonTranslate = findViewById(R.id.button_translate);
        Button buttonAlpha = findViewById(R.id.button_alpha);
        Button buttonSecondTranslate = findViewById(R.id.button_second_translate);
        Button buttonTodo= findViewById(R.id.button_todo);
        final ImageView imageView = findViewById(R.id.imageView);

        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate));
            }
        });

        buttonScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale));
            }
        });

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate));
            }
        });

        buttonAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha));
            }
        });

        buttonSecondTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.second_translate));
            }
        });

        buttonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.todo));
            }
        });
    }
}

