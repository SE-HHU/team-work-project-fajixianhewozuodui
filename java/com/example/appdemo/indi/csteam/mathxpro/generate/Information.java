package com.example.appdemo.indi.csteam.mathxpro.generate;

/**
 * @author Derek
 * @version 1.00
 * @Description to get needed information
 * @ClassName Information.java
 * @date 15:44 2021/9/27
 */
public class Information {
    /**
     * 判断是否为整数
     * @param num 待判断字符串
     * @return 判断结果
     */
    public static boolean isDigit(String num){
        char[] charOfNum = num.toCharArray();
        for (char c : charOfNum) {
            if(!Character.isDigit(c)){
                return false;
            }
        }

        return true;
    }

    /**
     * 获取符号优先级
     * @param operator 一个字符串
     * @return 相应的优先级
     */
    public static int priority(String operator){
        switch (operator) {
            case "+" :
            case "-" :
                return 0;
            case "×" :
            case "÷" :
            case "*" :
            case "/" :
                return 1;
            case "(" :
            case ")" :
                return 2;
            default :
                return -1;
        }
    }

    /**
     * 获取符号优先级
     * @param operator 一个字符
     * @return 相应的优先级
     */
    public static int priority(char operator){
        switch (operator) {
            case '+' :
            case '-' :
                return 0;
            case '×' :
            case '÷' :
            case '*' :
            case '/' :
                return 1;
            case '(' :
            case ')' :
                return 2;
            default :
                return -1;
        }
    }

    /**
     * 判断算式是否合法
     * @param tempEqu 算式的字符串表达
     * @return 判断结果
     */
    public static boolean validEquation(String tempEqu){
        /* 算式出现异常 */
        try{
            ComputeRPN.getAnswer(tempEqu);
        }
        catch (Exception e){
            return false;
        }

        return true;
    }

    /**
     * 前提说明：此方法与getAnswer()配合使用
     * 判断答案是否合法
     * @param max 数据最大值
     * @param min 数据最小值
     * @param answer 算式答案
     * @return 答案是否符合要求
     */
    public static boolean validAnswer(int max, int min, String answer){
        if(answer.equals("No Meaning!")){
            return false;
        }
        /* 结果为负 */
        else if(answer.charAt(0) == '-'){
            return false;
        }
        /* 结果为分数 */
        else if(Fraction.isFraction(answer)){
            return false;
        }
        else{
            int s = Integer.parseInt(answer);
            return s >= min && s <= max;
        }
    }
}
