package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    TextView questionTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;

    ConstraintLayout gameLayout;

    ArrayList<Integer> answerArrayList = new ArrayList<Integer>();

    public void start(View view)
    {
       goButton.setVisibility(View.INVISIBLE);
       gameLayout.setVisibility(View.VISIBLE);
       playAgain((TextView)findViewById(R.id.scoreTextView));

    }

    public void answerCall(View view)
    {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        {
            resultTextView.setText("CORRECT!");
            score++;
        }
        else
        {
            resultTextView.setText("Wrong :( ");
        }
        numberOfQuestions++;

        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        newQuestion();
        resultTextView.setVisibility(View.VISIBLE);
    }

    public void newQuestion()
    {
        Random  rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        locationOfCorrectAnswer = rand.nextInt(4);

        questionTextView.setText(Integer.toString(a)+ " + " +Integer.toString(b) );

        answerArrayList.clear();

        for(int i = 0; i < 4 ; i++ )
        {
            if(i == locationOfCorrectAnswer)
            {
                answerArrayList.add(a + b);
            }
            else
            {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(41);
                }
                answerArrayList.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answerArrayList.get(0)));
        button1.setText(Integer.toString(answerArrayList.get(1)));
        button2.setText(Integer.toString(answerArrayList.get(2)));
        button3.setText(Integer.toString(answerArrayList.get(3)));

    }

    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);

        newQuestion();

        new CountDownTimer(30100,1000)
        {
            public void onTick(long l )
            {
                timerTextView.setText(String.valueOf(l / 1000)+ "s");
            }
            public void onFinish()
            {

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);


                resultTextView.setText(" Finished ");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button)findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);

        questionTextView = (TextView)findViewById(R.id.questionTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        gameLayout = (ConstraintLayout)findViewById(R.id.gameLayout);


        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        resultTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}