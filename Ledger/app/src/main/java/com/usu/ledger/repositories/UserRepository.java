package com.usu.ledger.repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.usu.ledger.models.User;

public class UserRepository {
    FirebaseAuth auth;

    public static interface OnFailureListener {
        public void onFailure(Exception e);
    }

    public static interface OnAuthStateChanged {
        public void onAuthStateChanged(User user);
    }

    public UserRepository() {
        auth = FirebaseAuth.getInstance();
    }

    public void setAuthStateChangedListener(OnAuthStateChanged listener) {
        auth.addAuthStateListener(auth -> {
            listener.onAuthStateChanged(getCurrentUser());
        });
    }

    public User getCurrentUser() {
        User user = new User();
        FirebaseUser fbUser = auth.getCurrentUser();
        if (fbUser == null) return null;

        user.uid = fbUser.getUid();
        user.email = fbUser.getEmail();
        return user;
    }

    public void signIn(String email, String password, OnFailureListener onFailureListener ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println(task.getException().getMessage());
                onFailureListener.onFailure(task.getException());
                // handle the error
            }
        });
    }

    public void signUp(String email, String password, OnFailureListener onFailureListener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println(task.getException().getMessage());
                onFailureListener.onFailure(task.getException());
                // handle the error
            }
        });
    }

    public void logout() {
        auth.signOut();
    }
}
