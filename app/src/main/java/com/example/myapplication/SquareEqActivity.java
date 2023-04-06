package com.example.myapplication;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SquareEqActivity extends AppCompatActivity {

    EditText fieldA,fieldB,fieldC;
    TextView textDisk, textX1, textX2;
    Button buttonCalc;


    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_eq);
        findViews();

    }
    @SuppressLint("SetTextI18n")
    public void Calculate(View view){
        
        String Sa = fieldA.getText().toString();
        String Sb = fieldB.getText().toString();
        String Sc = fieldC.getText().toString();

        if(TextUtils.isEmpty(Sa) || Sa.equals("-") || Sa.equals(".")) {
            fieldA.setError("Заполните значение первого коэффициента");
        }
        else if (TextUtils.isEmpty(Sb) || Sb.equals("-") ||Sb.equals(".")) {
            fieldB.setError("Заполните значение второго коэффциента");

        }
        else if (TextUtils.isEmpty(Sc) || Sc.equals("-")||Sc.equals(".")) {
            fieldC.setError("Заполните значение третьего коэффициента");

        }

        else
        {
            double a = Double.parseDouble(fieldA.getText().toString());
            double b = Double.parseDouble(fieldB.getText().toString());
            double c = Double.parseDouble(fieldC.getText().toString());


            double d = b * b - (4 * a * c);
            double x1,x2;
            if (d>0)
            {
                x1 = (-b - sqrt(d)) / (2 * a);
                x2 = (-b + sqrt(d)) / (2 * a);
                textDisk.setText(String.format("%s%s", getString(R.string.label_disk),d));
                textX1.setText(getString(R.string.label_x1)+ x1);
                textX2.setText(getString(R.string.label_x2)+ x2);
                textX1.setVisibility(View.VISIBLE);
                textX2.setVisibility(View.VISIBLE);

            }
            else if (d == 0){
                x1 = -b /(2*a);
                textDisk.setText(String.format("%s%s", getString(R.string.label_disk),d));
                textX1.setText(getString(R.string.one_x) + x1);
                textX2.setVisibility(View.INVISIBLE);
                textX1.setVisibility(View.VISIBLE);

            }
            else if (d < 0){

                d *= -1;

                double x11 = (-b /(2 * a));
                double x12 =  (- sqrt(d)) / (2 * a);
                double x13 =  sqrt(d) / (2 * a);

                String minusI = x12 + "i";
                String plusI = "+" + x13 + "i";

                textDisk.setText(String.format("%s%s", getString(R.string.label_disk),-d));
                textX1.setText(getString(R.string.label_x1)+ x11 + minusI);
                textX2.setText(getString(R.string.label_x2)+ x11 + plusI);
                textX1.setVisibility(View.VISIBLE);
                textX2.setVisibility(View.VISIBLE);
            }

            textDisk.setVisibility(View.VISIBLE);
        }


    }
    private void findViews(){
        fieldA = findViewById(R.id.editTextNumberSignedA);
        fieldB =  findViewById(R.id.editTextNumberSignedB);
        fieldC = findViewById(R.id.editTextNumberSignedC);
        buttonCalc =  findViewById(R.id.buttonCalc);
        textDisk = findViewById(R.id.textViewDisk);
        textX1 = findViewById(R.id.textViewX1);
        textX2 = findViewById(R.id.textViewX2);
    }
}