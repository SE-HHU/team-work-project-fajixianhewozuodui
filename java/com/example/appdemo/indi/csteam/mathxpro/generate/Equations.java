package com.example.appdemo.indi.csteam.mathxpro.generate;

import java.util.ArrayList;

/**
 * @author Derek
 * @version 1.00
 * @Description 获取相应个数且符合需求的算式
 * @ClassName Equations.java
 * @date 16:45 2021/9/27
 */
public class Equations {
    private int max;//最大值
    private int min;//最小值
    private int count;//算式个数
    private boolean hasBracket;//是否有括号
    private final int OPE_NUM = 3;//运算符的最多个数
    private final String[] ope = {"+","-","×","÷"};//运算符

    /**
     * 构造相应参数的实例对象
     * @param n 算式个数
     * @param ma 最大值
     * @param mi 最小值
     * @param h 是否含括号
     */
    public Equations(int n,int ma,int mi,boolean h){
        count = n;// 算式个数
        max = ma;
        min = mi;
        hasBracket = h;
    }

    public Equations(){}

    /**
     * 生成一个符合数据要求且不含括号的中间式
     * @param opeNum 运算符个数
     * @return 不含括号的中间式
     */
    public String tempEqu(int opeNum){
        int[] operator = new int[opeNum],digit = new int[opeNum+1];// 运算数据与符号
        boolean breakPoint = false;//循环是否退出

        StringBuilder str = new StringBuilder("1+1");
        /* do-while循环代码块，生成一个算式，并判断合法性，直到生成合法算式 */
        until:
        while(!breakPoint){
            /* 随机生成符号 */
            for (int i = 0; i < opeNum; i++){
                operator[i] = (int)(Math.random() * 4);
            }
            /* 随机生成数据 */
            for (int i = 0; i < opeNum +1; i++){
                /* 0参与四则运算实际意义不大 */
                if(min <= 0){
                    digit[i] = (int)(Math.random() * max + 1);//[1,max]
                }
                else{
                    digit[i] = (int)(Math.random() * (max - min + 1) + min);//[min,max]
                }
            }

            /* 将数据与符号进行拼接 */
            str = new StringBuilder(String.valueOf(digit[0]));
            for (int i = 0; i < opeNum; i++) {
                /* 1× */
                if(digit[i] == 1 && ope[operator[i]].equals("×")){
                    continue until;
                }
                /* ×1或÷1 */
                if(Information.priority(ope[operator[i]]) == 1 && digit[i +1] == 1){
                    continue until;
                }

                str.append(ope[operator[i]]).append(digit[i +1]);
            }

            /* 判断算式及答案是否合法 */
            if(!(Information.validEquation(str.toString())
                    && Information.validAnswer(max,min, ComputeRPN.getAnswer(str.toString())))){
                continue;
            }
            breakPoint = true;
        }

        return str.toString();
    }

    /**
     * 对不含括号的临时式进行插入括号操作，控制括号内必须含有加减
     * @param tempEqu 不含括号的临时式
     * @return 含有括号的算式
     */
    public String insertBrackets(String tempEqu){
        /* 临时的（位置，第二次遍历时的起始位置，临时括号前一位的运算符下标，更新数组下标 */
        int tempBracket = 0, indexOfFirst = 0, before = 0, k = 0;
        /* 是否出现过加减，是否添加过临时（，是否满足添加括号条件（后面出现了低一级或同级运算符） */
        boolean flag0 = false, hasTB = false, flag = false;
        /* 括号的开始位置与结束位置 */
        int[] begin = new int[OPE_NUM], end = new int[OPE_NUM];
        char[] charTemp = tempEqu.toCharArray();

        /* 类似（1+2）*3的括号 */
        for (int i = 0; i < charTemp.length; i++) {
            /* 是乘除，并且之前出现过加减，并且没有出现过乘除 */
            if(Information.priority(charTemp[i]) == 1 && flag0){
                end[0] = i + 1;//begin[0]初始化已赋值为0
                k++;
                indexOfFirst = i;
                before = i;
                break;
            }
            if(Information.priority(charTemp[i]) == 0){
                flag0 = true;
            }
            /* 如果运行至此句，则乘除号之前没有出现过加减 */
            else if(Information.priority(charTemp[i]) == 1){
                break;
            }
        }
        /* 其他一般位置的括号 */
        for (int i = indexOfFirst; i < charTemp.length; i++) {
            /* 是运算符 */
            if(Information.priority(charTemp[i]) != -1){
                /* 没有添加过临时（ */
                if(!hasTB){
                    tempBracket = i + 1 + k*2;
                    hasTB = true;
                    before = i;
                }
                /* 添加过临时（，并且满足添加括号条件 */
                else if(flag){
                    /* 先记录下括号位置 */
                    begin[k] = tempBracket;
                    end[k] = i + 1 + k*2;
                    k++;

                    /* 再在当前乘除之后，添加新的临时（ */
                    tempBracket = i+ 1 + k*2;
                    before = i;
                }
                /* 满足添加括号的条件即为：后面出现了低一级或同级运算符 */
                else if(Information.priority(charTemp[i]) <= Information.priority(charTemp[before])){
                    flag = true;
                }
            }
        }
        /* 到了数组末尾，并且添加过临时（，并且满足添加括号条件 */
        if(hasTB && flag){
            begin[k] = tempBracket;
            end[k] = charTemp.length + 1 + k*2;
            k++;
        }

        StringBuilder result = new StringBuilder(tempEqu);
        for (int i = 0; i < k; i++) {
            /* 随机是否插入括号 */
            int hasBracket = 1;//(int)(Math.random() * 2);
            if(hasBracket == 1){
                result.insert(begin[i], "(");
                result.insert(end[i], ")");
            }
            /* 不插入括号，则之后的括号位置前移2位 */
            else{
                for (int j = i + 1; j < k; j++) {
                    begin[j] -= 2;
                    end[j] -= 2;
                }
            }
        }

        return result.toString();
    }
}
