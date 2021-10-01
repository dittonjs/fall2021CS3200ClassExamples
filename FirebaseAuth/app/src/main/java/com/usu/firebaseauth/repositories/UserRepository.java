package com.usu.firebaseauth.repositories;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.usu.firebaseauth.models.User;

import java.util.concurrent.Executor;

public class UserRepository {
    FirebaseAuth auth;
    public static interface OnSuccessListener {
        public void onSuccess(User user);
    }

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
