package com.example.appdemo.gradefragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appdemo.GradePage;
import com.example.appdemo.R;

public class Grade3Fragment extends Fragment {

    private int[] buttons = new int[9];
    private int[] btns = new int[2];

    private Button ButtonPoint, ButtonCount;
    private Button buttonPoint1, buttonPoint2, buttonPoint3, buttonPoint4;
    private Button buttonCount1, buttonCount2, buttonCount3, buttonCount4;

    private TextView textView;
    //用来判断动画是否正在进行
    private static boolean judge = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        for(int i = 0; i<8; i++){
            buttons[i] = 0;
        }

        for(int i = 0; i<2; i++){
            btns[i] = 0;
        }

        buttons[buttons.length-4] = 1;
        buttons[8] = 3;
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grade3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ButtonPoint = requireView().findViewById(R.id.Grade3KnowPoint);
        ButtonCount = requireView().findViewById(R.id.Grade3EquCount);

        buttonPoint1 = requireActivity().findViewById(R.id.Grade3Point1);
        buttonPoint2 = requireActivity().findViewById(R.id.Grade3Point2);
        buttonPoint3 = requireActivity().findViewById(R.id.Grade3Point3);
        buttonPoint4 = requireActivity().findViewById(R.id.Grade3Point4);

        buttonCount1 = requireActivity().findViewById(R.id.Grade3Count1);
        buttonCount2 = requireActivity().findViewById(R.id.Grade3Count2);
        buttonCount3 = requireActivity().findViewById(R.id.Grade3Count3);
        buttonCount4 = requireActivity().findViewById(R.id.Grade3Count4);

        textView = requireActivity().findViewById(R.id.Grade3textView);

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

            if(btns[0] == 0){
                objectPoint1.start();
                objectPoint2.start();
                objectPoint3.start();
                objectPoint4.start();

                objectAnimatorX.start();
                objectAnimatorY.start();
                btns[0] = 1;
            }
            else{
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

                    textView.setText("17 × 36 =");
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

                    textView.setText("720 - 185 ÷ 5 =");
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

                    textView.setText("( 220 - 180 ) ÷ 5 =");
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

                    textView.setText("2/3 - 1/3 =");
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

        buttonCount1.setOnClickListener(v->{
            ObjectAnimator object = ObjectAnimator.ofFloat(buttonCount1,"alpha", 0f, 1f);
            object.setDuration(1000);

            if(buttons[4] + buttons[5] + buttons[6] + buttons[7] == 0){
                buttonCount1.setBackgroundResource(R.drawable.ball2);
                buttons[4] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[4] = 1;

                buttons[5] = 0;
                buttons[6] = 0;
                buttons[7] = 0;
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

            if(buttons[4] + buttons[5] + buttons[6] + buttons[7] == 0){
                buttonCount2.setBackgroundResource(R.drawable.ball2);
                buttons[5] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[5] = 1;

                buttons[4] = 0;
                buttons[6] = 0;
                buttons[7] = 0;
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

            if(buttons[4] + buttons[5] + buttons[6] + buttons[7] == 0){
                buttonCount3.setBackgroundResource(R.drawable.ball2);
                buttons[6] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[6] = 1;

                buttons[4] = 0;
                buttons[5] = 0;
                buttons[7] = 0;
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

            if(buttons[4] + buttons[5] + buttons[6] + buttons[7] == 0){
                buttonCount4.setBackgroundResource(R.drawable.ball2);
                buttons[7] = 1;
                object.start();

                GradePage.setChoice(buttons);
            }else{
                buttons[7] = 1;

                buttons[4] = 0;
                buttons[5] = 0;
                buttons[6] = 0;
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
        ObjectAnimator textAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f, 0.2f);
        textAnimator.setDuration(2500);
        //为中间知识点设置消失动画
        ObjectAnimator PointAnimator = ObjectAnimator.ofFloat(ButtonPoint, "alpha", 1f, 0f);
        PointAnimator.setDuration(500);
        //进行显示，同时使用监听器来处理知识点消失和文本出现
        textAnimator.start();
        PointAnimator.start();
        textAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator textTwo = ObjectAnimator.ofFloat(textView, "alpha", 0.2f, 0f);
                textTwo.setDuration(500);
                ObjectAnimator PointAnimator = ObjectAnimator.ofFloat(ButtonPoint, "alpha", 0f, 1f);
                PointAnimator.setDuration(800);
                PointAnimator.start();
                textTwo.start();

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