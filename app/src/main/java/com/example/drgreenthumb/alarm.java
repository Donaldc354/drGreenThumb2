package com.example.drgreenthumb;

import java.text.DateFormat;
import java.util.Calendar;

public class alarm {
    public int requestCode;
    public String plantName;
    public int hr;
    public int min;

    public alarm(){}

    public alarm(int requestCode, String planeName, int hr, int min){
        this.requestCode = requestCode;
        this.plantName = planeName;
        this.hr = hr;
        this.min = min;
    }

    public void setRequestCode(int requestCode){
        this.requestCode = requestCode;
    }

    public void setPlantName(String plantName){
        this.plantName = plantName;
    }

    public void setHr(int hr){
        this.hr = hr;
    }

    public void setMin(int min){
        this.min = min;
    }

    public String formatTime(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, this.hr);
        c.set(Calendar.MINUTE, this.min);
        c.set(Calendar.SECOND, 0);

        return DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
    }

    public String formatOutput(){
        return this.plantName + "     " + formatTime();
    }

    public int getRequestCode(){
        return this.requestCode;
    }

    public Calendar getTime(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, this.hr);
        c.set(Calendar.MINUTE, this.min);
        c.set(Calendar.SECOND, 0);

        return c;
    }

    public String getPlantName(){
        return this.plantName;
    }
}
