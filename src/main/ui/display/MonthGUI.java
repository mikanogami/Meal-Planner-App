package ui.display;

import model.calendar.Month;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static javax.swing.BoxLayout.LINE_AXIS;

// Displays Month using Java Swing Library

public class MonthGUI {

    public JPanel monthPanel;
    public JPanel monthlyLayout;
    public JPanel monthHeader;
    public HashMap<Integer, JButton> monthButtons;
    public Month month;
    static final String[] WEEK_DATE_HEADER = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
    public static final int WEEK_LENGTH = 7;

    // EFFECTS: calls getMonthPanel
    public MonthGUI(Month month) {
        getMonthPanel(month);
    }

    // EFFECTS: returns all buttons in ShowMonthGUI
    public HashMap<Integer, JButton> getMonthButtons() {
        return monthButtons;
    }

    // MODIFIES: this
    // EFFECTS: creates month panel and formats layout
    public JPanel getMonthPanel(Month month) {
        this.month = month;
        monthPanel = new JPanel();
        monthButtons = new HashMap<>();
        monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.PAGE_AXIS));
        monthButtons();
        monthPanel.add(Box.createVerticalBox());
        getMonthlyLayout();
        return monthPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets up previous, next buttons and the month header text
    public void monthButtons() {
        monthHeader = new JPanel();
        JButton previousMonth = new JButton("<<<Previous");
        JButton nextMonth = new JButton("Next>>>");
        monthHeader.add(previousMonth);
        monthHeader.add(new JLabel(month.getMonthName() + " " + Integer.toString(month.getYear())));
        monthHeader.add(nextMonth);
        monthButtons.put(-100, previousMonth);
        monthButtons.put(100, nextMonth);
        monthPanel.add(monthHeader);
    }

    // MODIFIES: this
    // EFFECTS: sets up monthly layout consisting of week headers and day buttons
    public void getMonthlyLayout() {
        monthlyLayout = new JPanel();
        monthlyLayout.setLayout(new GridLayout(0, 7));
        for (String dayOfWeek: WEEK_DATE_HEADER) {
            monthlyLayout.add(new JLabel("   " + dayOfWeek));
        }
        int startWeekDate = WEEK_LENGTH - displayFirstWeek() + 2;
        for (int weeks = 1; weeks < month.getNumWeeksInMonth(); weeks++) {
            displayWeekRow(startWeekDate, 1);
            startWeekDate += 7;
        }
        monthPanel.add(monthlyLayout);
    }

    // EFFECTS: displays the first week of given month
    public int displayFirstWeek() {
        int i = 1;
        while (i < month.getListDaysInMonth().get(0).getWeekday()) {
            monthlyLayout.add(new Box(LINE_AXIS));
            i++;
        }
        displayWeekRow(1, i);
        return i;
    }

    // EFFECTS: displays full week on console starting from weekStartDate
    public void displayWeekRow(int weekStartDate, int weekIndex) {
        int date = weekStartDate;
        for (int i = weekIndex; i <= WEEK_LENGTH; i++) {
            if (date <= month.getMonthLength()) {
                JButton dateButton = new JButton(Integer.toString(date));
                monthlyLayout.add(dateButton);
                monthButtons.put(date, dateButton);
                date++;
            }
        }
    }

}
