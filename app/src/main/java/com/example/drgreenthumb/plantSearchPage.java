package com.example.drgreenthumb;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

public class plantSearchPage extends AppCompatActivity {

    String searchingFor;
    String link = "http://trefle.io/api/species/?token=eUJ6SnZ6TUh3bjhCcnlhMkNPSDMzdz09";
    String result;
    JSONObject plantResult;
    URL url;
    String output;
    BufferedReader br;
    HttpURLConnection connection = null;

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> resultsList = new ArrayList<>();
    private ArrayList<String> resultsIDList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_search_page);

        final ConstraintLayout temp = findViewById(R.id.plantSearchPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        final Bundle b = getIntent().getExtras();

        searchingFor = b.getString("SEARCH_FOR");
        TextView textView = findViewById(R.id.txtSearchingFor);
        textView.setText("Searching For: '" + searchingFor + "'");

        listView = findViewById(R.id.searchResults);

        arrayAdapter = new ArrayAdapter(this, R.layout.mylistview, resultsList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(view.getContext(), plantInfoPage.class);
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
                while (true) {
                    try {
                        if (((output = br.readLine()) != null)) {
                            sb.append(output + "\n");
                        } else {
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

        resultsList.add(searchingFor);
        resultsIDList.add(searchingFor);
        listView.setAdapter(arrayAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
