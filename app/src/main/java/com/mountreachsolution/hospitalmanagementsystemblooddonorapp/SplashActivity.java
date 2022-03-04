package com.mountreachsolution.hospitalmanagementsystemblooddonorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    ImageView img_logo;
    TextView tv_title;
    Animation fadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img_logo = findViewById(R.id.img_logo);
        tv_title = findViewById(R.id.tv_title);
        fadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        img_logo.startAnimation(fadein);

        Glide.with(this).load(R.raw.logo2).into(img_logo);

        new Thread()
        {
            public  void run()
            {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }.start();
    }
}