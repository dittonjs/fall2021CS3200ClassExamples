package com.usu.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.usu.notifications.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public final static String ADS_CHANNEL_ID = "ads";
    public final static String SHIPPING_CHANNEL_ID = "shipping";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // setup channel
            String name1 =  "Ads";
            String description = "Ads for sales going on in the app.";

            NotificationChannel channel1 = new NotificationChannel(
                    ADS_CHANNEL_ID,
                    name1,
                    NotificationManager.IMPORTANCE_DEFAULT
                );

            channel1.setDescription(description);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

            String name2 = "Shipping Notificaions";
            String description2 = "Notifications for when you purchases have shipped!";

            NotificationChannel channel2 = new NotificationChannel(
                    SHIPPING_CHANNEL_ID,
                    name2,
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            channel2.setDescription(description2);

            manager.createNotificationChannel(channel2);
        }

        binding.ad.setOnClickListener(view -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ADS_CHANNEL_ID)
                    .setContentTitle("I am an AD")
                    .setContentText("BUY MY STUFF!!!!!!!!!!!!!!!!")
                    .setSmallIcon(R.drawable.ic_baseline_animation_24)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.notify(1, builder.build());
        });

        binding.shipping.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, SHIPPING_CHANNEL_ID)
                    .setContentTitle("I shipped!")
                    .setContentText("You bought my stuff!!!!!!!!!!!!!!!!!!!!!!")
                    .setSmallIcon(R.drawable.ic_baseline_animation_24)
                    .setContentIntent(pIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.notify(1, builder.build());
        });


        setContentView(binding.getRoot());
    }
}
