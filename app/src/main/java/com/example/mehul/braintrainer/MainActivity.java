package com.example.mehul.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView exprView = (TextView) findViewById(R.id.exprView);
        Expression expr = getRandomExpression();
        exprView.setText(expr.getExprString());

        CountDownTimer timer = new CountDownTimer(5000, 1000){
            TextView timerView = (TextView) findViewById(R.id.timerView);

            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                timerView.setText("0");
            }

        };

        timer.start();
    }

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Expression getRandomExpression(){
        int leftOperand = randInt(0,100);
        int rightOperand = randInt(0, 100);
        String operator = "+";

        return new Expression(leftOperand, rightOperand, operator);
    }

    private List<TextView> getSolutionViews(){
        List<TextView> viewList = new ArrayList<>();

        TableLayout solTable = (TableLayout) findViewById(R.id.solTable);
        for(int i=0; i < solTable.getChildCount(); i++) {
            TableRow tableRow = (TableRow) solTable.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                TextView solView = (TextView) tableRow.getChildAt(j);
                viewList.add(solView);
            }
        }

        return viewList;
    }
}
