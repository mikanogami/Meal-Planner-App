package model.calendarTest;

import model.calendar.Day;
import model.calendar.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthsItemTest {

    Day dayTest;
    Month month1Test;
    Month month2Test;

    @BeforeEach
    public void setUpCalItemTest() {
        month1Test = new Month(2023, 3);
        month2Test = new Month(2020, 10);
        dayTest = new Day(2020, 9, 12);
    }

    @Test
    public void testMonthConstructor() {
        assertEquals(2023, month1Test.getYear());
        assertEquals(3, month1Test.getMonth());
        assertEquals(7, month1Test.getWeekday());
        assertEquals(30, month1Test.getMonthLength());
        assertEquals(6, month1Test.getNumWeeksInMonth());
        assertEquals(5, month2Test.getNumWeeksInMonth());
        assertEquals("Saturday", month1Test.getWeekName());
        assertEquals("April", month1Test.getMonthName());
    }

    @Test
    public void testDayConstructor() {
        assertEquals(2020, dayTest.getYear());
        assertEquals(9, dayTest.getMonth());
        assertEquals(2, dayTest.getWeekday());
        assertEquals(31, dayTest.getMonthLength());
        assertEquals(5, month2Test.getNumWeeksInMonth());
        assertEquals("Monday", dayTest.getWeekName());
        assertEquals("October", dayTest.getMonthName());
    }
}
