package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeAppointmentBtn = findViewById(R.id.makeAppBtn);
        Button showAppointmentBtn = findViewById(R.id.showAppBtn);

        makeAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Homepage.this, GridActivity.class);
                startActivity(send);
            }
        });
        showAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Homepage.this, ShowNames.class);
                startActivity(send);
            }
        });
    }
}