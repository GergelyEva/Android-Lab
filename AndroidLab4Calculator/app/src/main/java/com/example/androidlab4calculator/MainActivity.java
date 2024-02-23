package com.example.androidlab4calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
float number1=0, number2=0,total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        EditText inputNumber1 = (EditText) findViewById(R.id.editTextNumber);
        EditText inputNumber2 = (EditText) findViewById(R.id.editTextNumber2);

        Button add = (Button) findViewById(R.id.button5); //add
        Button substract = (Button) findViewById(R.id.button6); //substract
        Button divide = (Button) findViewById(R.id.button7); //divide
        Button multiply = (Button) findViewById(R.id.button8); //multply


        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                number1 = Float.parseFloat(inputNumber1.getText().toString());
                number2 = Float.parseFloat(inputNumber2.getText().toString());
                total=number1+number2;
                textView.setText(""+total);
                inputNumber1.setText("");
                inputNumber2.setText("");

            }
        });

        substract.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                number1 = Float.parseFloat(inputNumber1.getText().toString());
                number2 = Float.parseFloat(inputNumber2.getText().toString());
                total=number1-number2;
                textView.setText(""+total);
                inputNumber1.setText("");
                inputNumber2.setText("");

            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                number1 = Float.parseFloat(inputNumber1.getText().toString());
                number2 = Float.parseFloat(inputNumber2.getText().toString());
                total=number1*number2;
                textView.setText(""+total);
                inputNumber1.setText("");
                inputNumber2.setText("");

            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                number1 = Float.parseFloat(inputNumber1.getText().toString());
                number2 = Float.parseFloat(inputNumber2.getText().toString());
                total=number1/number2;
                textView.setText(""+total);
                inputNumber1.setText("");
                inputNumber2.setText("");

            }
        });


    }
}