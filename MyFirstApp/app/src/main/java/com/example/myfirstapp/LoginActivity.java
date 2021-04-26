package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class LoginActivity extends AppCompatActivity {
    Button register;
    Button login;
    EditText email, password;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent get = getIntent();
        if(get.getBooleanExtra("registered",false))
            DataHolder.setName(""); // TODO replace empty String with name from firebase
        // to the registration screen

        register = (Button)findViewById(R.id.button_no_account_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        // to the home screen ie logging in
        email = findViewById(R.id.editText_email_login);
        password = findViewById(R.id.editText_pass_one_login);
        fAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.button_submit_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO conditionals for logging in
                String Lemail = email.getText().toString().trim();
                String Lpassword = password.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);

                if(TextUtils.isEmpty(Lemail)){
                    email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(Lpassword)){
                    password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    password.setError("Password Must be >= 6 Characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(Lemail,Lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            i.putExtra("from","login");
                            i.putExtra("username","");
                            startActivity(i);
                        }else {
                            Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
    }
}