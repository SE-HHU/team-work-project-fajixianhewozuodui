package com.example.appdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdemo.controller.SetLevel;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    TextView up, center, down;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        up = findViewById(R.id.TestViewUp);
        center = findViewById(R.id.TestViewCenter);
        down = findViewById(R.id.TestViewDown);

        ArrayList<String> arr = SetLevel.setLevel("30/23×(44/54)×1/3");

        up.setText(arr.get(0));
        center.setText(arr.get(1));
        down.setText(arr.get(2));

        Log.e("Up", arr.get(0));
        Log.e("Center", arr.get(1));
        Log.e("Down", arr.get(2));

    }
}