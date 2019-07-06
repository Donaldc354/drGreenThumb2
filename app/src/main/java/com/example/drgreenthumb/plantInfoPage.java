package com.example.drgreenthumb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class plantInfoPage extends AppCompatActivity {

    String plantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info_page);

        final ConstraintLayout temp = findViewById(R.id.plantInfoPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        Bundle b = getIntent().getExtras();

        TextView plant = findViewById(R.id.txtPlantName2);

        plant.setText(b.getString("PLANT_NAME"));

        plantName = b.getString("PLANT_NAME");

        Button purchase = findViewById(R.id.btnPurchasePlant);

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), webView.class);
                Bundle bundle = new Bundle();
                bundle.putString("SEARCH", plantName);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
