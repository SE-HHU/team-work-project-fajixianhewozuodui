package com.example.appdemo.LogAndRegister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appdemo.MainAPPActivity;
import com.example.appdemo.R;

public class LoginTabFragment extends Fragment {
    private EditText lg_account, lg_password;
    private TextView forgotPass;
    private Button bt_login;
    private CheckBox cb_rem, cb_login_auto;

    private String get_account;
    private String get_password;
    private long delayMillis = 1000;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static final String TAG = "LogIn";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        InitView(root);

        InitSharedPreference();

        inCartoon(root);

        SetListener();

        PutAccount();

        Remember();

        LogIn_Auto();



        return root;
    }

    private void InitView(ViewGroup root){
        lg_account = root.findViewById(R.id.et_login_account);
        lg_password = root.findViewById(R.id.et_login_password);
        forgotPass = root.findViewById(R.id.forgotPass);
        bt_login = root.findViewById(R.id.bt_login);
        cb_login_auto = root.findViewById(R.id.cb_login_auto);
        cb_rem = root.findViewById(R.id.cb_rem_password);

    }


    private void inCartoon(ViewGroup root){

        lg_account.setTranslationY(800);
        lg_password.setTranslationY(800);
        forgotPass.setTranslationY(800);
        bt_login.setTranslationY(800);
        cb_login_auto.setTranslationY(800);
        cb_rem.setTranslationY(800);

        lg_account.setAlpha(0f);
        lg_password.setAlpha(0f);
        forgotPass.setAlpha(0f);
        bt_login.setAlpha(0f);
        cb_login_auto.setAlpha(0f);
        cb_rem.setAlpha(0f);


        lg_account.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        lg_password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        forgotPass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        bt_login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        cb_login_auto.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        cb_rem.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
    }

    private void LogIn_Auto() {
        if (sp.getBoolean("hasLogIn",false)
                &&sp.getBoolean("remember", false)
                &&sp.getBoolean("auto_log",false)) {

            Intent intent = new Intent(getActivity(), MainAPPActivity.class);
            startActivity(intent);
        }
    }

    public void InitSharedPreference() {
        sp = getActivity().getSharedPreferences("personInfo", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private void Remember() {
        if (sp.getBoolean("hasLogIn",false)
                &&sp.getBoolean("remember", false)) {

            String str1 = sp.getString("account", "");
            String str2 = sp.getString("password", "");
            lg_account.post(new Runnable() {
                @Override
                public void run() {
                    lg_account.setText(str1);
                }
            });
            lg_password.post(new Runnable() {
                @Override
                public void run() {
                    lg_password.setText(str2);
                }
            });
            cb_rem.post(new Runnable() {
                @Override
                public void run() {
                    cb_rem.setChecked(true);
                }
            });
        }
    }

//在第二次登陆的时候，如果用户没有保存密码，那起码要把账号写上去
    private void PutAccount() {
        if (sp.getBoolean("hasLogIn",false)) {

            String str1 = sp.getString("account", "");
            lg_account.post(new Runnable() {
                @Override
                public void run() {
                    lg_account.setText(str1);
                }
            });
        }
    }

    private void SetListener() {

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,lg_account.getText().toString()+"------2");
                Log.e(TAG,lg_password.getText().toString()+"------2");
                if(Judge()){
                    editor.putBoolean("hasLogIn",true);
                    editor.commit();
                    Toast.makeText(getActivity(),"登录成功, 欢迎~",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainAPPActivity.class);
                    startActivity(intent);
                }
            }
        });

        cb_rem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(getActivity(),"选中了保存密码",Toast.LENGTH_SHORT).show();
                    editor.putBoolean("remember", true);
                    editor.commit();
                }else {
                    Toast.makeText(getActivity(),"取消了保存密码",Toast.LENGTH_SHORT).show();
                    editor.putBoolean("remember",false);
                    editor.commit();
                }
            }
        });

        cb_login_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(getActivity(),"选中了自动登录",Toast.LENGTH_SHORT).show();
                    editor.putBoolean("auto_log", true);
                    editor.commit();
                }else {
                    Toast.makeText(getActivity(),"取消了自动登录",Toast.LENGTH_SHORT).show();
                    editor.putBoolean("auto_log",false);
                    editor.commit();
                }
            }
        });
    }

    //判断账号密码能否满足登录要求
    private boolean Judge() {
        get_account = lg_account.getText().toString();
        get_password = lg_password.getText().toString();
        Log.e(TAG,lg_account.getText().toString()+"------3");
        Log.e(TAG,lg_password.getText().toString()+"------3");
        String correct_account = sp.getString("account","");
        String correct_password = sp.getString("password","");

        if(get_account.equals(correct_account)){
            if(get_password.equals(correct_password)){
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }


}
