package com.pomden.apomden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pomden.apomden.Models.Bed;
import com.android.apomden.R;

import java.util.List;

public class BedRecyclerAdapter extends RecyclerView.Adapter<BedRecyclerAdapter.UserViewHolder> {

    private List<Bed> bedList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public  void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView name, status, sex, ward;



        public UserViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.bedName);
            status = itemView.findViewById(R.id.occupancy);
            sex = itemView.findViewById(R.id.gender);
            ward =  itemView.findViewById(R.id.wardBedIn);



        }
    }


    public BedRecyclerAdapter(List<Bed> bedList){
        this.bedList = bedList;

    }

    @NonNull
    @Override
    public BedRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.bed_card_item, parent, false);

       return new UserViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final BedRecyclerAdapter.UserViewHolder holder, int position) {

        Bed bed = bedList.get(position);
        holder.name.setText(bed.getName());
        holder.status.setText(bed.getStatus());
        holder.sex.setText(bed.getSex());
        holder.ward.setText(bed.getRoomName());

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


    public List<Bed> getBedList() {
        return bedList;
    }

    public void setBedList(List<Bed> bedList) {
        this.bedList = bedList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bedList.size();
    }

    public void filterList (List<Bed> filteredList){
        this.bedList = filteredList;
        notifyDataSetChanged();
    }
}
