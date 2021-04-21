package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button logout;
    Button create;
    Button study;
    ArrayList<String> completeList;
    String username;
    TextView welcome;
    EditText newListName;
    Button myList; // will become a spinner
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent get = getIntent();
        welcome = (TextView)findViewById(R.id.label_welcome_home);
        // TODO save username between all activities until a new one is created in either registration or login and continue welcoming whenever at home
        if(get.getStringExtra("from").equals("login")){
            username = get.getStringExtra("username");
            welcome.setText(welcome.getText().toString() + username);
        }
        else
            welcome.setText("");
        if(get.getStringExtra("from").equals("create") || get.getStringExtra("from").equals("study"))
            completeList = get.getStringArrayListExtra("complete_list");

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
        newListName = (EditText)findViewById(R.id.editText_new_list_name_home);
        create = (Button)findViewById(R.id.button_create_home);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateActivity.class);
                i.putExtra("list_name",newListName.getText().toString());
                startActivity(i);
            }
        });

        // to the study screen
        // TODO make the study button into a spinner and whatever else to implement multiple lists
        study = (Button)findViewById(R.id.button_my_list_1_home); // id will change, current will become that of a spinner, refactor spinner's id to spinner_my_list_home
        if(get.getStringExtra("from").equals("create") || get.getStringExtra("from").equals("study"))
            study.setText(completeList.get(0));
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (completeList.size() != 0) {
                    Intent i = new Intent(getApplicationContext(), StudyActivity.class);
                    i.putExtra("list_name", completeList.get(0));
                    i.putExtra("complete_list", completeList);
                    startActivity(i);
                }
            }
        });
    }
}
