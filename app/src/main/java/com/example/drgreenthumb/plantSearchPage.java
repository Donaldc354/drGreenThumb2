package com.example.drgreenthumb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class plantSearchPage extends AppCompatActivity {

    String searchingFor;
    String link = "https://trefle.io/api/species/?token=eUJ6SnZ6TUh3bjhCcnlhMkNPSDMzdz09";
    String token = "?token=eUJ6SnZ6TUh3bjhCcnlhMkNPSDMzdz09";
    String result;
    JSONArray plantResult;
    JSONObject plantObject;
    URL url;
    String output;
    BufferedReader br;
    HttpsURLConnection connection = null;
    String plantUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_search_page);

        final ConstraintLayout temp = findViewById(R.id.plantSearchPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        final Bundle b = getIntent().getExtras();

        final Button plant = this.findViewById(R.id.btnPlant);
        final EditText txt = this.findViewById(R.id.textView2);

        plant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)

            {
                //;
                //startActivity(intent);
                //searchingFor = b.getString("message");
                searchingFor = txt.getText().toString();
                link = link + "&common_name=" + searchingFor;
                new TrefleApiConnect().execute(link);
                plantUrl = plantUrl + token;
                new TrefleApiConnect().execute(plantUrl);
                Intent intent = new Intent(v.getContext(), plantInfoPage.class);
                Bundle a = new Bundle();
                intent.putExtras(a);
                startActivity(intent);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class TrefleApiConnect extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String temp = params[0];
            try {
                url = new URL(temp);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setDoOutput(true);
                connection.connect();
                br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                while ((output = br.readLine()) != null)
                {
                    sb.append(output + "\n");
                }
                br.close();
                result = sb.toString();
                plantResult = new JSONArray(result);

                JSONObject obj = plantResult.getJSONObject(0);
                plantObject = obj;
                //String name = obj.getString("common_name");
                plantUrl = obj.getString("link");

                //String tempString = "name: " + name + ", link: " + tempurl;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

