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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class plantSearchPage extends AppCompatActivity {

    String searchingFor;
    String link = "http://trefle.io/api/species/?token=eUJ6SnZ6TUh3bjhCcnlhMkNPSDMzdz09";
    String result;
    JSONObject plantResult;
    URL url;
    String output;
    BufferedReader br;
    HttpURLConnection connection = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_search_page);

        final ConstraintLayout temp = findViewById(R.id.plantSearchPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        final Bundle b = getIntent().getExtras();




        Button plant = this.findViewById(R.id.btnPlant);

        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), plantInfoPage.class);
                //startActivity(intent);
                searchingFor = b.getString("message");
                link = link + "&common_name=" + searchingFor;
                try {
                    url = new URL(link);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(15000);
                    connection.setDoOutput(true);
                    connection.connect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    br = new BufferedReader(new InputStreamReader(url.openStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringBuilder sb = new StringBuilder();
                while(true)
                {
                    try {
                        if (((output = br.readLine()) != null))
                        {
                            sb.append(output + "\n");
                        }
                        else
                        {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                result = sb.toString();
                try {
                    plantResult = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                TextView textView = findViewById(R.id.textView2);
                textView.setText(plantResult.toString());

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
