package com.example.managestock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Setting Back button
        ((Button) findViewById(R.id.btBackHistory)).setOnClickListener(v -> {
            finish();
        });

        // preparing History list
        ArrayList<OrderContent> list = new ArrayList<>();
        for(int i=MainActivity.history.size()-1; i>=0; i--){
            list.add(MainActivity.history.get(i));
        }

        // Setting the list
        ListView historyListView = (ListView) findViewById(R.id.historyListView);
        HistoryAdapter adapter = new HistoryAdapter(this, list);
        historyListView.setAdapter(adapter);
    }
}