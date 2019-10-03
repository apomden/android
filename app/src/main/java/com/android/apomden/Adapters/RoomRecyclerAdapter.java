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
import com.android.apomden.R;

import java.util.ArrayList;
import java.util.List;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.UserViewHolder> {

    private List<Room> objectList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public  void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView name, stat, available, gender;



        public UserViewHolder( @NonNull View itemView ) {
            super(itemView);
            name = itemView.findViewById(R.id.departmentName);
            stat = itemView.findViewById(R.id.departmentStat);
            available = itemView.findViewById(R.id.availableBeds);
            gender = itemView.findViewById(R.id.maleAndFemaleBeds);

        }
    }


    public RoomRecyclerAdapter(List<Room> objectList){
        this.objectList = objectList;

    }

    @NonNull
    @Override
    public RoomRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.department_item, parent, false);

       return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RoomRecyclerAdapter.UserViewHolder holder, int position) {

        Room department = objectList.get(position);
        holder.name.setText(department.getId());
        holder.gender.setText(department.getSex());

        // if no rooms check
        if (!(department.getBedArrayList().size() == 0)){

            List<String> result = prepareStatForDepartment(department);
            holder.stat.setText(result.get(0));
            // if no beds check
            holder.available.setText("Occupied: " + result.get(1) + " Available: " + result.get(2));


        } else {

            holder.stat.setText("0");
            holder.available.setText("Occupied: 0");
        }

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


    public List<Room> getObjectList() {
        return objectList;
    }

    public void setRoomList(List<Room> departmentList) {
        this.objectList = departmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void filterList (List<Room> filteredList){
        this.objectList = filteredList;
        notifyDataSetChanged();
    }

    private List<String> prepareStatForDepartment (Room roomList) {

        List<String> result = new ArrayList<>();


        String resultString = "";

        List<Bed> deptBeds = roomList.getBedArrayList();
        List<Bed> occupiedBeds = new ArrayList<>();
        List<Bed> unoccupiedBeds = new ArrayList<>();
        List<Bed> girlsBed = new ArrayList<>();
        List<Bed> boysBed  = new ArrayList<>();
        List<Bed> unisexBed = new ArrayList<>();



        // check for occupied

        for (int i = 0; i < deptBeds.size(); i++) {
            if (deptBeds.get(i).getStatus().equalsIgnoreCase("OCCUPIED")) {
                occupiedBeds.add(deptBeds.get(i));
            } else {
                unoccupiedBeds.add(deptBeds.get(i));
            }
        }


        // male and female birds
        for (int i = 0; i < deptBeds.size(); i++) {
            if (deptBeds.get(i).getSex().equalsIgnoreCase("MALE")) {
                boysBed.add(deptBeds.get(i));
            } else if (deptBeds.get(i).getSex().equalsIgnoreCase("FEMALE"))  {
                girlsBed.add(deptBeds.get(i));
            } else {
                unisexBed.add(deptBeds.get(i));
            }
        }

//
//        Log.e("==BOYS===", String.valueOf(boysBed.size()));
//        Log.e("==GIRLS===", String.valueOf(girlsBed.size()));

        result.add(String.valueOf(deptBeds.size()));
        result.add(String.valueOf(occupiedBeds.size()));
        result.add(String.valueOf(unoccupiedBeds.size()));
        result.add(String.valueOf(girlsBed.size()));
        result.add(String.valueOf(boysBed.size()));
        result.add(String.valueOf(unisexBed.size()));


        return result;

    }
}
