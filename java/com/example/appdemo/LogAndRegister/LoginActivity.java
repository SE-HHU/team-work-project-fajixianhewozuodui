package com.example.appdemo.LogAndRegister;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.appdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton qq, chat, massage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        qq = findViewById(R.id.qq_login);
        chat = findViewById(R.id.chat_login);
        massage = findViewById(R.id.massage_login);
        tabLayout.addTab(tabLayout.newTab().setText("登录"));
        tabLayout.addTab(tabLayout.newTab().setText("注册"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);

        init_cartoon();

    }


    private void init_cartoon(){

        qq.setTranslationY(300);
        chat.setTranslationY(300);
        massage.setTranslationY(300);
        tabLayout.setTranslationY(300);

        qq.setAlpha(0f);
        chat.setAlpha(0f);
        massage.setAlpha(0f);
        tabLayout.setAlpha(0f);

        qq.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        chat.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        massage.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }

//    //修改账号和密码 获取账号和密码
//    public static String getAccount() {
//        return account;
//    }
//
//    public static void setAccount(String account) {
//        LoginActivity.account = account;
//    }
//
//    public static String getPassword() {
//        return password;
//    }
//
//    public static void setPassword(String password) {
//        LoginActivity.password = password;
//    }
//


}