package com.qdes.roomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qdes.roomdatabase.Activity.AddActivity;
import com.qdes.roomdatabase.Adapter.RecyclerviewAdapter;
import com.qdes.roomdatabase.Database.RoomDB;
import com.qdes.roomdatabase.Model.UserData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rec;
    FloatingActionButton buttonAdd;
    List<UserData> model = new ArrayList<>();
    RoomDB databse;
    RecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialization();

        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        databse = RoomDB.getInstance(getApplicationContext());

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });


    }

    private void Initialization() {
        rec = findViewById(R.id.rec);
        buttonAdd = findViewById(R.id.buttonAdd);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetAllData();
    }

    private void GetAllData() {
        model = databse.mainDao().getAll();
        adapter = new RecyclerviewAdapter(getApplicationContext(), model);
        rec.setAdapter(adapter);
    }
}