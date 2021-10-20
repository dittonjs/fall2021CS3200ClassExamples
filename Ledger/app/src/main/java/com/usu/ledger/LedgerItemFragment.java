package com.usu.ledger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.usu.ledger.databinding.FragmentLedgerItemBinding;
import com.usu.ledger.models.Transaction;
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

        Transaction selectedTransction = transactionsViewModel.getSelectedTransaction().getValue();
        if (selectedTransction != null) {
            binding.amount.setText(selectedTransction.getAmount() + "");
            binding.details.setText(selectedTransction.getDetails() + "");
            binding.delete.setVisibility(View.VISIBLE);
            binding.delete.setOnClickListener(view -> {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this transaction?")
                        .setNegativeButton("cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("Delete", (dialog, which) -> {
                            transactionsViewModel.deleteTransaction(selectedTransction);
                        })
                .show();
            });
        }

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
                if (selectedTransction == null) {
                    transactionsViewModel.createTransaction(
                            binding.amount.getText().toString(),
                            binding.details.getText().toString(),
                            user.uid
                    );
                } else {
                    transactionsViewModel.updateTransaction(
                            selectedTransction,
                            binding.amount.getText().toString(),
                            binding.details.getText().toString()
                    );
                }

            });
        });


        return binding.getRoot();
    }
}