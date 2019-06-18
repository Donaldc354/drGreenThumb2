package com.example.drgreenthumb;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Bundle b = getIntent().getExtras();

        Button search = this.findViewById(R.id.btnPlantLookUp);
        Button favoritesList = this.findViewById(R.id.btnFavoritesList);
        Button findSupplies = this.findViewById(R.id.btnFindSupplies);
        Button utilities = this.findViewById(R.id.btnUtilities);
        Button settings = this.findViewById(R.id.btnSettings);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), plantSearchPage.class);

                Bundle bundle = new Bundle();

                TextInputEditText textInputEditText = findViewById(R.id.txtInputEditText);

                String search;
                if(textInputEditText.getText().toString() != null){
                    search = textInputEditText.getText().toString();
                    bundle.putString("message", search);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        favoritesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findSupplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        utilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), utilitiesPage.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), settingsPage.class);
                startActivity(intent);
            }
        });
    }
}
