package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeAppointmentBtn = findViewById(R.id.makeAppBtn);
        Button showAppointmentBtn = findViewById(R.id.showAppBtn);
    }
}