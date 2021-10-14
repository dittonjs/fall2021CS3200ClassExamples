package com.usu.ledger;

import android.os.Bundle;

import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usu.ledger.adapters.TransactionsAdapter;
import com.usu.ledger.databinding.FragmentHomeBinding;
import com.usu.ledger.databinding.FragmentLoginBinding;
import com.usu.ledger.models.Transaction;
import com.usu.ledger.viewmodels.TransactionsViewModel;
import com.usu.ledger.viewmodels.UserViewModel;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        TransactionsViewModel transactionsViewModel = new ViewModelProvider(getActivity()).get(TransactionsViewModel.class);
        UserViewModel userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        userViewModel.getUser().observe(getViewLifecycleOwner(), (user) -> {
            if (user == null) return;

            binding.fab.setOnClickListener(view -> {
                transactionsViewModel.setSelectedTransaction(null);
                NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_ledgerItemFragment2);
            });

            binding.transactions.setAdapter(
                    new TransactionsAdapter(
                            transactionsViewModel.getTransactions(user.uid),
                            transaction -> {
                                transactionsViewModel.setSelectedTransaction(transaction);
                                NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_ledgerItemFragment2);
                            }
                    )
            );
            binding.transactions.setLayoutManager(new LinearLayoutManager(getContext()));

        });





        return binding.getRoot();
    }
}