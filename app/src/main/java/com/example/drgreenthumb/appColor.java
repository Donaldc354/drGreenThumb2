package com.example.drgreenthumb;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class appColor extends AppCompatActivity {

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

        final ConstraintLayout constraintLayout = findViewById(R.id.constLayout);

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.orange));
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.cyan));
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.purple));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
