package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.ComputeRPN;
import com.example.appdemo.indi.csteam.mathxpro.generate.Information;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName PAndM.java
 * @date 19:30 2021/10/25
 */
public class GradeOne implements Grade{
    public String call(int point) {
        switch (point) {
            case 1:
                return plus();
            case 2:
                return blankPlus();
            case 3:
                return continuousPlusOrMinus();
            case 4:
                return minus();
        }

        return "1+1";
    }

    public String callAnswer(int point, String equ) {
        switch (point) {
            case 1:
            case 3:
            case 4:
                return Compute.getAnswer(equ);
            case 2:
                return Compute.getBlankP(equ);
        }

        return "2";
    }

    /**
     * 100以内单步加法
     */
    public String plus() {
        return new GenerateN(20, true, 0, 1, false).getEquation();
    }

    /**
     * 100以内单步减法
     */
    public String minus() {
        return new GenerateN(20, true, 1, 1, false).getEquation();
    }

    /**
     * 求未知加数：5+（）=9
     */
    public String blankPlus() {
        int min = 1;
        int max = 30;
        StringBuilder str;
        int digit = (int) (Math.random() * (max - min + 1) + min);
        int ans = (int) (Math.random() * (max - digit + 1 + 1) + digit + 1);
        int opera = 0;

        str = new StringBuilder(digit + ope[opera]);
        str.append("( )=").append(ans);

        return str.toString();
    }

    /**
     * 连续加或连续减
     */
    public String continuousPlusOrMinus() {
        int min = 1;
        int max = 30;
        int operator_num = 2;// 运算符号数
        int[] operator = new int[2], digit = new int[3];// 运算数据与符号
        StringBuilder str;

        int opera = (int) (Math.random() * 2);
        for (int j = 0; j < operator_num + 1; j++) {
            digit[j] = (int) (Math.random() * (max - min + 1) + min);
        }
        for (int j = 0; j < operator_num; j++) {
            operator[j] = opera;
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
        for (int j = 1; j < operator_num; j++) {
            str.append(ope[operator[j]]).append(digit[j + 1]);
        }

        while(!Information.validAnswer(100, 0, ComputeRPN.getAnswer(str.toString()))){
            for (int j = 0; j < operator_num + 1; j++) {
                digit[j] = (int) (Math.random() * (max - min + 1) + min);
            }
            for (int j = 0; j < operator_num; j++) {
                operator[j] = opera;
            }

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
            for (int j = 1; j < operator_num; j++) {
                str.append(ope[operator[j]]).append(digit[j + 1]);
            }
        }

        return str.toString();
    }
}
