package com.usu.mvvmcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.usu.mvvmcounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        CountViewModel viewModel = new ViewModelProvider(this).get(CountViewModel.class);

        viewModel.getCount().observe(this, count -> {
            binding.counter.setText("" + count);;
        });

        binding.increment.setOnClickListener(view -> {
            viewModel.incrementCount();
        });

        binding.decrement.setOnClickListener(view -> {
            viewModel.decrementCount();
        });

        binding.clear.setOnClickListener(view -> {
            viewModel.clear();
        });

        setContentView(binding.getRoot());
    }
}