package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // to the home screen ie done creating
        home = (Button) findViewById(R.id.button_done_create);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO conditionals to prevent errors, actual creation of set
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });
    }
}
