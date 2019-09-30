package com.android.apomden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.apomden.Models.Dashboard;
import com.android.apomden.R;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.UserViewHolder> {

    private List<Dashboard> userList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public  void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText, numberOfItems;
        public LinearLayout linearLayout;
        public ImageView imageView;



        public UserViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            numberOfItems = itemView.findViewById(R.id.numberOfBed);
            linearLayout = itemView.findViewById(R.id.linLayout);
            imageView = itemView.findViewById(R.id.imageView);

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


    public SummaryAdapter(List<Dashboard> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public SummaryAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.summary_card_item, parent, false);

       return new UserViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryAdapter.UserViewHolder holder, int position) {
        Dashboard user = userList.get(position);
        holder.titleText.setText(user.getTitle());
        holder.numberOfItems.setText(user.getNumber());
        holder.imageView.setImageDrawable(user.getIcon());
        holder.linearLayout.setBackground(user.getBg());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
