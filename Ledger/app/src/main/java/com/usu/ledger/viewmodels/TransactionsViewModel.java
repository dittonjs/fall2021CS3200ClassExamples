package com.usu.ledger.viewmodels;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.usu.ledger.models.Transaction;

public class TransactionsViewModel extends ViewModel {
    ObservableArrayList<Transaction> transactions;
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    FirebaseFirestore db;

    public TransactionsViewModel() {
        db = FirebaseFirestore.getInstance();
        saving.setValue(false);
    }


    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }



    public void createTransaction(String amount, String details, String userId) {
        saving.setValue(true);
        Transaction transaction = new Transaction(
                Double.parseDouble(amount),
                System.currentTimeMillis(),
                details,
                userId
        );
        db
                .collection("transactions")
                .document(transaction.getUserId() + "_" + transaction.getTimestamp())
                .set(transaction)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // do something
                        transactions.add(transaction);
                    } else {
                        // handle error
                    }
                    saving.setValue(false);
                }
        );

    }

    public void updateTransaction(Transaction transaction) {}

    public void deleteTransaction(Transaction transaction) {}

    public ObservableArrayList<Transaction> getTransactions(String userId) {
        if (transactions == null) {
            transactions = new ObservableArrayList<>();
            loadTransactions(userId);
        }
        return transactions;
    }

    private void loadTransactions(String userId) {
        db.collection("transactions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        transactions.addAll(task.getResult().toObjects(Transaction.class));
                    } else {
                        // handle error
                    }
                });
    }

}
