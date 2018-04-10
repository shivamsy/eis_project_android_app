package com.group7.www.demo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by SHIVAM on 10-04-2018.
 */

public class Notification {

    private Context myContext;
    private Intent intent;
    private PendingIntent pendingIntent;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;


    public Notification(Context context) {

        myContext = context;
        intent = new Intent(myContext, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(myContext, 0, intent,  0);
        notificationManager = (NotificationManager) myContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel fire = new NotificationChannel("default", "important", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(fire);
            NotificationChannel gas_leak = new NotificationChannel("default", "important", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(gas_leak);
            NotificationChannel theft = new NotificationChannel("default", "important", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(theft);
        }

        builder = new NotificationCompat.Builder(myContext, "default")
                .setContentText("Your warehouse is in danger!!!.")
                .setContentTitle("hello")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent);
    }

    public void notify1() {

       notificationManager.notify(0, builder.build());
    }
}
