package com.skylarroot.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    Button btn2_signup;
    EditText user_name, pass_word;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        user_name=findViewById(R.id.username);
        pass_word=findViewById(R.id.password1);
        btn2_signup=findViewById(R.id.sign);
        mAuth=FirebaseAuth.getInstance();
        btn2_signup.setOnClickListener(v -> {
            String email = user_name.getText().toString().trim();
            String password= pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                user_name.setError("Email is empty");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_name.setError("Enter the valid email address");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Enter the password");
                pass_word.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                pass_word.setError("Length of the password should be more than 6");
                pass_word.requestFocus();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(Registration.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this, Login.class));
                }
                else
                {
                    Toast.makeText(Registration.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                }
            });

        });

    }
}
