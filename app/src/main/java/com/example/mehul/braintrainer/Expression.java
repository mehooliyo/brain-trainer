package com.example.mehul.braintrainer;

/**
 * Created by MEHUL on 4/1/2017.
 */

public class Expression {

    int leftOperand;
    int rightOperand;
    String operator;

    public Expression(int leftOperand, int rightOperand, String operator){
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
    }

    public String getExprString(){
        return String.format("%s %s %s", this.leftOperand, this.operator, this.rightOperand);
    }

    public int getValue(){
        int returnValue;

        switch(this.operator){
            case "+": returnValue = this.leftOperand + this.rightOperand;
                break;
            case "-": returnValue = this.leftOperand - this.rightOperand;
                break;
            default: returnValue = this.leftOperand + this.rightOperand; //TODO: what should default behavior be?
                break;
        }

        return returnValue;
    }

}
