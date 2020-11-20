package model.mealTest;

import model.dish.Dish;
import model.grocery.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.grocery.FoodType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static model.dish.MealType.*;

public class DishTest {

    Dish testDish1;
    Dish testDish2;

    FoodItem testFoodItemA;
    FoodItem testFoodItemB;
    FoodItem testFoodItemC;

    @BeforeEach
    public void setUpDishTest() {
        testDish1 = new Dish("Pasta", DINNER);
        testDish2 = new Dish("Smoothie", BREAKFAST);

        testFoodItemA = new FoodItem("Tomato Sauce", PACKAGED);
        testFoodItemB = new FoodItem("Banana", FRUITVEG);
        testFoodItemC = new FoodItem("Yogurt", PACKAGED);
    }

    @Test
    public void testDishConstructor() {
        assertEquals("Pasta", testDish1.getName());
        assertEquals(DINNER, testDish1.getMealType());
        assertEquals(-1, testDish1.getRating());
        assertEquals(0, testDish1.getSpecialInstructions().size());
        assertEquals(0, testDish1.getGroceries().size());

        assertEquals("Smoothie", testDish2.getName());
        assertEquals(BREAKFAST, testDish2.getMealType());
        assertEquals(-1, testDish2.getRating());
        assertEquals(0, testDish2.getSpecialInstructions().size());
        assertEquals(0, testDish2.getGroceries().size());
    }

    @Test
    public void testAddGrocery() {
        assertEquals(0, testDish1.getGroceries().size());

        testDish1.addToGroceries(testFoodItemA);
        assertEquals(1, testDish1.getGroceries().size());
        assertTrue(testDish1.getGroceries().contains(testFoodItemA));

        testDish1.addToGroceries(testFoodItemB);
        assertEquals(2, testDish1.getGroceries().size());
        assertTrue(testDish1.getGroceries().contains(testFoodItemA));
        assertTrue(testDish1.getGroceries().contains(testFoodItemB));

        testDish1.addToGroceries(testFoodItemC);
        assertEquals(3, testDish1.getGroceries().size());
        assertTrue(testDish1.getGroceries().contains(testFoodItemA));
        assertTrue(testDish1.getGroceries().contains(testFoodItemB));
        assertTrue(testDish1.getGroceries().contains(testFoodItemC));
    }

    @Test
    public void testSetRating() {
        assertFalse(testDish1.setRating(0));
        assertFalse(testDish1.setRating(6));
        assertFalse(testDish1.setRating(-3));
        assertFalse(testDish1.setRating(-70));
        assertFalse(testDish1.setRating(70));
        assertTrue(testDish1.setRating(1));
        assertEquals(1, testDish1.getRating());
        assertTrue(testDish1.setRating(3));
        assertEquals(3, testDish1.getRating());
        assertTrue(testDish1.setRating(5));
        assertEquals(5, testDish1.getRating());
    }

    @Test
    public void testAddSpecialInstructions() {
        assertEquals(0, testDish1.getSpecialInstructions().size());

        testDish1.addSpecialInstructions("Use penne pasta");
        assertEquals(1, testDish1.getSpecialInstructions().size());
        assertTrue(testDish1.getSpecialInstructions().contains("Use penne pasta"));

        testDish1.addSpecialInstructions("Optional: add cream to sauce");
        assertEquals(2, testDish1.getSpecialInstructions().size());
        assertTrue(testDish1.getSpecialInstructions().contains("Use penne pasta"));
        assertTrue(testDish1.getSpecialInstructions().contains("Optional: add cream to sauce"));
    }

}
