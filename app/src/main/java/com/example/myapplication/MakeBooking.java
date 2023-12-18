package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MakeBooking extends AppCompatActivity {

    int month = 0;
    int day = 0;
    int slot = 0;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);
        Button saveBtn = findViewById(R.id.saveBtn);
        Bundle extras = getIntent().getExtras();
        name = (EditText) findViewById(R.id.txtName);
        if (extras != null) {
            month = extras.getInt("month");
            day = extras.getInt("day");
            slot = extras.getInt("slot");
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent navIntent = null;
            if (item.getItemId() == R.id.navHome) {
                navIntent = new Intent(MakeBooking.this, Homepage.class);
                startActivity(navIntent);
            }
            else
                finish();
            return true;
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String dbMonth = String.valueOf(month);
                String dbDay = String.valueOf(day);
                String dbSlot = String.valueOf(slot);
                DbHandler dbHandler = new DbHandler(MakeBooking.this);
                dbHandler.insertUserDetails(username,dbMonth,dbDay, dbSlot);
                Intent send = new Intent(MakeBooking.this, Homepage.class);
                //send.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(send);
                Toast.makeText(getApplicationContext(), "Booking has been made",Toast.LENGTH_SHORT).show();
                //finishAffinity();

            }
        });
    }
}