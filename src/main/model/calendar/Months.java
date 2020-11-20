package model.calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Months {

    private List<Month> months;

    public Months() {
        months = new ArrayList<>();
    }

    public void addMonth(Month m) {
        months.add(m);
    }

    public int getNumMonths() {
        return months.size();
    }

    public List<Month> getMonths() {
        return months;
    }

    public Month getMonth(int year, int month) {
        for (Month m: months) {
            if (m.getMonth() == month && m.getYear() == year) {
                return m;
            }
        }
        Month newMonth = new Month(year, month);
        addMonth(newMonth);
        return newMonth;
    }

    public JSONArray toJson() {
        JSONArray jsonCalendar = new JSONArray();
        for (Month m: months) {
            jsonCalendar.put(m.toJson());
        }
        return jsonCalendar;
    }
}
