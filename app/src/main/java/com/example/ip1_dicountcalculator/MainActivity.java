package com.example.ip1_dicountcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public int discountPercentage;
    final int INITIAL_DISCOUNT_PERCENTAGE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountPercentage = INITIAL_DISCOUNT_PERCENTAGE;
        final EditText editCost = findViewById(R.id.edtCost);
        Button btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reset Item cost
                EditText edtCost = findViewById(R.id.edtCost);
                edtCost.setText(null);
                edtCost.dispatchDisplayHint(View.VISIBLE);

                //reset discount percentage, slider, and percentage textview
                discountPercentage = INITIAL_DISCOUNT_PERCENTAGE;
                SeekBar slider = findViewById(R.id.seekBar);
                slider.setProgress(discountPercentage);
                displayPercentage();

                //reset Result
                TextView textResult = findViewById(R.id.txtResult);
                textResult.setText("Result");
            }
        });

        //set up text change listener
        editCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                displayResult();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //set up seekbar
        SeekBar slider = findViewById(R.id.seekBar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                discountPercentage = progress;
                displayPercentage();
                displayResult();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void displayResult() {

        double costNumber = 0;
        EditText editCost = findViewById(R.id.edtCost);
        String costText = editCost.getText().toString();
        double result;

        if (!costText.isEmpty()) {
            costNumber = Double.parseDouble(costText);
        }

        result = costNumber * discountPercentage/100;

        TextView txtResult = findViewById(R.id.txtResult);
        String messageResult = String.format(Locale.getDefault(), "$ %.2f", result);
        txtResult.setText(messageResult);

    }

    public void displayPercentage() {
        TextView display = findViewById(R.id.txtPercentage);
        display.setText(discountPercentage + "%");
    }
}
