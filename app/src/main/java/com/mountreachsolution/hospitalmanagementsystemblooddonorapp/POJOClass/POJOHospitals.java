package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.POJOClass;

import android.content.Intent;

public class POJOHospitals {

    String id,hospital_image,hospital_name,hospital_specialty,hours,contact_no,address,latitude,longitude;

    public POJOHospitals(String id, String hospital_image, String hospital_name, String hospital_specialty, String hours, String contact_no, String address, String latitude, String longitude) {
        this.id = id;
        this.hospital_image = hospital_image;
        this.hospital_name = hospital_name;
        this.hospital_specialty = hospital_specialty;
        this.hours = hours;
        this.contact_no = contact_no;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospital_image() {
        return hospital_image;
    }

    public void setHospital_image(String hospital_image) {
        this.hospital_image = hospital_image;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_specialty() {
        return hospital_specialty;
    }

    public void setHospital_specialty(String hospital_specialty) {
        this.hospital_specialty = hospital_specialty;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
