package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button logout;
    Button create;
    Button study;
    ArrayList<String[]> completeSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // to the register screen ie logging out
        logout = (Button)findViewById(R.id.button_log_out_home);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        // to the creation screen
        create = (Button)findViewById(R.id.button_create_home);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO send the text in EditText newListName to CreateActivity to store in completeSet and use to change Button myList
                Intent i = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(i);
            }
        });

        // to the study screen
        study = (Button)findViewById(R.id.button_my_list_1_home);
        // TODO when receiving completeSet, study.setText(completeSet.get(0)[0]);
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO conditionals to prevent opening an empty set, sending of completeSet to StudyActivity
                Intent i = new Intent(getApplicationContext(), StudyActivity.class);
                startActivity(i);
            }
        });
    }
}
