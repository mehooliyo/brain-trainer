package com.example.mehul.braintrainer;

/**
 * Created by MEHUL on 4/1/2017.
 */

public class Expression {

    private int leftOperand;
    private int rightOperand;
    private String operator;
    private int value;

    public Expression(int leftOperand, int rightOperand, String operator){
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
        this.value = this.calculateValue();
    }

    public String getExprString(){
        return String.format("%s %s %s", this.leftOperand, this.operator, this.rightOperand);
    }

    public int getValue(){
        return this.value;
    }

    private int calculateValue(){
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
