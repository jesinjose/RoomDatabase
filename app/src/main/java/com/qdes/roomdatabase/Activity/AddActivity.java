package com.qdes.roomdatabase.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.qdes.roomdatabase.Database.RoomDB;
import com.qdes.roomdatabase.Model.UserData;
import com.qdes.roomdatabase.R;

public class AddActivity extends AppCompatActivity {

    Button add;
    RoomDB database;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = RoomDB.getInstance(getApplicationContext());
        initialization();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Please fill this field !!");
                    username.requestFocus();
                    return;
                }
                Save();
            }
        });

    }

    private void Save() {

        UserData userData = new UserData();
        userData.setName(username.getText().toString());
        database.mainDao().insert(userData);
        finish();


    }

    private void initialization() {
        add = findViewById(R.id.add);
        username = findViewById(R.id.username);
    }
}