package com.android.apomden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Models.Bed;
import com.android.apomden.Models.Transfer;
import com.android.apomden.R;

import java.util.List;

public class TransferRecyclerAdapter extends RecyclerView.Adapter<TransferRecyclerAdapter.UserViewHolder> {

    private List<Transfer> transferList;


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView patientName, originClinic, originDepartment, referringStaff,
                referringStaffEmail, destinationFacility, destinationDepartment;




        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientName);
            originClinic = itemView.findViewById(R.id.originClinic);
            originDepartment = itemView.findViewById(R.id.originDepartment);
            referringStaff = itemView.findViewById(R.id.referringStaff);
            referringStaffEmail = itemView.findViewById(R.id.referringStaffEmail);
            destinationFacility = itemView.findViewById(R.id.destinationFacility);
            destinationDepartment = itemView.findViewById(R.id.destinationDepartment);
        }
    }


    public TransferRecyclerAdapter(List<Transfer> transferList){
        this.transferList = transferList;
    }

    @NonNull
    @Override
    public TransferRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.outgoing_transfer_item, parent, false);

       return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferRecyclerAdapter.UserViewHolder holder, int position) {
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
}
