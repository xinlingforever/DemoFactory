package com.xx.demoproject.demofactory.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import com.xx.demoproject.demofactory.R;

public class HandsupNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handsup_notification);

        //set intents and pending intents to call service on click of "dismiss" action button of notification
        Intent rainbowIntent = new Intent(this, RainbowBarActivity.class);
        PendingIntent piRainbow = PendingIntent.getActivity(this, 0, rainbowIntent, 0);

        //set intents and pending intents to call service on click of "snooze" action button of notification
        Intent gameIntent = new Intent(this, SlotGameActivity.class);
        PendingIntent piGame = PendingIntent.getActivity(this, 0, gameIntent, 0);

        //build a hands-up notification
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.image_0)
                        .setContentTitle("Ping Notification")
                        .setContentText("Tomorrow will be your birthday.")
                        .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                        .setPriority(NotificationCompat.PRIORITY_HIGH); //must give priority to High, Max which will considered as heads-up notification
//                        .addAction(R.drawable.ic_phone_in_talk_black_24dp, "Rainbow", piRainbow)
//                        .addAction(R.drawable.ic_alarm_on_black_24dp, "Game", piGame);

        //build a BigPictureStyle notification
        Notification notif = new Notification.Builder(getApplicationContext())
                .setContentTitle("New photo from " + "me")
                .setContentText("A Picture")
                .setSmallIcon(R.drawable.ic_alarm_on_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.image_1))
                .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.image_3))).build();


        // Gets an instance of the NotificationManager service
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //to post your notification to the notification bar with a id. If a notification with same id already exists, it will get replaced with updated information.
        notificationManager.notify(0, notif);
    }
}
