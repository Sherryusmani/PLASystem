package com.example.plasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
     ImageView welcomePLAS;
     Button booking;
     Button Tickets;
     Button Status;
     Button Logout;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        booking = findViewById(R.id.bookingTicket);
        Tickets = findViewById(R.id.yourTickets);
        Status = findViewById(R.id.flightStatus);
        Logout = findViewById(R.id.logout);
        Auth = FirebaseAuth.getInstance();

        booking.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BookingTicket.class);
            startActivity(intent);
        });
        Tickets.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), YourTickets.class);
            startActivity(intent);
        });
        Status.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), FlightStatus.class);
            startActivity(intent);
        });
        Logout.setOnClickListener(v -> {

            Auth.signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
                finish();
        });
    }


}
