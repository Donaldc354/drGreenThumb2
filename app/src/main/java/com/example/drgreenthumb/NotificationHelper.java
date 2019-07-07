package com.example.drgreenthumb;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String CHANNEL_ID0 = "com.example.drgreenthumb.WATERING_REMINDER0";
    public static final String CHANNEL_ID1 = "com.example.drgreenthumb.WATERING_REMINDER1";
    public static final String CHANNEL_ID2 = "com.example.drgreenthumb.WATERING_REMINDER2";
    public static final String CHANNEL_ID3 = "com.example.drgreenthumb.WATERING_REMINDER3";
    public static final String CHANNEL_ID4 = "com.example.drgreenthumb.WATERING_REMINDER4";
    public static final String CHANNEL_NAME = "Watering Reminder";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    public void createChannels(){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel0 = new NotificationChannel(CHANNEL_ID0, CHANNEL_NAME + " 1", importance);
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID1, CHANNEL_NAME + " 2", importance);
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID2, CHANNEL_NAME + " 3", importance);
            NotificationChannel channel3 = new NotificationChannel(CHANNEL_ID3, CHANNEL_NAME + " 4", importance);
            NotificationChannel channel4 = new NotificationChannel(CHANNEL_ID4, CHANNEL_NAME + " 5", importance);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            getManager().createNotificationChannel(channel0);
            getManager().createNotificationChannel(channel1);
            getManager().createNotificationChannel(channel2);
            getManager().createNotificationChannel(channel3);
            getManager().createNotificationChannel(channel4);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public NotificationManager getManager(){
        if(mManager == null){
            mManager = getSystemService(NotificationManager.class);
        }
        return mManager;
    }

    public NotificationCompat.Builder getNotification(String title, String message, String channelID){
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.leaf_notification)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
