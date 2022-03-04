package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.Hospitals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.Adapter.HospitalsAdapter;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.POJOClass.POJOHospitals;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.comman.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HospitalsActivity extends AppCompatActivity {

    ListView lv_hospitals;
    ProgressBar progress;
    TextView tv_no_records;
    List<POJOHospitals> list;
    HospitalsAdapter adapter;
    SearchView sv_hospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        setTitle("Hospitals");

        list = new ArrayList<>();
        lv_hospitals = findViewById(R.id.rv_hospitals);
        progress = findViewById(R.id.progress);
        tv_no_records = findViewById(R.id.tv_no_record);
        sv_hospitals = findViewById(R.id.sv_hospitals);

        sv_hospitals.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchHospitals(query);   //nikhil
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchHospitals(newText);   //nik
                return false;
            }
        });

        getAllHospitals();

    }

    private void searchHospitals(String query)
    {
        List<POJOHospitals> tempsearchlist = new ArrayList<>();
        tempsearchlist.clear();

        for (POJOHospitals p : list)
        {
         if (p.getHospital_name().toUpperCase().contains(query.toUpperCase()) ||
                 p.getContact_no().toUpperCase().contains(query.toUpperCase()))
         tempsearchlist.add(p);
        }

        adapter = new HospitalsAdapter(tempsearchlist,HospitalsActivity.this);
        lv_hospitals.setAdapter(adapter);
    }

    private void getAllHospitals() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.getAllHospitals,params,new JsonHttpResponseHandler() {
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
                            JSONArray jsonArray = response.getJSONArray("getHospitals");

                            for (int i = 0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String image = jsonObject.getString("images");
                                String name = jsonObject.getString("name");
                                String specialty = jsonObject.getString("specialty");
                                String hours = jsonObject.getString("hours");
                                String contact_no = jsonObject.getString("contact_no");
                                String address = jsonObject.getString("address");
                                String latitude = jsonObject.getString("latitude");
                                String longitude = jsonObject.getString("longitude");

                               list.add(new POJOHospitals(id,image,name,specialty,hours,contact_no,address,latitude,longitude));
                            }

                            adapter = new HospitalsAdapter(list,HospitalsActivity.this);
                            lv_hospitals.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progress.setVisibility(View.GONE);
                        Toast.makeText(HospitalsActivity.this, "Could not connect", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_hospitals,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_view_hospitals:
                startActivity(new Intent(HospitalsActivity.this,ViewHospitalsInMapActivity.class));
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}