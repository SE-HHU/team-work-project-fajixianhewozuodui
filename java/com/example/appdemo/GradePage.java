package com.example.appdemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appdemo.controller.CreateList;
import com.example.appdemo.controller.OnlineJudge;
import com.example.appdemo.controller.getPDF;
import com.example.appdemo.gradefragment.Grade1Fragment;
import com.example.appdemo.gradefragment.Grade2Fragment;
import com.example.appdemo.gradefragment.Grade3Fragment;
import com.example.appdemo.gradefragment.Grade4Fragment;
import com.example.appdemo.gradefragment.Grade5Fragment;
import com.example.appdemo.gradefragment.Grade6Fragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class GradePage extends AppCompatActivity {
    private static int[] choice = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    private Button gradeChoice1, gradeChoice2;
    ViewPager2 GradeViewPager;
    TabLayout GradeTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_page);

        GradeViewPager = findViewById(R.id.GradeViewPager);
        GradeTabLayout = findViewById(R.id.GradeTabLayout);

        gradeChoice1 = findViewById(R.id.GradeChoice1);
        gradeChoice2 = findViewById(R.id.GradeChoice2);

        gradeChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (choiceJudge()){
                    case 0:
                        Intent intent = new Intent(GradePage.this, OnlineJudge.class);
                        startActivity(intent);
                        CreateList.setChoice(choice);
                        choice = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
                        finish();
                        break;
                    case 1:
                        String massage1 = "未选择知识点和题目数量, 请选择！";
                        Toast.makeText(GradePage.this, massage1, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        String massage2 = "未选择知识点, 请选择！";
                        Toast.makeText(GradePage.this, massage2, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        String massage3 = "未选择题目数量, 请选择！";
                        Toast.makeText(GradePage.this, massage3, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        String massage4 = "未知异常!";
                        Toast.makeText(GradePage.this, massage4, Toast.LENGTH_SHORT).show();
                        break;
                }

                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(gradeChoice1, "scaleX", 1f, 1.2f, 1f);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(gradeChoice1, "scaleY", 1f, 1.2f, 1f);
                objectAnimatorX.setDuration(500);
                objectAnimatorY.setDuration(500);

                objectAnimatorX.start();
                objectAnimatorY.start();
            }
        });


        gradeChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (choiceJudge()){
                    case 0:
                        Intent intent = new Intent(GradePage.this, getPDF.class);
                        startActivity(intent);
                        CreateList.setChoice(choice);
                        choice = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
                        finish();
                        break;
                    case 1:
                        String massage1 = "未选择知识点和题目数量, 请选择！";
                        Toast.makeText(GradePage.this, massage1, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        String massage2 = "未选择知识点, 请选择！";
                        Toast.makeText(GradePage.this, massage2, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        String massage3 = "未选择题目数量, 请选择！";
                        Toast.makeText(GradePage.this, massage3, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        String massage4 = "未知异常!";
                        Toast.makeText(GradePage.this, massage4, Toast.LENGTH_SHORT).show();
                        break;
                }

                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(gradeChoice2, "scaleX", 1f, 1.2f, 1f);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(gradeChoice2, "scaleY", 1f, 1.2f, 1f);
                objectAnimatorX.setDuration(500);
                objectAnimatorY.setDuration(500);

                objectAnimatorX.start();
                objectAnimatorY.start();
            }
        });



        GradeViewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new Grade1Fragment();
                    case 1:
                        return new Grade2Fragment();
                    case 2:
                        return new Grade3Fragment();
                    case 3:
                        return new Grade4Fragment();
                    case 4:
                        return new Grade5Fragment();
                    case 5:
                        return new Grade6Fragment();
                    default:
                        return new Grade1Fragment();
                }
            }

            @Override
            public int getItemCount() {
                return 6;
            }
        });

        new TabLayoutMediator(GradeTabLayout, GradeViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("一年级");
                    break;
                case 1:
                    tab.setText("二年级");
                    break;
                case 2:
                    tab.setText("三年级");
                    break;
                case 3:
                    tab.setText("四年级");
                    break;
                case 4:
                    tab.setText("五年级");
                    break;
                case 5:
                    tab.setText("六年级");
                    break;
            }
        }).attach();

    }

    public static void setChoice(int[] inChoice){
        choice = inChoice;
    }

    public static int[] getChoice(){
        return choice;
    }

    private int choiceJudge(){
        //确定是否选中了知识点
        int point = 0;
        int start = choice.length - 1;
        for(int i = start-5; i >= 0; i--){
            point += choice[i];
        }

        boolean count = false;
        for(int i = start-1; i > start-5; i--){
            if(choice[i] != 0){
                count = true;
                break;
            }
        }

        if(count && point != 0){
            return 0;
        }

        if(point == 0 && !count){
            return 1;
        }

        if(point == 0 && count){
            return 2;
        }

        if(!count){
            return 3;
        }


        return  4;
    }


}
