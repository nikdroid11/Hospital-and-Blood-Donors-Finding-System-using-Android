package com.mountreachsolution.hospitalmanagementsystemblooddonorapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.BloodBanks.BloodBanksActivity;

public class MyMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
    showNotification(remoteMessage.getNotification().getTitle() ,remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title,String Message)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotification")
                .setContentTitle(Message)
                .setContentText(Message)
                .setSmallIcon(R.drawable.notification_icon)
                .setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());

    }
}
