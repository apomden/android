package com.pomden.apomden.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.apomden.R;
import com.pomden.apomden.Models.Facility;

import java.util.List;

public class FacilityAdapter2 extends ArrayAdapter<Facility> {

    private static class ViewHolder {

        TextView name, addresse;

    }

    public FacilityAdapter2(Context context, int resource, List<Facility> objects) {
        super(context, R.layout.facility_item, objects);
    }

    @Override
    public Facility getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Facility facility = getItem(position);

        ViewHolder viewHolder;


        if (convertView == null){

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.services_item, parent, false);
            viewHolder.name     = convertView.findViewById(R.id.serviceName);
            viewHolder.addresse = convertView.findViewById(R.id.serviceDesc);


            convertView.setTag(viewHolder);


        } else {

            viewHolder = (ViewHolder)convertView.getTag();

        }

        viewHolder.name.setText(facility.getFacilityName());
        viewHolder.addresse.setText(facility.getFacilityAddress());


        return convertView;
    }
}
