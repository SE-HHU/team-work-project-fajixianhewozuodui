package com.example.appdemo.Grade;

/**
 * @author Derek
 * @version 1.00
 * @Description TODO
 * @ClassName ForUse.java
 * @date 16:10 2021/11/14
 */
public class ForUse {
    public static EquationAndAnswer call(int Grade, int point){
        Grade grade;
        switch (Grade){
            case 1 :
                grade = new GradeOne();
                break;
            case 2 :
                grade = new GradeTwo();
                break;
            case 3 :
                grade = new GradeThree();
                break;
            case 4 :
                grade = new GradeFour();
                break;
            case 5 :
                grade = new GradeFive();
                break;
            case 6 :
                grade = new GradeSix();
                break;
            default :
                grade = new GradeOne();
                break;
        }

        String equ = grade.call(point);

        return new EquationAndAnswer(equ, grade.callAnswer(point, equ));
    }
}
