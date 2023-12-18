package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ShowBookings extends AppCompatActivity implements MyRecyclerViewAdapter3.ItemClickListener{
    List<String> monthNum = new ArrayList<>();
    List<String> days = new ArrayList<>();
    List<String> slots = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bookings);
        Bundle extras = getIntent().getExtras();
        String name = "";
        if (extras != null) {
            name = extras.getString("name");
        }
        DbHandler db = new DbHandler(this);
        try {
            ArrayList<HashMap<String, String>> appointments = db.GetUsers();

            for (HashMap<String, String> pos : appointments) {
                if (Objects.equals(pos.get("name"), name)) {
                    monthNum.add(pos.get("month"));
                    days.add(pos.get("day"));
                    slots.add(pos.get("slot"));
                }
            }
        } catch (Exception e){

        }



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent navIntent = null;
            if (item.getItemId() == R.id.navHome) {
                navIntent = new Intent(ShowBookings.this, Homepage.class);
                startActivity(navIntent);
            }
            else
                finish();
            return true;
        });

        RecyclerView recyclerView = findViewById(R.id.bookingRC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter3 myRecyclerViewAdapter3 = new MyRecyclerViewAdapter3(days, monthNum, slots, this);
        myRecyclerViewAdapter3.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter3);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent send = new Intent(ShowBookings.this, MakeBooking.class);
        send.putExtra("month", monthNum.get(position));
        send.putExtra("day", days.get(position));
        send.putExtra("slot", slots.get(position));
        startActivity(send);
    }
}