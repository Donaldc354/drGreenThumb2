package com.example.drgreenthumb;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homePage extends AppCompatActivity {
    DatabaseHelper myDB;
    //Firebase Current User variables
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String uName;
    private String uEmail;
    private int uID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Bundle b = getIntent().getExtras();
        Button search = this.findViewById(R.id.btnPlantLookUp);
        Button favoritesList = this.findViewById(R.id.btnFavoritesList);
        Button findSupplies = this.findViewById(R.id.btnFindSupplies);
        Button utilities = this.findViewById(R.id.btnUtilities);
        Button settings = this.findViewById(R.id.btnSettings);
        TextView greeting = findViewById(R.id.txtWelcomeUser);
        //mAuth = FirebaseAuth.getInstance();
        //currentUser = mAuth.getCurrentUser();
        String uEmail = b.getString("id");
        greeting.setText(greeting + " " + uEmail);

        User user = viewUserInfo(uEmail);

        TextView welcomeUser = findViewById(R.id.txtWelcomeUser);

        welcomeUser.setText(user.getUserEmail());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), plantSearchPage.class);

                Bundle bundle = new Bundle();

                TextInputEditText textInputEditText = findViewById(R.id.txtInputEditText);

                String search;
                if(textInputEditText.getText().toString() != null){
                    search = textInputEditText.getText().toString();
                    bundle.putString("message", search);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        favoritesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findSupplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        utilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), utilitiesPage.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), settingsPage.class);
                startActivity(intent);
            }
        });
    }


    public User viewUserInfo(String uEmail){
        Cursor res = (Cursor) myDB.getUserData(uEmail);
        if(res.getCount() == 0){
            Toast.makeText(homePage.this, "Shit Went WRONG!!!", Toast.LENGTH_LONG).show();
        }

            Toast.makeText(homePage.this, "Shit Went RIGHT!!!", Toast.LENGTH_LONG).show();
            User user = new User(res.getString(0), res.getString(1), res.getInt(2));

             /*StringBuffer buffer = new StringBuffer();
             while(res.moveToNext()){

                 //buffer.append("ID :" + res.getString(0));
                 //buffer.append("userID :" + res.getString(1));
                 //buffer.append("userEmail :" + res.getString(2));
                 txtWelcomeUser
             }
             */
             return user;
    }

}


