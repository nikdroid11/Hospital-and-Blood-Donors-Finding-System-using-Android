package com.mountreachsolution.hospitalmanagementsystemblooddonorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.comman.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyProfileActivity extends AppCompatActivity {

    ImageView img_profile,img_qr_code;
    TextView tv_name,tv_mobile_no,tv_email,tv_username,tv_no_record;
    ProgressBar progress;

    String username;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        setTitle("My Profile");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        img_profile = findViewById(R.id.img_my_profile);
        tv_name = findViewById(R.id.txt_my_details_user_name);
        tv_mobile_no = findViewById(R.id.txt_my_details_user_mobile_no);
        tv_email = findViewById(R.id.txt_my_details_email);
        tv_username = findViewById(R.id.txt_my_details_username);
        tv_no_record = findViewById(R.id.tv_no_record);

        progress = findViewById(R.id.progress);
        username = preferences.getString("username","");

        getMyDetails();

    }

    private void getMyDetails() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",username);
        client.post(Config.getMyData,params,new JsonHttpResponseHandler(){

            @Override
            public void onStart() {
                super.onStart();
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progress.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = response.getJSONArray("getMyDetails");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id1 = jsonObject.getString("id");
                        String profile_img1 = jsonObject.getString("profile_img");
                        String name1 = jsonObject.getString("name");
                        String mobile_no1 = jsonObject.getString("mobile_no");
                        String email1 = jsonObject.getString("email");
                        String username1 = jsonObject.getString("username");

                        Glide.with(MyProfileActivity.this).load(Config.onlineImageAddress+""+profile_img1).
                                error(R.drawable.error_icon).into(img_profile);
                        tv_name.setText(name1);
                        tv_mobile_no.setText(mobile_no1);
                        tv_email.setText(email1);
                        tv_username.setText(username1);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                tv_no_record.setText("No Records");
                Toast.makeText(MyProfileActivity.this, "Could not connect", Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}