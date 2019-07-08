package com.example.drgreenthumb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

public class plantSearchPage extends AppCompatActivity {

    String searchingFor;
    String plantIDlink = "https://trefle.io/api/plants?token=eUJ6SnZ6TUh3bjhCcnlhMkNPSDMzdz09";
    String token = "?token=eUJ6SnZ6TUh3bjhCcnlhMkNPSDMzdz09";
    String plantID;
    String result;
    String actualResult;
    JSONArray plantResult;
    JSONObject plantObject;
    Intent intent;

    URL url;
    URL actualPlantUrl;
    String output;
    BufferedReader br;
    HttpsURLConnection connection = null;
    String plantUrl = "https://trefle.io/api/plants/";;

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
                //;
                //startActivity(intent);
                //searchingFor = b.getString("message");
                plantIDlink = plantIDlink + "&common_name=" + searchingFor;
                new TrefleApiConnect(plantSearchPage.this).execute(plantIDlink);
                //intent = new Intent(plantSearchPage.this, plantInfoPage.class);

                //startActivity(intent);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class TrefleApiConnect extends AsyncTask<String, Void, JSONObject>
    {
        private Activity activity;

        public TrefleApiConnect(Activity activity)
        {
            this.activity = activity;
        }

        @Override
        protected JSONObject doInBackground(String... params)
        {
            String temp = params[0];
            JSONObject actualPlantObject = null;
            try {
                //first api call
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
                //plantUrl = obj.getString("link");
                plantID = obj.getString("id");
                plantUrl = plantUrl + plantID + token;
                connection.disconnect();

                //second api call
                actualPlantUrl = new URL(plantUrl);
                connection = (HttpsURLConnection) actualPlantUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setDoOutput(true);
                connection.connect();
                br = new BufferedReader(new InputStreamReader(actualPlantUrl.openStream()));
                StringBuilder sd = new StringBuilder();
                while ((output = br.readLine()) != null)
                {
                    sd.append(output + "\n");
                }
                br.close();
                actualResult = sd.toString();
                actualPlantObject = new JSONObject(actualResult);
                //JSONObject obj2 = actualPlantObject.getJSONObject("main_species");
                connection.disconnect();

                //String tempString = "name: " + name + ", link: " + tempurl;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return actualPlantObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject)
        {
            super.onPostExecute(jsonObject);
            JSONObject t = jsonObject;
            activity.startActivity(new Intent(activity, plantInfoPage.class).putExtra("actualPlantObject", t.toString()));

        }
    }
}

