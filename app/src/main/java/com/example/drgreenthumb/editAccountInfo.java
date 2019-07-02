package com.example.drgreenthumb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class editAccountInfo extends AppCompatActivity {

    //Firebase Current User variables
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    String fileName = "userFile";
    String fileContents;
    FileOutputStream opStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try{
            opStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            opStream.write(fileContents.getBytes());
            opStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }
}
