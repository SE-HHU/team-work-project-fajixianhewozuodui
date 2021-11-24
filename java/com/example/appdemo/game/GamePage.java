package com.example.appdemo.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdemo.MainAPPActivity;
import com.example.appdemo.R;
import com.example.appdemo.controller.CreateList;

import java.util.ArrayList;

public class GamePage extends AppCompatActivity implements View.OnClickListener {

    String answerForTest = "";
    int cnt = 0;
    boolean judge = true;
    ArrayList<String> arrQuestion;
    ArrayList<String> arrAnswer;

    private TextView center;
    private ImageButton back;
    private TextView answer;
    private Button backmain;
    private Button[] buttons = new Button[13];
    private Button reviewBtn;
    private TextView score;

    public static boolean touch = false;

    GameView gameView;
    public static int Score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_page);

        showAlterDialog();
        gameView = findViewById(R.id.gameView);
        gameView.setGameListener(new GameView.GameListener() {

            @Override
            public void addScore() {
                runOnUiThread(new Runnable() {   // 切换至UI线程
                    @Override
                    public void run() {
                        ((TextView)findViewById(R.id.score)).setText("" + Score);
                    }
                });
            }

            @Override
            public void gameOver() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((TextView)findViewById(R.id.scoreText)).setText("分数:" + Score);
                        findViewById(R.id.relative).setVisibility(View.VISIBLE);
                    }
                });

            }

            @Override
            public void gameReady() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.relative).setVisibility(View.GONE);
                    }
                });
            }
        });


        findViewById(R.id.StartAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新开始
                findViewById(R.id.relative).setVisibility(View.GONE);
                gameView.reSet();//重新开始
                cnt = 0;
                center.setText(arrQuestion.get(cnt));
                answerForTest = "";
                answer.setText("");
                Score = 0;
                ((TextView)findViewById(R.id.score)).setText("" + 0);
            }
        });

        backmain = findViewById(R.id.BackMain);
        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.surfaceDestroyed(gameView.getHolder());
                Intent intent = new Intent(GamePage.this, MainAPPActivity.class);
                startActivity(intent);
                finish();
            }
        });




        center = findViewById(R.id.TestViewCenter);
        answer = findViewById(R.id.Answer);
        score = findViewById(R.id.score);


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

        //为每一个按钮设置监听事件
        for (int i = 0; i < 13; i++) {
            buttons[i].setOnClickListener(this);
        }

        //生成题目
//        CreateList sheng_cheng_qi = new CreateList();
//        sheng_cheng_qi.getList();
//        arrQuestion = CreateList.arrQuestion;
//        arrAnswer = CreateList.arrAnswer;

        int[] choice = new int[] {1,1,1,1,0,1,0,0,1};
        CreateList.setChoice(choice);
        CreateList sheng_cheng_qi = new CreateList();
        sheng_cheng_qi.getList();
        arrQuestion = CreateList.arrQuestion;
        arrAnswer = CreateList.arrAnswer;

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
                        cnt = 0;
                        CreateList sheng_cheng_qi = new CreateList();
                        sheng_cheng_qi.getList();
                        arrQuestion = CreateList.arrQuestion;
                        arrAnswer = CreateList.arrAnswer;
                    }
                    center.setText(arrQuestion.get(cnt));
                    answerForTest = "";
                    answer.setText("");

                    GameView.isDown = false;
                    Score++;
                    score.setText(Score+"");

                }else{
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

    private void showAlterDialog(){
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(GamePage.this);
        alterDiaglog.setTitle("欢迎来到算数世界");//文字
        alterDiaglog.setMessage("是否立即开始游戏,开始了就不会暂停了哦，而且没法退出哦~");//提示消息
        //立即开始
        alterDiaglog.setPositiveButton("立即开始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(GamePage.this,"点击开始游戏",Toast.LENGTH_SHORT).show();
                if(GameView.gameStatus == GameView.GAME_READY){
                    GameView.gameStatus = GameView.GAME_START;
                }
                center.setText(arrQuestion.get(0));
            }
        });

        //显示
        alterDiaglog.show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }


}