package model.calendarTest;

import model.calendar.Month;
import model.calendar.Months;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class MonthsTest {

    Months testMonths;
    Month testJanuary;
    Month testJune;
    Month testDecember;
    Month testLeapMonth;

    @BeforeEach
    public void setUpMonthsTest() {
        testMonths = new Months();
        testJanuary = new Month(2020, 0);
        testJune = new Month (2025, 5);
        testDecember = new Month(2030, 11);
        testLeapMonth = new Month(2024, 1);
    }

    @Test
    public void testAddMonth() {
        assertEquals(0, testMonths.getNumMonths());
        testMonths.addMonth(testJanuary);
        assertEquals(1, testMonths.getNumMonths());
        assertTrue(testMonths.getMonths().contains(testJanuary));
        testMonths.addMonth(testJune);
        assertEquals(2, testMonths.getNumMonths());
        assertTrue(testMonths.getMonths().contains(testJune));
        testMonths.addMonth(testDecember);
        assertEquals(3, testMonths.getNumMonths());
        assertTrue(testMonths.getMonths().contains(testDecember));
        testMonths.addMonth(testLeapMonth);
        assertEquals(4, testMonths.getNumMonths());
        assertTrue(testMonths.getMonths().contains(testLeapMonth));
    }

    @Test
    public void testGetMonth() {
        testMonths.addMonth(testJanuary);
        testMonths.addMonth(testDecember);
        assertEquals(testJanuary, testMonths.getMonth(2020, 0));
        assertEquals(testDecember, testMonths.getMonth(2030, 11));
        testMonths.getMonth(2024, 4);
        assertEquals(3, testMonths.getNumMonths());
        assertEquals(4, testMonths.getMonths().get(2).getMonth());
        assertEquals(2024, testMonths.getMonths().get(2).getYear());
        testMonths.getMonth(2024, 0);
        assertEquals(4, testMonths.getNumMonths());
        assertEquals(0, testMonths.getMonths().get(3).getMonth());
        assertEquals(2024, testMonths.getMonths().get(2).getYear());
    }
}
