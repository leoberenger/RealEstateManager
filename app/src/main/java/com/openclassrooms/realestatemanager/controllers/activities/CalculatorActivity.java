package com.openclassrooms.realestatemanager.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorActivity extends AppCompatActivity {

    private String TAG = "Calculator Activity";

    float monthlyPayment = 0;
    float totalInterests = 0;

    @BindView(R.id.calculator_loan) EditText editTextLoan;
    @BindView(R.id.calculator_down_payment) EditText editTextDownPayment;
    @BindView(R.id.calculator_interest_rate) EditText editTextInterestRate;
    @BindView(R.id.calculator_duration) EditText editTextDuration;
    @BindView(R.id.calculator_monthly_payment) TextView textViewMonthlyPayment;
    @BindView(R.id.calculator_interests) TextView textViewInterests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.calculator_button)
    public void onClickCalculateButton(){
        int loan = Integer.valueOf(editTextLoan.getText().toString());
        int downPayment = Integer.valueOf(editTextDownPayment.getText().toString());
        float interestRate = Float.valueOf(editTextInterestRate.getText().toString());
        int duration = Integer.valueOf(editTextDuration.getText().toString());

        monthlyPayment = Utils.calculateMonthlyPayment(loan, downPayment, interestRate, duration);
        totalInterests = Utils.calculateTotalInterests(loan, downPayment, interestRate);

        textViewMonthlyPayment.setText(String.valueOf(monthlyPayment));
        textViewInterests.setText(String.valueOf(totalInterests));
    }
}
