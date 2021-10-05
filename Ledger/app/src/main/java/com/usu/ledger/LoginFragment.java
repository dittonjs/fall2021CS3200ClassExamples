package com.usu.ledger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usu.ledger.databinding.FragmentLoginBinding;
import com.usu.ledger.viewmodels.UserViewModel;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(getLayoutInflater());
        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_main_app);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), message -> {
            binding.errorMessage.setText(message);
        });

        binding.signIn.setOnClickListener(view -> {
            viewModel.signIn(binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());
        });

        binding.signUp.setOnClickListener(view -> {
            viewModel.signUp(binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());
        });

        return binding.getRoot();
    }
}