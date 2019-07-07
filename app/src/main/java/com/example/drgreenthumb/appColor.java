package com.example.drgreenthumb;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class appColor extends AppCompatActivity {

    private static int globalAppColor;
    //public int globalAppColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_color);

        Button white = findViewById(R.id.btnWhite);
        Button red = findViewById(R.id.btnRed);
        Button orange = findViewById(R.id.btnOrange);
        Button yellow = findViewById(R.id.btnYellow);
        Button green = findViewById(R.id.btnGreen);
        Button blue = findViewById(R.id.btnBlue);
        Button cyan = findViewById(R.id.btnCyan);
        Button purple = findViewById(R.id.btnPurple);
        Button save = findViewById(R.id.btnSave);

        final ConstraintLayout constraintLayout = findViewById(R.id.constLayout);
        constraintLayout.setBackgroundColor(appColor.setAppColor());

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.white);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.red);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.orange);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.orange));
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.yellow);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.green);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.cyan);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.cyan));
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.blue);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalAppColor = getResources().getColor(R.color.purple);
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.purple));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appColor.this, homePage.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public static int setAppColor()
    {
        return globalAppColor;
    }
}
