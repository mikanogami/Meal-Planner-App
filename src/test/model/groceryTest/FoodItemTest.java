
package model.groceryTest;

import model.grocery.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.grocery.FoodType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodItemTest {

    FoodItem testFoodItem1;
    FoodItem testFoodItem2;

    @BeforeEach
    public void setUpFoodItemTest() {
        testFoodItem1 = new FoodItem("Tomato", FRUITVEG);
        testFoodItem2 = new FoodItem("Ground Beef", MEAT);
    }

    @Test
    public void testFoodItemConstructor() {
        assertEquals("Tomato", testFoodItem1.getName());
        assertEquals(FRUITVEG, testFoodItem1.getType());
        assertFalse(testFoodItem1.getIsFavourite());
        assertFalse(testFoodItem1.getIsCompleted());

        assertEquals("Ground Beef", testFoodItem2.getName());
        assertEquals(MEAT, testFoodItem2.getType());
        assertFalse(testFoodItem2.getIsFavourite());
        assertFalse(testFoodItem2.getIsCompleted());
    }

    @Test
    public void testFoodItemSetter() {
        testFoodItem1.setIsFavourite(true);
        testFoodItem1.setIsCompleted(true);
        assertTrue(testFoodItem1.getIsFavourite());
        assertTrue(testFoodItem1.getIsCompleted());
        testFoodItem1.setIsFavourite(false);
        testFoodItem1.setIsCompleted(false);
        assertFalse(testFoodItem1.getIsFavourite());
        assertFalse(testFoodItem1.getIsCompleted());

        testFoodItem2.setIsFavourite(true);
        testFoodItem2.setIsCompleted(true);
        assertTrue(testFoodItem2.getIsFavourite());
        assertTrue(testFoodItem2.getIsCompleted());
        testFoodItem2.setIsFavourite(false);
        testFoodItem2.setIsCompleted(false);
        assertFalse(testFoodItem2.getIsFavourite());
        assertFalse(testFoodItem2.getIsCompleted());
    }
}

