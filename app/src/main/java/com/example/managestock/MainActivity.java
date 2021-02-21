package com.example.managestock;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.*;
import java.util.*;


public class MainActivity extends AppCompatActivity {
    public static List<Product> allProducts;
    public static List<OrderContent> history;
    public static List<String> categories;
    public static OrderContent currentOrder;
    public static String selectedCategory = "";
    public static ArrayAdapter<String> spinnerAdapter;
    public static String externalPath;
    FileTools fileTools = FileTools.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checking storage permission
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestStoragePermission();
        }

        // Create folder
        String folder_main = "ManageStock";
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }

        // Setting external storage path
        externalPath = Environment.getExternalStorageDirectory() + "/" + folder_main;

        // Creating current order content
        currentOrder = new OrderContent();

        // Loading app data
        fileTools.loadAppData(this);

        // Filling the spinner in main_activity with options
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        selectedCategory = (String)((Spinner) findViewById(R.id.spinner)).getSelectedItem();
        // Setting View Store button
        // and checking for what is selected in the spinner
        Button viewStoreBt = (Button)findViewById(R.id.btViewStore);
        viewStoreBt.setOnClickListener(v -> {
            selectedCategory = (String)((Spinner) findViewById(R.id.spinner)).getSelectedItem();
            if(selectedCategory != null) {
                Intent intent = new Intent(this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        // Setting Add/Edit button
        Button addEditProductBt = (Button)findViewById(R.id.btAddEditProduct);
        addEditProductBt.setOnClickListener(v -> {
            Intent intent = new Intent(this, CheckCodeActivity.class);
            startActivity(intent);
        });

        // Setting History button
        Button historyBt = (Button)findViewById(R.id.btHistory);
        historyBt.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });

        //What is remaining is Export and Import
        ///////////////////////////////////////
        ///////////////////////////////////////
        ////////////////////////////////////////
        ///////////////////////////////////////
        ////////////////////////////////////////
    }
    private int STORAGE_PERMISSION_CODE = 1;
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


}