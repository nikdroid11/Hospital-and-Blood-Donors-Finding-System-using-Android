package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.Hospitals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.Adapter.HospitalsAdapter;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.LoginActivity;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.POJOClass.POJOHospitals;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.comman.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;



public class ViewHospitalsInMapActivity extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    //get the current location and manage the location we used location Manager
    LocationManager locationManager;
    //this variable go to the manifest.xml file and check whether user allow the
    // app to used the location feature or not
    public static final int REQUEST_LOCATION_PERMISSION = 1;
    double latitude,longitude;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hospitals_in_map);

        setTitle("Current Location");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }
        else if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }

        //check the network provider is enable or not
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            LatLng latLng = new LatLng(latitude,longitude);

                            Geocoder geocoder = new Geocoder(getApplicationContext());

                            try {
                                List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
                                address = addressList.get(0).getAddressLine(0)+" "+addressList.get(0).getLocality()+" "+addressList.get(0).getCountryName();

                                mMap.addMarker(new MarkerOptions().position(latLng).title(address));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16),5000,null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        //check the GPS provider is enable or not
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            LatLng latLng = new LatLng(latitude,longitude);

                            Geocoder geocoder = new Geocoder(getApplicationContext());

                            try {
                                List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
                                address = addressList.get(0).getAddressLine(0)+" "+addressList.get(0).getLocality()+" "+addressList.get(0).getCountryName();

                                mMap.addMarker(new MarkerOptions().position(latLng).title(address));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16),5000,null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
    }
}