package com.openclassrooms.realestatemanager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConversionUnitTest {
    @Test
    public void conversionToDollar_isCorrect() throws Exception {
        int dollar = 1000;
        int euro = 812;
        assertEquals(dollar, Utils.convertEurotoDollar(euro));
    }
}