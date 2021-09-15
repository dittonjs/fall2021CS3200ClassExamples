package com.usu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.usu.fragments.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.button.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, ProfileFragment.class, null)
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
        });
        return binding.getRoot();
    }
}
