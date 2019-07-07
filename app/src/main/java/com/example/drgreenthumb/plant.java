package com.example.drgreenthumb;

public class plant {
    public String plantName;

    public int speciesID;
    public String plantType;
    public String synonym;
    public String toxicity;
    public float maxHeightAtBaseAge_ft;
    public float maxHeightAtBaseAge_cm;
    public float matureHeight_ft;
    public float matureHeight_cm;
    public String lifeSpan;
    public String growthRate;
    public String growthPeriod;
    public String growthHabit;
    public String growthForm;
    public String soilsAdaptationMedium;
    public String soilsAdaptationFine;
    public String soilsAdaptationCoarse;
    public String vegetativeSpreadRate;
    public String seedSpreadRate;
    public String commercialAvailability;
    public String bloomPeriod;
    public float tempMin_f;
    public float tempMin_c;
    public String shadeTolerance;
    public String salinityTolerance;
    public String hedgeTolerance;
    public String fireTolerance;
    public String droughtTolerance;
    public float rootDepthMin_in;
    public float rootDepthMin_cm;
    public String resproutAbility;
    public float precipitationMin_in;
    public float precipitationMin_cm;
    public float precipitationMax_in;
    public float precipitationMax_cm;
    public float pHmin;
    public float pHmax;
    public String seedPeriodEnd;
    public String seedPeriodBegin;
    
    public plant(String plantName, int speciesID, String plantType, String synonym, String toxicity, float maxHeightAtBaseAge_ft, float maxHeightAtBaseAge_cm,
             float matureHeight_ft, float matureHeight_cm, String lifeSpan, String growthRate, String growthPeriod, String growthHabit, String growthForm,
             String soilsAdaptationMedium, String soilsAdaptationFine, String soilsAdaptationCoarse, String vegetativeSpreadRate, String seedSpreadRate, String commercialAvailability, String bloomPeriod,
             float tempMin_f, float tempMin_c, String shadeTolerance, String salinityTolerance, String hedgeTolerance, String fireTolerance, String droughtTolerance,
             float rootDepthMin_in, float rootDepthMin_cm, String resproutAbility, float precipitationMin_in, float precipitationMin_cm, float precipitationMax_in,
             float precipitationMax_cm, float pHmin, float pHmax, String seedPeriodEnd, String seedPeriodBegin)
    {
        this.plantName = plantName;
        this.speciesID = speciesID;
        this.plantType = plantType;
        this.synonym = synonym;
        this.toxicity = toxicity;
        this.maxHeightAtBaseAge_cm = maxHeightAtBaseAge_cm;
        this.maxHeightAtBaseAge_ft = maxHeightAtBaseAge_ft;
        this.matureHeight_cm = matureHeight_cm;
        this.matureHeight_ft = matureHeight_ft;
        this.lifeSpan = lifeSpan;
        this.growthForm = growthForm;
        this.growthPeriod = growthPeriod;
        this.growthHabit = growthHabit;
        this.growthRate = growthRate;
        this.soilsAdaptationMedium = soilsAdaptationMedium;
        this.soilsAdaptationCoarse = soilsAdaptationCoarse;
        this.soilsAdaptationFine = soilsAdaptationFine;
        this.vegetativeSpreadRate = vegetativeSpreadRate;
        this.seedSpreadRate = seedSpreadRate;
        this.commercialAvailability = commercialAvailability;
        this.bloomPeriod = bloomPeriod;
        this.tempMin_c = tempMin_c;
        this.tempMin_f = tempMin_f;
        this.shadeTolerance = shadeTolerance;
        this.salinityTolerance = salinityTolerance;
        this.hedgeTolerance = hedgeTolerance;
        this.fireTolerance = fireTolerance;
        this.droughtTolerance = droughtTolerance;
        this.rootDepthMin_in = rootDepthMin_in;
        this.rootDepthMin_cm = rootDepthMin_cm;
        this.resproutAbility = resproutAbility;
        this.precipitationMin_in = precipitationMin_in;
        this.precipitationMin_cm = precipitationMin_cm;
        this.precipitationMax_in = precipitationMax_in;
        this.precipitationMax_cm = precipitationMax_cm;
        this.pHmax = pHmax;
        this.pHmin = pHmin;
        this.seedPeriodBegin = seedPeriodBegin;
        this.seedPeriodEnd = seedPeriodEnd;
    }
}


