package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.ComputeRPN;
import com.example.appdemo.indi.csteam.mathxpro.generate.Equations;
import com.example.appdemo.indi.csteam.mathxpro.generate.Fraction;
import com.example.appdemo.indi.csteam.mathxpro.generate.Information;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName GradeThree.java
 * @date 20:09 2021/10/26
 */
public class GradeThree implements Grade{
    public String call(int point){
        switch (point){
            case 1:
                return multiple();
            case 2:
                return twoMixedWithin100(false);
            case 3:
                return twoMixedWithin100(true);
            case 4:
                return fractionPlusMinus();
        }
        return "1+1";
    }

    public String callAnswer(int point, String equ){
        switch (point){
            case 1:
            case 2:
            case 3:
            case 4:
                return Compute.getAnswer(equ);
        }
        return "2";
    }

    /**
     * 100以内乘法
     */
    public String multiple(){
        return new GenerateN(true, 2, 1, false).getEquation();
    }

    /**
     * 两步混合运算(100以内)
     * @param hasBracket 是否带括号
     */
    public String twoMixedWithin100(boolean hasBracket){
        int min = 1;
        int max = 99;
        int operator_num = 2;// 运算符号数
        int[] operator = new int[2],digit = new int[3];// 运算数据与符号
        StringBuilder str;

        /* 随机数据 */
        for (int j = 0; j < operator_num+1; j++) {
            digit[j] = (int) (Math.random() * (max - min + 1) + min);
        }
        /* 运算符 */
        operator[0] = (int)(Math.random() * 4);
        if(operator[0] == 0 || operator[0] == 1){
            operator[1] = (int)(Math.random() * 2 + 2);
        }
        else {
            operator[1] = (int)(Math.random() * 2);
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
        str.append(ope[operator[1]]).append(digit[2]);

        if(hasBracket){
            str = new StringBuilder(new Equations().insertBrackets(str.toString()));
        }

        while(!Information.validAnswer(max, min, ComputeRPN.getAnswer(str.toString()))){
            /* 随机数据 */
            for (int j = 0; j < operator_num+1; j++) {
                digit[j] = (int) (Math.random() * (max - min + 1) + min);
            }
            /* 运算符 */
            operator[0] = (int)(Math.random() * 3);
            if(operator[0] == 0 || operator[0] == 1){
                operator[1] = 2;
            }
            else {
                operator[1] = (int)(Math.random() * 2);
            }

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
            for (int j = 1; j < operator_num; j++) {
                str.append(ope[operator[j]]).append(digit[j + 1]);
            }

            if(hasBracket){
                str = new StringBuilder(new Equations().insertBrackets(str.toString()));
            }
        }

        return str.toString();
    }

    /**
     * 简单分数加减法
     */
    public String fractionPlusMinus(){
        int[] operator = new int[1];
        String[] digit = new String[2];
        StringBuilder str;

        int a = (int)(Math.random() * 9 + 1);
        int b = (int)(Math.random() * (9 - (a+1) + 1) + a+1);
        digit[0] = new Fraction(a, b).getFraction();
        digit[1] = new Fraction((int)(Math.random() * b + 1), b * (int)(Math.random() * 3 + 1)).getFraction();

        operator[0] = (int)(Math.random() * 2);

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);

        while (ComputeRPN.getAnswer(str.toString()).charAt(0) == '-'){
            a = (int)(Math.random() * 9 + 1);
            b = (int)(Math.random() * (9 - (a+1) + 1) + a+1);
            digit[0] = new Fraction(a, b).getFraction();
            digit[1] = new Fraction((int)(Math.random() * 9 + 1), b * (int)(Math.random() * 3 + 1)).getFraction();

            operator[0] = (int)(Math.random() * 2);

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
        }

        return str.toString();
    }
}
