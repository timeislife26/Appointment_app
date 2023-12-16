package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.ViewHolder2>{
    private List<Boolean> dData;
    private List<String> appointmentTime;

    private LayoutInflater mInflate;
    private MyRecyclerViewAdapter2.ItemClickListener mClickListener;


    public MyRecyclerViewAdapter2(List<Boolean> dData, List<String> appointmentTime, Context context) {
        this.dData = dData;
        this.mInflate = LayoutInflater.from(context);
        this.appointmentTime = appointmentTime;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.day_view, parent, false );
        return new MyRecyclerViewAdapter2.ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter2.ViewHolder2 holder, int position) {
        String time = appointmentTime.get(position);
        holder.timeTv.setText(time);
        if (dData.get(position)){
            holder.card.setCardBackgroundColor(ContextCompat.getColor(mInflate.getContext(),R.color.green));
        }
        else {
            holder.card.setCardBackgroundColor(ContextCompat.getColor(mInflate.getContext(),R.color.red));

        }
    }


    @Override
    public int getItemCount() {
        return dData.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView timeTv;
        CardView card;

        public ViewHolder2(@NonNull View itemView){
            super(itemView);
            timeTv = itemView.findViewById(R.id.apTime);
            card = itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public void setClickListener(MyRecyclerViewAdapter2.ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }
}
