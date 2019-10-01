package com.android.apomden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Models.Transfer;
import com.android.apomden.R;

import java.util.List;

public class InComingTransferRecyclerAdapter extends RecyclerView.Adapter<InComingTransferRecyclerAdapter.UserViewHolder> {

    private List<Transfer> transferList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public  void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView patientName, originClinic, originDepartment, referringStaff,
                referringStaffEmail, destinationFacility, destinationDepartment;




        public UserViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientName);
            originClinic = itemView.findViewById(R.id.originClinic);
            originDepartment = itemView.findViewById(R.id.originDepartment);
            referringStaff = itemView.findViewById(R.id.referringStaff);
            referringStaffEmail = itemView.findViewById(R.id.referringStaffEmail);
            destinationFacility = itemView.findViewById(R.id.destinationFacility);
            destinationDepartment = itemView.findViewById(R.id.destinationDepartment);

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


    public InComingTransferRecyclerAdapter(List<Transfer> transferList){
        this.transferList = transferList;
    }

    @NonNull
    @Override
    public InComingTransferRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.incoming_transfer_item, parent, false);

       return new UserViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InComingTransferRecyclerAdapter.UserViewHolder holder, int position) {
        Transfer object = transferList.get(position);
        holder.patientName.setText("Name: " + object.getName());
        holder.originClinic.setText("Origin Facility: " + object.getOriginFacility().getName());
        holder.originDepartment.setText("Origin Department: " + object.getOriginDepartment().getName());
        holder.referringStaff.setText( "Referring Staff: " + object.getReferringStaff());
        holder.referringStaffEmail.setText("Ref Staff Email: " + object.getReferringStaffEmail());
        holder.destinationFacility.setText("Destination Facility: " + object.getDestinationFacility().getName());
        holder.destinationDepartment.setText("Destination Department: " + object.getDestinationDepertment().getName());

    }

    @Override
    public int getItemCount() {
        return transferList.size();
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
    }
}
