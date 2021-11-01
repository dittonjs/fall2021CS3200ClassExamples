package com.usu.googlemaps;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.usu.googlemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity {
    private ActivityMapsBinding binding;
    private Marker marker;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        setupMap();
                    } else {
                        System.out.println("USER DENIED PERMISSIONS");
                    }
                }
        );

        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void setupMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((map) -> {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            map.setMyLocationEnabled(true);
//            client = new FusedLocationProviderClient(this);
//
//            LocationRequest request = LocationRequest.create()
//                    .setInterval(1)
//                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                    .setSmallestDisplacement(.0001f);
//
//            client.requestLocationUpdates(request, new LocationCallback() {
//                @Override
//                public void onLocationResult(@NonNull LocationResult locationResult) {
//                    Location location = locationResult.getLastLocation();
//
//                    CameraPosition pos = new CameraPosition.Builder()
//                            .target(new LatLng(location.getLatitude(), location.getLongitude()))
//                            .zoom(15)
//                            .build();
//
//                    map.animateCamera(
//                            CameraUpdateFactory.newCameraPosition(pos),
//                            300,
//                            null
//                    );
//                }
//            }, Looper.getMainLooper());


            map.setOnMapClickListener(latLng -> {

                if (marker != null) {
                    marker.setPosition(latLng);
                }
                else {
                    MarkerOptions markerOps = new MarkerOptions()
                            .position(latLng)
                            //                        .title(latLng.toString())
                            .draggable(true)
                            .alpha(.5f);
                    this.marker = map.addMarker(markerOps);

                }

//                map.setOnMarkerClickListener(marker -> {
//                    marker.remove();
//                    marker.set
//                    return true;
//                });
            });
        });
    }

}