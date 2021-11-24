package com.example.appdemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdemo.controller.CreateList;
import com.example.appdemo.controller.OnlineJudge;
import com.example.appdemo.game.GamePage;

public class MainAPPActivity extends AppCompatActivity {

    private Button selfPage, gamePage, modelPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appactivity);

        selfPage = findViewById(R.id.SelfPage);
        gamePage = findViewById(R.id.GamePage);
        modelPage = findViewById(R.id.ModelButton);


        selfPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAPPActivity.this, GradePage.class);
                startActivity(intent);

                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(selfPage, "scaleX", 1f, 1.2f, 1f);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(selfPage, "scaleY", 1f, 1.2f, 1f);
                objectAnimatorX.setDuration(500);
                objectAnimatorY.setDuration(500);

                objectAnimatorX.start();
                objectAnimatorY.start();
            }
        });

        gamePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAPPActivity.this, GamePage.class);
                startActivity(intent);
                finish();

                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(gamePage, "scaleX", 1f, 1.2f, 1f);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(gamePage, "scaleY", 1f, 1.2f, 1f);
                objectAnimatorX.setDuration(500);
                objectAnimatorY.setDuration(500);

                objectAnimatorX.start();
                objectAnimatorY.start();
            }
        });

        modelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] choice = new int[] {1,1,1,1,0,1,0,0,2};
                CreateList.setChoice(choice);
                Intent intent = new Intent(MainAPPActivity.this, OnlineJudge.class);
                startActivity(intent);

                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(modelPage, "scaleX", 1f, 1.2f, 1f);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(modelPage, "scaleY", 1f, 1.2f, 1f);
                objectAnimatorX.setDuration(500);
                objectAnimatorY.setDuration(500);

                objectAnimatorX.start();
                objectAnimatorY.start();

            }
        });


    }
}