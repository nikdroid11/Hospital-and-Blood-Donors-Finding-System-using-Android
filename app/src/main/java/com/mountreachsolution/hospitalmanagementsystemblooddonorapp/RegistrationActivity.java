package com.mountreachsolution.hospitalmanagementsystemblooddonorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.comman.Config;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {

    EditText edt_name,edt_email,edt_mobile_no,edt_username,edt_retype_password;
    TextInputEditText edt_password;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btn_sign_up;
    Spinner spinner_blood_group;
    ArrayAdapter<CharSequence> adapter;
    ProgressBar progress;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        progress = findViewById(R.id.progress);

        radioGroup = findViewById(R.id.radiogroup);
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_mobile_no = findViewById(R.id.edt_mobile_no);
        edt_username = findViewById(R.id.edt_username);
        edt_retype_password = findViewById(R.id.edt_retype_password);
        edt_password = findViewById(R.id.edt_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        spinner_blood_group = findViewById(R.id.spinner_blood_group);

        adapter = ArrayAdapter.createFromResource(this,R.array.blood_group, android.R.layout.simple_spinner_dropdown_item);
        spinner_blood_group.setAdapter(adapter);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkbutton = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(checkbutton);
                if(edt_name.getText().toString().isEmpty())
                {
                    edt_name.setError("Please Enter Your Name");
                }
                else if(edt_mobile_no.getText().toString().isEmpty())
                {
                    edt_mobile_no.setError("Please Enter Your Mobile Number");
                }
                else if(edt_mobile_no.getText().toString().length()!=10)
                {
                    edt_mobile_no.setError("Mobile Number must be 10 digit");
                }
                else if (edt_email.getText().toString().isEmpty())
                {
                    edt_email.setError("Please Enter Your Mail Id");
                }
                else if(!edt_email.getText().toString().contains("@") ||!edt_email.getText().toString().contains(".com") )
                {
                    edt_email.setError("Please Enter Valid Email-Id");
                }
                else if (edt_username.getText().toString().isEmpty())
                {
                    edt_username.setError("Please Enter Your Username");
                }
                else if(edt_username.getText().toString().length()<=7)
                {
                    edt_username.setError("Username must be 8 digit");
                }
                else if (edt_password.getText().toString().isEmpty())
                {
                    edt_password.setError("Enter Strong Password");
                }
                else if(edt_password.getText().toString().length()<=7)
                {
                    edt_password.setError("Password must be 10 digit");
                }
                else if (!edt_password.getText().toString().equals(edt_retype_password.getText().toString()))
                {
                    edt_retype_password.setError("Password is Not Match");
                }
                else
                {
                    addRegisterUser();
                }
            }
        });
    }

    private void addRegisterUser() {
        AsyncHttpClient client=new AsyncHttpClient(); //mananging request
        RequestParams params=new RequestParams(); //get and put the params

        params.put("name",edt_name.getText().toString());
        params.put("gender",radioButton.getText().toString());
        params.put("mobile_no",edt_mobile_no.getText().toString());
        params.put("email_id",edt_email.getText().toString());
        params.put("blood_group",spinner_blood_group.getSelectedItem().toString());
        params.put("username",edt_username.getText().toString());
        params.put("password",edt_password.getText().toString());

        client.post(Config.addUser,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String success=response.getString("success");

                    if(success.equals("1"))
                    {
                        Toast.makeText(RegistrationActivity.this,"Registration Successfully",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(RegistrationActivity.this,"could not connect",Toast.LENGTH_SHORT).show();
            }
        });


    }

//    private void addRegisterUser() {
//        AsyncHttpClient client = new AsyncHttpClient(); // managing the request
//        RequestParams params = new RequestParams();
//
//        params.put("name",edt_name.getText().toString());
//        params.put("mobile_no",edt_mobile_no.getText().toString());
//        params.put("email_id",edt_email.getText().toString());
//        params.put("username",edt_username.getText().toString());
//        params.put("password",edt_password.getText().toString());
//
//        client.post(Config.addUser, params, new JsonHttpResponseHandler()
//                {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//                        try {
//                            String success = response.getString("success");
//
//                            if (success.equals("1"))
//                            {
//                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
//                                finish();
//                            }
//                            else
//                            {
//                                Toast.makeText(RegistrationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        super.onFailure(statusCode, headers, throwable, errorResponse);
//                        Toast.makeText(RegistrationActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}