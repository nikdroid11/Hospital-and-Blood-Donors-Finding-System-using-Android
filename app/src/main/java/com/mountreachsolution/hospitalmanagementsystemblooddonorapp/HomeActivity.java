package com.mountreachsolution.hospitalmanagementsystemblooddonorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.BloodBanks.BloodBanksActivity;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.DonateBlood.DonateBloodActivity;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.DonateHistory.DonateHistoryActivity;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.Hospitals.HospitalsActivity;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.MyQRCode.MyQRCodeActivity;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.RequestForBlood.RequestForBloodActivity;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    CardView cardView11,cardView22,cardView33,cardView44,cardView55,cardView66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        setTitle("User Dashboard");

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("MyNotification","MyNotification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("health")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfully Sent";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        cardView11 = findViewById(R.id.cardview11);
        cardView22 = findViewById(R.id.cardview22);
        cardView33 = findViewById(R.id.cardview33);
        cardView44 = findViewById(R.id.cardview44);
        cardView55 = findViewById(R.id.cardview55);
        cardView66 = findViewById(R.id.cardview66);

        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DonateBloodActivity.class));

            }
        });

        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, RequestForBloodActivity.class));
            }
        });

        cardView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, HospitalsActivity.class));
            }
        });

        cardView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BloodBanksActivity.class));
            }
        });

        cardView55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DonateHistoryActivity.class));
            }
        });

        cardView66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.home_menu_qr_code:
                startActivity(new Intent(HomeActivity.this, MyQRCodeActivity.class));
                return true;
            case R.id.home_menu_my_profile:
                startActivity(new Intent(HomeActivity.this,MyProfileActivity.class));
                return  true;

            case R.id.home_menu_logout:
                logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("Logout");
        ad.setMessage("You Sure Want to Logout?");
        ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                editor.putBoolean("isLogin",false).commit();
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = ad.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}