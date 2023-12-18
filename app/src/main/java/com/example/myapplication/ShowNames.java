package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowNames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_names);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent navIntent = null;
            if (item.getItemId() == R.id.navHome) {
                navIntent = new Intent(ShowNames.this, Homepage.class);
                startActivity(navIntent);
            }
            else
                finish();
            return true;
        });
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(ShowNames.this, userList, R.layout.activity_main2,new String[]{"name"}, new int[]{R.id.name,});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click here
            HashMap<String, String> selectedItem = userList.get(position);
            String userName = selectedItem.get("name");
            Intent send = new Intent(ShowNames.this, ShowBookings.class);
            send.putExtra("name", userName);
            startActivity(send);
        });
    }
}