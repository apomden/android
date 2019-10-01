package com.android.apomden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Models.Bed;
import com.android.apomden.Models.Department;
import com.android.apomden.Models.Room;
import com.android.apomden.Models.Service;
import com.android.apomden.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceRecyclerAdapter extends RecyclerView.Adapter<ServiceRecyclerAdapter.UserViewHolder> {

    private List<Service> objectList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public  void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView name, stat;



        public UserViewHolder( @NonNull View itemView ) {
            super(itemView);
            name = itemView.findViewById(R.id.serviceName);
            stat = itemView.findViewById(R.id.serviceDesc);


        }
    }


    public ServiceRecyclerAdapter(List<Service> objectList){
        this.objectList = objectList;

    }

    @NonNull
    @Override
    public ServiceRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.services_item, parent, false);

       return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ServiceRecyclerAdapter.UserViewHolder holder, int position) {


        Service object = objectList.get(position);
        holder.name.setText(object.getName());
        holder.stat.setText(object.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mListener != null){
                    int position = holder.getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }

            }
        });
    }


    public List<Service> getObjectList() {
        return objectList;
    }

    public void setBedList(List<Service> objectList) {
        this.objectList = objectList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void filterList (List<Service> filteredList){
        this.objectList = filteredList;
        notifyDataSetChanged();
    }

}
