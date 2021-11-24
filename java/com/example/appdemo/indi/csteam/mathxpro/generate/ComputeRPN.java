package com.example.appdemo.indi.csteam.mathxpro.generate;

import java.util.Stack;

/**
 * @author Derek
 * @version 1.00
 * @Description to compute by RPN
 * @ClassName ComputeRPN.java
 * @date 15:10 2021/9/27
 */
public class ComputeRPN {
    /**
     * 获取一个中序算式的计算结果
     * @param equation 一个中序算式
     * @return 相应的计算结果
     */
    public static String getAnswer(String equation){
        ComputeRPN computer = new ComputeRPN();
        return computer.answerFromStack(RPN.transformToRPN(equation));
    }

    /**
     * 由逆波兰式计算算式结果
     * @param RPNOfEqu 一个算式的逆波兰表达式
     * @return 相应的计算结果
     */
    public String answerFromStack(Stack<String> RPNOfEqu){
        Stack<String> result = new Stack<>();
        for(String str : RPNOfEqu){
            if(Information.isDigit(str)){
                result.push(str);
            }
            else{
                String first, second, t = "1";
                second = result.pop();
                first = result.pop();
                switch (str){
                    case "+":
                        t = add(first,second);
                        break;
                    case "-" :
                        t = minus(first,second);
                        break;
                    case "*":
                    case "×" :
                        t = multiply(first,second);
                        break;
                    case "/":
                    case "÷":
                        t = divide(first,second);
                        break;
                }
                result.push(t);
            }
        }

        return result.pop();
    }

    /**
     * 两个数的加法运算
     * @param a 一个数
     * @param b 另一个数
     * @return 加法运算结果
     */
    public String add(String a,String b){
        Fraction fa,fb,r;
        if(Fraction.isFraction(a)|| Fraction.isFraction(b)){
            fa = Fraction.transform(a);
            fb = Fraction.transform(b);
            r = new Fraction(fa.numerator*fb.denominator+fa.denominator*fb.numerator,fa.denominator*fb.denominator);

            return r.getFraction();
        }
        else return String.valueOf(Integer.parseInt(a)+Integer.parseInt(b));
    }

    /**
     * 两个数的减法运算
     * @param a 一个数
     * @param b 另一个数
     * @return 减法运算结果
     */
    public String minus(String a,String b){
        Fraction fa,fb,r;
        if(Fraction.isFraction(a)|| Fraction.isFraction(b)){
            fa = Fraction.transform(a);
            fb = Fraction.transform(b);
            r = new Fraction(fa.numerator*fb.denominator-fa.denominator*fb.numerator,fa.denominator*fb.denominator);

            return r.getFraction();
        }
        else return String.valueOf(Integer.parseInt(a)-Integer.parseInt(b));
    }

    /**
     * 两个数的乘法运算
     * @param a 一个数
     * @param b 另一个数
     * @return 乘法运算结果
     */
    public String multiply(String a,String b){
        Fraction fa,fb,r;
        if(Fraction.isFraction(a)|| Fraction.isFraction(b)){
            fa = Fraction.transform(a);
            fb = Fraction.transform(b);
            r = new Fraction(fa.numerator*fb.numerator,fa.denominator*fb.denominator);

            return r.getFraction();
        }
        else return String.valueOf(Integer.parseInt(a)*Integer.parseInt(b));
    }

    /**
     * 两个数的除法运算
     * @param a 一个数
     * @param b 另一个数
     * @return 除法运算结果
     */
    public String divide(String a,String b){
        Fraction fa,fb,r;
        if(Fraction.isFraction(a) || Fraction.isFraction(b)){
            fa = Fraction.transform(a);

            if(Fraction.isFraction(b)){
                int endIndex = b.indexOf('/');
                if(endIndex == -1){
                    endIndex = b.indexOf('÷');
                }
                //b要转化为倒数
                int bd = Integer.parseInt(b.substring(0, endIndex)),
                        bn = Integer.parseInt(b.substring(endIndex + 1));
                fb = new Fraction(bn, bd);
            }
            else fb = new Fraction(1, Integer.parseInt(b));

            r = new Fraction(fa.numerator*fb.numerator,fa.denominator*fb.denominator);

        }
        else{
            r = new Fraction(Integer.parseInt(a),Integer.parseInt(b));
        }

        return r.getFraction();
    }
}
