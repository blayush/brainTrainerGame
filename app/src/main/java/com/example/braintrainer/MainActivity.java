package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button submitButton;
    TextView timertextview;
    TextView scoretextview;
    TextView questiontextview;
    TextView opt1textview;
    TextView opt2textview;
    TextView opt3textview;
    TextView opt4textview;
    TextView resulttextview;
    TextView lastQuestiontextView;
    int totalcorrect=0;
    int totalquestions=0;
    Random rand=new Random();
    int number1;
    int number2;
    int correctanswer;
    int correcttag;
    Boolean timeover=false;

    Boolean started=false;

    public void timer(int n){
        timertextview=findViewById(R.id.timertextview);
        submitButton = findViewById(R.id.submitbutton);
        lastQuestiontextView=findViewById(R.id.lastQuestiontextView);
        new CountDownTimer(n*1000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timertextview.setText(Long.toString(millisUntilFinished/1000));
            }
            public void onFinish(){
            MediaPlayer mediaPlayerbuzzer=MediaPlayer.create(getApplicationContext(),R.raw.buzzer);
            mediaPlayerbuzzer.start();
            resulttextview.setText(scoretextview.getText().toString());
            timeover=true;
            started=false;
            lastQuestiontextView.setText("FINAL RESULT :");
            submitButton.setAlpha(1);
            totalcorrect=0;
            totalquestions=0;
            submitButton.setText("START AGAIN");

            }
        }.start();
    }
    public void newquestion(){
        ArrayList<Integer> optionnumbers=new ArrayList<>();
        opt1textview= findViewById(R.id.opt1textview);
        opt2textview= findViewById(R.id.opt2textview);
        opt3textview= findViewById(R.id.opt3textview);
        opt4textview= findViewById(R.id.opt4textview);
        questiontextview= findViewById(R.id.questiontextview);
        number1= rand.nextInt(51);
        number2=rand.nextInt(51);
        correctanswer=number1+number2;
        correcttag=rand.nextInt(4);
        questiontextview.setText(MessageFormat.format("{0} + {1}", number1, number2));
        for(int i=0;i<4;i++){
            if (i==correcttag)optionnumbers.add(correctanswer);
            else {
                int randnum=rand.nextInt(101);
                if(randnum==number1)optionnumbers.add(randnum+1);
                else if(randnum==number2)optionnumbers.add(randnum+1);
                else optionnumbers.add(randnum);
            }
        }
        int t1=optionnumbers.get(0);
        String o1=Integer.toString(t1);
        int t2=optionnumbers.get(1);
        String o2=Integer.toString(t2);
        int t3=optionnumbers.get(2);
        String o3=Integer.toString(t3);
        int t4=optionnumbers.get(3);
        String o4=Integer.toString(t4);

        opt1textview.setText(o1);
        opt2textview.setText(o2);
        opt3textview.setText(o3);
        opt4textview.setText(o4);

    }
    public void checkanswer(View view){
     resulttextview= findViewById(R.id.resulttextview);
     scoretextview= findViewById((R.id.scoretextview));
        if(started){
            MediaPlayer clickingSound = MediaPlayer.create(this, R.raw.choose);
            clickingSound.start();
            String checkTag = view.getTag().toString();
            if (checkTag.equals(Integer.toString(correcttag))) {
                resulttextview.setText("Correct!");
                totalcorrect++;
            } else {
                resulttextview.setText("Wrong!");
            }
            totalquestions++;
            scoretextview.setText(MessageFormat.format("{0}/{1}", Integer.toString(totalcorrect), Integer.toString(totalquestions)));
            newquestion();
        }
    }
    public void startgamefun(View view){
        submitButton = findViewById(R.id.submitbutton);
        timertextview= findViewById(R.id.timertextview);
        scoretextview= findViewById(R.id.scoretextview);
        questiontextview= findViewById(R.id.questiontextview);
        opt1textview= findViewById(R.id.opt1textview);
        opt2textview= findViewById(R.id.opt2textview);
        opt3textview= findViewById(R.id.opt3textview);
        opt4textview= findViewById(R.id.opt4textview);
        resulttextview=findViewById(R.id.resulttextview);
        lastQuestiontextView=findViewById(R.id.lastQuestiontextView);
        Log.i("clicked","true");
        if(!started){
            lastQuestiontextView.setText("Last Question :");
            lastQuestiontextView.setAlpha(1);
            resulttextview.setText("Not yet Answered");
            scoretextview.setText("0/0");
            started=true;
            submitButton.setAlpha(0);
            newquestion();
            timer(30);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView startingImageview=findViewById(R.id.startingImageView);
        ConstraintLayout gameLayout=findViewById(R.id.gameLayout);
        startingImageview.animate().alpha(0.5f).translationYBy(-1200).setDuration(9000);
        gameLayout.animate().alpha(1).setDuration(9200);
    }
}