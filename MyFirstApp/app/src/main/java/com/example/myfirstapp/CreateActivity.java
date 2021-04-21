package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {
    Button done;
    ArrayList<Button> displayedArray;
    List<String[]> emojiList;
    CSVReader reader;
    String[][] emojiArray;
    Button nextEmojiSet;
    int emojiSetCount;
    int lastSetCount;
    int page;
    EditText emojiTranslation;
    Button next;
    ArrayList<String> completeList;
    EditText englishTranslation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // reading csv and moving data from List to ArrayList
        reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.emoji_df)));
        try {
            emojiList = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        emojiArray = new String[emojiList.size()][emojiList.get(0).length];
        for(int i = 0; i < emojiList.size(); i++) {
            emojiArray[i] = emojiList.get(i);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        completeList = new ArrayList<String>();
        Intent get = getIntent();
        completeList.add(get.getStringExtra("list_name"));

        // setting emojis
        displayedArray = new ArrayList<Button>();
        displayedArray.add((Button)findViewById(R.id.button_emoji_1_create));
        displayedArray.add((Button)findViewById(R.id.button_emoji_2_create));
        displayedArray.add((Button)findViewById(R.id.button_emoji_3_create));
        displayedArray.add((Button)findViewById(R.id.button_emoji_4_create));
        displayedArray.add((Button)findViewById(R.id.button_emoji_5_create));
        displayedArray.add((Button)findViewById(R.id.button_emoji_6_create));
        displayedArray.add((Button)findViewById(R.id.button_emoji_7_create));
        for(int i = 0; i < displayedArray.size(); i++){
            displayedArray.get(i).setText(emojiArray[i+1][0]);
        }

        // to next page of emojis
        emojiSetCount = emojiArray.length / displayedArray.size();
        lastSetCount = emojiArray.length - emojiSetCount*7;
        nextEmojiSet = (Button)findViewById(R.id.button_next_emoji_set_create);
        nextEmojiSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                if(page > emojiSetCount){
                    page = 0;
                }
                if(page < emojiSetCount) {
                    for (int i = 0; i < displayedArray.size(); i++) {
                        displayedArray.get(i).setText(emojiArray[i+1+page*7][0]);
                    }
                } else {
                    for (int i = 0; i < lastSetCount; i++) {
                        displayedArray.get(i).setText(emojiArray[i+1+page*7][0]);
                    }
                }
            }
        });

        //TODO (optional) add other means of navigating the vast array of emojis

        // selecting emoji ie adding to emoji translation
        emojiTranslation = (EditText)findViewById(R.id.editText_emoji_translation_create);
        for(Button i : displayedArray){
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emojiTranslation.setText(emojiTranslation.getText().toString() + i.getText());
                }
            });
        }

        // adding completed translation to completeList via having each EditText not empty and clicking Button next
        englishTranslation = (EditText)findViewById(R.id.editText_english_translation_create);
        next = (Button)findViewById(R.id.button_next_create);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!emojiTranslation.getText().toString().equals("") && !englishTranslation.getText().toString().equals("")){
                    completeList.add(emojiTranslation.getText().toString());
                    completeList.add(englishTranslation.getText().toString());
                    emojiTranslation.setText(null);
                    englishTranslation.setText(null);
                }
            }
        });

        // to the home screen ie done creating via clicking Button home
        done = (Button) findViewById(R.id.button_done_create);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emojiTranslation.getText().toString().equals("") && !englishTranslation.getText().toString().equals("")) {
                    completeList.add(emojiTranslation.getText().toString());
                    completeList.add(englishTranslation.getText().toString());
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    i.putExtra("complete_list", completeList);
                    i.putExtra("from", "create");
                    startActivity(i);
                }
            }
        });
    }
}
