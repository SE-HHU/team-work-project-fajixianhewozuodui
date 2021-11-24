package com.example.appdemo.Grade;

import com.example.appdemo.indi.csteam.mathxpro.generate.ComputeRPN;
import com.example.appdemo.indi.csteam.mathxpro.generate.Equations;
import com.example.appdemo.indi.csteam.mathxpro.generate.Information;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName GradeFour.java
 * @date 23:11 2021/10/26
 */
public class GradeFour implements Grade{
    public String call(int point){
        switch (point){
            case 1:
                return divideTwo();
            case 2:
                return twoMixedBeyond100(false);
            case 3:
                return twoMixedBeyond100(true);
            case 4:
                return commAndAssP();
            case 5:
                return commAndAssMul();
            case 6:
                return distributionMul();
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
     * 两、三位数除两位数
     */
    public String divideTwo(){
        int[] operator = new int[1];
        int[] digit = new int[2];
        StringBuilder str;

        operator[0] = 3;

        digit[1] = (int)(Math.random() * (99 - 10 + 1) + 10);
        digit[0] = digit[1] * (int)(Math.random() * 30 + 1);

        while (digit[0] > 999){
            digit[1] = (int)(Math.random() * (99 - 10 + 1) + 10);
            digit[0] = digit[1] * (int)(Math.random() * 30 + 1);
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]);

        return str.toString();
    }

    /**
     * 两步混合运算（100以上）
     * @param hasBracket 是否带括号
     */
    public String twoMixedBeyond100(boolean hasBracket){
        int min = 1;
        int max = 200;
        int operator_num = 2;// 运算符号数
        int[] operator = new int[2],digit = new int[3];// 运算数据与符号
        StringBuilder str;

        operator[0] = (int)(Math.random() * 3);
        if(hasBracket){
            if(operator[0] == 0){
                digit[0] = (int)(Math.random() * (max - min + 1) + min);
                digit[1] = (int)(Math.random() * (5 - digit[0]/100 + 1) + digit[0]/100) * 100 - digit[0];
                digit[2] = (int)(Math.random() * (20 - 2 + 1) + 2);
                operator[1] = 2;
            }
            else if(operator[0] == 1){
                digit[1] = (int)(Math.random() * (max - min + 1) + min);
                digit[0] = digit[1] + (int)(Math.random() * 5 + 1) * 100;
                digit[2] = (int)(Math.random() * (20 - 2 + 1) + 2);
                operator[1] = 2;
            }
            else {
                digit[0] = (int)(Math.random() * 9 + 1);
                operator[1] = (int)(Math.random() * 2);
                if(operator[1] == 0){
                    digit[1] = (int)(Math.random() * (max - min + 1) + min);
                    digit[2] = (int)(Math.random() * (5 - digit[0]/100 + 1) + digit[0]/100) * 100 - digit[0];
                }
                else {
                    digit[2] = (int)(Math.random() * (max - min + 1) + min);
                    digit[1] = digit[2] + (int)(Math.random() * 5 + 1) * 100;
                }
            }
        }
        else {
            if(operator[0] == 0 || operator[0] == 1){
                digit[1] = (int)(Math.random() * 5 + 1) * 10;
                digit[2] = (int)(Math.random() * 5 + 1) * 10;
                digit[0] = (int)(Math.random() * (max - min + 1) + min);
                operator[1] = 2;
            }
            else {
                digit[0] = (int)(Math.random() * 5 + 1) * 10;
                digit[1] = (int)(Math.random() * 5 + 1) * 10;
                digit[2] = (int)(Math.random() * (max - min + 1) + min);
                operator[1] = (int)(Math.random() * 2);
            }
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1] + ope[operator[1]] + digit[2]);

        if(hasBracket){
            str = new StringBuilder(new Equations().insertBrackets(str.toString()));
        }

        return str.toString();
    }

    /**
     * 加减法交换结合律
     */
    public String commAndAssP(){
        int min = 1;
        int max = 999;
        int operator_num = 2;// 运算符号数
        int[] operator = new int[2],digit = new int[3];// 运算数据与符号
        boolean f1, f2, f3;
        StringBuilder str;

        digit[0] = (int)(Math.random() * (max - min + 1) + min);
        digit[1] = (int)(Math.random() * (max - min + 1) + min);
        digit[2] = (int)(Math.random() * (max - min + 1) + min);
        boolean f11 = (digit[1] + digit[2]) % 100 == 0 || (digit[0] + digit[2]) % 100 == 0;
        f1 = f11;
        f2 = (digit[0] - digit[2]) % 100 ==0;
        f3 = (digit[1] - digit[2]) % 100 ==0;
        if(f1){
            for (int j = 0; j < operator_num; j++) {
                operator[j] = 0;
            }
        }
        else if(f2){
            operator[0] = (int)(Math.random() * 2);
            operator[1] = 1;
        }
        else if(f3){
            operator[0] = 0;
            operator[1] = 1;
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1] + ope[operator[1]] + digit[2]);

        while(!Information.validAnswer(max, min, ComputeRPN.getAnswer(str.toString())) || (!f1 && !f2 && !f3)){
            digit[0] = (int)(Math.random() * (max - min + 1) + min);
            digit[1] = (int)(Math.random() * (max - min + 1) + min);
            digit[2] = (int)(Math.random() * (max - min + 1) + min);
            f1 = f11;
            f2 = (digit[0] - digit[2]) % 100 ==0;
            f3 = (digit[1] - digit[2]) % 100 ==0;
            if(f1){
                for (int j = 0; j < operator_num; j++) {
                    operator[j] = 0;
                }
            }
            else if(f2){
                operator[0] = (int)(Math.random() * 2);
                operator[1] = 1;
            }
            else if(f3){
                operator[0] = 0;
                operator[1] = 1;
            }

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1] + ope[operator[1]] + digit[2]);
        }

        return str.toString();
    }

    /**
     * 乘法交换结合律
     */
    public String commAndAssMul(){
        int min = 2;
        int max = 50;
        int operator_num = 2;// 运算符号数
        int[] operator = new int[2],digit = new int[3];// 运算数据与符号
        StringBuilder str;
        boolean flag;

        digit[0] = (int)(Math.random() * (max - min + 1) + min);
        digit[1] = (int)(Math.random() * (max - min + 1) + min);
        digit[2] = (int)(Math.random() * (max - min + 1) + min);
        boolean flag1 = (digit[1] * digit[2]) % 100 == 0 || (digit[0] * digit[2]) % 100 == 0;
        flag = flag1;
        if(flag){
            for (int j = 0; j < operator_num; j++) {
                operator[j] = 2;
            }
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1] + ope[operator[1]] + digit[2]);

        while(!Information.validAnswer(9999, 1, ComputeRPN.getAnswer(str.toString())) || !flag){
            digit[0] = (int)(Math.random() * (max - min + 1) + min);
            digit[1] = (int)(Math.random() * (max - min + 1) + min);
            digit[2] = (int)(Math.random() * (max - min + 1) + min);
            flag1 = (digit[1] * digit[2]) % 100 == 0 || (digit[0] * digit[2]) % 100 == 0;
            flag = flag1;
            if(flag){
                for (int j = 0; j < operator_num; j++) {
                    operator[j] = 2;
                }
            }

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1] + ope[operator[1]] + digit[2]);
        }

        return str.toString();
    }

    /**
     * 乘法分配律
     */
    public String distributionMul(){
        int min = 2;
        int max = 500;
        int[] operator = new int[3],digit = new int[4];// 运算数据与符号
        StringBuilder str;
        boolean f1, f2;

        digit[0] = (int)(Math.random() * (max - min + 1) + min);
        digit[1] = (int)(Math.random() * (max - min + 1) + min);
        digit[2] = (int)(Math.random() * (max - min + 1) + min);
        f1 = (digit[1] + digit[2]) % 100 ==0;
        f2 = (digit[1] - digit[2]) % 100 ==0;
        if(f1){
            operator[0] = 2;
            operator[1] = 0;
        }
        else if(f2){
            operator[0] = 2;
            operator[1] = 1;
        }

        str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]
                + ope[operator[1]] + digit[0]
                + ope[operator[0]] + digit[2]);

        while(!Information.validAnswer(999, 1, ComputeRPN.getAnswer(str.toString())) || (!f1 && !f2)){
            digit[0] = (int)(Math.random() * (max - min + 1) + min);
            digit[1] = (int)(Math.random() * (max - min + 1) + min);
            digit[2] = (int)(Math.random() * (max - min + 1) + min);
            f1 = (digit[1] + digit[2]) % 100 ==0;
            f2 = (digit[1] - digit[2]) % 100 ==0;
            if(f1){
                operator[0] = 2;
                operator[1] = 0;
            }
            else if(f2){
                operator[0] = 2;
                operator[1] = 1;
            }

            str = new StringBuilder(digit[0] + ope[operator[0]] + digit[1]
                    + ope[operator[1]] + digit[0]
                    + ope[operator[0]] + digit[2]);

        }

        return str.toString();
    }
}
