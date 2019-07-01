package com.example.drgreenthumb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class personalization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalization);

        final ConstraintLayout temp = findViewById(R.id.personalizationLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
