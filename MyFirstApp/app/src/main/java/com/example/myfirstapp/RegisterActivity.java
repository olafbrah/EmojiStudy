package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    Button login;
    Button register;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // to the login screen
        login = (Button)findViewById(R.id.button_already_have_account_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.putExtra("registered",false);
                startActivity(i);
            }
        });

        // to the login screen via registration
        name = (EditText)findViewById(R.id.editText_name_register);
        register = (Button)findViewById(R.id.button_submit_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO conditionals for registration
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.putExtra("registered",true);
                DataHolder.setName(name.getText().toString()); // TODO send name to firebase
                startActivity(i);
            }
        });
    }
}
