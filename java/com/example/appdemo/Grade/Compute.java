package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.ComputeRPN;
import com.example.appdemo.indi.csteam.mathxpro.generate.Information;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName Compute.java
 * @date 22:16 2021/10/26
 */
public class Compute {
    public static String getAnswer(String equ){
        return ComputeRPN.getAnswer(equ);
    }

    public static String getComFacAnswer(String equ){
        int d1 = Integer.parseInt(equ.substring(0, equ.indexOf(',')));
        int d2 = Integer.parseInt(equ.substring(equ.indexOf(',') + 1));
        return Compute.getComFac(d1, d2);
    }

    public static String getComMtpAnswer(String equ){
        int d1 = Integer.parseInt(equ.substring(0, equ.indexOf(',')));
        int d2 = Integer.parseInt(equ.substring(equ.indexOf(',') + 1));
        return Compute.getComMtp(d1, d2);
    }

    /**
     * 计算求加数
     */
    public static String getBlankP(String equ){
        int endOfBefore = 0, beginOfAfter = 0;
        String be, af;
        boolean flag = false;
        char[] charOfEqu = equ.toCharArray();
        for (int i = 0; i < charOfEqu.length; i++) {
            if(Information.priority(charOfEqu[i]) != -1 && !flag){
                endOfBefore = i;
                flag = true;
            }
            else if(charOfEqu[i] == '='){
                beginOfAfter = i + 1;
            }
        }
        be = equ.substring(0, endOfBefore);
        af = equ.substring(beginOfAfter);

        return ComputeRPN.getAnswer(af + "-" + be);
    }

    /**
     * 计算求乘数
     */
    public static String getBlankMul(String equ){
        int endOfBefore = 0, beginOfAfter = 0;
        String be, af;
        boolean flag = false;
        char[] charOfEqu = equ.toCharArray();
        for (int i = 0; i < charOfEqu.length; i++) {
            if(Information.priority(charOfEqu[i]) != -1 && !flag){
                endOfBefore = i;
                flag = true;
            }
            else if(charOfEqu[i] == '='){
                beginOfAfter = i + 1;
            }
        }
        be = equ.substring(0, endOfBefore);
        af = equ.substring(beginOfAfter);

        return ComputeRPN.getAnswer(af + "÷" + be);
    }

    /**
     * 求最大公因数
     */
    public static String getComFac(int numerator, int denominator){
        int b = 1, c = 1;
        if(Math.abs(numerator)>Math.abs(denominator)) {
            b = Math.abs(numerator);
            c = Math.abs(denominator);
        }
        if(Math.abs(numerator)<Math.abs(denominator)) {
            b = Math.abs(denominator);
            c = Math.abs(numerator);
        }
        int mod = b % c;// 两数之模
        while(mod!=0) {
            b = c;
            c = mod;
            mod = b % c;
        }

        return String.valueOf(c);
    }

    /**
     * 求最小公倍数
     */
    public static String getComMtp(int numerator, int denominator){
        return String.valueOf((numerator * denominator) / Integer.parseInt(getComFac(numerator, denominator)));
    }
}
