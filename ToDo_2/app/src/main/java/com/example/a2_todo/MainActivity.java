package com.example.a2_todo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private TextView hashtag, newsTicker;
    private ImageView channelLogo, airplane, dolphin, sea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hashtag = findViewById(R.id.hashtag);
        channelLogo = findViewById(R.id.channelLogo);
        newsTicker = findViewById(R.id.newsTicker);
        airplane = findViewById(R.id.airplane);
        dolphin = findViewById(R.id.dolphin);
        sea = findViewById(R.id.sea);

        // Load and set images with resized bitmaps
        channelLogo.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.logo, 100, 100));
        airplane.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.airplane, 200, 200));
        dolphin.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.dolphin, 100, 100));
        sea.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.img, 1920, 200));

        startAnimations();
    }

    private void startAnimations() {
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        Animation airplaneAnimation = AnimationUtils.loadAnimation(this, R.anim.airplane_animation);
        Animation dolphinAnimation = AnimationUtils.loadAnimation(this, R.anim.dolphin_rotation);

        hashtag.startAnimation(fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                hashtag.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                hashtag.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        channelLogo.startAnimation(scaleAnimation);
        newsTicker.startAnimation(translateAnimation);
        airplane.startAnimation(airplaneAnimation);
        dolphin.startAnimation(dolphinAnimation);
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
