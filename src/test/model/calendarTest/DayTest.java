package model.calendarTest;

import model.calendar.Day;
import model.calendar.Month;
import model.dish.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;
import java.util.List;

import static model.dish.MealType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DayTest extends GregorianCalendar {
    Month testMonth;

    Day testDayA;
    Day testDayB;

    Dish testDish1;
    Dish testDish2;
    Dish testDish3;
    Dish testDish4;
    Dish testDish5;
    Dish testDish6;

    @BeforeEach
    public void setUpDayTest() {
        testMonth = new Month(2030, 3);
        testDayA = new Day(2030, 3, 7);
        testDayB = new Day(testMonth, 7);
        testDish1 = new Dish("Smoothie", SNACK);
        testDish2 = new Dish("Garlic Broccoli", DINNER);
        testDish3 = new Dish("Pasta SALAD", LUNCH);
        testDish4 = new Dish("Ice Cream", SNACK);
        testDish5 = new Dish("Barbecue Chicken", DINNER);
        testDish6 = new Dish("Roasted Potatoes", DINNER);

        testDayA.addDish(testDish1);
        testDayA.addDish(testDish2);
        testDayA.addDish(testDish3);
        testDayA.addDish(testDish4);
        testDayA.addDish(testDish5);
        testDayA.addDish(testDish6);

        testDayB.addDish(testDish1);
        testDayB.addDish(testDish2);
        testDayB.addDish(testDish3);
        testDayB.addDish(testDish4);
        testDayB.addDish(testDish5);
        testDayB.addDish(testDish6);
    }

    @Test
    public void testGettersFromCalendarItem() {
        assertEquals(7, testDayA.getDay());
        assertEquals(7, testDayB.getDay());

        assertEquals(2030, testDayA.getYear());
        assertEquals(3, testDayA.getMonth());
        assertEquals(1, testDayA.getWeekday());
        assertEquals(30, testDayA.getMonthLength());
        assertEquals(5, testDayA.getNumWeeksInMonth());
        assertEquals("Sunday", testDayA.getWeekName());
        assertEquals("April", testDayA.getMonthName());
    }

    @Test
    public void testGetListOfDishes() {
        assertEquals(6, testDayA.getListOfDishes().size());
        assertTrue(testDayA.getListOfDishes().contains(testDish1));
        assertTrue(testDayA.getListOfDishes().contains(testDish2));
        assertTrue(testDayA.getListOfDishes().contains(testDish3));
        assertTrue(testDayA.getListOfDishes().contains(testDish4));
        assertTrue(testDayA.getListOfDishes().contains(testDish5));
        assertTrue(testDayA.getListOfDishes().contains(testDish6));

        assertEquals(6, testDayA.getListOfDishes().size());
        assertTrue(testDayB.getListOfDishes().contains(testDish1));
        assertTrue(testDayB.getListOfDishes().contains(testDish2));
        assertTrue(testDayB.getListOfDishes().contains(testDish3));
        assertTrue(testDayB.getListOfDishes().contains(testDish4));
        assertTrue(testDayB.getListOfDishes().contains(testDish5));
        assertTrue(testDayB.getListOfDishes().contains(testDish6));
    }

    @Test
    public void testListOfGivenMT() {
        List<Dish> breakfastA = testDayA.listOfGivenMealType(BREAKFAST);
        List<Dish> lunchA = testDayA.listOfGivenMealType(LUNCH);
        List<Dish> dinnerA = testDayA.listOfGivenMealType(DINNER);
        List<Dish> snackA = testDayA.listOfGivenMealType(SNACK);
        assertEquals(0, breakfastA.size());
        assertEquals(1, lunchA.size());
        assertTrue(lunchA.contains(testDish3));
        assertEquals(3, dinnerA.size());
        assertTrue(dinnerA.contains(testDish2));
        assertTrue(dinnerA.contains(testDish5));
        assertTrue(dinnerA.contains(testDish6));
        assertEquals(2, snackA.size());
        assertTrue(snackA.contains(testDish1));
        assertTrue(snackA.contains(testDish4));

        List<Dish> breakfastB = testDayB.listOfGivenMealType(BREAKFAST);
        List<Dish> lunchB = testDayB.listOfGivenMealType(LUNCH);
        List<Dish> dinnerB = testDayB.listOfGivenMealType(DINNER);
        List<Dish> snackB = testDayB.listOfGivenMealType(SNACK);
        assertEquals(0, breakfastB.size());
        assertEquals(1, lunchB.size());
        assertTrue(lunchB.contains(testDish3));
        assertEquals(3, dinnerB.size());
        assertTrue(dinnerB.contains(testDish2));
        assertTrue(dinnerB.contains(testDish5));
        assertTrue(dinnerB.contains(testDish6));
        assertEquals(2, snackB.size());
        assertTrue(snackB.contains(testDish1));
        assertTrue(snackB.contains(testDish4));
    }

    @Test
    public void testNumDishOfGivenMT() {
        assertEquals(0, testDayA.numDishOfGivenMealType(BREAKFAST));
        assertEquals(1, testDayA.numDishOfGivenMealType(LUNCH));
        assertEquals(3, testDayA.numDishOfGivenMealType(DINNER));
        assertEquals(2, testDayA.numDishOfGivenMealType(SNACK));

        assertEquals(0, testDayB.numDishOfGivenMealType(BREAKFAST));
        assertEquals(1, testDayB.numDishOfGivenMealType(LUNCH));
        assertEquals(3, testDayB.numDishOfGivenMealType(DINNER));
        assertEquals(2, testDayB.numDishOfGivenMealType(SNACK));
    }
}
