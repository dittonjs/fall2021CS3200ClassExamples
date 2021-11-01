package com.usu.permissions;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.usu.permissions.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        setupGeolocation();
                    } else {
                        System.out.println("USER DENIED PERMISSIONS");
                    }
                }
        );

        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

    }

    public void setupGeolocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            FusedLocationProviderClient client = new FusedLocationProviderClient(this);
//            client.getLastLocation().addOnSuccessListener(location -> {
//               if (location != null) {
//                   binding.myTextView.setText(location.toString());
//               }
//            });

//            client
//                    .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
//                    .addOnSuccessListener(location -> {
//                        binding.myTextView.setText(location.toString());
//                    });

            LocationRequest request = LocationRequest.create()
                    .setInterval(1000)
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                    .setSmallestDisplacement(.0001f);

            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    System.out.println("Updated!");
                    binding.myTextView.setText(locationResult.getLastLocation().toString());
                }
            }, Looper.getMainLooper());

        }
    }
}