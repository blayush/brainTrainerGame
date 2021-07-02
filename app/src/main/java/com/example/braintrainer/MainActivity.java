package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button submitbutton;
    TextView timertextview;
    TextView scoretextview;
    TextView questiontextview;
    TextView opt1textview;
    TextView opt2textview;
    TextView opt3textview;
    TextView opt4textview;
    TextView resulttextview;
    int totalcorrect=0;
    int totalquestions=0;
    Random rand=new Random();
    int number1;
    int number2;
    int correctanswer;
    int correcttag;

    Boolean started=false;


    public void newquestion(){
        ArrayList<Integer> optionnumbers=new ArrayList<Integer>();
        opt1textview=(TextView)findViewById(R.id.opt1textview);
        opt2textview=(TextView)findViewById(R.id.opt2textview);
        opt3textview=(TextView)findViewById(R.id.opt3textview);
        opt4textview=(TextView)findViewById(R.id.opt4textview);
        questiontextview=(TextView)findViewById(R.id.questiontextview);
        number1= rand.nextInt(51);
        number2=rand.nextInt(51);
        correctanswer=number1+number2;
        correcttag=rand.nextInt(4);
        questiontextview.setText(Integer.toString(number1)+" + "+Integer.toString(number2) );
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
     resulttextview=(TextView)findViewById(R.id.resulttextview);
     scoretextview=(TextView)findViewById((R.id.scoretextview));
     MediaPlayer clickingSound=MediaPlayer.create(this,R.raw.choose);
     clickingSound.start();
     String checkTag=view.getTag().toString();
     if(checkTag.equals(Integer.toString(correcttag))){
           resulttextview.setText("Correct!");
           totalcorrect++;
     }
     else {
         resulttextview.setText("Wrong!");
     }
     totalquestions++;
     scoretextview.setText(Integer.toString(totalcorrect)+"/"+Integer.toString(totalquestions));
     newquestion();
    }
    public void startgamefun(View view){
        submitbutton = (Button)findViewById(R.id.submitbutton);
        timertextview=(TextView)findViewById(R.id.timertextview);
        scoretextview=(TextView)findViewById(R.id.scoretextview);
        questiontextview=(TextView)findViewById(R.id.questiontextview);
        opt1textview=(TextView)findViewById(R.id.opt1textview);
        opt2textview=(TextView)findViewById(R.id.opt2textview);
        opt3textview=(TextView)findViewById(R.id.opt3textview);
        opt4textview=(TextView)findViewById(R.id.opt4textview);

        Log.i("clicked","true");
        if(!started){
            started=true;
            submitbutton.setText("SUBMIT");
            newquestion();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}