package persistence;

import model.calendar.Day;
import model.calendar.Month;
import model.calendar.Months;
import model.dish.Dish;
import model.grocery.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static model.dish.MealType.*;
import static model.grocery.FoodType.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    Dish pasta;
    Dish smoothie;
    FoodItem tomato;

    public JsonWriterTest() {

    }

    @BeforeEach
    public void setUpMealComponents() {
        pasta = new Dish("Pasta", DINNER);
        pasta.setRating(4);
        pasta.addSpecialInstructions("Salt the water");
        pasta.addSpecialInstructions("Caramelize onions");
        tomato = new FoodItem("Tomato", FRUITVEG);
        tomato.setIsFavourite(true);
        pasta.addToGroceries(tomato);
        pasta.addToGroceries(new FoodItem("Penne Pasta", GRAIN));
        pasta.addToGroceries(new FoodItem("Garlic", HERBSPICE));
        pasta.addToGroceries(new FoodItem("Oregano", HERBSPICE));

        smoothie = new Dish("Smoothie", BREAKFAST);
        smoothie.addToGroceries(new FoodItem("Banana", FRUITVEG));
        smoothie.addToGroceries(new FoodItem("Yogurt", PACKAGED));
    }

    @Test
    public void testInvalidFile() {
        try {
            Months emptyMealPlan = new Months();
            Month thisMonth = new Month(2020, 9);
            emptyMealPlan.addMonth(thisMonth);
            JsonWriter writer = new JsonWriter("./data/testWriter\u0000testWriter.json");
            writer.open();
            fail("Exception should be thrown");
        } catch (FileNotFoundException fileNotFoundException) {
            // Exception is expected to be thrown
        }
    }

    @Test
    public void testEmptyMealPlanSingleMonth() {
        try {
            Months emptyMealPlan = new Months();
            Month thisMonth = new Month(2020, 9);
            emptyMealPlan.addMonth(thisMonth);
            JsonWriter writer = new JsonWriter("./data/testWriter/emptyMealPlanSingleMonth.json");
            writer.open();
            writer.write(emptyMealPlan);
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fail("Valid file name: Exception should not be thrown");
        }
    }

    @Test
    public void testEmptyMealPlanMultipleMonths() {
        try {
            Months emptyMealPlan = new Months();
            Month october2020 = new Month(2020, 9);
            emptyMealPlan.addMonth(october2020);
            Month november2020 = new Month(october2020.nextMonthYear(), october2020.nextMonth());
            emptyMealPlan.addMonth(november2020);
            Month december2020 = new Month(november2020.nextMonthYear(), november2020.nextMonth());
            emptyMealPlan.addMonth(december2020);
            Month january2021 = new Month(december2020.nextMonthYear(), december2020.nextMonth());
            emptyMealPlan.addMonth(january2021);
            JsonWriter writer = new JsonWriter("./data/testWriter/emptyMealPlanMultipleMonths.json");
            writer.open();
            writer.write(emptyMealPlan);
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fail("Valid file name: Exception should not be thrown");
        }
    }

    @Test
    public void testGeneralMealPlanOneMonth() {
        try {
            Months emptyMealPlan = new Months();
            Month thisMonth = new Month(2020, 9);
            emptyMealPlan.addMonth(thisMonth);
            Day october1 = thisMonth.getListDaysInMonth().get(0);
            october1.addDish(pasta);
            Day october12 = thisMonth.getListDaysInMonth().get(11);
            october12.addDish(pasta);
            october12.addDish(smoothie);
            Day halloween = thisMonth.getListDaysInMonth().get(30);
            halloween.addDish(smoothie);
            JsonWriter writer = new JsonWriter("./data/testWriter/generalMealPlanOneMonth.json");
            writer.open();
            writer.write(emptyMealPlan);
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fail("Valid file name: Exception should not be thrown");
        }
    }

    @Test
    public void testGeneralMealPlanMultipleMonths() {
        try {
            Months emptyMealPlan = new Months();
            Month october2020 = new Month(2020, 9);
            emptyMealPlan.addMonth(october2020);
            Month november2020 = new Month(october2020.nextMonthYear(), october2020.nextMonth());
            Day november6 = november2020.getListDaysInMonth().get(5);
            november6.addDish(pasta);
            november6.addDish(smoothie);
            emptyMealPlan.addMonth(november2020);
            Month december2020 = new Month(november2020.nextMonthYear(), november2020.nextMonth());
            emptyMealPlan.addMonth(december2020);
            Month january2021 = new Month(december2020.nextMonthYear(), december2020.nextMonth());
            Day january20 = january2021.getListDaysInMonth().get(19);
            january20.addDish(pasta);
            january20.addDish(smoothie);
            emptyMealPlan.addMonth(january2021);
            JsonWriter writer = new JsonWriter("./data/testWriter/generalMealPlanMultipleMonths.json");
            writer.open();
            writer.write(emptyMealPlan);
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fail("Valid file name: Exception should not be thrown");
        }
    }

}
