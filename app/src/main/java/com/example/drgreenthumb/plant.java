package com.example.drgreenthumb;

public class plant {
    public String plantName;
    public int speciesID;
    public String plantType;
   // public String synonym;
    public String toxicity;
    //public float maxHeightAtBaseAge_ft;
   // public float maxHeightAtBaseAge_cm;
    public float matureHeight_ft;
    //public float matureHeight_cm;
    public String lifeSpan;
    //public String growthRate;
    //public String growthPeriod;
    //public String growthHabit;
    //public String growthForm;
   // public String soilsAdaptationMedium;
    //public String soilsAdaptationFine;
    //public String soilsAdaptationCoarse;
    //public String vegetativeSpreadRate;
    //public String seedSpreadRate;
    //public String commercialAvailability;
    public String bloomPeriod;
    public float tempMin_f;
    public String shadeTolerance;
    public String salinityTolerance;
    public String droughtTolerance;
   // public float rootDepthMin_in;
   // public float rootDepthMin_cm;
    //public String resproutAbility;
    public float precipitationMin_in;
    //public float precipitationMin_cm;
    public float precipitationMax_in;
    //public float precipitationMax_cm;
    public float pHmin;
    public float pHmax;
   // public String seedPeriodEnd;
   // public String seedPeriodBegin;

    public plant(String plantName, int speciesID, String plantType, String synonym, String toxicity,
             float matureHeight_ft, String lifeSpan, String bloomPeriod,
             float tempMin_f, String shadeTolerance, String salinityTolerance, String droughtTolerance, float precipitationMin_in, float precipitationMin_cm, float precipitationMax_in,
             float pHmin, float pHmax)
    {
        this.plantName = plantName;
        this.speciesID = speciesID;
        this.plantType = plantType;
        //this.synonym = synonym;
        this.toxicity = toxicity;
        this.matureHeight_ft = matureHeight_ft;
        this.lifeSpan = lifeSpan;
        //this.growthRate = growthRate;
        this.bloomPeriod = bloomPeriod;
        this.tempMin_f = tempMin_f;
        this.shadeTolerance = shadeTolerance;
        this.salinityTolerance = salinityTolerance;
        this.droughtTolerance = droughtTolerance;
        this.precipitationMin_in = precipitationMin_in;
        this.precipitationMax_in = precipitationMax_in;
        this.pHmax = pHmax;
        this.pHmin = pHmin;
    }
    public String getPlantName(){
        return plantName;
    }
    public int getPlantID(){
        return speciesID;
    }
    public String getPlantType(){
        return plantType;
    }
    public String isPlantToxic(){
        return toxicity;
    }
    public float getPlantHeight(){
        return this.matureHeight_ft;
    }
    public String getPlantLifeSpan(){
        return lifeSpan;
    }
    public String getBloomPeriod(){
        return bloomPeriod;
    }
    public float getTempMin(){
        return tempMin_f;
    }
    public float getPrecipitationMin_in(){
        return precipitationMin_in;
    }
    public float getPrecipitationMax_in(){
        return precipitationMax_in;
    }
    public float getpHmin(){
        return pHmin;
    }
    public float getpHmax(){
        return  pHmax;
    }
}



