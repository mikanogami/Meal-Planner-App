package model.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

// CalendarItem.java extends GregorianCalendar and holds general information about a calendar item
// either a month or a day
// Month.java and Day.java extend this class

public abstract class CalendarItem extends GregorianCalendar {

    static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};

    static final String[] WEEK_NAMES = {"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};

    protected final Calendar calDay;
    protected final int year;
    protected final int month;
    protected final int weekday;
    protected int monthLength;
    protected int numWeeksInMonth;

    public CalendarItem(int year, int month, int day) {
        calDay = new GregorianCalendar(year, month, day);
        this.year  = year;
        this.month = month;
        weekday = calDay.get(DAY_OF_WEEK);
        monthLength = calDay.getActualMaximum(DAY_OF_MONTH);
        numWeeksInMonth = calDay.getActualMaximum(WEEK_OF_MONTH);
    }

    public CalendarItem(int year, int month) {
        calDay = new GregorianCalendar(year, month, 1);
        this.year = year;
        this.month = month;
        this.weekday = calDay.get(DAY_OF_WEEK);
        monthLength = calDay.getActualMaximum(DAY_OF_MONTH);
        numWeeksInMonth = calDay.getActualMaximum(WEEK_OF_MONTH);
    }


    // getters:
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getWeekday() {
        return weekday;
    }

    public int getMonthLength() {
        return monthLength;
    }

    public int getNumWeeksInMonth() {
        return numWeeksInMonth;
    }

    public String getWeekName() {
        return WEEK_NAMES[weekday - 1];
    }

    public String getMonthName() {
        return MONTH_NAMES[month];
    }
}
