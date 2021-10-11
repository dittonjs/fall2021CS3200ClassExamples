package com.usu.ledger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usu.ledger.databinding.FragmentLedgerItemBinding;
import com.usu.ledger.viewmodels.TransactionsViewModel;
import com.usu.ledger.viewmodels.UserViewModel;

public class LedgerItemFragment extends Fragment {

    private boolean isSaving = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLedgerItemBinding binding = FragmentLedgerItemBinding.inflate(inflater, container, false);
        TransactionsViewModel transactionsViewModel = new ViewModelProvider(getActivity()).get(TransactionsViewModel.class);
        UserViewModel userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        transactionsViewModel.getSaving().observe(getViewLifecycleOwner(), saving -> {
            if (!isSaving && saving) {
                isSaving = true;
            } else if (isSaving && !saving) { // and no error
                // transition back
                NavHostFragment.findNavController(this).navigateUp();
            }
        });

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null) return;
            binding.save.setOnClickListener(view -> {
                binding.save.setEnabled(false);
                transactionsViewModel.createTransaction(
                        binding.amount.getText().toString(),
                        binding.details.getText().toString(),
                        user.uid
                        );
            });
        });


        return binding.getRoot();
    }
}