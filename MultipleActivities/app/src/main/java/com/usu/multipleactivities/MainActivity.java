package com.usu.multipleactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE_KEY = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatButton button = new AppCompatButton(this);
        button.setText("GO TO SECOND ACTIVITY");
        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(MESSAGE_KEY, "I am custom message");
            intent.putExtra("userName", "JosephDitton");
            this.startActivitFor
        });
        setContentView(button);
    }
}