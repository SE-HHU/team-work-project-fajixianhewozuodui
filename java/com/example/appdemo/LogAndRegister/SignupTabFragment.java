package com.example.appdemo.LogAndRegister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.appdemo.MainAPPActivity;
import com.example.appdemo.R;

public class SignupTabFragment extends Fragment {

    private EditText account, username, password, password_again;
    Button bt_register;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment, container, false);
        //初始化控件
        InitView(root);
        InitSharedPreference();
        //设置开场动画
        inCartoon(root);
        SetListener();

        return root;
    }

    private void InitView(ViewGroup root) {
        bt_register = root.findViewById(R.id.bt_register);
        account = root.findViewById(R.id.et_reg_account);
        username = root.findViewById(R.id.et_reg_UserName);
        password = root.findViewById(R.id.et_reg_password);
        password_again = root.findViewById(R.id.et_reg_password_again);
    }

    private void inCartoon(ViewGroup root){
        account.setTranslationY(800);
        password.setTranslationY(800);
        password_again.setTranslationY(800);
        username.setTranslationY(800);

        account.setAlpha(0f);
        password.setAlpha(0f);
        password_again.setAlpha(0f);
        username.setAlpha(0f);

        account.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        password_again.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        username.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
    }

    private void InitSharedPreference() {
        sp = getActivity().getSharedPreferences("personInfo", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private void SetListener() {
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                handleRegister();
            }
        });
    }

    private void handleRegister() {
        String usernameText = username.getText().toString();
        String accountText = account.getText().toString();
        String passwordText = password.getText().toString();
        String passwordAgainText = password_again.getText().toString();

        if(TextUtils.isEmpty(usernameText)){
            Toast.makeText(getActivity(),"用户名不可以为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(accountText)){
            Toast.makeText(getActivity(),"账号不可以为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if(accountText.length()<8){
            Toast.makeText(getActivity(),"账号太简单啦~",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordText)){
            Toast.makeText(getActivity(),"密码不可以为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordText.length()<8){
            Toast.makeText(getActivity(),"你的密码不够安全哟~~最少8位数",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordAgainText)){
            Toast.makeText(getActivity(),"重复输入密码不可以为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!passwordText.equals(passwordAgainText)) {
            Toast.makeText(getActivity(),"两次输入不一致，好好检查哟~",Toast.LENGTH_SHORT).show();
            return;
        }


        editor.putString("username",usernameText);
        editor.putString("account",accountText );
        editor.putString("password",passwordText);
        editor.commit();

        Toast.makeText(getActivity(),"注册成功,"+usernameText+"欢迎~",Toast.LENGTH_SHORT).show();

//        测试时用代码
//        Log.e("TAG",sp.getString("username",null));
//        Log.e("TAG",sp.getString("account",null));
//        Log.e("TAG",sp.getString("password",null));
        Intent intent = new Intent(getActivity(), MainAPPActivity.class);
        startActivity(intent);
        //往登陆页面上传入新注册好的账号和密码
//        LoginActivity.setAccount(accountText);
//        LoginActivity.setPassword(passwordText);
    }


}
