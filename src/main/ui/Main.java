package ui;

import java.util.GregorianCalendar;

// Main method calls MealPlannerAppGUI()

public class Main extends GregorianCalendar {

    public static void main(String[] args) {
        new MealPlannerAppGUI().startGUI();
    }
}
