package com.example.appdemo.indi.csteam.mathxpro.generate;

/**
 * @author Derek
 * @version 1.00
 * @Description to deal with fractions
 * @ClassName Fraction.java
 * @date 15:24 2021/9/27
 */
public class Fraction {
    /** 分子，分母*/
    int numerator,denominator;

    /**
     * 传入分子和分母构建分数
     * @param a 传入分子
     * @param b 传入分母
     */
    public Fraction(int a, int b){
        numerator = a;
        denominator = b;
    }

    /**
     * 获取分数的字符串表达
     * @return 经过约分化简后的分式或除式的字符串
     */
    public String getFraction(){
        /* 对特殊分式先处理 */
        if(denominator==0) {
            return "No Meaning!";
        }
        if(numerator==0) {
            return "0";
        }
        if(numerator==denominator) {
            return "1";
        }
        if(numerator+denominator==0) {
            return "-1";
        }

        /* 对一般分式约分化简 */
        int b = 1,c = 1;
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
        numerator /= c;
        denominator /= c;

        /* 分母为1或-1 */
        if(denominator==1) {
            return String.valueOf(numerator);
        }
        if(denominator==-1) {
            return String.valueOf(-numerator);
        }
        if(numerator*denominator<0) {
            return "-" + Math.abs(numerator) + "/" + Math.abs(denominator);
        }
        return numerator + "/" + denominator;
    }

    /**
     * 判断是否为假分数
     * @return 布尔值作为判断结果
     */
    public boolean isFalseFraction(){
        return numerator > denominator && Fraction.isFraction(getFraction());
    }

    /**
     * 判断是否是分数
     * @param s 一个数的字符串表达
     * @return 布尔值作为判断结果
     */
    public static boolean isFraction(String s){
        int i = s.indexOf('÷');
        int j = s.indexOf('/');
        return (i != -1 || j!=-1);
    }

    /**
     * 将所有数转化为分数，将整数转化为分母为1的分数，便于运算
     * @param a 一个数的字符串表达
     * @return 一个相应分数的实例对象
     */
    public static Fraction transform(String a){
        Fraction fa;

        if(Fraction.isFraction(a)){
            int endIndex = a.indexOf('/');
            if(endIndex == -1){
                endIndex = a.indexOf('÷');
            }
            int an = Integer.parseInt(a.substring(0,endIndex)),
                    ad = Integer.parseInt(a.substring(endIndex + 1));
            fa = new Fraction(an,ad);
            return fa;
        }

        fa = new Fraction(Integer.parseInt(a),1);

        return fa;
    }
}
