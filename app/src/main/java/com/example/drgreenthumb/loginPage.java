package com.example.drgreenthumb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText newPassword;
    private EditText newEmail;
    //private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);

        newEmail = findViewById(R.id.txtEmail);
        newPassword = findViewById(R.id.txtPassword);
        mAuth = FirebaseAuth.getInstance();
        //currentUser = mAuth.getCurrentUser();

    }

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

    public void loginUser(View v) {
        String userEmail = newEmail.getText().toString().trim();
        String userPassword = newPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //currentUser = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(getApplicationContext(), homePage.class));
                        } else {
                            Toast.makeText(loginPage.this, "couldn't login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



















        //String password = newPassword.toString();
        //String email = newEmail.toString();




















        //User newUser = new User(email, password);
        //Intent intent = new Intent(this, homePage.class);
       // intent.putExtra("User", newUser);
        //startActivity(intent);

        //Possibly
        //Intent i = getIntent();
        //Deneme dene = (Deneme)i.getSerializableExtra("sampleObject");
   // }
}



