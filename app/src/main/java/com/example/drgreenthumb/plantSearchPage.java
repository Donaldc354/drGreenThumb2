package com.example.drgreenthumb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class plantSearchPage extends AppCompatActivity {

    String searchingFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_search_page);

        final ConstraintLayout temp = findViewById(R.id.plantSearchPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        Bundle b = getIntent().getExtras();
        searchingFor = b.getString("message");
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Searching For: '" + searchingFor + "'");

        Button plant = this.findViewById(R.id.btnPlant);

        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), plantInfoPage.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
