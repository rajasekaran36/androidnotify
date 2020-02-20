package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_NAME = "MyChannel";
    public static final String CHANNEL_ID = "1001";
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b =(Button)findViewById(R.id.notifybutton);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                makeNotification();
            }
        });
    }

    public void makeNotification(){

        //Helps to build Notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setContentTitle("New Notification");
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        notificationBuilder.setContentText("Click to Launch Notification Activity");
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //Notification Channel
        NotificationChannel myChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);

        //Setting Channel to Builder
        notificationBuilder.setChannelId(CHANNEL_ID);


        //Manager Helps to display notification
        NotificationManager noficationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //Manager creates channel
        noficationManager.createNotificationChannel(myChannel);

        //Intent to be launched after clicking notification
        Intent notifyIntent = new Intent(this, NotifiedActivity.class);
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

        //Help to launch intent without permissions
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        //Intent connected to notification
        notificationBuilder.setContentIntent(pendingIntent);


        //Notification Created
        Notification notification = notificationBuilder.build();


        //Notify the Notification with help of manager
        noficationManager.notify(0,notification);
    }
}
