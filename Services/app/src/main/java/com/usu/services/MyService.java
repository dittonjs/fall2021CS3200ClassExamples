package com.usu.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Handler().postDelayed(() -> {
            System.out.println("I ran from a service");
        }, 10000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
