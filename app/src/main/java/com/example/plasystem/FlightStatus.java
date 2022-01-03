package com.example.plasystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FlightStatus extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    DatabaseReference twoWayData;
    MyAdapter myAdapter;
    ArrayList<Status> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_status);

        recyclerView =findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance().getReference("OneWayRecords");
        twoWayData = FirebaseDatabase.getInstance().getReference("TwoWayRecords");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Status user = dataSnapshot.getValue(Status.class);
                    list.add(user);

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(

                this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FlightStatus.this,YourTickets.class);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }
        ));
        //MyAdapter adapter = new MyAdapter(list,this);
        // recyclerview.setAdapter(adapter);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // recyclerview.setLayoutManager(layoutManager);
    }
}