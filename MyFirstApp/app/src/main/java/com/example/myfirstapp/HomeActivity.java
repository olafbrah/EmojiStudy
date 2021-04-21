package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    Spinner myList;
    Button delete;
    ArrayList<String> listNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent get = getIntent();
        welcome = (TextView)findViewById(R.id.label_welcome_home);
        if(get.getStringExtra("from").equals("login"))
            username = get.getStringExtra("username");
        welcome.setText(welcome.getText().toString() + DataHolder.getName());
        listNames = new ArrayList<String>();
        listNames.add("-------");
        if(get.getStringExtra("from").equals("create") || get.getStringExtra("from").equals("study")) {
            completeList = get.getStringArrayListExtra("complete_list");
            DataHolder.addList(completeList);
            listNames.add(completeList.get(0));
        }
        completeList = new ArrayList<String>();

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
        myList = (Spinner)findViewById(R.id.spinner_my_list_home);
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

        // selecting from spinner
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, listNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        myList.setAdapter(aa);
        myList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0)
                    completeList = DataHolder.getSingle(i - 1);
                else
                    completeList.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // to the study screen
        study = (Button)findViewById(R.id.button_study_home);
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (completeList.size() != 0) {
                    Intent i = new Intent(getApplicationContext(), StudyActivity.class);
                    i.putExtra("list_name", completeList.get(0));
                    i.putExtra("complete_list", completeList);
                    startActivity(i);
                }
                // for else, show a toast?
            }
        });

        // delete the set
        delete = (Button)findViewById(R.id.button_delete_home);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DataHolder.getLength() > 1) {
                    DataHolder.removeList(completeList);
                    listNames.remove(completeList.get(0));
                    completeList = new ArrayList<String>();
                }
                myList.setSelection(0);
            }
        });
    }
}
