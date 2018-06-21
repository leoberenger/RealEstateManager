package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorUnitTest {

    int loan = 10000;
    int downPayment = 2000;
    float interestRate = 3.0f;
    int duration = 10;

    @Test
    public void calculateMonthlyPayment_isCorrect() {
        float monthlyPayment = 68.66f;
        assertEquals(monthlyPayment, Utils.calculateMonthlyPayment(loan, downPayment, interestRate, duration), 2);
    }

    @Test
    public void calculateTotalInterests_isCorrect() {
        float totalInterests = 240f;
        assertEquals(totalInterests, Utils.calculateTotalInterests(loan, downPayment, interestRate), 2);
    }
}