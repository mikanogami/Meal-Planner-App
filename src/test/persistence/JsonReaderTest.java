package persistence;

import model.calendar.Day;
import model.calendar.Month;
import model.calendar.Months;
import model.dish.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    public JsonReaderTest() {

    }

    @Test
    public void testReadNonExistentFile() {
        JsonReader reader = new JsonReader("./data/testReader/NonExistentFile.json");
        try {
            Months mealPlan = reader.read();
            fail("File does not exist, so exception should be thrown");
        } catch (IOException ioException) {
            // Expected
        }
    }

    @Test
    public void testReadEmptyMealPlan() {
        JsonReader reader = new JsonReader("./data/testReader/mealPlanEmpty.json");
        try {
            Months mealPlan = reader.read();
            assertEquals(4, mealPlan.getMonths().size());
            for (Month month: mealPlan.getMonths()) {
                for (Day day: month.getListDaysInMonth()) {
                    assertEquals(0, day.getListOfDishes().size());
                }
            }
        } catch (IOException ioException) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testReadMealPlanWithEntries() {
        JsonReader reader = new JsonReader("./data/testReader/mealPlanWithEntries.json");
        try {
            Months mealPlan = reader.read();
            assertEquals(4, mealPlan.getMonths().size());
            Month october2020 = mealPlan.getMonths().get(0);
            Month november2020 = mealPlan.getMonths().get(1);
            Month december2020 = mealPlan.getMonths().get(2);
            Month january2021 = mealPlan.getMonths().get(3);
            for (Day day: october2020.getListDaysInMonth()) {
                assertEquals(0, day.getListOfDishes().size());
            }
            for (Day day: december2020.getListDaysInMonth()) {
                assertEquals(0, day.getListOfDishes().size());
            }
            Day nov9 = november2020.getListDaysInMonth().get(8);
            assertEquals(9, nov9.getDay());
            assertEquals(2, nov9.getListOfDishes().size());
            Dish chili = nov9.getListOfDishes().get(0);
            assertEquals("Chili", chili.getName());
            assertEquals(2, chili.getSpecialInstructions().size());
            assertEquals(4, chili.getGroceries().size());
            Dish milkshake = nov9.getListOfDishes().get(1);
            assertEquals(0, milkshake.getSpecialInstructions().size());
            assertEquals(2, milkshake.getGroceries().size());

            Day jan31 = january2021.getListDaysInMonth().get(30);
            assertEquals(31, jan31.getDay());
            assertEquals(2, jan31.getListOfDishes().size());
            Dish pasta = jan31.getListOfDishes().get(0);
            assertEquals("Pasta", pasta.getName());
            assertEquals(2, pasta.getSpecialInstructions().size());
            assertEquals(4, pasta.getGroceries().size());
            Dish smoothie = jan31.getListOfDishes().get(1);
            assertEquals(0, smoothie.getSpecialInstructions().size());
            assertEquals(2, smoothie.getGroceries().size());
        } catch (IOException ioException) {
            fail("Exception should not be thrown");
        }
    }

}
