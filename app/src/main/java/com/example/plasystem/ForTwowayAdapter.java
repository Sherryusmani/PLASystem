package com.example.plasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ForTwoWayAdapter extends RecyclerView.Adapter<ForTwoWayAdapter.MyViewHolder>{
    Context context;
    ArrayList<StatusForTwoWay> list;

    public ForTwoWayAdapter(Context context, ArrayList<StatusForTwoWay> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ForTwoWayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.twoway_layout,parent,false);
        return  new ForTwoWayAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForTwoWayAdapter.MyViewHolder holder, int position) {
        StatusForTwoWay user =list.get(position);
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
