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
    Button home;
    ArrayList<Button> displayedArray;
    List<String[]> emojiList;
    CSVReader reader;
    ArrayList<String[]> emojiArray;
    Button nextEmojiSet;
    int emojiSetCount;
    int lastSetCount;
    int page;
    EditText emojiTranslation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.emoji_df)));
        try {
            emojiList = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        emojiArray = new ArrayList<String[]>();
        for(String[] i : emojiList) {
            emojiArray.add(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

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
            displayedArray.get(i).setText(emojiArray.get(i+1)[0]);
        }

        // to next page of emojis
        emojiSetCount = emojiArray.size() / displayedArray.size();
        lastSetCount = emojiArray.size() - emojiSetCount*7;
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
                        displayedArray.get(i).setText(emojiArray.get(i + 1 + page * 7)[0]);
                    }
                } else {
                    for (int i = 0; i < lastSetCount; i++) {
                        displayedArray.get(i).setText(emojiArray.get(i + 1 + page * 7)[0]);
                    }
                }
            }
        });

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
