package ua.artemenko.bankapp.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class CreditLogicTest {
    GregorianCalendar calendar;
    int year;
    int month;
    int day;
    int daysInMonth;
    int daysInYear;

    @Before
    public void setUp() throws Exception {
        year = 2016;
        month = 9;
        day = 10;
        daysInMonth = 31;
        daysInYear = 366;
        calendar = new GregorianCalendar(year,month,day);
    }

    @Test
    public void daysInMonth() throws Exception {
        System.out.println(CreditLogic.daysInMonth(calendar) + "  " + daysInMonth);
        Assert.assertTrue(CreditLogic.daysInMonth(calendar) == daysInMonth);
    }

    @Test
    public void daysInYear() throws Exception {
        Assert.assertTrue(CreditLogic.daysInYear(calendar) == daysInYear);
    }

}