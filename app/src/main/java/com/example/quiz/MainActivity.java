package com.example.quiz;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView questionLabel;
    private RadioButton radioButtonVar11;
    private RadioButton radioButtonVar12;
    private RadioButton radioButtonVar13;
    private RadioGroup radioGroup;

    private String rightAnswer;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;
    private int progressStatus = 0;
    ProgressBar _progressBar;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String[][] quizData = new String[5][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _progressBar = (ProgressBar)findViewById(R.id.progressBar);
            int counter = 0;
            try {
                XmlPullParser parser = getResources().getXml(R.xml.contacts);

                while (parser.getEventType()!= XmlPullParser.END_DOCUMENT) {

                    if (parser.getEventType() == XmlPullParser.START_TAG
                            && parser.getName().equals("question")) {
                        String[] tempArr = {
                                parser.getAttributeValue(0),
                                parser.getAttributeValue(1),
                                parser.getAttributeValue(2),
                                parser.getAttributeValue(3)
                        };

                        if(tempArr[0] != null){
                            quizData[counter] = tempArr;
                            counter += 1;
                        }
                    }
                    parser.next();
                }
            }
            catch (Throwable t) {

            }

        questionLabel = (TextView)findViewById(R.id.questionLabel);
        radioButtonVar11 = (RadioButton)findViewById(R.id.radioButtonVar11);
        radioButtonVar12 = (RadioButton)findViewById(R.id.radioButtonVar12);
        radioButtonVar13 = (RadioButton)findViewById(R.id.radioButtonVar13);
	  radioGroup = (RadioGroup)findViewById(R.id.radioGroup);


        for (int i = 0; i < quizData.length; i++){
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);

            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }
    public void showNextQuiz() {
        progressStatus +=1;
        _progressBar.setProgress(progressStatus);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(3);
        quiz.remove(0);

        Collections.shuffle(quiz);
        radioButtonVar11.setText(quiz.get(0));
        radioButtonVar12.setText(quiz.get(1));
        radioButtonVar13.setText(quiz.get(2));
        quizArray.remove(randomNum);
    }
    public void checkAnswer(View view){
        RadioButton answerBtn = (RadioButton)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        if (btnText.equals(rightAnswer)){
            result.increaseCounter();
        }

	  radioGroup.clearCheck();
        if (quizCount == QUIZ_COUNT) {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            startActivity(intent);

        }
        else{
            quizCount++;
            showNextQuiz();
        }

    }
}