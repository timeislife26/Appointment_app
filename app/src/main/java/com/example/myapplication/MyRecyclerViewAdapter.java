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

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Integer> mData;
    private LayoutInflater mInflate;
    private ItemClickListener mClickListener;


    public MyRecyclerViewAdapter(List<Integer> mData, Context context) {
        this.mData = mData;
        this.mInflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.month_view2, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        int date = mData.get(position);
        holder.dateTv.setText(String.valueOf(date));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dateTv;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dateTv = itemView.findViewById(R.id.dateTv);
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
