package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.BloodBanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;

import java.io.IOException;

public class BloodBanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_banks);

        setTitle("Blood Banks");

    }
}