package model.calendar;

import model.dish.Dish;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a month as a list of days

public class Month extends CalendarItem implements Writable {

    private static final int MAX_MONTH = 11;
    private static final int MIN_MONTH = 0;

    private final List<Day> daysInMonth;


    // MODIFIES: this
    // EFFECTS: changes month and year to current month and year
    // acts as starting month for user, they have the option to later switch between months
    public Month(int year, int month) {
        super(year, month);

        daysInMonth = new ArrayList<>();
        for (int i = 1; i <= getMonthLength(); i++) {
            daysInMonth.add(new Day(this, i));
        }
    }

    public List<Day> getListDaysInMonth() {
        return this.daysInMonth;
    }


    // REQUIRES: Date to exist within given month
    // MODIFIES: this, Day.listOfDishes
    // EFFECTS: adds dish to listOfDishes within given Day object within list of daysOfMonth
    // returns true if successful, returns false if not successful
    public void addDish(Dish dish, int date) {
        daysInMonth.get(date - 1).addDish(dish);
    }

    // MODIFIES: this
    // EFFECTS: changes this.month and this.year to the month and year of the previous month
    // returns next month
    public int nextMonth() {
        if (month == MAX_MONTH) {
            return MIN_MONTH;
        } else {
            return month + 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes this.month and this.year to the month and year of the previous month
    // returns next month
    public int prevMonth() {
        if (month == MIN_MONTH) {
            return MAX_MONTH;
        } else {
            return month - 1;
        }
    }

    // EFFECTS: returns true if next month goes into next year
    public int nextMonthYear() {
        if (month == MAX_MONTH) {
            return year + 1;
        } else {
            return year;
        }
    }

    // EFFECTS: returns true if previous month goes back into last year
    public int prevMonthYear() {
        if (month == MIN_MONTH) {
            return year - 1;
        } else {
            return year;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonMonth = new JSONObject();
        JSONArray listDays = new JSONArray();
        jsonMonth.put("month", month);
        jsonMonth.put("year", year);
        for (Day day: daysInMonth) {
            listDays.put(day.toJson());
        }
        jsonMonth.put("days", listDays);
        return jsonMonth;
    }
}
