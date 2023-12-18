package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    List<Integer> mData;
    int month;
    String[] monthList;
    TextView monthTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        month = 0;
        mData = new ArrayList<>();
        getDates(month);
        monthList = getResources().getStringArray(R.array.Months);
        monthTv = findViewById(R.id.Monthtv);
        monthTv.setText(monthList[month]);
        Button nextBtn = findViewById(R.id.nextBtn);
        Button prevBtn = findViewById(R.id.prevBtn);
        createRecyclerView(mData);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent navIntent = null;
            if (item.getItemId() == R.id.navHome) {
                navIntent = new Intent(GridActivity.this, Homepage.class);
                startActivity(navIntent);
            }
            else
                finish();
            return true;
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month++;
                if (month == 11)
                    nextBtn.setVisibility(View.GONE);
                if (month==1)
                    prevBtn.setVisibility(View.VISIBLE);
                monthTv.setText(monthList[month]);
                getDates(month);
                createRecyclerView(mData);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month--;
                if (month==0)
                    prevBtn.setVisibility(View.GONE);
                    //prevBtn.setClickable(false);
                if (month==10)
                    nextBtn.setVisibility(View.VISIBLE);
                monthTv.setText(monthList[month]);
                getDates(month);
                createRecyclerView(mData);
            }
        });
    }

    public void getDates(int month) {
        mData.clear();
        ArrayList<Integer> days31 = new ArrayList<>(Arrays.asList(0, 2, 4, 6, 7, 9, 11));
        ArrayList<Integer> days30 = new ArrayList<>(Arrays.asList(3, 5, 8, 10));

        if (days31.contains(month)) {
            for (int i = 1; i <= 31; i++) {
                mData.add(i);
            }
        } else if (days30.contains(month)) {
            for (int i = 1; i <= 30; i++) {
                mData.add(i);
            }
        } else if (month == 1) {
            for (int i = 1; i <= 29; i++) {
                mData.add(i);
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent send = new Intent(GridActivity.this, DayActivity.class);
        send.putExtra("month", month);
        send.putExtra("day", position);
        startActivity(send);


    }

    private void createRecyclerView(List<Integer> mData){
        RecyclerView recyclerView = findViewById(R.id.rcMonth);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(mData, this);
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }
    public int getMonth(){return month;}
}