package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class LoginActivity extends AppCompatActivity {
    Button register;
    Button login;
    EditText email;
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
        email = (EditText)findViewById(R.id.editText_email_login);
        login = (Button)findViewById(R.id.button_submit_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO conditionals for logging in
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                i.putExtra("from","login");
                i.putExtra("username","");
                startActivity(i);
            }
        });
    }
}