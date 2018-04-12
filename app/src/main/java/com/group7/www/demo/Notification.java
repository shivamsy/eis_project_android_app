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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationManager = (NotificationManager) myContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel fire = new NotificationChannel("fire", "important", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(fire);
            NotificationChannel gas_leak = new NotificationChannel("gas_leak", "important", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(gas_leak);
            NotificationChannel theft = new NotificationChannel("theft", "important", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(theft);
        }

        //if (alert.equals("fire")) {
//            builder = new NotificationCompat.Builder(myContext, "fire")
//                    .setContentText("Your warehouse is in danger!!!.")
//                    .setContentTitle("FIRE!!!")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setContentIntent(pendingIntent);
//        //}
//        //if (alert.equals("gas_laek")) {
//            builder = new NotificationCompat.Builder(myContext, "gas_leak")
//                    .setContentText("Your warehouse is in danger!!!.")
//                    .setContentTitle("GAS LEAK!!!")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setContentIntent(pendingIntent);
//        //}
//        //if (alert.equals("theft")) {
//            builder = new NotificationCompat.Builder(myContext, "theft")
//                    .setContentText("Your warehouse is in danger!!!.")
//                    .setContentTitle("THEFT!!!")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setContentIntent(pendingIntent);
        //}
    }

    public void notify1(String alert) {

        if (alert.equals("fire")) {
            builder = new NotificationCompat.Builder(myContext, "fire")
                    .setContentText("Your warehouse is in danger!!!.")
                    .setContentTitle("FIRE!!!")
                    .setSmallIcon(R.drawable.ic_security)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            notificationManager.notify(0, builder.build());
        }
        if (alert.equals("gas_leak")) {
            builder = new NotificationCompat.Builder(myContext, "gas_leak")
                    .setContentText("Your warehouse is in danger!!!.")
                    .setContentTitle("GAS LEAK!!!")
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            notificationManager.notify(1, builder.build());
        }
        if (alert.equals("theft")) {
            builder = new NotificationCompat.Builder(myContext, "theft")
                    .setContentText("Your warehouse is in danger!!!.")
                    .setContentTitle("THEFT!!!")
                    .setSmallIcon(R.drawable.ic_theft)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            notificationManager.notify(2, builder.build());
        }

      // notificationManager.notify(0, builder.build());
    }
}
