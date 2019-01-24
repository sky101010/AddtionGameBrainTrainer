package com.example.lenore.addtiongamebraintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView timeTextView;
    TextView additionTextView;
    TextView scoreTextView;
    TextView resultTextView;
    GridLayout gridLayoutId;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ConstraintLayout layoutId;

    int total=0;
    int score=0;

    boolean activate = false;


    int a, b;
    ArrayList <Integer> ans = new ArrayList<Integer>(4);
    int rightAns;


    public void reset(){
        total = 0;
        score = 0;
        nextQuestion();
        timeTextView.setText("30s");
        resultTextView.setText("");

        scoreTextView.setText(score+"/"+total);

    }

    public void startClicked(View view){
        activate = true;
        reset();
        startButton.setVisibility(View.GONE);

        layoutId.setVisibility(View.VISIBLE);


        new CountDownTimer(30100,500){
            @Override
            public void onTick(long l){
                String time = String.valueOf(l/1000)+"s";
                timeTextView.setText(time);
            }

            @Override
            public void onFinish(){
                resultTextView.setText("Done!");
                startButton.setText("Play again!");
                startButton.setVisibility(View.VISIBLE);
                activate= false;

            }

        }.start();
    }

    public void numberClicked(View view){
        if (activate) {
            ++total;
            String response = view.getTag().toString();
            if (response.equals(Integer.toString(rightAns))) {
                resultTextView.setText("correct!");
                ++score;

            } else {
                resultTextView.setText("wrong:(");

            }
            scoreTextView.setText(score + "/" + total);
            nextQuestion();
        } else {

        }

    }

    public void nextQuestion(){
        ans.clear();
        Random rand = new Random();
        a =rand.nextInt(21);
        b =rand.nextInt(21);
        String question = Integer.toString(a)+"+"+ Integer.toString(b);

        rightAns = rand.nextInt(4);


        for(int j = 0; j<4;++j){
            int fakeAns = rand.nextInt(41);
            while (fakeAns == a+b) {fakeAns = rand.nextInt(41);}
            while (ans.contains(fakeAns)){fakeAns = rand.nextInt(21);}
            ans.add(fakeAns);
            Log.i("fakeAns",j+" : "+Integer.toString(fakeAns));
           // ans.set(j,fakeAns);
        }
        ans.set(rightAns,a+b);


        additionTextView.setText(question);

        String zero = Integer.toString(ans.get(0));
        button0.setText(zero);
        button1.setText(Integer.toString(ans.get(1)));
        button2.setText(ans.get(2)+"");
        button3.setText(ans.get(3)+"");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButtonId);

        additionTextView = findViewById(R.id.additionTextView);
        timeTextView = findViewById(R.id.timeTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);

        gridLayoutId = (GridLayout) findViewById(R.id.gridLayoutId);
        button0 = findViewById(R.id.number0);
        button1 = findViewById(R.id.number1);
        button2 = findViewById(R.id.number2);
        button3 = findViewById(R.id.number3);

        layoutId = findViewById(R.id.layoutId);

        layoutId.setVisibility(View.INVISIBLE);


        reset();

    }
}
