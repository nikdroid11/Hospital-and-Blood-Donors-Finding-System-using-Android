package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.DonateHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;

public class DonateHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_history);

        setTitle("Donate History");

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myNotification")
//                .setContentTitle("This is my Title")
//                .setSmallIcon(R.drawable.blood_request_icon)
//                .setAutoCancel(true)
//                .setContentText("This is my Text");
//
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//        managerCompat.notify(999,builder.build());
    }

}