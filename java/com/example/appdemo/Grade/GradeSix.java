package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.Fraction;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName GradeSix.java
 * @date 16:23 2021/10/27
 */
public class GradeSix implements Grade{
    public String call(int point){
        switch (point){
            case 1:
                return fractionAndInt(2);
            case 2:
                return fractionMulFra(1);
            case 3:
                return fractionMulFra(2);
            case 4:
                return fractionAndInt(3);
            case 5:
                return divideFraction();
            case 6:
                return fractionMixed();
        }
        return "1+1";
    }

    public String callAnswer(int point, String equ){
        switch (point){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return Compute.getAnswer(equ);
        }
        return "2";
    }

    /**
     * 分数与整数乘或除
     * @param opera 运算符：2，乘；3，除
     */
    public String fractionAndInt(int opera){
        int[] operator = new int[3];
        String[] digit = new String[4];

        int a = (int)(Math.random() * 50 + 1);
        int b = (int)(Math.random() * (50 - (a+1) + 1) + a+1);
        digit[0] = new Fraction(a, b).getFraction();
        digit[1] = String.valueOf((int)(Math.random() * 99 + 1));

        operator[0] = opera;

        StringBuilder str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);

        while(opera == 2 && Compute.getComFac(b, Integer.parseInt(digit[1])).equals("1")
                || opera == 3 && Compute.getComFac(a, Integer.parseInt(digit[1])).equals("1")){
            a = (int)(Math.random() * 50 + 1);
            b = (int)(Math.random() * (50 - (a+1) + 1) + a+1);
            digit[0] = new Fraction(a, b).getFraction();
            digit[1] = String.valueOf((int)(Math.random() * 99 + 1));

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
        }

        return str.toString();
    }

    /**
     * 分数与分数相乘
     * @param operator_num 运算符个数
     */
    public String fractionMulFra(int operator_num){
        int[] operator = new int[3];
        String[] digit = new String[4];
        int[] a = new int[4], b = new int[4];

        for (int i = 0; i < operator_num; i++) {
            operator[i] = 2;
        }

        for (int i = 0; i < operator_num + 1; i++) {
            a[i] = (int)(Math.random() * 10 + 1);
            b[i] = (int)(Math.random() * (10 - (a[i]+1) + 1) + a[i]+1);
            digit[i] = new Fraction(a[i], b[i]).getFraction();
        }

        while((operator_num == 1
                && Compute.getComFac(a[0], b[1]).equals("1")
                && Compute.getComFac(a[1], b[0]).equals("1"))
                || (operator_num == 2
                && Compute.getComFac(a[0], b[1]).equals("1")
                && Compute.getComFac(a[1], b[0]).equals("1")
                && Compute.getComFac(a[1], b[2]).equals("1")
                && Compute.getComFac(a[2], b[1]).equals("1")
                && Compute.getComFac(a[0], b[2]).equals("1")
                && Compute.getComFac(a[2], b[0]).equals("1"))){
            for (int i = 0; i < operator_num + 1; i++) {
                a[i] = (int)(Math.random() * 10 + 1);
                b[i] = (int)(Math.random() * (10 - (a[i]+1) + 1) + a[i]+1);
                digit[i] = new Fraction(a[i], b[i]).getFraction();
            }
        }

        StringBuilder str = new StringBuilder(digit[0] + ope[operator[0]] + "(" + digit[1] + ")");
        for (int i = 1; i < operator_num; i++) {
            str.append(ope[operator[i]]).append("(").append(digit[i + 1]).append(")");
        }

        return str.toString();
    }

    /**
     * 一个数除以分数
     */
    public String divideFraction(){
        int operator;
        String[] digit = new String[2];
        int[] a = new int[2], b = new int[2];
        String str;

        for (int i = 0; i < 2; i++) {
            a[i] = (int)(Math.random() * 10 + 1);
            b[i] = (int)(Math.random() * (10 - a[i]+1 + 1) + a[i]+1);
            digit[i] = new Fraction(a[i], b[i]).getFraction();
        }

        operator = 3;

        str = digit[0] + ope[operator] + "(" + digit[1] + ")";

        return str;
    }

    /**
     * 三步四则混合运算
     */
    public String fractionMixed(){
        int[] operator = new int[3];
        String[] digit = new String[4];
        int[] a = new int[4], b = new int[4];

        int operator_num = (int)(Math.random() * (3 - 2 + 1) + 2);

        for (int j = 0; j < operator_num; j++) {
            operator[j] = (int)(Math.random() * 4);
        }

        for (int i = 0; i < operator_num + 1; i++) {
            a[i] = (int)(Math.random() * 10 + 1);
            b[i] = (int)(Math.random() * (10 - a[i]+1 + 1) + a[i]+1);
            digit[i] = new Fraction(a[i], b[i]).getFraction();
        }

        StringBuilder str = new StringBuilder(digit[0]);
        for (int j = 0; j < operator_num; j++) {
            str.append(ope[operator[j]]).append("(").append(digit[j + 1]).append(")");
        }

        while(Compute.getAnswer(str.toString()).charAt(0) == '-'){
            for (int i = 0; i < operator_num + 1; i++) {
                a[i] = (int)(Math.random() * 10 + 1);
                b[i] = (int)(Math.random() * (10 - a[i]+1 + 1) + a[i]+1);
                digit[i] = new Fraction(a[i], b[i]).getFraction();
            }

            str = new StringBuilder(digit[0]);
            for (int j = 0; j < operator_num; j++) {
                str.append(ope[operator[j]]).append("(").append(digit[j + 1]).append(")");
            }
        }

        return str.toString();
    }
}
