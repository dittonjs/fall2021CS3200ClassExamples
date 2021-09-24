package com.usu.navigationui2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.usu.navigationui2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_fragment_container_view);
        NavController controller = navHost.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, controller);
    }
}