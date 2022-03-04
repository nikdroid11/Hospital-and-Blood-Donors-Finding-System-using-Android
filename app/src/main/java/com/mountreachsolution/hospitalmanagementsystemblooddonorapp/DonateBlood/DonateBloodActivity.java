package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.DonateBlood;


import android.app.Activity;
import android.os.Bundle;

import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;
public class DonateBloodActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);

        setTitle("Donate Blood");

    }
}