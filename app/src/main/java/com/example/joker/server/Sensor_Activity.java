package com.example.joker.server;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sensor_Activity extends AppCompatActivity {

    private Button btn_light,btn_accel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        btn_light= (Button) findViewById(R.id.btn_light);
        btn_accel= (Button) findViewById(R.id.btn_accel);

        btn_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Sensor_Activity.this,Light_Activity.class);
                startActivity(i);
            }
        });

        btn_accel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sensor_Activity.this,Accelrate_Activity.class);
                startActivity(intent);
            }
        });
    }
}
