package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {
    Button home;
    ArrayList<String> completeList;
    Button flashcard;
    int index;
    Button next;
    Button prev;
    String listName;
    boolean english;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        Intent get = getIntent();
        completeList = get.getStringArrayListExtra("complete_list");
        completeList.remove(0);
        listName = get.getStringExtra("list_name");
        index = 0;

        // flipping card
        flashcard = (Button)findViewById(R.id.button_flashcard_study);
        flashcard.setText(completeList.get(index));
        english = false;
        flashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!english){
                    flashcard.setText(completeList.get(index*2+1));
                    english = true;
                }
                else
                    flashcard.setText(completeList.get(index*2));
            }
        });

        // next card
        next = (Button)findViewById(R.id.button_next_study);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if(index > completeList.size() / 2 - 1)
                    index = 0;
                flashcard.setText(completeList.get(index*2));
                english = false;
            }
        });

        // prev card
        prev = (Button)findViewById(R.id.button_previous_study);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if(index < 0)
                    index = completeList.size() / 2 - 1;
                flashcard.setText(completeList.get(index*2));
                english = false;
            }
        });

        // to the home screen
        home = (Button) findViewById(R.id.button_home_study);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                completeList.add(0,listName);
                i.putExtra("complete_list",completeList);
                i.putExtra("from","study");
                startActivity(i);
            }
        });
    }
}
