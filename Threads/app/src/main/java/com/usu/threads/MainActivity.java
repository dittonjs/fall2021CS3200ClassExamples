package com.usu.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.usu.threads.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                binding.button.setText();
//            }
//        }, 5000);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            binding.button.setText("asdfasdf");
        }, 5000);


        binding.button.setOnClickListener(view -> {
            System.out.println("Hello, world!");
        });
        setContentView(binding.getRoot());
    }
}