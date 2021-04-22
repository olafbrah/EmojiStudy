package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {
    Button login;
    Button register;
    FirebaseAuth fAuth;
    public static final String TAG = "TAG";
    FirebaseFirestore fStore;
    String userID;
    EditText name, email, pass_one, pass_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        // to the login screen
        login = (Button) findViewById(R.id.button_already_have_account_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.putExtra("registered", false);
                startActivity(i);
            }
        });

        // to the login screen via registration
        name = findViewById(R.id.editText_name_register);
        email = findViewById(R.id.editText_email_register);
        pass_one = findViewById(R.id.editText_pass_one_register);
        pass_two = findViewById(R.id.editText_pass_two_register);
        register = findViewById(R.id.button_submit_register);


        register.setOnClickListener(new View.OnClickListener() {

            //mRegisterBtn= findViewById(R.id.registerBtn);
            //mLoginBtn   = findViewById(R.id.createText);

            @Override
            public void onClick(View view) { //the thing lol
                final String eEmail = email.getText().toString().trim();
                String password1 = pass_one.getText().toString().trim();
                String password2 = pass_two.getText().toString().trim();
                final String eName = name.getText().toString();


                if (TextUtils.isEmpty(eEmail)) {
                    email.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    pass_one.setError("Password is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password2)) {
                    pass_two.setError("Password is Required");
                    return;
                }

                if (password1.length() < 6) {
                    pass_one.setError("Password Must be at least 6 Characters");
                    return;
                }

                if (password2.length() < 6) {
                    pass_two.setError("Password Must be at least 6 Characters");
                    return;
                }

                if (!(password1.equals(password2))){
                    pass_two.setError("Passwords must match");
                    return;
                }


                // TODO conditionals for registration
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.putExtra("registered", true);
                DataHolder.setName(name.getText().toString()); // TODO send name to firebase

                fAuth.createUserWithEmailAndPassword(eEmail, password1).addOnCompleteListener(new OnCompleteListener < AuthResult > () {
                    @Override
                    public void onComplete(@NonNull Task < AuthResult > task) {
                        if (task.isSuccessful()) {

                            // send verification link

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener < Void > () {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });

                            Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map < String, Object > user = new HashMap < > ();
                            user.put("name", eName);
                            user.put("email", eEmail);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener < Void > () {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                startActivity(i);
            }
        })
    ;}
}