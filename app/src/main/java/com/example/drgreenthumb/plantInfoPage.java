package com.example.drgreenthumb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class plantInfoPage extends AppCompatActivity {

    JSONObject plantObject;
    JSONObject imageObject;
    String plantImageUrl;
    plant resultPlant;
    String plantName;
    Image plantImage;
    String plantImageString;
    JSONArray imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info_page);

        final ConstraintLayout temp = findViewById(R.id.plantInfoPageLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        plant newPlant;

        try {
            plantObject = new JSONObject(getIntent().getStringExtra("actualPlantObject"));
            parsePlant(plantObject);
            plantName = plantObject.getString("common_name");
            plantImageString = plantObject.getString("images");
            imageArray = new JSONArray(plantImageString);
            imageObject = imageArray.getJSONObject(0);
            plantImageUrl = imageObject.getString("url");
            /*TextView textView = findViewById(R.id.txtPlantInfo2);
            String displayString = "Plant Name: " + resultPlant.getPlantName() + "\nPlant Type: " + resultPlant.getPlantType() + "\nToxicity: "
                    + resultPlant.isPlantToxic() + "\nMature Height (ft): " + resultPlant.getPlantHeight() + "\nLife Span: " + resultPlant.getPlantLifeSpan()
                    + "\nBloom Period: " + resultPlant.getBloomPeriod() + "\nMinimum Temperature (Fahrenheit): " + resultPlant.getTempMin()
                    + "\nShade Tolerance: " + resultPlant.getShadeTolerance() + "\nSalinityTolerance: " + resultPlant.getSalinityTolerance()
                    + "\nDroughtTolerance: " + resultPlant.getDroughtTolerance() + "\nMinimum Precipitation (in): " + resultPlant.getPrecipitationMin_in()
                    + "\nMaximum Precipitation (in): " + resultPlant.getPrecipitationMax_in() + "\npH Minimum: " + resultPlant.getpHmin()
                    + "\npH Max: " + resultPlant.getpHmax();
            textView.setText(displayString);*/
            ImageView image = findViewById(R.id.imgPlant2);
            new DownloadImageTask(image).execute(plantImageUrl);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView txtView = findViewById(R.id.txtPlantName2);
        txtView.setText(plantName);

        final ImageView favoriteHeart = findViewById(R.id.favoriteHeart);
        final PlantDatabaseHelper plantDatabaseHelper = new PlantDatabaseHelper(this);
        final ArrayList<plant> plants = new ArrayList<>(plantDatabaseHelper.getFavorites());

        if(plants.contains(resultPlant)){
            favoriteHeart.setImageResource(R.drawable.favorite_true);
        }

        favoriteHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(plants.contains(resultPlant)){
                    favoriteHeart.setImageResource(R.drawable.favorite_false);
                    plantDatabaseHelper.removeFavorite(resultPlant);
                } else {
                    favoriteHeart.setImageResource(R.drawable.favorite_true);
                    plantDatabaseHelper.insertFavorite(resultPlant);
                }
            }
        });

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

    private void parsePlant (JSONObject p) throws JSONException {
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
        public float pHmax;*/

        JSONObject temp = p;
        JSONObject mainSpeciesObject = temp.getJSONObject("main_species");
        JSONObject specificationsObject = mainSpeciesObject.getJSONObject("specifications");
        JSONObject matureHeightObject = specificationsObject.getJSONObject("mature_height");
        JSONObject seedObject = mainSpeciesObject.getJSONObject("seed");
        JSONObject growthObject = mainSpeciesObject.getJSONObject("growth");
        JSONObject temperatureObject = growthObject.getJSONObject("temperature_minimum");
        JSONObject precipitationMinObject = growthObject.getJSONObject("precipitation_minimum");
        JSONObject precipitationMaxObject = growthObject.getJSONObject("precipitation_maximum");

        plant t;

        try {
            t = new plant(p.getString("common_name"),
                    Integer.parseInt(p.getString("id")),
                    mainSpeciesObject.getString("type"),
                    specificationsObject.getString("toxicity"),
                    Float.parseFloat(matureHeightObject.getString("ft")),
                    specificationsObject.getString("lifespan"),
                    seedObject.getString("bloom_period"),
                    Float.parseFloat(temperatureObject.getString("deg_f")),
                    growthObject.getString("shade_tolerance"),
                    growthObject.getString("salinity_tolerance"),
                    growthObject.getString("drought_tolerance"),
                    Float.parseFloat(precipitationMinObject.getString("inches")),
                    Float.parseFloat(precipitationMaxObject.getString("inches")),
                    Float.parseFloat(growthObject.getString("ph_minimum")),
                    Float.parseFloat(growthObject.getString("ph_maximum")));
            System.out.println(t.getPlantName());
            resultPlant = new plant(t);
            PlantDatabaseHelper myDB = new PlantDatabaseHelper(this);
            myDB.insertPlant(t);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
