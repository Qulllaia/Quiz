package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    TextView physicsLvl;
    TextView physicsAdvice;
    TextView resultLabel;

    int final_result = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        physicsLvl = (TextView)findViewById(R.id.physicsLevel);
        physicsAdvice = (TextView)findViewById(R.id.advice);

        resultLabel = (TextView) findViewById(R.id.resultLabel);
        final_result = result.returnCounter();
        resultLabel.setText(final_result + " / 5");

        if(final_result == 5){
            physicsLvl.setText(physicsLvl.getText().toString() + "\n" + "Отличный!");
            physicsAdvice.setText("Молодец, ты хорошо знаешь физику!");
        }
        else if(final_result ==4){
            physicsLvl.setText(physicsLvl.getText().toString() + "\n" +"Хороший!");
            physicsAdvice.setText("Ты явно хорошо учил физику в школе!");
        }
        else if(final_result == 3){
            physicsLvl.setText(physicsLvl.getText().toString() + "\n" +"Средний!");
            physicsAdvice.setText("Ты неплохо знаешь основы, но надо бы немного поботать");
        }
        else if(final_result == 2){
            physicsLvl.setText(physicsLvl.getText().toString() + "\n" + "Ниже среднего!");
            physicsAdvice.setText("Это плохо, друг! Открывай учебник физики за пятый класс!");
        }
        else{
            physicsLvl.setText(physicsLvl.getText().toString() + "\n" +"Ужасный!");
            physicsAdvice.setText("Начни изучение физики с учебника по оркужающему миру...");
        }

    }
    public void returnTop(View view){
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        result.toZero();
        startActivity(intent);
    }
}