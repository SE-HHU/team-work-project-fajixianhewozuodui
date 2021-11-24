package com.example.appdemo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdemo.MainAPPActivity;
import com.example.appdemo.R;
import com.example.appdemo.reviewbook.MainActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class OnlineJudge extends AppCompatActivity implements View.OnClickListener{

    String answerForTest = "";
    int cnt = 0;
    boolean judge = true;
    ArrayList<String> arrQuestion;
    ArrayList<String> arrAnswer;
    ArrayList<String> reviewBook;

    private TextView up, center, down;
    private ImageButton back;
    private TextView answer;
    private Button skip;
    private Button[] buttons = new Button[13];
    private Button reviewBtn;

    SharedPreferences sp ;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_judge);

        up = findViewById(R.id.TestViewUp);
        center = findViewById(R.id.TestViewCenter);
        down = findViewById(R.id.TestViewDown);
        answer = findViewById(R.id.answer);
        back = findViewById(R.id.testButton);
        skip = findViewById(R.id.skip);

        //创建一个错题本
        reviewBook = new ArrayList<String>();

        buttons[0] = findViewById(R.id.JudgeNumber1);
        buttons[1] = findViewById(R.id.JudgeNumber2);
        buttons[2] = findViewById(R.id.JudgeNumber3);
        buttons[3] = findViewById(R.id.JudgeNumber4);
        buttons[4] = findViewById(R.id.JudgeNumber5);
        buttons[5] = findViewById(R.id.JudgeNumber6);
        buttons[6] = findViewById(R.id.JudgeNumber7);
        buttons[7] = findViewById(R.id.JudgeNumber8);
        buttons[8] = findViewById(R.id.JudgeNumber9);
        buttons[9] = findViewById(R.id.JudgeNumber0);
        buttons[10] = findViewById(R.id.JudgeNumberCommit);
        buttons[11] = findViewById(R.id.JudgeNumberDelete);
        buttons[12] = findViewById(R.id.JudgeNumberDiv);
        reviewBtn = findViewById(R.id.reviewBook);

        sp = getSharedPreferences("personInfo",MODE_PRIVATE);
        editor = sp.edit();
        
        //为每一个按钮设置监听事件
        for (int i = 0; i < 13; i++) {
            buttons[i].setOnClickListener(this);
        }

        //生成题目
        CreateList sheng_cheng_qi = new CreateList();
        sheng_cheng_qi.getList();
        arrQuestion = CreateList.arrQuestion;
        arrAnswer = CreateList.arrAnswer;

        int indexDiv =arrQuestion.get(0).indexOf('/');
        if(indexDiv == -1){
            center.setText(arrQuestion.get(0));
            up.setText("");
            down.setText("");
        }else{
            ArrayList<String> arr = SetLevel.setLevel(arrQuestion.get(0));
            up.setText(arr.get(0));
            center.setText(arr.get(1));
            down.setText(arr.get(2));
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineJudge.this, MainAPPActivity.class);
                startActivity(intent);
                finish();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                if(judge){
                    if(cnt == arrAnswer.toArray().length) {
                        center.setText("您已成功完成作答");
                        up.setText("");
                        down.setText("");
                        Read(reviewBook);
                        judge = false;
                    } else {
                        int indexDiv =arrQuestion.get(cnt).indexOf('/');
                        if(indexDiv == -1){
                            center.setText(arrQuestion.get(cnt));
                            up.setText("");
                            down.setText("");
                        }else{
                            ArrayList<String> arr = SetLevel.setLevel(arrQuestion.get(cnt));
                            up.setText(arr.get(0));
                            center.setText(arr.get(1));
                            down.setText(arr.get(2));
                        }

                        reviewBook.add(arrQuestion.get(cnt-1));
                        answerForTest = "";
                        answer.setText("");
                    }
                }else{
                    Toast.makeText(OnlineJudge.this, "已经完成所有题目啦", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OnlineJudge.this, MainActivity.class);
                startActivity(intent1);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.JudgeNumber1:
                answerForTest += 1;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber2:
                answerForTest += 2;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber3:
                answerForTest+=3;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber4:
                answerForTest+=4;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber5:
                answerForTest+=5;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber6:
                answerForTest+=6;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber7:
                answerForTest+=7;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber8:
                answerForTest+=8;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber9:
                answerForTest+=9;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumberDiv:
                answerForTest+="/";
                answer.setText(answerForTest);break;
            case R.id.JudgeNumber0:
                answerForTest+=0;
                answer.setText(answerForTest);break;
            case R.id.JudgeNumberCommit:
                //   此处需要连接口
                if(arrAnswer.get(cnt).equals(answerForTest))
                {
                    cnt++;
                    if(cnt == arrAnswer.toArray().length) {
                        center.setText("您已成功完成作答");
                        up.setText("");
                        down.setText("");
                        Read(reviewBook);
                    } else {
                        int indexDiv =arrQuestion.get(cnt).indexOf('/');
                        if(indexDiv == -1){
                            center.setText(arrQuestion.get(cnt));
                            up.setText("");
                            down.setText("");
                        }else{
                            ArrayList<String> arr = SetLevel.setLevel(arrQuestion.get(cnt));
                            up.setText(arr.get(0));
                            center.setText(arr.get(1));
                            down.setText(arr.get(2));
                        }
                        answerForTest = "";
                        answer.setText("");
                    }
                }else{
                    Toast.makeText(OnlineJudge.this, "答错了，再试试吧",
                            Toast.LENGTH_SHORT).show();
                    reviewBook.add(arrQuestion.get(cnt));
                    answerForTest="";
                    answer.setText("");
                }
                break;
            case R.id.JudgeNumberDelete:
                if(answerForTest.length() != 0){
                    answerForTest = answerForTest.substring(0,answerForTest.length()-1);
                    answer.setText(answerForTest);
                }
                break;
        }
    }
    public void Read(ArrayList<String> NeedToBeRead) {
        Set<String> temp = new HashSet<String>();

        for(String str:NeedToBeRead){
            temp.add(str);
            Log.d("TAG","-------"+str);
        }
        editor.putStringSet("WrongFormulations",temp);
        editor.commit();
    }

}