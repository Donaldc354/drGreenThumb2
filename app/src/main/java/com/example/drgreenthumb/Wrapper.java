package com.example.drgreenthumb;

import org.json.JSONObject;

public class Wrapper {

    public JSONObject object;
    public int result;

    public Wrapper (JSONObject object, int result) {
        this.object = object;
        this.result = result;
    }


}
