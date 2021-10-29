package com.usu.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
//        Intent intent = new Intent(this, MyService.class);
////        intent.putExtra("name", "Joseph");
//        startService(intent);
        new Handler().postDelayed(() -> {
            System.out.println("DO I GET CALLED");
        }, 10000);
    }
}