package com.example.plasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingTicket extends AppCompatActivity {
     Button OneWays;
     Button TwoWays;
     Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_ticket);
        OneWays = findViewById(R.id.OneWay);
        TwoWays = findViewById(R.id.TwoWay);
        Back = findViewById(R.id.BackBtn);

        OneWays.setOnClickListener(v -> OneWayActivity());
        TwoWays.setOnClickListener(v -> TwoWayActivity());
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
    }
    public void OneWayActivity() {
        Intent intent = new Intent(getApplicationContext(), OneWayBooking.class);
        startActivity(intent);
    }

    public void TwoWayActivity() {
        Intent intent = new Intent(getApplicationContext(),  TwoWayBooking.class);
        startActivity(intent);
    }

}