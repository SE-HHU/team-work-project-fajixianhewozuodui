package com.example.appdemo.controller;

import com.example.appdemo.Grade.EquationAndAnswer;
import com.example.appdemo.Grade.ForUse;
import com.example.appdemo.Grade.GradeOne;
import com.example.appdemo.Grade.GradeThree;
import com.example.appdemo.Grade.GradeTwo;

import java.util.ArrayList;

public class CreateList {

    public static int[] choice;
    private int equCount = 0;
    private int pointNumber = 0;
    private int eachCount = 0;
    public static ArrayList<String> arrQuestion;
    public static ArrayList<String> arrAnswer;

    public void getList(){
        //先遍历题目数量，得到具体的题目数量
        int start = choice.length - 1;
        for(int i = start-1; i > start-5; i--){
            if(choice[i] != 0){
                equCount = 10 * (i-start+5);
                break;
            }
        }
        //再遍历知识点，得到知识点的数量
        for(int i = start-5; i >= 0; i--){
            pointNumber += choice[i];
        }
        //为每道题目平均分配题目数量
        eachCount = equCount / pointNumber;

        //生成具体题目和答案
        arrQuestion = new ArrayList<String>();
        arrAnswer = new ArrayList<String>();
        createList();
    }

    //生成对应的题目序列, 这是供其它函数调用的
    public static void setChoice(int[] inChoice){
        choice = inChoice;
    }

    private void createList() {
        int start = choice.length - 1;
        switch (choice[choice.length - 1]) {
            case 1:
                //如果题目数量存在余数
                if (equCount % pointNumber != 0) {
                    boolean judge = true;
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            if (judge) {
                                for (int j = 0; j < eachCount + equCount % pointNumber; j++) {
                                    EquationAndAnswer eaa = ForUse.call(1,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                    judge = false;
                                }
                            } else {
                                for (int j = 0; j < eachCount; j++) {
                                    EquationAndAnswer eaa = ForUse.call(1,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            }
                        }
                    }
                }else{
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            for (int j = 0; j < eachCount; j++) {
                                EquationAndAnswer eaa = ForUse.call(1,i+1);
                                String equation = eaa.equation;
                                String answer = eaa.answer;
                                arrQuestion.add(equation);
                                arrAnswer.add(answer);
                            }
                        }
                    }
                }break;

            case 2:
                //如果题目数量存在余数
                if (equCount % pointNumber != 0) {
                    boolean judge = true;
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            if (judge) {
                                for (int j = 0; j < eachCount + equCount % pointNumber; j++) {
                                    EquationAndAnswer eaa = ForUse.call(2,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            } else {
                                for (int j = 0; j < eachCount; j++) {
                                    EquationAndAnswer eaa = ForUse.call(2,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            }
                        }
                    }
                }else{
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            for (int j = 0; j < eachCount; j++) {
                                EquationAndAnswer eaa = ForUse.call(2,i+1);
                                String equation = eaa.equation;
                                String answer = eaa.answer;
                                arrQuestion.add(equation);
                                arrAnswer.add(answer);
                            }
                        }
                    }
                }break;

            case 3:
                //如果题目数量存在余数
                if (equCount % pointNumber != 0) {
                    boolean judge = true;
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            if (judge) {
                                for (int j = 0; j < eachCount + equCount % pointNumber; j++) {
                                    EquationAndAnswer eaa = ForUse.call(3,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            } else {
                                for (int j = 0; j < eachCount; j++) {
                                    EquationAndAnswer eaa = ForUse.call(3,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            }
                        }
                    }
                }else{
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            for (int j = 0; j < eachCount; j++) {
                                EquationAndAnswer eaa = ForUse.call(3,i+1);
                                String equation = eaa.equation;
                                String answer = eaa.answer;
                                arrQuestion.add(equation);
                                arrAnswer.add(answer);
                            }
                        }
                    }
                }break;

            case 4:
                //如果题目数量存在余数
                if (equCount % pointNumber != 0) {
                    boolean judge = true;
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            if (judge) {
                                for (int j = 0; j < eachCount + equCount % pointNumber; j++) {
                                    EquationAndAnswer eaa = ForUse.call(4,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            } else {
                                for (int j = 0; j < eachCount; j++) {
                                    EquationAndAnswer eaa = ForUse.call(4,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            }
                        }
                    }
                }else{
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            for (int j = 0; j < eachCount; j++) {
                                EquationAndAnswer eaa = ForUse.call(4,i+1);
                                String equation = eaa.equation;
                                String answer = eaa.answer;
                                arrQuestion.add(equation);
                                arrAnswer.add(answer);
                            }
                        }
                    }
                }break;

            case 5:
                //如果题目数量存在余数
                if (equCount % pointNumber != 0) {
                    boolean judge = true;
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            if (judge) {
                                for (int j = 0; j < eachCount + equCount % pointNumber; j++) {
                                    EquationAndAnswer eaa = ForUse.call(5,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            } else {
                                for (int j = 0; j < eachCount; j++) {
                                    EquationAndAnswer eaa = ForUse.call(5,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            }
                        }
                    }
                }else{
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            for (int j = 0; j < eachCount; j++) {
                                EquationAndAnswer eaa = ForUse.call(5,i+1);
                                String equation = eaa.equation;
                                String answer = eaa.answer;
                                arrQuestion.add(equation);
                                arrAnswer.add(answer);
                            }
                        }
                    }
                }break;

            case 6:
                //如果题目数量存在余数
                if (equCount % pointNumber != 0) {
                    boolean judge = true;
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            if (judge) {
                                for (int j = 0; j < eachCount + equCount % pointNumber; j++) {
                                    EquationAndAnswer eaa = ForUse.call(6,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            } else {
                                for (int j = 0; j < eachCount; j++) {
                                    EquationAndAnswer eaa = ForUse.call(6,i+1);
                                    String equation = eaa.equation;
                                    String answer = eaa.answer;
                                    arrQuestion.add(equation);
                                    arrAnswer.add(answer);
                                }
                            }
                        }
                    }
                }else{
                    //遍历这个题目序列,找到被选中的知识点
                    for (int i = start - 5; i >= 0; i--) {
                        //如果这个知识点被选中了
                        if (choice[i] == 1) {
                            //具体生成题目的过程
                            for (int j = 0; j < eachCount; j++) {
                                EquationAndAnswer eaa = ForUse.call(6,i+1);
                                String equation = eaa.equation;
                                String answer = eaa.answer;
                                arrQuestion.add(equation);
                                arrAnswer.add(answer);
                            }
                        }
                    }
                }break;
        }
    }


}
