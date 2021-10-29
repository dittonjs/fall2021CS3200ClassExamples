package com.usu.timers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.usu.timers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        Delay delay = new Delay(() -> {
            binding.autosaveMessage.setText("");
        }, 3000);
        Debouncer debouncer = new Debouncer(() -> {
           // handle the autosave
           binding.autosaveMessage.setText("Saved!");
           delay.execute();
        }, 1000);

        binding.edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                debouncer.execute();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Throttler throttler = new Throttler(() -> {
            binding.manualSaveMessage.setText("Last save " + System.currentTimeMillis());
        }, 3000);

        binding.save.setOnClickListener(view -> {
            throttler.execute();
        });

        setContentView(binding.getRoot());

    }
}