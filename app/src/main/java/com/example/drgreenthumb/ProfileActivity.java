package com.example.drgreenthumb;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView Email;
    private TextView Uid;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final LinearLayout temp = findViewById(R.id.profileLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        Email = findViewById(R.id.profileEmail);
        Uid = findViewById(R.id.profileUid);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.button_logout);
        user = mAuth.getCurrentUser();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==logout){
                    if (user != null) {
                        mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), loginPage.class));
                    }
                }
            }
        });
        if (user != null) {
            String email = user.getEmail();
            String uid = user.getUid();
            Email.setText(email);
            Uid.setText(uid);
        }

    }



}
