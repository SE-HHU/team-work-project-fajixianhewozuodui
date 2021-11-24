package com.example.appdemo.controller;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.appdemo.GradePage;
import com.example.appdemo.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.util.ArrayList;

public class getPDF extends Activity {

    private PDFView pdfView;
    private Button button_direct, button_wechat;
    private ImageButton button_back;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private String save_path, save_path_exes, save_path_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pdf);
        //生成pdf
        generatePdf();
        //获取动态权限
        getPermission();
        //查看pdf
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(new File(save_path_exes)).load();

        button_direct = findViewById(R.id.button_direct);
        button_wechat = findViewById(R.id.button_wechat);
        button_back = findViewById(R.id.PDFBack);

        button_direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPdfPrint(save_path_exes);
            }
        });

        button_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("document/*");
                Intent chooser = Intent.createChooser(intent, "发送到");
                try {
                    startActivity(chooser);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getPDF.this,"You don't have any app to open it. ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getPDF.this, GradePage.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void generatePdf() {
        //
        CreateList sheng_cheng_qi = new CreateList();
        sheng_cheng_qi.getList();
        ArrayList<String> exes_forTest = CreateList.arrQuestion;
        ArrayList<String> ans_forTest = CreateList.arrAnswer;
        //
        save_path = this.getExternalFilesDir(null).getPath();
        save_path_exes = save_path + "Test.pdf";
        save_path_answer = save_path + "Answer.pdf";
        try {
            toPdf to = new toPdf();
            //正常的生成PDF需要依次传入 题目链表, 答案链表, 年级, 题目路径, 答案路径
            to.generatePdf(exes_forTest,ans_forTest,CreateList.choice[CreateList.choice.length-1], save_path_exes,save_path_answer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void doPdfPrint(String filePath) {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        MyPrintPdfAdapter myPrintAdapter = new MyPrintPdfAdapter(filePath);
        printManager.print("jobName", myPrintAdapter, null);
    }

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 获取动态权限
     */
    public void getPermission(){
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getPDF.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(getPDF.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.d("yx","get permission");
                ActivityCompat.requestPermissions(getPDF.this,
                        PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
            Log.d("yx","get permission2");
            ActivityCompat.requestPermissions(getPDF.this,
                    PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }
        Log.d("yx","wait for PERMISSION_GRANTED");
        while (( ContextCompat.checkSelfPermission(getPDF.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))!= PackageManager.PERMISSION_GRANTED) {
        }
        Log.d("yx","wait for PERMISSION_GRANTED finish");
    }

}
