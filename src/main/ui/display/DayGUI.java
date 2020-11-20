package ui.display;

import model.calendar.Day;
import model.calendar.Month;
import model.dish.Dish;
import model.dish.MealType;
import model.grocery.FoodItem;

import javax.swing.*;
import java.awt.*;

import static model.dish.MealType.*;

// Displays given day using Java Swing Library

public class DayGUI extends JPanel {

    private static final String INDENT = "";
    private static final String TAB = INDENT + "\t\t";
    private static final String TAB_DASH = TAB + " -";

    public JFrame groceryList;

    public JPanel dayPanel;
    public JPanel mealPlanForDay;

    public JButton backButton;
    public JButton addDishButton;
    public JButton groceryListButton;

    Month month;
    Day day;

    // MODIFIES: this
    // EFFECTS: Creates assigns month, day, calls getDayPanel
    public DayGUI(Month month, int day) {
        this.month = month;
        this.day = month.getListDaysInMonth().get(day - 1);
        getDayPanel(month, day);
    }

    // MODIFIES: this
    // EFFECTS: creates new dayPanel and returns it
    public JPanel getDayPanel(Month month, int day) {
        dayPanel = new JPanel();
        dayPanel.setLayout(new GridLayout(0, 1));
        dayPanel.setAlignmentY(TOP_ALIGNMENT);
        getDayInformation();
        return dayPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets up day display and creates buttons on day display
    public void getDayInformation() {
        JPanel dayHeader = new JPanel();
        dayHeader.setAlignmentY(TOP_ALIGNMENT);
        backButton = new JButton("Back");
        dayHeader.add(backButton);
        groceryListButton = new JButton("Generate Grocery List");
        dayHeader.add(groceryListButton);
        addDishButton = new JButton("+ Add Dish");
        dayHeader.add(addDishButton);
        dayPanel.add(dayHeader);
        JPanel dayTitle = new JPanel();
        dayTitle.add(new JLabel(day.getWeekName() + ", " + day.getMonthName() + " "
                + Integer.toString(day.getDay()) + ", " + Integer.toString(day.getYear())));
        dayPanel.add(dayTitle);
        mealPlanForDay = new JPanel();
        mealPlanForDay.setLayout(new GridLayout(1, 0));
        mealPlanForDay.setAlignmentY(TOP_ALIGNMENT);
        printDishOfMealType(BREAKFAST, "Breakfast:");
        printDishOfMealType(LUNCH, "Lunch:");
        printDishOfMealType(DINNER, "Dinner:");
        mealPlanForDay.setAlignmentY(TOP_ALIGNMENT);
        printDishOfMealType(SNACK, "Snack:");
        dayPanel.add(mealPlanForDay);
    }

    // EFFECTS: for a given meal type, prints those dishes in a JPanel mealList
    public void printDishOfMealType(MealType mealType, String mealName) {
        JPanel mealList = new JPanel();
        mealList.setLayout(new GridLayout(0, 1));
        mealList.setAlignmentX(TOP_ALIGNMENT);
        mealList.add(new JLabel(INDENT + mealName));
        mealList.add(Box.createVerticalBox());
        if (!day.listOfGivenMealType(mealType).isEmpty()) {
            printMealInfo(mealType, mealList);
        }
        mealPlanForDay.add(mealList);
    }

    // EFFECTS: prints all dishes and their information in a day categorized by meal type
    public void printMealInfo(MealType mealType, JPanel mealList) {
        for (Dish dish : day.listOfGivenMealType(mealType)) {
            mealList.add(new JLabel(INDENT + dish.getName()));
            if (dish.getRating() == -1) {
                mealList.add(new JLabel(TAB + "Rating:\t\t" + "No Rating"));
            } else {
                mealList.add(new JLabel(TAB + "Rating:\t\t" + Integer.toString(dish.getRating())));
            }
            mealList.add(new JLabel(TAB + "Groceries:"));
            for (FoodItem grocery: dish.getGroceries()) {
                mealList.add(new JLabel(TAB_DASH + grocery.getName()));
            }
            mealList.add(new JLabel(TAB + "Special Instructions:"));
            for (String instruct: dish.getSpecialInstructions()) {
                mealList.add(new JLabel(TAB_DASH + instruct));
            }
            mealList.add(Box.createVerticalBox());
        }
    }


    // EFFECTS: prints grocery list for this.day
    public void groceryList() {
        groceryList = new JFrame();
        JPanel groceryListTitle = new JPanel();
        groceryListTitle.add(new JLabel("Grocery list for " + day.getWeekName() + ", " + day.getMonthName() + " "
                + Integer.toString(day.getDay()) + ", " + Integer.toString(day.getYear())));
        groceryList.add(groceryListTitle);
        groceryList.add(Box.createVerticalBox());
        JPanel groceries = new JPanel();
        for (Dish dish: day.getListOfDishes()) {
            for (FoodItem grocery: dish.getGroceries()) {
                groceries.add(new JLabel(grocery.getName()));
            }
        }
        groceryList.add(groceries);
    }
}
