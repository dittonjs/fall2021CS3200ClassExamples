package com.usu.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.usu.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FakeDatabase db = new FakeDatabase();
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        ArrayList<String> data = db.getData(1000000);
        FakeDataAdapter adapter = new FakeDataAdapter(data, dataPoint -> {
            System.out.println(dataPoint);
        });

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(binding.getRoot());
    }
}