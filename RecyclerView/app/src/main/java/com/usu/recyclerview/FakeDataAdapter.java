package com.usu.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usu.recyclerview.databinding.FakeDataListItemBinding;

import java.util.ArrayList;

public class FakeDataAdapter extends RecyclerView.Adapter<FakeDataAdapter.FakeDataViewHolder> {
    ArrayList<String> data;
    private int viewHolderCount = 0;
    OnFakeDataClickedListener listener;
    public static interface OnFakeDataClickedListener {
        public void onClick(String data);
    }

    public FakeDataAdapter(ArrayList<String> data, OnFakeDataClickedListener listener) {
        this.data = data;
        this.listener = listener;
    }



    @NonNull
    @Override
    public FakeDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewHolderCount ++;
        System.out.println(viewHolderCount);
        FakeDataListItemBinding binding = FakeDataListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new FakeDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeDataViewHolder holder, int position) {
        System.out.println("VIEW HOLDER BOUND AT POS " + position);
        holder.getBinding().fakeDataText.setText(data.get(position));
        holder.getBinding().fakeDataText.setOnClickListener(view -> {
            listener.onClick(data.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class FakeDataViewHolder extends RecyclerView.ViewHolder {
        FakeDataListItemBinding binding;
        public FakeDataViewHolder(FakeDataListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public FakeDataListItemBinding getBinding() {
            return binding;
        }
    }
}
