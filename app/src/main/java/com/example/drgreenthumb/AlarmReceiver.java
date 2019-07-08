package com.example.drgreenthumb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String plantName = bundle.getString("PLANT_NAME");
        String message = "Your " + plantName + " might be a little thirsty!";
        String val = bundle.getString("CHANNEL_NUM");
        String channelID = "com.example.drgreenthumb.WATERING_REMINDER" + val;
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getNotification("Watering Reminder", message, channelID);
        notificationHelper.getManager().notify(Integer.parseInt(val), nb.build());
    }
}
