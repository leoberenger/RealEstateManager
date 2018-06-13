package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatDateUnitTest {
    @Test
    public void formatDate_isCorrect() {
        String date = "16/05/2018";
        assertEquals(date, Utils.getTodayDate());
    }
}