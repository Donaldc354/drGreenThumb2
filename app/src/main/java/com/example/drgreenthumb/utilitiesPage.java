package com.example.drgreenthumb;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class utilitiesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities_page);

        final ConstraintLayout temp = findViewById(R.id.utilityLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        Button wateringSchedule = findViewById(R.id.btnWateringSchedule);

        wateringSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(utilitiesPage.this, com.example.drgreenthumb.wateringSchedule.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button personalization = findViewById(R.id.btnPersonalization);

        personalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(utilitiesPage.this, com.example.drgreenthumb.personalization.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button newsletters = findViewById(R.id.btnNewsletters);

        newsletters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(utilitiesPage.this, com.example.drgreenthumb.newsletters.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
