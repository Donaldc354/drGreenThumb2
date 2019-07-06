package com.example.drgreenthumb;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class wateringSchedule extends AppCompatActivity {

    static final int TIME_DIALOG_ID = 1111;
    static final int PLANT_DIALOG_ID = 2222;
    static final int OPTIONS_DIALOG_ID = 3333;

    private int hr;
    private int min;
    private Calendar c = Calendar.getInstance();
    private String timeText;

    private ListView listView;
    private Button addRemind;
    private int pos;

    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();

    private Context context = this;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watering_schedule);

        final ConstraintLayout temp = findViewById(R.id.wateringLayout);
        temp.setBackgroundColor(appColor.setAppColor());

        listView = findViewById(R.id.alarmListView);

        arrayAdapter = new ArrayAdapter(this, R.layout.mylistview, arrayList);

        addRemind = findViewById(R.id.btnAddReminder);

        addRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createdDialog(TIME_DIALOG_ID).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                createdDialog(OPTIONS_DIALOG_ID).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createAlarm(Calendar c, String plantName, int index){
        Intent intent = new Intent(context, AlarmReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putString("PLANT_NAME", plantName);
        bundle.putString("CHANNEL_NUM", Integer.toString(index));
        intent.putExtras(bundle);
        alarmMgr = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        alarmIntent = PendingIntent.getBroadcast(context, index, intent, 0);
        switch(plantName){
            case "Cactus/Succulent":
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * 7, alarmIntent);
                //saveSetAlarm(7, index, plantName, c);
            case "Fern":
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * 4, alarmIntent);
                //saveSetAlarm(4, index, plantName, c);
            case "Herb":
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * 3, alarmIntent);
                //saveSetAlarm(3, index, plantName, c);
            case "Shrub":
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * 5, alarmIntent);
                //saveSetAlarm(5, index, plantName, c);
            case"Perennial":
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * 2, alarmIntent);
                //saveSetAlarm(2, index, plantName, c);
        }
    }

    private void cancelAlarm(int index){
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmMgr = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        alarmIntent = PendingIntent.getBroadcast(context, index, intent, 0);

        alarmMgr.cancel(alarmIntent);
    }

    protected Dialog createdDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hr, min, false);
            case PLANT_DIALOG_ID:
                return PlantPicker();
            case OPTIONS_DIALOG_ID:
                return Options();
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minutes);
            c.set(Calendar.SECOND, 0);

            timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

            createdDialog(PLANT_DIALOG_ID).show();
        }
    };

    private Dialog Options(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options")
                .setItems(R.array.alarm_options_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case 0:
                                arrayList.remove(pos);

                                listView.setAdapter(arrayAdapter);

                                if(arrayList.size() < 5)
                                    addRemind.setEnabled(true);
                                else
                                    addRemind.setEnabled(false);

                                cancelAlarm(pos);
                        }
                    }
                });
        return builder.create();
    }

    private Dialog PlantPicker(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_plant_type)
                .setItems(R.array.plant_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Resources r = getResources();
                        TypedArray plants = r.obtainTypedArray(R.array.plant_array);
                        String plant = plants.getString(which);
                        arrayList.add(plant + "     " + timeText);

                        listView.setAdapter(arrayAdapter);

                        if(arrayList.size() < 5)
                            addRemind.setEnabled(true);
                        else
                            addRemind.setEnabled(false);

                        createAlarm(c, plant, arrayList.size()-1);
                    }
                });
        return builder.create();
    }

    /*private void writeDataToFileOutputStream(FileOutputStream fileOutputStream, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch(FileNotFoundException e) {
            Log.e("TAG_WRITE_READ_FILE", e.getMessage(), e);
        } catch(IOException e) {
            Log.e("TAG_WRITE_READ_FILE", e.getMessage(), e);
        }
    }*/

    /*private String readFromFileInputStream(FileInputStream fileInputStream) {
        StringBuffer retBuf = new StringBuffer();

        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String lineData = bufferedReader.readLine();
                while (lineData != null) {
                    retBuf.append(lineData);
                    lineData = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            Log.e("TAG_WRITE_READ_FILE", e.getMessage(), e);
        } finally {
            return retBuf.toString();
        }
    }*/

    /*private void saveSetAlarm(int i, int index, String plantName, Calendar c){
        try{
            FileOutputStream fileOutput = context.openFileOutput("AlarmsFile", Context.MODE_APPEND);

            writeDataToFileOutputStream(fileOutput, Integer.toString(index));
            writeDataToFileOutputStream(fileOutput, plantName);
            writeDataToFileOutputStream(fileOutput, c.getTime().toString());

            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY * i, alarmIntent);
        } catch(FileNotFoundException e){
            Log.e("TAG_WRITE_READ_FILE", e.getMessage(), e);
        }
    }*/
}
