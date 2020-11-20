package ui;

import model.calendar.Month;
import model.calendar.Months;
import ui.display.AddDishGUI;
import ui.display.DayGUI;
import ui.display.MonthGUI;
import ui.exceptions.DishNotAcceptedException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;

// Displays Meal Plan using the Java Swing Library

public class MealPlannerAppGUI extends GregorianCalendar {

    private static JFrame frame;
    protected static JPanel home;
    protected static MonthGUI currentMonth;
    protected static DayGUI currentDay;
    protected static AddDishGUI addDishGUI;

    protected static CardLayout cards;
    private static JPanel startDay;
    private static JPanel addDishToDay;

    private static final String WELCOME_CARD = "Welcome";
    private static final String MONTH_CARD = "This Month";
    public static final String START_DAY_CARD = "Start Day: Not Adding Dish";
    public static final String ADD_DISH_CARD = "Adding Dish";
    public static final String SAVE_CARD = "Save";
    private static final int PREV_MONTH_KEY = -100;
    private static final int NEXT_MONTH_KEY = 100;

    private static final String JSON_SAVED_FILE = "./data/mealPlan.json";
    //public static final Scanner scanner = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    protected Month displayedMonth;
    protected Months calendar;
    private int day;
    protected HashMap<Integer, JButton> currentMonthButtons;

    private static int WIDTH = 1200;
    private static int HEIGHT = 800;

    // EFFECTS: returns Months() that represents calendar containing meal plan
    public Months getCalendar() {
        return this.calendar;
    }

    // MODIFIES: this
    // EFFECTS: Initializes JFrame and CardLayout for entire GUI
    // Displays GUI
    public void startGUI() {
        jsonWriter = new JsonWriter(JSON_SAVED_FILE);
        jsonReader = new JsonReader(JSON_SAVED_FILE);
        calendar = new Months();

        frame = new JFrame();
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        cards = new CardLayout();
        home = new JPanel(cards);

        currentMonthButtons = new HashMap<>();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveCard();
            }
        });

        welcomeGUI();
        frame.add(home);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Welcome screen is made and added to cards
    public void welcomeGUI() {
        JPanel welcomeScreen = new JPanel();
        welcomeScreen.setLayout(new BoxLayout(welcomeScreen, BoxLayout.Y_AXIS));
        welcomeScreen.add(new JLabel("Welcome to your personal meal plan and grocery list generator!"));
        welcomeScreen.add(new JLabel("Would you like to load your meal plan from the file?"));
        JButton yesButton = new JButton("Yes - Get the meal plan that I most recently worked on");
        JButton noButton = new JButton("No - Start from scratch");
        welcomeScreen.add(yesButton);
        welcomeScreen.add(noButton);
        possibleLoadFile(yesButton, noButton);
        home.add(welcomeScreen, WELCOME_CARD);
    }

    // MODIFIES: this
    // EFFECTS: Adds action listeners for yes button and no button
    // If yes is pressed, meal plan is loaded from file
    // If no is pressed, meal plan is restarted
    public void possibleLoadFile(JButton yesButton, JButton noButton) {
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == yesButton) {
                    loadMealPlan();
                    displayedMonth = calendar.getMonths().get(0);
                    displayMonth();
                }
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == noButton) {
                    displayedMonth = new Month(getInstance().get(YEAR), getInstance().get(MONTH));
                    calendar.addMonth(displayedMonth);
                    displayMonth();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates new ShowMonthGUI with displayed month
    // adds the ShowMonthGUI to cards and displays it
    public void displayMonth() {
        currentMonth = new MonthGUI(displayedMonth);
        buttonsFromMonth();
        home.add(currentMonth.monthPanel, MONTH_CARD);
        cards.show(home, MONTH_CARD);
    }

    // MODIFIES: this.displayedMonth
    // EFFECTS: Adds action listeners for previous button and next button
    // If previous is pressed, gets previous month and sets this.displayedMonth to previous month
    // If next is pressed, gets next month and sets this.displayedMonth to next month
    public void prevNextButtons() {
        HashMap<Integer, JButton> monthButtons = currentMonth.getMonthButtons();
        JButton prevButton = monthButtons.get(-100);
        JButton nextButton = monthButtons.get(100);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == prevButton) {
                    Month prevMonth = calendar.getMonth(displayedMonth.prevMonthYear(), displayedMonth.prevMonth());
                    displayedMonth = prevMonth;
                    displayMonth();
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nextButton) {
                    Month nextMonth = calendar.getMonth(displayedMonth.nextMonthYear(), displayedMonth.nextMonth());
                    displayedMonth = nextMonth;
                    displayMonth();
                }
            }
        });
    }

    // EFFECTS: calls selectDay() which initializes day buttons and adds action listeners
    // calls prevNextButtons() which initializes
    public void buttonsFromMonth() {
        selectDay();
        prevNextButtons();
    }

    // MODIFIES: this
    // EFFECTS: initiates all buttons each representing one day of the displayedMonth
    // add action listener for each of the day buttons
    public void selectDay() {
        HashMap<Integer, JButton> dayButtons = currentMonth.getMonthButtons();
        for (int i = 1; i <= displayedMonth.getMonthLength(); i++) {
            JButton dayButton = dayButtons.get(i);
            int finalI = i;
            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == dayButton) {
                        day = finalI;
                        reloadDay();
                        cards.show(home, START_DAY_CARD);
                    }
                }
            });
        }
    }

    // EFFECTS: displays current day
    public void reloadDay() {
        currentDay = new DayGUI(displayedMonth, day);
        startDayCard();
        startDay.updateUI();
    }

    // MODIFIES: this
    // EFFECTS: creates panel that shows day
    // add day panel to cards and displays it
    // adds action listeners for back to month
    public void startDayCard() {
        startDay = new JPanel();
        startDay.add(currentDay.dayPanel);
        home.add(startDay, START_DAY_CARD);
        addDishAndGenerateListButtons();
        JButton backButton = currentDay.backButton;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    displayMonth();
                }
            }
        });
    }

    // EFFECTS: adds action listeners for add dish button and grocery list button
    // if dish button is pressed, displays add dish GUI
    // if grocery list button is pressed, generates list of groceries for that day
    public void addDishAndGenerateListButtons() {
        JButton groceryList = currentDay.groceryListButton;
        groceryList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == groceryList) {
                    // TODO
                }
            }
        });
        JButton addDishButton = currentDay.addDishButton;
        addDishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addDishButton) {
                    newEditDayPanel();
                    cards.show(home, ADD_DISH_CARD);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates new EditDishGUI() and adds it to cards
    public void newEditDayPanel() {
        addDishToDay = new JPanel();
        addDishGUI = new AddDishGUI();
        addDishToDay.setLayout(new GridLayout(0, 1));
        addDishToDay.add(addDishGUI.editDishPanel);
        home.add(addDishToDay, ADD_DISH_CARD);
        dayAddingDishButtons(addDishGUI);
    }

    // EFFECTS: gets buttons for new EditDishGUI()
    public void dayAddingDishButtons(AddDishGUI addDishGUI) {
        dishCompleted();
        addComponentToDish();
    }

    // EFFECTS: action listener for dish completed
    // if dish is not valid and cannot be complete, throws DishNotAcceptedException
    public void dishCompleted() {
        JButton complete = addDishGUI.complete;
        int numPanels = addDishToDay.getComponentCount();
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == complete) {
                    JLabel warning = new JLabel("To add a dish you need to add both a name and a meal type!!");
                    warning.setHorizontalAlignment(SwingConstants.CENTER);
                    try {
                        addDishGUI.getDishFields(displayedMonth, day);
                        reloadDay();
                        cards.show(home, START_DAY_CARD);
                    } catch (DishNotAcceptedException dishNotAcceptedException) {
                        if (!(addDishToDay.getComponentCount() > numPanels)) {
                            addDishToDay.add(warning, 0);
                            addDishToDay.updateUI();
                        }
                    }
                }
            }
        });
    }


    // EFFECTS: action listeners for addInstruct and addGroceryList
    public void addComponentToDish() {
        JButton addInstruct = addDishGUI.addInstruction;
        addInstruct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addInstruct) {
                    addDishGUI.addInstruction();
                    addDishToDay.updateUI();
                }
            }
        });
        JButton addGrocery = addDishGUI.addGrocery;
        addGrocery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addGrocery) {
                    addDishGUI.addGrocery();
                    addDishToDay.updateUI();
                }
            }
        });
    }


    // MODIFIES: ".data/mealPlan.json"
    // EFFECTS: saves meal plan to file ".data/mealPlan.json"
    public void saveCard() {
        JFrame closeFrame = new JFrame();
        closeFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel savePlan = new JPanel();
        savePlan.setLayout(new BoxLayout(savePlan, BoxLayout.PAGE_AXIS));
        savePlan.add(new JLabel("Would you like to save your meal plan?"));
        JButton yesSave = new JButton("YES");
        JButton noSave = new JButton("NO");
        savePlan.add(yesSave);
        savePlan.add(noSave);
        closeFrame.add(savePlan);
        closeFrame.pack();
        closeFrame.setVisible(true);

        saveMealPlan(yesSave, noSave);
    }

    public void saveMealPlan(JButton yesSave, JButton noSave) {
        yesSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(getCalendar());
                    jsonWriter.close();
                    System.exit(0);
                } catch (FileNotFoundException fileNotFoundException) {
                    // file name is defined, this error should never be thrown
                }
            }
        });
        noSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // EFFECTS: loads all data from ".data/mealPlan.json"
    public void loadMealPlan() {
        try {
            this.calendar = this.jsonReader.read();
        } catch (IOException var2) {
            // Not supposed to happen
        }
    }
}
