package com.example.drgreenthumb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class favoritesList extends AppCompatActivity {

    private ListView listView;

    private ArrayAdapter arrayAdapter;
    private ArrayList<String> favoritesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        listView = findViewById(R.id.favoritesList);
        arrayAdapter = new ArrayAdapter(this, R.layout.mylistview, favoritesList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
