package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DayActivity extends AppCompatActivity implements MyRecyclerViewAdapter2.ItemClickListener {

    int month = 0;
    int day = 0;
    List<Boolean> dData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Bundle extras = getIntent().getExtras();
        List<String> apTimes = new ArrayList<>();
        List<Integer> takenPos = new ArrayList<>();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent navIntent = null;
            if (item.getItemId() == R.id.navHome) {
                navIntent = new Intent(DayActivity.this, Homepage.class);
                startActivity(navIntent);
            }
            else
                finish();
            return true;
        });
        if (extras != null) {
            month = extras.getInt("month");
            day = extras.getInt("day");
        }
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> appointments = db.GetUsers();
        for (HashMap<String, String> pos : appointments){
            int occ_month = Integer.parseInt(Objects.requireNonNull(pos.get("month")));
            if (occ_month == month){
                int occ_day = Integer.parseInt(Objects.requireNonNull(pos.get("day")));
                if (occ_day == day){
                    takenPos.add(Integer.parseInt(Objects.requireNonNull(pos.get("slot"))));
                }
            }
        }

        String[] times;
        times = getResources().getStringArray(R.array.times);
        apTimes.addAll(Arrays.asList(times));
        for (int i = 0; i < 7; i++) {
            dData.add(true);
        }
        for (int i = 0; i < takenPos.size();i++){
            int num = takenPos.get(i);
            dData.set(num, false);
        }
        RecyclerView recyclerView2 = findViewById(R.id.rcDay);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter2 myRecyclerViewAdapter2 = new MyRecyclerViewAdapter2(dData, apTimes, this);
        myRecyclerViewAdapter2.setClickListener(this);
        recyclerView2.setAdapter(myRecyclerViewAdapter2);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (dData.get(position)) {
            Intent send = new Intent(DayActivity.this, MakeBooking.class);
            send.putExtra("month", month);
            send.putExtra("day", day);
            send.putExtra("slot", position);
            startActivity(send);
        }
        else {
            Toast.makeText(getApplicationContext(), "Sorry this time is not available",Toast.LENGTH_SHORT).show();
        }

    }
}