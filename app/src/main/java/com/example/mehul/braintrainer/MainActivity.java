package com.example.mehul.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int TIMER_LENGTH = 10000;
    private static final int TIMER_INTERVAL = 1000;

    private int correct = 0;
    private int attempts = 0;
    private int solutionValue;
    private CountDownTimer timer;

    private TextView exprView;
    private TextView statusView;
    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exprView = (TextView) findViewById(R.id.exprView);
        statusView = (TextView) findViewById(R.id.statusView);
        scoreView = (TextView) findViewById(R.id.scoreView);

        timer = new CountDownTimer(TIMER_LENGTH, TIMER_INTERVAL){
            TextView timerView = (TextView) findViewById(R.id.timerView);

            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(millisUntilFinished / TIMER_INTERVAL));
            }

            public void onFinish() {
                timerView.setText("0");
                attempts++;
                updateState();
            }

        };

        updateState();
    }

    private void updateState(){
        //update score with current attempts
        scoreView.setText(correct + "/" + attempts);

        //generate new expression and update expression view
        Expression expr = getRandomExpression();
        exprView.setText(expr.getExprString());
        solutionValue = expr.getValue();

        //generate new random solutions
        populateSolutionViews(expr, getSolutionViews());
        
        timer.start();
    }

    public void onSolutionAttempt(View v){
        TextView view = (TextView) v;
        int clickedAnswer = Integer.parseInt(view.getText().toString());
        boolean isCorrect = clickedAnswer == solutionValue;

        if(isCorrect){
            statusView.setText(R.string.correct);
            correct++;
        } else {
            statusView.setText(R.string.wrong);
        }

        attempts++;

        timer.cancel();
        updateState();
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    private Expression getRandomExpression(){
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

    private void populateSolutionViews(Expression mainExpr, List<TextView> solViews){
        List<Integer> values = new ArrayList<>();
        int actualValue = mainExpr.getValue();
        values.add(actualValue);

        for(int i=1; i<solViews.size(); i++){
            int fakeValue;
            do {
                fakeValue = randInt(0, 10); //generate random value to add/subtract
                if (getRandomBoolean())
                    fakeValue = actualValue + fakeValue;
                else
                    fakeValue = actualValue - fakeValue;
            } while (fakeValue == actualValue); //redo calculation to prevent the actual value showing twice
            values.add(fakeValue);
        }

        Collections.shuffle(values);

        for(int i=0; i<solViews.size(); i++){
            TextView view = solViews.get(i);
            int valueToSet = values.get(i);

            view.setText(String.valueOf(valueToSet));
        }
    }

}
