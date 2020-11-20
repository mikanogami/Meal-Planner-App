package model.calendarTest;

import model.calendar.Month;
import model.dish.Dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.dish.MealType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonthTest {

    Month testJanuary;
    Month testJune;
    Month testDecember;
    Month testLeapMonth;

    Dish testDish;

    // EFFECTS: Instantiates multiple instances for Month
    // Instantiates a testDish and testDay used only for testing Month class
    // NOTE: Dish and Day classes are not being tested here
    @BeforeEach
    public void setUpMonthTest() {
        testJanuary = new Month(2020, 0);
        testJune = new Month (2025, 5);
        testDecember = new Month(2030, 11);
        testLeapMonth = new Month(2024, 1);

        testDish = new Dish("Pasta", DINNER);
    }

    @Test
    public void testGettersFromCalendarItem() {
        assertEquals(2030, testDecember.getYear());
        assertEquals(11, testDecember.getMonth());
        assertEquals(1, testDecember.getWeekday());
        assertEquals(31, testDecember.getMonthLength());
        assertEquals(5, testDecember.getNumWeeksInMonth());
        assertEquals("Sunday", testDecember.getWeekName());
        assertEquals("December", testDecember.getMonthName());
    }

    @Test
    public void testListDaysInMonth() {
        assertEquals(31, testJanuary.getListDaysInMonth().size());
        assertEquals(30, testJune.getListDaysInMonth().size());
        assertEquals(31, testDecember.getListDaysInMonth().size());
        assertEquals(29, testLeapMonth.getListDaysInMonth().size());
    }

    @Test
    public void testAddDish() {
        testDecember.addDish(testDish, 8);
        assertTrue(testDecember.getListDaysInMonth().get(8 - 1).getListOfDishes().contains(testDish));
        testJune.addDish(testDish, 19);
        assertTrue(testJune.getListDaysInMonth().get(19 - 1).getListOfDishes().contains(testDish));
    }

    @Test
    public void testNextMonth() {
        assertEquals(1, testJanuary.nextMonth());
        assertEquals(2020, testJanuary.nextMonthYear());
        assertEquals(6, testJune.nextMonth());
        assertEquals(2025, testJune.nextMonthYear());
        assertEquals(0, testDecember.nextMonth());
        assertEquals(2031, testDecember.nextMonthYear());
    }

    @Test
    public void testPrevMonth() {
        assertEquals(11, testJanuary.prevMonth());
        assertEquals(2019, testJanuary.prevMonthYear());
        assertEquals(4, testJune.prevMonth());
        assertEquals(2025, testJune.prevMonthYear());
        assertEquals(10, testDecember.prevMonth());
        assertEquals(2030, testDecember.prevMonthYear());
    }
}
