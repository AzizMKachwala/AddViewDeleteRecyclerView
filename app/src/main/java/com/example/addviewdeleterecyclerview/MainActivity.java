package com.example.addviewdeleterecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etvName, etvRollNo;
    Button btnAddRecords;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<DataModel> dataModels;
    TextView txtNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etvName = findViewById(R.id.etvName);
        etvRollNo = findViewById(R.id.etvRollNo);
        btnAddRecords = findViewById(R.id.btnAddRecord);
        recyclerView = findViewById(R.id.recView);
        txtNoData = findViewById(R.id.txtNoData);
        hideShow();

        dataModels = new ArrayList<>();
        recyclerView.setAdapter(recyclerViewAdapter);

        btnAddRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etvName.getText().toString().trim().equalsIgnoreCase("")) {
                    etvName.setError("please enter name");
                    etvName.requestFocus();
                } else {
                    if (dataModels != null) {
                        dataModels.add(new DataModel(etvName.getText().toString().trim()));
                        if (recyclerViewAdapter == null) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, dataModels);
                            recyclerView.setAdapter(recyclerViewAdapter);

                          recyclerViewAdapter.setUpInterface(new RecyclerViewAdapter.DataClick() {
                              @Override
                              public void ItemClick(DataModel model, int pos) {
                                  if (dataModels!=null);
                                  dataModels.remove(pos);
                                  recyclerViewAdapter.DataUpdate(dataModels);
                              }
                          });
                        } else {
                            recyclerViewAdapter.DataUpdate(dataModels);
                        }
                        hideShow();
                    }
                }
            }
        });
    }

    private void hideShow() {
        if (dataModels != null && dataModels.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.GONE);
        }
    }

}