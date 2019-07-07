package com.example.drgreenthumb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
                search(v).show();
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

    protected Dialog search(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setHint("Search");
        builder.setView(input)
                .setTitle("Search for Supplies")
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText().toString() != null){
                            Intent intent = new Intent(v.getContext(), supplies.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("SEARCH", input.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                });
        return builder.create();
    }

}
