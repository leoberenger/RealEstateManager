package com.openclassrooms.realestatemanager;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FormatDateUnitTest {
    @Test
    public void formatDate_isCorrect() throws Exception {
        String date = "16/05/2018";
        assertEquals(date, Utils.getTodayDate());
    }
}