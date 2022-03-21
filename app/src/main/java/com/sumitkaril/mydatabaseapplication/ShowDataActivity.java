package com.sumitkaril.mydatabaseapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
    private ArrayList<StudentModel> studentArrayList;
    private DatabaseHelper db;
    private DataViewerAdapter studentAdapter;
    private RecyclerView studentRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        studentArrayList = new ArrayList<>();
        db = new DatabaseHelper(ShowDataActivity.this);
        studentArrayList = db.readData();
        studentRV = findViewById(R.id.rv_data_viewer);
        studentAdapter = new DataViewerAdapter(studentArrayList,ShowDataActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowDataActivity.this, RecyclerView.VERTICAL, false);
        studentRV.setLayoutManager(linearLayoutManager);
        studentRV.setAdapter(studentAdapter);
    }
}       