package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;



public class MatrixActivity extends AppCompatActivity {

    LinearLayout layoutVertical;
    LinearLayout[] layoutHorizontal;
    EditText[][] editText;
    EditText inputSize;
    Button selectSize;
    Button buttonCalcDet;
    TextView textViewResultDet;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        findViews();
        textViewResultDet.setVisibility(View.GONE);
        buttonCalcDet.setVisibility(View.GONE);

    }
    private void findViews(){
        layoutVertical = findViewById(R.id.layoutVertical);
        inputSize = findViewById(R.id.InputSizeEditText);
        selectSize = findViewById(R.id.buttonAcceptInput);
        textViewResultDet = findViewById(R.id.textViewResultDet);
        buttonCalcDet = findViewById(R.id.buttonCalcDet);
    }

    public void drawableMatrix(View view) {

        int n = Integer.parseInt(String.valueOf(inputSize.getText()));
        editText = new EditText[n][n];
        ArrayList<EditText> editTexts = new ArrayList<>();
        layoutHorizontal = new LinearLayout[n];

        if(layoutVertical.getChildCount()>0)
            layoutVertical.removeAllViewsInLayout();

        int t = 0;
        for (int i = 0; i < n; i++) {

            layoutHorizontal[i]= new LinearLayout(this);
            layoutHorizontal[i].setOrientation(LinearLayout.HORIZONTAL);
            layoutVertical.addView(layoutHorizontal[i]);

            for (int j = 0; j < n; j++) {
                editText[i][j] = new EditText(this);
                editTexts.add(editText[i][j]);
                t ++;
                editText[i][j].setId(t);
                editText[i][j].setTextSize(20);
                editText[i][j].setSingleLine();
                editText[i][j].setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_CLASS_NUMBER  |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText[i][j].setOnEditorActionListener((v, actionId, event) -> {
                    if (actionId == 0 || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {

                        int curentId = v.getId();
                        if(curentId <= n*n - n){
                            editTexts.get(curentId).requestFocus();
                        } else if (curentId < n*n) {

                            editTexts.get(curentId).requestFocus();

                        }
                         else{

                            editText[n-1][n-1].requestFocus();}
                    }
                    return false;
                });

                layoutHorizontal[i].addView(editText[i][j]);
                layoutHorizontal[i].setHorizontalGravity(1);
            }
        }
        buttonCalcDet.setVisibility(View.VISIBLE);

    }
    @SuppressLint("SetTextI18n")
    public void calculateDet(View view){
        int n = Integer.parseInt(String.valueOf(inputSize.getText()));
        double [][] matrix = new double[n][n];
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++)
                matrix[i][j] = Double.parseDouble(editText[i][j].getText().toString());
        }

        DeterminantCalc deter = new DeterminantCalc(matrix);
        BigDecimal det = deter.determinant();

        String determinant = NumberFormat.getInstance().format(det);

        textViewResultDet.setVisibility(View.VISIBLE);
        textViewResultDet.setText(getString(R.string.detLabel) + " " + determinant);
    }


}