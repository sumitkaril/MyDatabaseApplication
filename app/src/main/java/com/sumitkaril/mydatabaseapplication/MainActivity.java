package com.sumitkaril.mydatabaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText nameET, phoneET, addressET, rowET;
    Button insertDataBTN, showDatabaseListBTN, deleteRowFromDatabaseBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        nameET = findViewById(R.id.name);
        phoneET = findViewById(R.id.phone);
        addressET = findViewById(R.id.address);
        rowET = findViewById(R.id.et_raw_id);
        deleteRowFromDatabaseBTN = findViewById(R.id.btn_delete_raw_from_database);
        insertDataBTN = findViewById(R.id.btn_insert_data);
        showDatabaseListBTN = findViewById(R.id.btn_data_list);
        insertDataBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean check = db.insertData(nameET.getText().toString(), Integer.valueOf(phoneET.getText().toString()), addressET.getText().toString());
               if (check ==true){
                   Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(MainActivity.this, "Data Failure", Toast.LENGTH_SHORT).show();
               }
            }
        });

        showDatabaseListBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
                startActivity(intent);
            }
        });

        deleteRowFromDatabaseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkStatus = db.deleteRawThroughID(rowET.getText().toString());
                if (checkStatus == 1){
                    Toast.makeText(MainActivity.this, "Raw Deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "An Error occurred!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}