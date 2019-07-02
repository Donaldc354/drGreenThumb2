package com.example.drgreenthumb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class loginPage extends AppCompatActivity {
    DatabaseHelper myDB;
    private FirebaseAuth mAuth;
    private EditText newPassword;
    private EditText newEmail;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);

        //Create Database helper instance
        myDB = new DatabaseHelper(this);
        newEmail = findViewById(R.id.txtEmail);
        newPassword = findViewById(R.id.txtPassword);
        //loginButton = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    //Working on this one

    public void registerUser(View v) {
        String userEmail = newEmail.getText().toString().trim();
        String userPassword = newPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                //start Profile Activity here
                                Toast.makeText(loginPage.this, "registration successful",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), homePage.class));
                            } else {
                                Toast.makeText(loginPage.this, "Couldn't register, try again",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    //Needs verification
    public void LoginAction(View v){
        boolean isInserted = myDB.insertData(newEmail.getText().toString(), newPassword.getText().toString());
        if  (isInserted  == true){
            Toast.makeText(loginPage.this, "User Logged In", Toast.LENGTH_LONG).show();
            User newUser = new User(newEmail.getText().toString(), newPassword.getText().toString() );
            Intent intent = new Intent(this, homePage.class);
            Bundle loginBundle = new Bundle();
            loginBundle.putString("id", newEmail.getText().toString());
            intent.putExtras(loginBundle);
            startActivity(intent);
        }
        else {
            Toast.makeText(loginPage.this, "User Not Logged In", Toast.LENGTH_LONG).show();
        }
    }








    /*
    Old login using firebase
    public void loginUser(View v) {
        String userEmail = newEmail.getText().toString().trim();
        String userPassword = newPassword.getText().toString().trim();


        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String fileName = "userStorage";
                            String fileContents = "blah";
                            FileOutputStream outputStream;
                            try{

                                outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                                outputStream.write(fileContents.getBytes());
                                outputStream.close();
                            } catch(Exception e){
                                //May need to run a catch file!
                                e.printStackTrace();
                            }
                            finish();
                            startActivity(new Intent(getApplicationContext(), homePage.class));
                        } else {
                            Toast.makeText(loginPage.this, "couldn't login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    */


    //User newUser = new User(email, password);
        //Intent intent = new Intent(this, homePage.class);
       // intent.putExtra("User", newUser);
        //startActivity(intent);

        //Possibly
        //Intent i = getIntent();
        //Deneme dene = (Deneme)i.getSerializableExtra("sampleObject");
   // }
}



