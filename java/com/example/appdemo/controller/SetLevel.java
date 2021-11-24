package com.example.appdemo.controller;

import java.util.ArrayList;

public class SetLevel {
    public static ArrayList<String> setLevel(String pending) {
        ArrayList<String> array = new ArrayList<String>();
        int length = pending.length();
        int judgeFraction = 0;
        char[] values = pending.toCharArray();
        for (int i = 0; i < length; i++) {
            if (values[i] == '/') {
                judgeFraction++;
            }
        }

        String strUp = "";
        String strCenter = "";
        String strDown = "";
        String placeHolder = "&#12288;";


        if (judgeFraction == 1) { //如果是只有一个分数
            //用来判断 除法 和 运算符的顺序
            boolean judgeIndex = true;

            int indexOperation = 0;
            for (int i = 0; i < length; i++) {
                boolean judge1 = (values[i] == '+' || values[i] == '-' || values[i] == '×' || values[i] == '÷');
                if (judge1) {
                    indexOperation = i;
                }
            }

            int indexDiv = pending.indexOf('/');
            if (indexDiv == Math.max(indexDiv, indexOperation)) {
                judgeIndex = false;
            } else {
                judgeIndex = true;
            }



            if(indexOperation == 0){
                boolean judgeNumerator = true;
                int temp1 = 0;
                int temp2 = 0;
                for(int i=0; i<length; i++){
                    if(Character.isDigit(values[i])){
                        if(judgeNumerator){       //此时是分子
                            strUp += values[i];
                            temp1++;
                        }else {
                            strDown += values[i];
                            temp2++;
                        }
                    }else {
                        if(values[i] == '/'){
                            judgeNumerator = false;
                        }
                    }
                }
                int temp = Math.max(temp1,temp2);
                if(temp != 0){
                    if(temp==1){
                        strCenter += " — ";
                    }else if(temp==2){
                        strCenter += " 一 ";
                    }
                }
                strDown.substring(0,1);
                array.add(strUp);
                array.add(strCenter);
                array.add(strDown);
                return array;

            }

            if (judgeIndex) { //如果分数在前面
                boolean judgeNumerator = true;
                boolean judgeChoice = true;    //就优先保存分数
                int temp1 = 0;
                int temp2 = 0;
                for (int i = 0; i < length; i++) {     //开始遍历，按逻辑分
                    if (Character.isDigit(values[i])) {
                        if (judgeChoice) {
                            if (judgeNumerator) {    //先存分子
                                strUp += values[i];
                                temp1++;
                            } else {                 //再存分母
                                strDown += values[i];
                                temp2++;
                            }
                        } else {
                            if(values[i] == '-'){    //存除分数以外的数
                                strCenter += "—";
                                strUp += "  ";
                                strDown += "  ";
                            }else {
                                strCenter += values[i];
                                strUp += "  ";
                                strDown += "  ";
                            }

                        }
                    } else {
                        if (values[i] == '/') {     //如果检测到了 / 就开始存分母
                            judgeNumerator = false;
                        }
                        boolean judge = (values[i] == '+' || values[i] == '-' || values[i] == '×' || values[i] == '÷');
                        if (judge) {
                            int temp = Math.max(temp1, temp2);
                            if(temp==1){
                                strCenter += "—";
                            }else if(temp==2){
                                strCenter += "一";
                            }
                            if(values[i] == '-'){
                                judgeChoice = false;
                                strCenter += " " + "—" + " ";
                                strUp += " " + "    ";
                                strDown += " " + "    ";
                            }else if(values[i] == '+' || values[i] == '×' || values[i] == '÷'){
                                judgeChoice = false;
                                strCenter += " " + values[i] + " ";
                                strUp += " " + "    ";
                                strDown += " " + "    ";
                            }
                        }
                    }
                }
            } else {         //上述情况的镜像
                boolean judgeNumerator = true;
                int temp1 = 0;
                int temp2 = 0;
                boolean judgeChoice = false;
                for (int i = 0; i < length; i++) {
                    if (Character.isDigit(values[i])) {
                        if (judgeChoice) {
                            if (judgeNumerator) {
                                strUp += values[i];
                                temp1++;
                            } else {
                                strDown += values[i];
                                temp2++;
                            }
                        } else {
                            if(values[i] == '-'){    //存除分数以外的数
                                strCenter += "—";
                                strUp += "  ";
                                strDown += "  ";
                            }else {
                                strCenter += values[i];
                                strUp += "  ";
                                strDown += "  ";
                            }
                        }
                    } else {
                        if (values[i] == '/') {
                            judgeNumerator = false;
                        }
                        boolean judge = (values[i] == '+' || values[i] == '-' || values[i] == '×' || values[i] == '÷');
                        if (judge) {
                            if(values[i] == '-'){
                                judgeChoice = true;
                                strCenter += " " + "—" + " ";
                                strUp += " " + "    ";
                                strDown += " " + "    ";
                            }else if(values[i] == '+' || values[i] == '×' || values[i] == '÷'){
                                judgeChoice = true;
                                strCenter += " " + values[i] + " ";
                                strUp += " " + "    ";
                                strDown += " " + "    ";
                            }
                        }
                    }
                }
                int temp = Math.max(temp1, temp2);
                if(temp==1){
                    strCenter += "—";
                }else if(temp==2){
                    strCenter += "一";
                }
            }
        }


        //如果分数不只有一个
        if(judgeFraction != 1){
            boolean judgeNumerator = true;
            int temp1 = 0;
            int temp2 = 0;
            for(int i=0; i<length; i++){
                if(Character.isDigit(values[i])){
                    if(judgeNumerator){       //此时是分子
                       strUp += values[i];
                       temp1++;
                    }else {
                        strDown += values[i];
                        temp2++;
                    }
                }else {
                    if(values[i] == '/'){
                        judgeNumerator = false;
                    }
                    boolean judge = (values[i] == '+' || values[i] == '-' || values[i] == '×' || values[i] == '÷');
                    if (judge) {
                        judgeNumerator = true;

                        int temp = Math.max(temp1, temp2);
                        if(temp==1){
                            strCenter += "—";
                        }else if(temp==2){
                            strCenter += "一";
                        }

                        if(values[i] == '-'){
                            temp1 = 0;
                            temp2 = 0;
                            strCenter += " " + "—" + " ";
                            strUp += "     ";
                            strDown += "    ";
                        }else if(values[i] == '+' || values[i] == '×' || values[i] == '÷'){
                            temp2 = 0;
                            temp1 = 0;
                            strCenter += " " + values[i] + " ";
                            strUp += "     ";
                            strDown += "    ";
                        }
                    }
                }
            }
            int temp = Math.max(temp1,temp2);
            if(temp != 0){
                if(temp==1){
                    strCenter += "—";
                }else if(temp==2){
                    strCenter += "一";
                }
            }
        }
        strDown.substring(0,1);
        array.add(strUp);
        array.add(strCenter);
        array.add(strDown);
        return array;
    }
}