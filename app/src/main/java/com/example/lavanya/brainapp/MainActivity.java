package com.example.lavanya.brainapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton;
    ArrayList<Integer> answers=new ArrayList<>();
    int locationforcorrectanswer;
    TextView correcttext;
    TextView scoretext;
    TextView sumtext;
    TextView timertext;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playagain;
    int score=0;
    int incorrectanswer;
    int numberofquestions=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton= (Button) findViewById(R.id.startbutton);
         sumtext= (TextView) findViewById(R.id.sumtext);
         button1= (Button) findViewById(R.id.button1);
         button2=(Button) findViewById(R.id.button2);
         button3= (Button) findViewById(R.id.button3);
         button4=(Button)findViewById(R.id.button4);
        correcttext=(TextView)findViewById(R.id.correcttext);
        scoretext= (TextView) findViewById(R.id.scoretext);
        timertext= (TextView) findViewById(R.id.timertext);
        playagain= (Button) findViewById(R.id.playagain);

        generatequestion();
        new CountDownTimer(30000+100,1000){ //adjustment for 30 sec

            @Override
            public void onTick(long l) {
                int sec= (int)l/1000;
                timertext.setText(Integer.toString(sec) +"s");
            }

            @Override
            public void onFinish() {
                timertext.setText("0s");
                correcttext.setText("Well Done" + Integer.toString(score) +"/" + Integer.toString(numberofquestions));
                playagain.setVisibility(View.VISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                scoretext.setVisibility(View.INVISIBLE);
                timertext.setVisibility(View.INVISIBLE);
                sumtext.setVisibility(View.INVISIBLE);

            }
        }.start();

    }
    public void startgame(View view){
        startbutton.setVisibility(View.INVISIBLE);
    }
    public void chooseanswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationforcorrectanswer))){
            correcttext.setText("Correct Answer");
            score++;
        }
        else{
            correcttext.setText("Wrong Answer");
        }
        numberofquestions++;
        scoretext.setText(Integer.toString(score) +"/" + Integer.toString(numberofquestions));
        generatequestion();
    }

    public void generatequestion(){
        Random rand= new Random();
        int a=rand.nextInt(21);
        int b= rand.nextInt(21);
        sumtext.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationforcorrectanswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationforcorrectanswer){
                answers.add(a+b);
            }
            else {

                incorrectanswer = rand.nextInt(41);
               // System.out.print(incorrectanswer);
                while (incorrectanswer == a + b) {
                    incorrectanswer = rand.nextInt();
                }
               // Log.i("the random number",Integer.toString(incorrectanswer));
                answers.add(incorrectanswer);
            }
        }
        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));

    }

    public void playagain(View view){
        score=0;
        numberofquestions=0;
        timertext.setText("30s");
        scoretext.setText("0/0");
        playagain.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        scoretext.setVisibility(View.VISIBLE);
        timertext.setVisibility(View.VISIBLE);
        sumtext.setVisibility(View.VISIBLE);
        generatequestion();
        new CountDownTimer(30000+100,1000){ //adjustment for 30 sec

            @Override
            public void onTick(long l) {
                int sec= (int)l/1000;
                timertext.setText(String.valueOf(sec) +"s");
            }

            @Override
            public void onFinish() {
                timertext.setText("0s");
                correcttext.setText("Well Done" + Integer.toString(score) +"/" + Integer.toString(numberofquestions));
            }
        }.start();
    }
}
