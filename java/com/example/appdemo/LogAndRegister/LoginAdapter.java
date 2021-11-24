package com.example.appdemo.LogAndRegister;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {
//    private Context context;
    int totalTabs;
    public LoginAdapter(FragmentManager fm, int totalTabs){
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoginTabFragment();
            case 1:
                return new SignupTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "登录";
            case 1:
                return "注册";
            default:
                return "异常!";
        }
    }

}
