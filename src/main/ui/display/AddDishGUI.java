package ui.display;

import model.calendar.Month;
import model.dish.Dish;
import model.dish.MealType;
import ui.exceptions.DishNotAcceptedException;
import ui.exceptions.EnumNotSelectedException;
import ui.exceptions.FoodNotAcceptedException;
import ui.formatting.ImmutableSizeComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Component.BOTTOM_ALIGNMENT;
import static javax.swing.BoxLayout.Y_AXIS;
import static model.dish.MealType.*;

// Edit Dish GUI using Java Swing Library

public class AddDishGUI {

    public JPanel editDishPanel;
    JPanel dishPanel;
    JPanel basicPanel;
    JPanel instructPanel;
    JPanel groceryPanel;

    ArrayList<JTextField> instructions;
    ArrayList<AddIngredientGUI> groceries;
    JPanel addInstructPanel;
    JPanel addGroceryPanel;

    JTextField name;
    JComboBox mealType;
    JComboBox rating;

    public JButton addInstruction;
    public JButton addGrocery;
    public JButton complete;

    public static final String[] MEAL_TYPE = {"Enter meal type", "Breakfast",
            "Lunch", "Dinner", "Snack"};
    public static final String[] RATING = {"Rate dish from 1 to 5", "1", "2", "3", "4", "5"};

    // EFFECTS: calls getDishPanel()
    public AddDishGUI() {
        getDishPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates new JPanel and adds necessary components
    public JPanel getDishPanel() {
        editDishPanel = new JPanel();
        editDishPanel.setLayout(new BoxLayout(editDishPanel, BoxLayout.PAGE_AXIS));
        dishPanel = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(40);
        dishPanel.setLayout(layout);
        dishBasicInformation();
        dishPanel.add(basicPanel);
        instructionsPanel();
        dishPanel.add(instructPanel);
        groceryPanel();
        dishPanel.add(groceryPanel);
        editDishPanel.add(dishPanel);

        complete = new JButton("Complete");
        editDishPanel.add(new ImmutableSizeComponent(complete).panel, BOTTOM_ALIGNMENT);

        return editDishPanel;
    }

    // MODIFIES: this
    // EFFECTS: adds JComponents for dish name, meal type, rating to EditDishPanel
    public void dishBasicInformation() {
        basicPanel = new JPanel();
        basicPanel.setLayout(new GridLayout(0, 1));

        name = new JTextField("Enter dish name", 20);
        mealType = new JComboBox(MEAL_TYPE);
        rating = new JComboBox(RATING);

        basicPanel.add(new ImmutableSizeComponent(name).panel);
        basicPanel.add(mealType);
        basicPanel.add(rating);
        basicPanel.add(new Box(Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: creates addInstruct button and adds it to panel
    public void instructionsPanel() {
        instructions = new ArrayList<>();
        instructPanel = new JPanel();
        instructPanel.setLayout(new GridLayout(0, 1));
        addInstruction = new JButton("+ Add Instruction");
        addInstructPanel = new ImmutableSizeComponent(addInstruction).panel;
        instructPanel.add(addInstructPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds JTextField to instructPanel
    public void addInstruction() {
        JTextField newTextField = new JTextField("Enter special instructions", 30);
        instructPanel.add(newTextField);
        instructions.add(newTextField);
    }

    // MODIFIES: this
    // EFFECTS: creates grocery panel and adds addGrocery button to panel
    public void groceryPanel() {
        groceries = new ArrayList<>();
        groceryPanel = new JPanel();
        groceryPanel.setLayout(new GridLayout(0, 1));
        addGrocery = new JButton("+ Add Grocery");
        addGroceryPanel = new ImmutableSizeComponent(addGrocery).panel;
        groceryPanel.add(addGroceryPanel);
    }

    // MODIFIES: this
    // EFFECTS: add new ingredient panel to groceryPanel
    public void addGrocery() {
        AddIngredientGUI newIngredient = new AddIngredientGUI();
        groceryPanel.add(newIngredient.ingredientPanel);
        groceries.add(newIngredient);
    }

    // MODIFIES: this, Dish dish
    // EFFECTS: gets dish fields and gives info to new dish object
    // if dish is not accepted then throws DishNotAcceptedException
    public void getDishFields(Month month, int day) throws DishNotAcceptedException {
        Dish dish;
        String dishName = name.getText();
        try {
            MealType dishMealType = getMealType((String) mealType.getSelectedItem());
            if (!dishName.equals("Enter dish name")) {
                dish = new Dish(dishName, dishMealType);
            } else {
                throw new DishNotAcceptedException();
            }
        } catch (EnumNotSelectedException e) {
            throw new DishNotAcceptedException();
        }
        for (JTextField textField: instructions) {
            if (!textField.getText().equals("Enter special instructions")) {
                dish.addSpecialInstructions(textField.getText());
            }
        }
        getFoodItemFields(dish);
        month.getListDaysInMonth().get(day - 1).addDish(dish);
    }

    // MODIFIES: Dish dish
    // EFFECTS: gets grocery information and adds it to dish
    public void getFoodItemFields(Dish dish) {
        for (AddIngredientGUI grocery: groceries) {
            try {
                dish.addToGroceries(grocery.returnFoodFields());
            } catch (FoodNotAcceptedException e) {
                // Do nothing, just do not add it to dish
            }
        }
    }

    // EFFECTS: gets meal type from JComboBox
    // if not selected throws EnumNotSelectedException
    public MealType getMealType(String mealType) throws EnumNotSelectedException {
        switch (mealType) {
            case "Breakfast":
                return BREAKFAST;
            case "Lunch":
                return LUNCH;
            case "Dinner":
                return DINNER;
            case "Snack":
                return SNACK;
            default:
                throw new EnumNotSelectedException();
        }
    }

}
