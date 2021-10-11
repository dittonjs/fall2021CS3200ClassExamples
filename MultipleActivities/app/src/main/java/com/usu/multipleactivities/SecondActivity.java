package com.usu.multipleactivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText(message);
        setContentView(textView);
    }

    @Override
    public void onBackPressed() {
        System.out.println("I WENT BACK");
        super.onBackPressed();
    }
}
