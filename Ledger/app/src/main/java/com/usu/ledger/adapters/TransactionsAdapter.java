package com.usu.ledger.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.usu.ledger.databinding.TransactionListItemBinding;
import com.usu.ledger.models.Transaction;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    ObservableArrayList<Transaction> transactions;
    OnTransactionSelectedListener listener;

    public static interface OnTransactionSelectedListener {
        public void onSelected(Transaction transaction);
    }

    public TransactionsAdapter(ObservableArrayList<Transaction> transactions, OnTransactionSelectedListener listener ) {
        this.transactions = transactions;
        this.listener = listener;
        transactions.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Transaction>>() {
            @Override
            public void onChanged(ObservableList<Transaction> sender) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<Transaction> sender, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Transaction> sender, int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Transaction> sender, int fromPosition, int toPosition, int itemCount) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Transaction> sender, int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TransactionListItemBinding binding = TransactionListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getBinding().amountDisplay.setText(transactions.get(position).getAmount() + "");
        holder.getBinding().dateDisplay.setText(transactions.get(position).getTimestamp() + "");
        holder.itemView.setOnClickListener(view -> {
            this.listener.onSelected(transactions.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TransactionListItemBinding binding;
        public ViewHolder(@NonNull TransactionListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public TransactionListItemBinding getBinding() {
            return binding;
        }
    }
}
