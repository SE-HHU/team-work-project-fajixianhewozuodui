package com.example.appdemo.gradefragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appdemo.GradePage;
import com.example.appdemo.R;
import com.example.appdemo.controller.SetLevel;

import java.util.ArrayList;


public class Grade6Fragment extends Fragment {

    private int[] buttons = new int[10];
    private int[] btns = new int[2];

    private Button ButtonPoint, ButtonCount;
    private Button buttonPoint1, buttonPoint2, buttonPoint3, buttonPoint4, buttonPoint5;
    private Button buttonCount1, buttonCount2, buttonCount3, buttonCount4;

    private TextView up, center, down;
    //用来判断动画是否正在进行
    private static boolean judge = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        for(int i = 0; i<9; i++){
            buttons[i] = 0;
        }

        for(int i = 0; i<2; i++){
            btns[i] = 0;
        }

        buttons[buttons.length-4] = 1;
        buttons[9] = 6;
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grade6, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ButtonPoint = requireView().findViewById(R.id.Grade6KnowPoint);
        ButtonCount = requireView().findViewById(R.id.Grade6EquCount);

        buttonPoint1 = requireActivity().findViewById(R.id.Grade6Point1);
        buttonPoint2 = requireActivity().findViewById(R.id.Grade6Point2);
        buttonPoint3 = requireActivity().findViewById(R.id.Grade6Point3);
        buttonPoint4 = requireActivity().findViewById(R.id.Grade6Point4);
        buttonPoint5 = requireActivity().findViewById(R.id.Grade6Point5);

        buttonCount1 = requireActivity().findViewById(R.id.Grade6Count1);
        buttonCount2 = requireActivity().findViewById(R.id.Grade6Count2);
        buttonCount3 = requireActivity().findViewById(R.id.Grade6Count3);
        buttonCount4 = requireActivity().findViewById(R.id.Grade6Count4);

        up = requireActivity().findViewById(R.id.Grade6textViewUp);
        center = requireActivity().findViewById(R.id.Grade6textViewCenter);
        down = requireActivity().findViewById(R.id.Grade6textViewDown);


        ButtonPoint.setOnClickListener(v -> {
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(ButtonPoint, "scaleX", 1f, 1.2f, 1f);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(ButtonPoint, "scaleY", 1f, 1.2f, 1f);
            objectAnimatorX.setDuration(500);
            objectAnimatorY.setDuration(500);

            ObjectAnimator objectPoint1 = ObjectAnimator.ofFloat(buttonPoint1, "alpha",0f, 1f);
            objectPoint1.setDuration(2000);
            ObjectAnimator objectPoint2 = ObjectAnimator.ofFloat(buttonPoint2, "alpha",0f, 1f);
            objectPoint2.setDuration(2000);
            ObjectAnimator objectPoint3 = ObjectAnimator.ofFloat(buttonPoint3, "alpha",0f, 1f);
            objectPoint3.setDuration(2000);
            ObjectAnimator objectPoint4 = ObjectAnimator.ofFloat(buttonPoint4, "alpha",0f, 1f);
            objectPoint4.setDuration(2000);
            ObjectAnimator objectPoint5 = ObjectAnimator.ofFloat(buttonPoint5, "alpha",0f, 1f);
            objectPoint5.setDuration(2000);


            if(btns[0] == 0){
                if(!objectPoint1.isRunning()){
                    objectPoint1.start();
                    objectPoint2.start();
                    objectPoint3.start();
                    objectPoint4.start();
                    objectPoint5.start();

                    objectAnimatorX.start();
                    objectAnimatorY.start();
                    btns[0] = 1;
                }
            }else{
                objectAnimatorX.start();
                objectAnimatorY.start();
            }
        });


        ButtonCount.setOnClickListener(v ->{
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(ButtonCount, "scaleX", 1f, 1.2f, 1f);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(ButtonCount, "scaleY", 1f, 1.2f, 1f);
            objectAnimatorX.setDuration(500);
            objectAnimatorY.setDuration(500);

            ObjectAnimator objectCount1 = ObjectAnimator.ofFloat(buttonCount1,"alpha", 0f, 1f);
            objectCount1.setDuration(2000);
            ObjectAnimator objectCount2 = ObjectAnimator.ofFloat(buttonCount2,"alpha", 0f, 1f);
            objectCount2.setDuration(2000);
            ObjectAnimator objectCount3 = ObjectAnimator.ofFloat(buttonCount3,"alpha", 0f, 1f);
            objectCount3.setDuration(2000);
            ObjectAnimator objectCount4 = ObjectAnimator.ofFloat(buttonCount4,"alpha", 0f, 1f);
            objectCount4.setDuration(2000);

            if(btns[1] == 0){
                objectCount1.start();
                objectCount2.start();
                objectCount3.start();
                objectCount4.start();

                objectAnimatorX.start();
                objectAnimatorY.start();
                btns[1] = 1;
            }
            else{
                objectAnimatorX.start();
                objectAnimatorY.start();
            }
        });

        buttonPoint1.setOnClickListener(v ->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonPoint1,"alpha", 0f, 1f);
            object.setDuration(1000);
            if(buttons[0] == 0){
                if(!object.isRunning()){
                    buttonPoint1.setBackgroundResource(R.drawable.ball2);
                    object.start();
                    buttons[0] = 1;

                    GradePage.setChoice(buttons);


                    ArrayList<String> arr = SetLevel.setLevel("2/9×4=");
                    up.setText(arr.get(0));
                    center.setText(arr.get(1));
                    down.setText(arr.get(2));

                    if(judge){
                        animationEffect();
                    }
                }
            }else{
                if(!object.isRunning()){
                    buttonPoint1.setBackgroundResource(R.drawable.ball);
                    object.start();
                    buttons[0] = 0;

                    GradePage.setChoice(buttons);
                }
            }
        });

        buttonPoint2.setOnClickListener(v ->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonPoint2,"alpha", 0f, 1f);
            object.setDuration(1000);
            if(buttons[1] == 0){
                if(!object.isRunning()){
                    buttonPoint2.setBackgroundResource(R.drawable.ball2);
                    object.start();
                    buttons[1] = 1;

                    GradePage.setChoice(buttons);

                    ArrayList<String> arr = SetLevel.setLevel("8/17×9/18=");
                    up.setText(arr.get(0));
                    center.setText(arr.get(1));
                    down.setText(arr.get(2));
                    if(judge){
                        animationEffect();
                    }
                }
            }else{
                if(!object.isRunning()){
                    buttonPoint2.setBackgroundResource(R.drawable.ball);
                    object.start();
                    buttons[1] = 0;

                    GradePage.setChoice(buttons);
                }
            }
        });

        buttonPoint3.setOnClickListener(v ->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonPoint3,"alpha", 0f, 1f);
            object.setDuration(1000);
            if(buttons[2] == 0){
                if(!object.isRunning()){
                    buttonPoint3.setBackgroundResource(R.drawable.ball2);
                    object.start();
                    buttons[2] = 1;

                    GradePage.setChoice(buttons);

                    ArrayList<String> arr = SetLevel.setLevel("4/5÷2=");
                    up.setText(arr.get(0));
                    center.setText(arr.get(1));
                    down.setText(arr.get(2));
                    if(judge){
                        animationEffect();
                    }
                }
            }else{
                if(!object.isRunning()){
                    buttonPoint3.setBackgroundResource(R.drawable.ball);
                    object.start();
                    buttons[2] = 0;

                    GradePage.setChoice(buttons);
                }
            }
        });

        buttonPoint4.setOnClickListener(v ->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonPoint4,"alpha", 0f, 1f);
            object.setDuration(1000);
            if(buttons[3] == 0){
                if(!object.isRunning()){
                    buttonPoint4.setBackgroundResource(R.drawable.ball2);
                    object.start();
                    buttons[3] = 1;

                    GradePage.setChoice(buttons);
                    ArrayList<String> arr = SetLevel.setLevel("5/6÷5/12=");
                    up.setText(arr.get(0));
                    center.setText(arr.get(1));
                    down.setText(arr.get(2));

                    if(judge){
                        animationEffect();
                    }
                }
            }else{
                if(!object.isRunning()){
                    buttonPoint4.setBackgroundResource(R.drawable.ball);
                    object.start();
                    buttons[3] = 0;

                    GradePage.setChoice(buttons);
                }
            }
        });

        buttonPoint5.setOnClickListener(v ->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonPoint5,"alpha", 0f, 1f);
            object.setDuration(1000);
            if(buttons[4] == 0){
                if(!object.isRunning()){
                    buttonPoint5.setBackgroundResource(R.drawable.ball2);
                    object.start();
                    buttons[4] = 1;

                    GradePage.setChoice(buttons);

                    ArrayList<String> arr = SetLevel.setLevel("7/16×9/4÷14/9=");
                    up.setText(arr.get(0));
                    center.setText(arr.get(1));
                    down.setText(arr.get(2));
                    if(judge){
                        animationEffect();
                    }
                }
            }else{
                if(!object.isRunning()){
                    buttonPoint5.setBackgroundResource(R.drawable.ball);
                    object.start();
                    buttons[4] = 0;

                    GradePage.setChoice(buttons);
                }
            }
        });

        buttonCount1.setOnClickListener(v->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonCount1,"alpha", 0f, 1f);
            object.setDuration(1000);

            if(buttons[5] + buttons[6] + buttons[7] + buttons[8] == 0){
                buttonCount1.setBackgroundResource(R.drawable.ball2);
                buttons[5] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[5] = 1;

                buttons[6] = 0;
                buttons[7] = 0;
                buttons[8] = 0;
                buttonCount1.setBackgroundResource(R.drawable.ball2);

                buttonCount2.setBackgroundResource(R.drawable.ball3);
                buttonCount3.setBackgroundResource(R.drawable.ball3);
                buttonCount4.setBackgroundResource(R.drawable.ball3);

                object.start();

                GradePage.setChoice(buttons);
            }
        });

        buttonCount2.setOnClickListener(v->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonCount2,"alpha", 0f, 1f);
            object.setDuration(1000);

            if(buttons[5] + buttons[6] + buttons[7] + buttons[8] == 0){
                buttonCount2.setBackgroundResource(R.drawable.ball2);
                buttons[6] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[6] = 1;

                buttons[5] = 0;
                buttons[7] = 0;
                buttons[8] = 0;
                buttonCount2.setBackgroundResource(R.drawable.ball2);

                buttonCount1.setBackgroundResource(R.drawable.ball3);
                buttonCount3.setBackgroundResource(R.drawable.ball3);
                buttonCount4.setBackgroundResource(R.drawable.ball3);

                object.start();

                GradePage.setChoice(buttons);
            }
        });

        buttonCount3.setOnClickListener(v->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonCount3,"alpha", 0f, 1f);
            object.setDuration(1000);

            if(buttons[5] + buttons[6] + buttons[7] + buttons[8] == 0){
                buttonCount3.setBackgroundResource(R.drawable.ball2);
                buttons[7] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[7] = 1;

                buttons[5] = 0;
                buttons[6] = 0;
                buttons[8] = 0;
                buttonCount3.setBackgroundResource(R.drawable.ball2);

                buttonCount1.setBackgroundResource(R.drawable.ball3);
                buttonCount2.setBackgroundResource(R.drawable.ball3);
                buttonCount4.setBackgroundResource(R.drawable.ball3);

                object.start();

                GradePage.setChoice(buttons);
            }
        });

        buttonCount4.setOnClickListener(v->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonCount4,"alpha", 0f, 1f);
            object.setDuration(1000);

            if(buttons[5] + buttons[6] + buttons[7] + buttons[8] == 0){
                buttonCount4.setBackgroundResource(R.drawable.ball2);
                buttons[8] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[8] = 1;

                buttons[5] = 0;
                buttons[6] = 0;
                buttons[7] = 0;
                buttonCount4.setBackgroundResource(R.drawable.ball2);

                buttonCount1.setBackgroundResource(R.drawable.ball3);
                buttonCount2.setBackgroundResource(R.drawable.ball3);
                buttonCount3.setBackgroundResource(R.drawable.ball3);

                object.start();

                GradePage.setChoice(buttons);
            }
        });

    }

    private void animationEffect(){
        judge = false;
        ObjectAnimator textAnimatorUp = ObjectAnimator.ofFloat(up, "alpha", 0f, 1f, 0.2f);
        ObjectAnimator textAnimatorCenter = ObjectAnimator.ofFloat(center, "alpha", 0f, 1f, 0.2f);
        ObjectAnimator textAnimatorDown = ObjectAnimator.ofFloat(down, "alpha", 0f, 1f, 0.2f);
        textAnimatorUp.setDuration(2500);
        textAnimatorCenter.setDuration(2500);
        textAnimatorDown.setDuration(2500);
        //为中间知识点设置消失动画
        ObjectAnimator PointAnimator = ObjectAnimator.ofFloat(ButtonPoint, "alpha", 1f, 0f);
        PointAnimator.setDuration(500);
        //进行显示，同时使用监听器来处理知识点消失和文本出现
        textAnimatorUp.start();
        textAnimatorCenter.start();
        textAnimatorDown.start();
        PointAnimator.start();
        textAnimatorUp.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator textTwo1 = ObjectAnimator.ofFloat(up, "alpha", 0.2f, 0f);
                ObjectAnimator textTwo2 = ObjectAnimator.ofFloat(center, "alpha", 0.2f, 0f);
                ObjectAnimator textTwo3 = ObjectAnimator.ofFloat(down, "alpha", 0.2f, 0f);
                textTwo1.setDuration(500);
                textTwo2.setDuration(500);
                textTwo3.setDuration(500);
                ObjectAnimator PointAnimator = ObjectAnimator.ofFloat(ButtonPoint, "alpha", 0f, 1f);
                PointAnimator.setDuration(800);
                PointAnimator.start();
                textTwo1.start();
                textTwo2.start();
                textTwo3.start();

                PointAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        judge = true;
                    }
                });
            }
        });
    }



}