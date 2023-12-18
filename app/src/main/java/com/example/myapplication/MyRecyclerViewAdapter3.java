package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.renderscript.Long4;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter3 extends RecyclerView.Adapter<MyRecyclerViewAdapter3.ViewHolder> {
    private List<String> months;
    private List<String> days;
    private List<String> slots;
    private LayoutInflater mInflate;
    private ItemClickListener mClickListener;


    public MyRecyclerViewAdapter3(List<String> days, List<String> months, List<String> slots, Context context) {
        this.days = days;
        this.mInflate = LayoutInflater.from(context);
        this.months = months;
        this.slots = slots;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.month_view2, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter3.ViewHolder holder, int position) {
        String[] strMonth = mInflate.getContext().getResources().getStringArray(R.array.Months);
        String[] strSlots = mInflate.getContext().getResources().getStringArray(R.array.times);
        String date = days.get(position) + " " + strMonth[Integer.parseInt(months.get(position))];
        holder.dateTv.setText(months.get(position));//date);
        holder.slotTv.setText(slots.get(position));//strSlots[Integer.parseInt(slots.get(position))]);
    }


    @Override
    public int getItemCount() {
        return slots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dateTv;
        TextView slotTv;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dateTv = itemView.findViewById(R.id.date);
            slotTv = itemView.findViewById(R.id.slot);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }
}
