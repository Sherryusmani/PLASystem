package com.example.plasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

     EditText UserName;
     EditText Password;
     Button login, button2;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        UserName = findViewById(R.id.username);
        Password = findViewById(R.id.Password);
        login = (Button) findViewById(R.id.login);
        button2 = (Button) findViewById(R.id.buttonsignup);

        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(v -> {
            String email = UserName.getText().toString().trim();
            String password = Password.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                UserName.setError("Email is Required");
                UserName.requestFocus();
                //Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(Login.this, "Please Enter a Valid Email Address", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Password.setError("Password is Required");
                Password.requestFocus();

                //Toast.makeText(Login.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 6) {
                Password.setError("Password too short");
                Password.requestFocus();
                return;
                //Toast.makeText(Login.this, "password too Short", Toast.LENGTH_SHORT).show();
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Login.this, task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Login Failed or User  not available", Toast.LENGTH_SHORT).show();

                        }
                    });
        });
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUpWithEmail.class);
            startActivity(intent);
        });


    }
}



