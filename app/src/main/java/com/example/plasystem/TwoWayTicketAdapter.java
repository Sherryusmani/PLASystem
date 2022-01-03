package com.example.plasystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TwoWayTicketAdapter extends RecyclerView.Adapter<TwoWayTicketAdapter.MyViewHolder>{

    Context context;
    ArrayList<TwoWayTicketStatus> list;

    public TwoWayTicketAdapter(Context context, ArrayList<TwoWayTicketStatus> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TwoWayTicketAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.twowayticket_layout,parent,false);
        return  new TwoWayTicketAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoWayTicketAdapter.MyViewHolder holder, int position) {
        TwoWayTicketStatus user =list.get(position);
        holder.spin.setText(user.getSpin());
        holder.spin2.setText(user.getSpin2());
        holder.passeng.setText(user.getPasseng());
        holder.date.setText(user.getDate());
        holder.eclass.setText(user.getEclass());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView spin, spin2,passeng,date,eclass;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            spin = itemView.findViewById(R.id.tcktFrom);
            spin2 = itemView.findViewById(R.id.tcktTo);
            passeng = itemView.findViewById(R.id.passng);
            date = itemView.findViewById(R.id.dateFrom);
            date = itemView.findViewById(R.id.dateTo);
            eclass = itemView.findViewById(R.id.eclass);


        }
    }

}

