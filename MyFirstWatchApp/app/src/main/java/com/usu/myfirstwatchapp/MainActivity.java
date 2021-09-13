package com.usu.myfirstwatchapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.usu.myfirstwatchapp.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView countTextView = findViewById(R.id.countTextView);
        Button decrementButton = findViewById(R.id.decrementButton);
        Button incrementButton = findViewById(R.id.incrementButton);
//        decrementButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        decrementButton.setOnClickListener(view -> {
            count --;
            countTextView.setText(count);
        });

        incrementButton.setOnClickListener(view -> {
            count ++;
            countTextView.setText(count + "");
        });
    }
}