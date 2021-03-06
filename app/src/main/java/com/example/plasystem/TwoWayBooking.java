package com.example.plasystem;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TwoWayBooking extends AppCompatActivity {
     TextView from,to,date,Airline,Seat;
     EditText PLA_airline,AirlineSeat,twodate;
    Spinner staticSpinner,dynamicSpinner;
    DatabaseReference bookingDbRefrence;
    Button proceedticket;
    ImageView Back;
    DatePickerDialog.OnDateSetListener mDateSetListener;
     static final String TAG = "TwoWayBooking";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_way_booking);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        from=findViewById(R.id.cityfrom);
        to=findViewById(R.id.cityto);
        date=findViewById(R.id.Date);
        Airline=findViewById(R.id.airline);
        Seat=findViewById(R.id.AirlineSeat);
        AirlineSeat=findViewById(R.id.Seat);
        bookingDbRefrence = FirebaseDatabase.getInstance().getReference().child("TwoWayRecords");

        staticSpinner =  findViewById(R.id.spin);
         dynamicSpinner =  findViewById(R.id.spin2);
        twodate=findViewById(R.id.twowaydate);
        PLA_airline=findViewById(R.id.PLA);
        proceedticket=findViewById(R.id.ticket);
        Back = findViewById(R.id.backtreturn);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookingTicket.class);
                startActivity(intent);
            }
        });


        proceedticket.setOnClickListener(v -> {

            String passenger = AirlineSeat.getText().toString().trim();
            String date = twodate.getText().toString().trim();
            String Aclass = PLA_airline.getText().toString().trim();
            if (staticSpinner.getSelectedItem().toString().trim().equals("Please select a value")) {
                TextView errorText = (TextView)staticSpinner.getSelectedView();
                errorText.setError("abc");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please Select");
                return;
            }
            if (dynamicSpinner.getSelectedItem().toString().trim().equals("Please select a value")) {
                TextView errorText = (TextView) dynamicSpinner.getSelectedView();
                errorText.setError("abc");
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                errorText.setText("Please Select");
                return;
            }
            if (TextUtils.isEmpty(passenger)){
                AirlineSeat.setError("Passengers required");
                AirlineSeat.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(date)) {
                twodate.setError("Enter Date");
                twodate.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(Aclass)) {
                PLA_airline.setError("Enter Class");
                PLA_airline.requestFocus();
                return;
            }
            insertBookingData();
            Intent intent= new Intent(getApplicationContext(),ForTwoWay.class);

            startActivity(intent);
        });
        twodate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    TwoWayBooking.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

            String date = month + "/" + day + "/" + year;
            twodate.setText(date);
        };



        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.Depart_aray,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> dynamicAdapter = ArrayAdapter.createFromResource(this, R.array.Airport_aray,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        dynamicSpinner.setAdapter(dynamicAdapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id)
            {
                Log.v("item", (String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void insertBookingData(){
        String spin = staticSpinner.getSelectedItem().toString();
        String spin2 = dynamicSpinner.getSelectedItem().toString();
        String date= twodate.getText().toString();
        String passeng = AirlineSeat.getText().toString();
        String Eclass = PLA_airline.getText().toString();

        OnewayClass booking = new OnewayClass(spin,spin2,date,passeng,Eclass);

        bookingDbRefrence.push().setValue(booking);
        Toast.makeText(TwoWayBooking.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();


    }
}