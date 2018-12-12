package com.example.dialg.projectlayouts;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String Channel1ID = "Channel1ID";
    public static final String Channel1Name = "Channel 1";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels(){
        NotificationChannel channel1 = new NotificationChannel(Channel1ID, Channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableVibration(true);
        channel1.setDescription("Alarm has been activated!");

        getManager().createNotificationChannel(channel1);
    }

    public NotificationManager getManager() {
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(){
        return new NotificationCompat.Builder(getApplicationContext(), Channel1ID)
                .setSmallIcon(R.drawable.ic_launcher_background); // change with actual icon
    }
}
