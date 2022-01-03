package com.example.plasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignUpWithEmail extends AppCompatActivity {
     Button signup;
     EditText Email;
     EditText Password, CPassword;
     TextView login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Email = findViewById(R.id.txtemail);
        Password = findViewById(R.id.text_input_password_toggle);
        CPassword = findViewById(R.id.Confirmpassword);
        login = findViewById(R.id.Login);
        mAuth = FirebaseAuth.getInstance();
        signup = findViewById(R.id.sup);

        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });

        signup.setOnClickListener(v -> {
            String email = Email.getText().toString().trim();
            String password = Password.getText().toString().trim();
            String ConfirmPswd = CPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Email.setError("Password is Required");
                Email.requestFocus();

                //Toast.makeText(SignUpWithEmail.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(SignUpWithEmail.this, "Please Enter a Valid Email Address", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(password)) {
                Password.setError("Password is Required");
                Password.requestFocus();

                //Toast.makeText(SignUpWithEmail.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (TextUtils.isEmpty(ConfirmPswd)) {
                Password.setError("Password is Required");
                Password.requestFocus();

//                    Toast.makeText(SignUpWithEmail.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (password.length() < 6) {
                Password.setError("Password too short");
                Password.requestFocus();
                return;
                //Toast.makeText(SignUpWithEmail.this, "password too Short", Toast.LENGTH_SHORT).show();
            }
            else if (password.equals(ConfirmPswd)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpWithEmail.this, task -> {
                            if (task.isSuccessful()) {
                                User user = new User(email, password, ConfirmPswd);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                        .setValue(user).addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(SignUpWithEmail.this, "User has been registered", Toast.LENGTH_LONG).show();

                                                /*startActivity(new Intent(getApplicationContext(), Home.class));*/
                                                /*Toast.makeText(SignUpWithEmail.this, "Registration Completed", Toast.LENGTH_SHORT).show();*/
                                            } else {
                                                Toast.makeText(SignUpWithEmail.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }else {
                                Toast.makeText(SignUpWithEmail.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }


                        });
            }
        });
    }
}
