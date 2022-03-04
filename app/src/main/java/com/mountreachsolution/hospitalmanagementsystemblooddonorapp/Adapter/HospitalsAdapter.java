package com.mountreachsolution.hospitalmanagementsystemblooddonorapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.POJOClass.POJOHospitals;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.R;
import com.mountreachsolution.hospitalmanagementsystemblooddonorapp.comman.Config;

import java.util.List;

public class HospitalsAdapter extends BaseAdapter {

    List<POJOHospitals> list;
    Activity activity;

    public HospitalsAdapter(List<POJOHospitals> list1,Activity activity1)
    {
        this.list = list1;
        this.activity = activity1;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final HospitalsAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null)
        {
            holder = new HospitalsAdapter.ViewHolder();
            view = inflater.inflate(R.layout.lv_hospitals_list,null);

            holder.img_hospital = view.findViewById(R.id.img_lv_hospital_image);
            holder.tv_hospital_name = view.findViewById(R.id.tv_lv_hospital_name);
            holder.tv_hospital_specialty = view.findViewById(R.id.tv_lv_hospital_specialty);
            holder.tv_hospital_hour = view.findViewById(R.id.tv_lv_hospital_hours);
            holder.tv_hospital_contact_no = view.findViewById(R.id.tv_lv_hospital_contact_no);
            holder.tv_hospital_address = view.findViewById(R.id.tv_lv_hospital_address);
            view.setTag(holder);
        }
        else
        {
            holder = (HospitalsAdapter.ViewHolder) view.getTag();
        }

        final POJOHospitals obj = list.get(position);
        holder.tv_hospital_name.setText(obj.getHospital_name());
        holder.tv_hospital_specialty.setText(obj.getHospital_specialty());
        holder.tv_hospital_hour.setText(obj.getHours());
        holder.tv_hospital_contact_no.setText(obj.getContact_no());
        holder.tv_hospital_address.setText(obj.getAddress());

        Glide.with(activity).load(Config.onlineImageAddress+""+obj.getHospital_image()).
                error(R.drawable.error_icon).into(holder.img_hospital);

        return view;
    }

    class ViewHolder
    {
        ImageView img_hospital;
        TextView tv_hospital_name,tv_hospital_specialty,tv_hospital_hour,tv_hospital_contact_no,tv_hospital_address;

    }
}
