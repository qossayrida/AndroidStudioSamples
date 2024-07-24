package com.example.secondlab;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private static final String MY_CHANNEL_ID = "my_chanel_1";
    private static final String MY_CHANNEL_NAME = "My channel";

    private static final int NOTIFICATION_ID = 123;
    private static final String NOTIFICATION_TITLE = "Important message";
    private static final String NOTIFICATION_BODY = "Hiiiiiiiiiiiiiiiiiiii";

    private static final String TOAST_TEXT = "Doneeee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button dialButton = findViewById(R.id.button_dial);
        Button gmailButton = findViewById(R.id.button_gmail);
        Button mapsButton = findViewById(R.id.button_maps);
        Button notificationButton = findViewById(R.id.button_notification);
        Button toastButton = findViewById(R.id.button_toast);
        Button settingsButton = findViewById(R.id.button_settings);

        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent();
                dialIntent.setAction(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:+9725985924232"));
                startActivity(dialIntent);
            }
        });

        gmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gmailIntent = new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:"));
                gmailIntent.putExtra(Intent.EXTRA_EMAIL, "aqusay@gmail.com");
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email");
                gmailIntent.putExtra(Intent.EXTRA_TEXT, "Content of the message");
                startActivity(gmailIntent);
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapsIntent = new Intent();
                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:35.5,35.6"));
                startActivity(mapsIntent);
            }
        });

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification(NOTIFICATION_TITLE, NOTIFICATION_BODY);
            }
        });

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, TOAST_TEXT, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(settingsIntent);
            }
        });

    }

    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID, MY_CHANNEL_NAME, importance);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MY_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
