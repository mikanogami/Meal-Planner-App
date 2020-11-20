package ui.display;

import model.grocery.FoodItem;
import model.grocery.FoodType;
import ui.exceptions.EnumNotSelectedException;
import ui.exceptions.FoodNotAcceptedException;

import javax.swing.*;
import java.awt.*;

import static model.grocery.FoodType.*;

// GUI for adding information for ingredients using Java Swing Library

public class AddIngredientGUI extends JPanel {

    public JPanel ingredientPanel;

    JTextField groceryName;
    JComboBox foodTypeComboBox;
    JCheckBox favourite;
    JCheckBox completed;

    public static final String[] FOOD_TYPE = {"Enter the food type of ingredient", "Fruit or vegetable",
        "Meat", "Grain", "Dairy", "Packaged", "Canned", "Frozen", "Herb or Spice"};

    // EFFECTS: calls getIngredientPanel()
    public AddIngredientGUI() {
        getIngredientPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up ingredientPanel and returns it
    public JPanel getIngredientPanel() {
        ingredientPanel = new JPanel();
        ingredientPanel.setLayout(new GridLayout(0, 1));
        getIngredientTools();
        ingredientPanel.add(Box.createVerticalBox());
        return ingredientPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents for grocery name, favourite, completed
    public void getIngredientTools() {
        groceryName = new JTextField("   Enter grocery name",20);
        foodTypeComboBox = new JComboBox(FOOD_TYPE);
        favourite = new JCheckBox("Is this a favourite ingredient?");
        favourite.setHorizontalAlignment(SwingConstants.LEFT);
        completed = new JCheckBox("Have you already gotten this ingredient?");
        completed.setHorizontalAlignment(SwingConstants.LEFT);

        ingredientPanel.add(groceryName);
        ingredientPanel.add(foodTypeComboBox);
        ingredientPanel.add(favourite);
        ingredientPanel.add(completed);
    }

    // MODIFIES: FoodItem grocery
    // EFFECTS: gets information from JComponents
    // if FoodItem is not valid, throws FoodNotAcceptedException
    // returns grocery
    public FoodItem returnFoodFields() throws FoodNotAcceptedException {
        FoodItem grocery;
        String foodName = groceryName.getText();
        try {
            FoodType foodType = getFoodType((String) foodTypeComboBox.getSelectedItem());
            if (!foodName.equals("Enter dish name")) {
                grocery = new FoodItem(foodName, foodType);
            } else {
                throw new FoodNotAcceptedException();
            }
        } catch (EnumNotSelectedException e) {
            throw new FoodNotAcceptedException();
        }
        if (favourite.isSelected()) {
            grocery.setIsFavourite(true);
        }
        if (completed.isSelected()) {
            grocery.setIsCompleted(true);
        }
        return grocery;
    }

    // EFFECTS: gets food type from JComboBox
    // if not selected throws EnumNotSelectedException
    public FoodType getFoodType(String type) throws EnumNotSelectedException {
        switch (type) {
            case "Fruit or vegetable":
                return FRUITVEG;
            case "Meat":
                return MEAT;
            case "Grain":
                return GRAIN;
            case "Dairy":
                return DAIRY;
            case "Packaged":
                return PACKAGED;
            case "Canned":
                return CANNED;
            case "Frozen":
                return FROZEN;
            case "Herb or Spice":
                return HERBSPICE;
            default:
                throw new EnumNotSelectedException();
        }
    }

}
