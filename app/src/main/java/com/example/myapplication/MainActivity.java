package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonSquareEq;
    Button buttonSquareMatrix;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_main);

        buttonSquareEq = findViewById(R.id.buttonSquareEq);
        buttonSquareMatrix = findViewById(R.id.buttonSquareMatrix);



        buttonSquareEq.setOnClickListener(view -> {
            Intent intent = new Intent(this,SquareEqActivity.class);
            startActivity(intent);

        });

        buttonSquareMatrix.setOnClickListener(view -> {
            Intent intent = new Intent(this,MatrixActivity.class);
            startActivity(intent);
        });
    }


}
