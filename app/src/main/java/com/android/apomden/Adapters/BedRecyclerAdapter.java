package com.android.apomden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Models.Bed;
import com.android.apomden.Models.Dashboard;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });

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
    public void onBindViewHolder(@NonNull BedRecyclerAdapter.UserViewHolder holder, int position) {
        Bed bed = bedList.get(position);
        holder.name.setText(bed.getName());
        holder.status.setText(bed.getStatus());
        holder.sex.setText(bed.getSex());
        holder.ward.setText(bed.getRoomName());

    }

    @Override
    public int getItemCount() {
        return bedList.size();
    }

    public void filterList (List<Bed> filterdList){
        bedList = filterdList;
        notifyDataSetChanged();
    }
}
