package com.usu.accelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.usu.accelerometer.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    long firstUpdateTimestamp = 0;
    long secondUpdateTimestamp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        TextView xView = findViewById(R.id.x);
        TextView yView = findViewById(R.id.y);
        TextView zView = findViewById(R.id.z);
        findViewById(R.id.container).setOnClickListener(view -> {
            System.out.println("I AM THE CONTAINER");
        });

//        findViewById(R.id.button).setOnClickListener(view -> {
//            System.out.println("I AM THE BUTTON");
//        });
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (firstUpdateTimestamp == 0) {
                    firstUpdateTimestamp = System.currentTimeMillis();
                } else if (secondUpdateTimestamp == 0) {
                    secondUpdateTimestamp = System.currentTimeMillis();
                    System.out.println(secondUpdateTimestamp - firstUpdateTimestamp);
                }

                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                xView.setText(x + "");
                yView.setText(y + "");
                zView.setText(z + "");

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);

    }
}