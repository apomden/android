package com.android.apomden.Adapters;

import android.util.Log;
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

public class DepartmentRecyclerAdapter extends RecyclerView.Adapter<DepartmentRecyclerAdapter.UserViewHolder> {

    private List<Department> objectList;

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


    public DepartmentRecyclerAdapter(List<Department> objectList){
        this.objectList = objectList;

    }

    @NonNull
    @Override
    public DepartmentRecyclerAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.department_item, parent, false);

       return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DepartmentRecyclerAdapter.UserViewHolder holder, int position) {

        Department department = objectList.get(position);
        holder.name.setText(department.getName());

        // if no rooms check
        if (!(department.getRoomArrayList().size() == 0)){

            List<String> result = prepareStatForDepartment(department);
            holder.stat.setText(result.get(0));

            // if no beds check
            holder.available.setText("Occupied: " + result.get(1) + " Available: " + result.get(2));
            holder.gender.setText("Male: " + result.get(3) + " Female: " + result.get(4) + " Uni: " + result.get(5));

        } else {

            holder.stat.setText("0");
            holder.available.setText("Occupied: 0");
            holder.gender.setText("Male: 0  Female: 0 Uni: 0");

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


    public List<Department> getObjectList() {
        return objectList;
    }

    public void setBedList(List<Department> departmentList) {
        this.objectList = departmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void filterList (List<Department> filteredList){
        this.objectList = filteredList;
        notifyDataSetChanged();
    }

    private List<String> prepareStatForDepartment (Department department) {

        List<String> result = new ArrayList<>();


        String resultString = "";

        List<Room> roomList = department.getRoomArrayList();
        List<Bed> bedList = roomList.get(0).getBedArrayList();
        List<Bed> deptBeds = new ArrayList<>();
        List<Bed> occupiedBeds = new ArrayList<>();
        List<Bed> unoccupiedBeds = new ArrayList<>();
        List<Bed> girlsBed = new ArrayList<>();
        List<Bed> boysBed  = new ArrayList<>();
        List<Bed> unisexBed = new ArrayList<>();


        for (int i = 0; i < bedList.size(); i++) {
            if (bedList.get(i).getRoomName().equalsIgnoreCase(department.getName())) {
                deptBeds.add(bedList.get(i));
            }
        }

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
