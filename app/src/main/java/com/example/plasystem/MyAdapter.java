package com.example.plasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
Context context;
ArrayList<Status>list;

    public MyAdapter(Context context, ArrayList<Status> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(context).inflate(R.layout.flight_layout,parent,false);
         return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Status user =list.get(position);
    holder.spin.setText(user.getSpin());
    holder.spin2.setText(user.getSpin2());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView spin, spin2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spin = itemView.findViewById(R.id.textView);
            spin2 = itemView.findViewById(R.id.text_vwA);

        }
    }

}

