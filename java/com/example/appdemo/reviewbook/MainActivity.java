package com.example.appdemo.reviewbook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdemo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    ArrayList<String> WrongEquals = new ArrayList<>();
    SharedPreferences sp ;

    Set<String> Equals = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.lv);


        sp = getSharedPreferences("personInfo",MODE_PRIVATE);


        Equals = sp.getStringSet("WrongFormulations",null);
        for(String str: Equals){
            WrongEquals.add(str);
        }

        MyBaseAdapter mAdapter = new MyBaseAdapter();

        mListView.setAdapter(mAdapter);


    }
    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return WrongEquals.toArray().length;
        }

        @Override
        public Object getItem(int position) {
            return WrongEquals.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this,R.layout.list_item,null);
            TextView context_ = (TextView) view.findViewById(R.id.tv_view);
            context_.setText(WrongEquals.get(position));
            return view;
        }
    }
}