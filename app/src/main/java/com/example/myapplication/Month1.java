package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Month1 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    View view;
    List<Integer> mdata = new ArrayList<>();


    public Month1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_month1, container, false);
        return view;
    }

    @Override
    public void OnViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        super.onViewCreated(view, savedInstance);

        RecyclerView recyclerView = view.findViewById(R.id.rcJan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(mdata, this.getContext());
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        
    }
}