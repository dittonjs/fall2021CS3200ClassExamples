package com.usu.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.usu.firebaseauth.databinding.ActivityMainBinding;
import com.usu.firebaseauth.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.getUser().observe(this, user -> {
            if (user == null) {
                // we are not logged in
                binding.userMessage.setText("User is not logged in");
            } else {
                binding.userMessage.setText("User is  logged in, yay!");
                // we are logged in and want to do something
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {
            binding.errorMessage.setText(message);
        });

        binding.signIn.setOnClickListener(view -> {
            viewModel.signIn(binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());
        });

        binding.signUp.setOnClickListener(view -> {
            viewModel.signUp(binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());
        });

        binding.signOut.setOnClickListener(view -> {
            viewModel.logout();
        });

        setContentView(binding.getRoot());
    }
}