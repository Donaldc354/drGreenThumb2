package com.example.drgreenthumb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class editAccountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);

        final ConstraintLayout temp = findViewById(R.id.editAccountInfoLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
