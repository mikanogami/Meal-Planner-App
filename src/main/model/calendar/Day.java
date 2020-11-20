package model.calendar;

import model.dish.Dish;
import model.dish.MealType;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a day, containing information about current day and the month that contains it
// Contains list of dishes as part of meal plan for particular day

public class Day extends CalendarItem implements Writable {

    protected final List<Dish> listOfDishes;
    private int day;

    public Day(Month month, int date) {
        super(month.getYear(), month.getMonth(), date);
        listOfDishes = new ArrayList<>();
        day = date;
    }

    public Day(int year, int month, int date) {
        super(year, month, date);
        listOfDishes = new ArrayList<>();
        day = date;
    }

    public List<Dish> getListOfDishes() {
        return listOfDishes;
    }

    // MODIFIES: this
    // EFFECTS: Adds given dish to list of dishes in this day
    public void addDish(Dish dish) {
        listOfDishes.add(dish);
    }

    // EFFECTS: returns list of dishes in this.listOfDishes of given type
    public List<Dish> listOfGivenMealType(MealType mt) {
        List<Dish> listDishesOfMT = new ArrayList<>();
        for (Dish dish: listOfDishes) {
            if (dish.getMealType().equals(mt)) {
                listDishesOfMT.add(dish);
            }
        }
        return listDishesOfMT;
    }

    // EFFECTS: returns number of dishes of given type in listOfDishes
    public int numDishOfGivenMealType(MealType mt) {
        int numDishOfMT = 0;
        for (Dish dish: listOfDishes) {
            if (dish.getMealType().equals(mt)) {
                numDishOfMT++;
            }
        }
        return numDishOfMT;
    }

    public int getDay() {
        return day;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonDay = new JSONObject();
        JSONArray listDishes = new JSONArray();
        jsonDay.put("date", this.day);
        for (Dish d: this.listOfDishes) {
            listDishes.put(d.toJson());
        }
        jsonDay.put("listOfDishes", listDishes);
        return jsonDay;
    }
}
