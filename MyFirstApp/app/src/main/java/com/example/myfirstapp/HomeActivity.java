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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.emoji_df)));
        List<String[]> emojiList = null;
        try {
            emojiList = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> emojiArray = new ArrayList<String[]>();
        for(String[] i : emojiList) {
            emojiArray.add(emojiList.toArray(i));
        }*/
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
                Intent i = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(i);
            }
        });

        // to the study screen
        study = (Button)findViewById(R.id.button_my_list_1_home);
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO conditionals to prevent opening an empty set
                Intent i = new Intent(getApplicationContext(), StudyActivity.class);
                startActivity(i);
            }
        });
    }
}
