package com.example.drgreenthumb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class plantInfoPage extends AppCompatActivity {

    JSONObject plantObject;
    plant resultPlant;
    String plantName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info_page);

        final ConstraintLayout temp = findViewById(R.id.plantInfoPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        try {
            plantObject = new JSONObject(getIntent().getStringExtra("actualPlantObject"));
            plantName = plantObject.getString("common_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView txtView = (TextView) findViewById(R.id.txtPlantName2);
        txtView.setText(plantName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*private plant parsePlant (JSONObject p) throws JSONException {
        /*public String plantName;

        public int speciesID;
        public String plantType;
        public String toxicity;
        public float matureHeight_ft;
        public String lifeSpan;
        public String bloomPeriod;
        public float tempMin_f;
        public String shadeTolerance;
        public String salinityTolerance;
        public String droughtTolerance;
        public float precipitationMin_in;
        public float precipitationMax_in;
        public float pHmin;
        public float pHmax;

        plant t;
        t = new plant(p.getString("q"), Integer.parseInt(p.getString("species_id")), p.getString("type"),
                p.getString("toxicity"), Float.parseFloat(p.getString()));


        return t;
    }*/
}
