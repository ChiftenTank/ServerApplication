package com.example.joker.server;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Light_Activity extends AppCompatActivity implements SensorEventListener {

    public SensorManager sen_manager;
    public Sensor sensor_light;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        sen_manager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_light=sen_manager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sen_manager.registerListener(this,sensor_light,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sen_manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float light=event.values[0];
        Log.i("log = " , + light + "");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
