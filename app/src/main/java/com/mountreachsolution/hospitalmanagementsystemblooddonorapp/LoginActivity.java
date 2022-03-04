package com.mountreachsolution.hospitalmanagementsystemblooddonorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.comman.Config;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    boolean doubletap = false;
    ImageView img_logo;
    EditText et_username;
    TextInputEditText et_password;
    Button btn_login,btn_register;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (preferences.getBoolean("isLogin",false))
        {
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        et_username = findViewById(R.id.edt_username);
        et_password = findViewById(R.id.edt_password);
        img_logo = findViewById(R.id.img_logo);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        Glide.with(this).load(R.raw.logo1).into(img_logo);

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(et_password.getText().toString().length()<4)
                {
                    Drawable imageView =   getDrawable(R.drawable.visibility_on);
                    imageView.setVisible(true,true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().isEmpty())
                {
                    et_username.setError("Please Enter Username");
                }
                else if (et_username.getText().toString().length()<8)
                {
                    et_username.setError("Username lenght much be greater then 8");
                }
                else if (et_password.getText().toString().isEmpty())
                {
                    et_password.setError("Please Enter Password");
                }
                else if (et_password.getText().toString().length()<8)
                {
                    et_password.setError("Password lenght much be greater then 8");
                }
                else
                {
                    checkLoginUser();
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View v){
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
    });
}

    private void checkLoginUser() {
        AsyncHttpClient client = new AsyncHttpClient();  //Manage all request
        RequestParams params = new RequestParams(); // get and put the params

        params.put("username",et_username.getText().toString());
        params.put("password",et_password.getText().toString());

        client.post(Config.checkUser,params,new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String success1 = response.getString("success");

                            if (success1.equals("1"))
                            {
//                                save data in editor using sharedpreferences
                                editor.putBoolean("isLogin",true).commit();
                                editor.putString("username",et_username.getText().toString()).commit();
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Please Enter Correct Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(LoginActivity.this, "Could not connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }


    @Override
    public void onBackPressed() {
        if (doubletap)
        {
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            },2000);
        }
    }
}