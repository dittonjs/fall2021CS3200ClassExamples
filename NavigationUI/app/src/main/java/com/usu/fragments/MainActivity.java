package com.usu.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.usu.fragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CREATE");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("RESUME");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("RESTART");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("DESTROY");
    }
}