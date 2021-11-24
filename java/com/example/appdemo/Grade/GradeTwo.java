package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.ComputeRPN;
import com.example.appdemo.indi.csteam.mathxpro.generate.Information;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName GradeTwo.java
 * @date 16:44 2021/10/26
 */
public class GradeTwo implements Grade{
    public String call(int point){
        switch (point){
            case 1:
                return multipleAndPlusMinus();
            case 2:
                return blankMultiple();
            case 3:
                return mixed4();
            case 4:
                return plusAndMinusWithin1000();
        }
        return "1+1";
    }

    public String callAnswer(int point, String equ){
        switch (point){
            case 1:
            case 3:
            case 4:
                return Compute.getAnswer(equ);
            case 2:
                return Compute.getBlankMul(equ);
        }
        return "2";
    }


    /**
     * 乘加，乘减
     */
    public String multipleAndPlusMinus(){
        int min = 1;
        int max = 50;
        int operator_num = 2;// 运算符号数
        int[] operator = new int[2],digit = new int[3];// 运算数据与符号

        for (int j = 0; j < operator_num+1; j++) {
            digit[j] = (int) (Math.random() * (max - min + 1) + min);
        }
        operator[0] = (int)(Math.random() * 3);
        if(operator[0] == 0 || operator[0] == 1){
            operator[1] = 2;
        }
        else {
            operator[1] = (int)(Math.random() * 2);
        }

        StringBuilder str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);
        for (int j = 1; j < operator_num; j++) {
            str.append(ope[operator[j]]).append(digit[j + 1]);
        }

        while(!Information.validAnswer(max, min, ComputeRPN.getAnswer(str.toString()))){
            for (int j = 0; j < operator_num+1; j++) {
                digit[j] = (int) (Math.random() * (max - min + 1) + min);
            }
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
        }

        return str.toString();
    }

    /**
     * 求未知乘数
     */
    public String blankMultiple(){
        int min = 1;
        int max = 9;
        StringBuilder str;
        int digit = (int)(Math.random()*(max - min +1)+ min);
        int ans = digit * (int)(Math.random() * 10);
        int opera = 2;

        str = new StringBuilder(digit + ope[opera]);
        str.append("( )=").append(ans);

        return str.toString();
    }

    /**
     * 四则混合运算
     */
    public String mixed4(){
        return new GenerateN(4, 2, false).getEquation();
    }

    /**
     * 1000以内加减法
     */
    public String plusAndMinusWithin1000(){
        return new GenerateN(999, 2, 1, false).getEquation();
    }
}
