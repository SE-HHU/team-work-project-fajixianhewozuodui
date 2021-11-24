package com.example.appdemo.indi.csteam.mathxpro.generate;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Derek
 * @version 1.00
 * @Description 将字符串算式转化为逆波兰表达式，以栈的形式返回
 * @ClassName RPN.java
 * @date 12:52 2021/9/27
 */
public class RPN {
    /**
     * 将中序表达式转化为逆波兰式，并分项存放到栈中
     * @param equation 一个中序表达式，不含空格
     * @return 存放了相应逆波兰式的栈
     */
    public static Stack<String> transformToRPN(String equation){
        Stack<String> operator = new Stack<>();
        Stack<String> reversePolish = new Stack<>();
        ArrayList<String> stringArray = toStringArray(equation);

        for (String i : stringArray) {
            if(Information.isDigit(i)){
                reversePolish.push(i);
            }
            else if(operator.empty() || i.equals("(") || operator.peek().equals("(")){
                operator.push(i);
            }
            else if(i.equals(")")){
                while(!operator.peek().equals("(")){
                    reversePolish.push(operator.pop());
                }
                operator.pop();//将"("出栈
            }
            else if(Information.priority(i) > Information.priority(operator.peek())){
                operator.push(i);
            }
            else if(Information.priority(i) <= Information.priority(operator.peek())){
                while(!operator.empty()
                        && !operator.peek().equals("(")
                        && Information.priority(i) <= Information.priority(operator.peek())){
                    reversePolish.push(operator.pop());
                }
                operator.push(i);
            }
        }

        while(!operator.empty()){
            reversePolish.push(operator.pop());
        }

        return reversePolish;
    }

    /**
     * 将算式中的数字和运算符分开，并存放到表中
     * @param equation 算式的字符串表达
     * @return 转化后的算式
     */
    public static ArrayList<String> toStringArray(String equation){
        ArrayList<String> newString = new ArrayList<>();
        char[] charOfEquation = equation.toCharArray();
        StringBuilder digit = new StringBuilder();
        for (char c : charOfEquation) {
            if (Character.isDigit(c)) {
                digit.append(c);
            } else if (digit.length() != 0) {
                newString.add(digit.toString());
                digit = new StringBuilder();
                newString.add(Character.toString(c));
            } else {
                newString.add(Character.toString(c));
            }
        }
        if(digit.length() != 0){
            newString.add(digit.toString());
        }

        return newString;
    }
}
