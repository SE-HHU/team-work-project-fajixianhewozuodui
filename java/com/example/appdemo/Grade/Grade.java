package com.example.appdemo.Grade;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName Grade.java
 * @date 14:15 2021/11/13
 */
public interface Grade {
    public static final String[] ope = {"+", "-", "×", "÷"};

    /**
     * 根据知识点调用相应方法
     * @param point 知识点序号
     * @return 生成题目的字符串
     */
    public abstract String call(int point);

    /**
     * 根据知识点计算相应题目的答案
     * @param point 知识点序号
     * @param equ 所需计算的题目
     * @return 答案的字符串
     */
    public abstract String callAnswer(int point, String equ);
}
