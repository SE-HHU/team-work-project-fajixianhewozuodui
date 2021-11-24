package com.example.appdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.appdemo.LogAndRegister.LoginActivity;
import com.example.appdemo.view.OnBoardingFragment1;
import com.example.appdemo.view.OnBoardingFragment2;
import com.example.appdemo.view.OnBoardingFragment3;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView logo, splashImg;
    LottieAnimationView lottieAnimationView;

    Animation anim;

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        logo = findViewById(R.id.logo);
        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(lottieAnimationView, "translationY", -3000f);
        objectAnimator.setDuration(1000).setStartDelay(2000);
        objectAnimator.start();
        splashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(2000);
        logo.animate().translationY(-3000).setDuration(1000).setStartDelay(2000);
//        lottieAnimationView.animate().translationY(-3000).setDuration(1000).setStartDelay(2000);

        //控制界面滚动效果
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(pagerAdapter);

        anim = AnimationUtils.loadAnimation(this, R.anim.o_n_anim);
        viewPager.setAdapter(pagerAdapter);




    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new OnBoardingFragment1();
                case 1:
                    return new OnBoardingFragment2();
                case 2:
                    return new OnBoardingFragment3();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}