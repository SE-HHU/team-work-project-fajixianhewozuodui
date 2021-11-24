package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.ComputeRPN;
import com.example.appdemo.indi.csteam.mathxpro.generate.Fraction;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName GradeSix.java
 * @date 9:23 2021/10/27
 */
public class GradeFive implements Grade{
    public String call(int point){
        switch (point){
            case 1:
            case 2:
                return common();
            case 3:
                return reduction();
            case 4:
                return fractionPM();
            case 5:
                return fractionMixed();
        }
        return "1+1";
    }

    public String callAnswer(int point, String equ){
        switch (point){
            case 1:
                return Compute.getComFacAnswer(equ);
            case 2:
                return Compute.getComMtpAnswer(equ);
            case 3:
            case 4:
            case 5:
                return Compute.getAnswer(equ);
        }
        return "2";
    }


    /**
     * 公因数，公倍数
     */
    public String common(){
        int min = 2;
        int max = 99;
        int digit1, digit2;
        digit1 = (int)(Math.random() * (max - min + 1) + min);
        digit2 = (int)(Math.random() * (max - min + 1) + min);

        String str = digit1 + "," + digit2;

        while(Compute.getComFac(digit1, digit2).equals("1")){
            digit1 = (int)(Math.random() * (max - min + 1) + min);
            digit2 = (int)(Math.random() * (max - min + 1) + min);

            str = digit1 + "," + digit2;
        }

        return str;
    }

    /**
     * 约分
     */
    public String reduction(){
        int min = 2;
        int max = 99;
        int digit1, digit2;
        digit1 = (int)(Math.random() * (max - min + 1) + min);
        digit2 = (int)(Math.random() * (max - min + 1) + min);

        String str = digit1 + "/" + digit2;

        while (Compute.getComFac(digit1, digit2).equals("1")){
            digit1 = (int)(Math.random() * (max - min + 1) + min);
            digit2 = (int)(Math.random() * (max - min + 1) + min);

            str = digit1 + "/" + digit2;
        }

        return str;
    }

    /**
     * 单步异分母分数加减
     */
    public String fractionPM(){
        int[] digit = new int[4];
        int operator;
        for (int j = 0; j < 3; j += 2) {
            digit[j] = (int)(Math.random() * 10 + 1);
            digit[j+1] = (int)(Math.random() * (10 - (digit[j]+1) + 1) + digit[j]+1);
        }
        operator = (int)(Math.random()*2);

        String str = new Fraction(digit[0], digit[1]).getFraction()
                + ope[operator]
                + new Fraction(digit[2], digit[3]).getFraction();

        while(ComputeRPN.getAnswer(str).charAt(0) == '-' || digit[1] == digit[3]){
            for (int j = 0; j < 3; j += 2) {
                digit[j] = (int)(Math.random() * 10 + 1);
                digit[j+1] = (int)(Math.random() * (10 - (digit[j]+1) + 1) + digit[j]+1);
            }
            operator = (int)(Math.random()*2);

            str = new Fraction(digit[0], digit[1]).getFraction()
                    + ope[operator]
                    + new Fraction(digit[2], digit[3]).getFraction();
        }

        return str;
    }

    /**
     * 两步分数加减混合运算
     */
    public String fractionMixed(){
        int[] digit = new int[6];
        int[] operator = new int[2];

        for (int j = 0; j < 5; j += 2) {
            digit[j] = (int)(Math.random() * 10 + 1);
            digit[j+1] = (int)(Math.random() * (10 - (digit[j]+1) + 1) + digit[j]+1);
        }
        for (int j = 0; j < 2; j++) {
            operator[j] = (int)(Math.random()*2);
        }

        String str = new Fraction(digit[0], digit[1]).getFraction()
                + ope[operator[0]]
                + new Fraction(digit[2], digit[3]).getFraction()
                + ope[operator[1]]
                + new Fraction(digit[4], digit[5]).getFraction();

        while(ComputeRPN.getAnswer(str).charAt(0) == '-'){
            for (int j = 0; j < 5; j += 2) {
                digit[j] = (int)(Math.random() * 10 + 1);
                digit[j+1] = (int)(Math.random() * (10 - (digit[j]+1) + 1) + digit[j]+1);
            }
            for (int j = 0; j < 2; j++) {
                operator[j] = (int)(Math.random()*2);
            }

            str = new Fraction(digit[0], digit[1]).getFraction()
                    + ope[operator[0]]
                    + "(" + new Fraction(digit[2], digit[3]).getFraction() + ")"
                    + ope[operator[1]]
                    + "(" + new Fraction(digit[4], digit[5]).getFraction() + ")";
        }

        return str;
    }
}
