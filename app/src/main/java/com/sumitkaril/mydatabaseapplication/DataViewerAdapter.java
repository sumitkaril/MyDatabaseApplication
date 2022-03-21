package com.sumitkaril.mydatabaseapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataViewerAdapter extends RecyclerView.Adapter<DataViewerAdapter.ViewHolder>{
    private ArrayList<StudentModel> studentArrayList;
    private Context context;

    public DataViewerAdapter(ArrayList<StudentModel> studentArrayList, Context context) {
        this.studentArrayList = studentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_viewer_layout,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModel model = studentArrayList.get(position);
        holder.studentId.setText(model.id.toString());
        holder.studentName.setText(model.name);
        holder.studentPhone.setText(model.phone.toString());
        holder.studentAddress.setText(model.address);
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView studentId, studentName, studentPhone, studentAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentId = itemView.findViewById(R.id.dv_id);
            studentName = itemView.findViewById(R.id.dv_name);
            studentPhone = itemView.findViewById(R.id.dv_phone);
            studentAddress = itemView.findViewById(R.id.dv_address);
        }
    }
}
