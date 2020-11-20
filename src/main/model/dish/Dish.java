package model.dish;

import model.grocery.FoodItem;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents dish having a name, a meal type, a rating, instructions and list of groceries needed to make it

public class Dish implements Writable {

    private final String name;
    private final MealType mealType;
    private int rating;
    private List<String> specialInstructions;
    private final List<FoodItem> groceries;

    public Dish(String name, MealType mt) {
        this.name = name;
        mealType = mt;
        rating = -1;
        specialInstructions = new ArrayList<>();
        groceries = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds grocery to list of needed groceries for this dish
    public void addToGroceries(FoodItem grocery) {
        groceries.add(grocery);
    }

    // setters

    // REQUIRES: given integer value from 1 to 5
    // MODIFIES: this
    // EFFECTS: sets rating for dish
    public Boolean setRating(int rating) {
        if (1 <= rating && rating <= 5) {
            this.rating = rating;
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: takes string on instructions as param. and assigns them to this.specialInstructions
    public void addSpecialInstructions(String instructions) {
        specialInstructions.add(instructions);
    }


    // getters

    // EFFECTS: returns name of dish
    public String getName() {
        return name;
    }

    // EFFECTS: returns MealType of dish
    public MealType getMealType() {
        return mealType;
    }

    // EFFECTS: returns rating if there is any
    public int getRating() {
        return rating;
    }

    // EFFECTS: returns special instructions if there are any
    public List<String> getSpecialInstructions() {
        return specialInstructions;
    }

    // EFFECTS: returns all FoodItems for dish that are missing ingredients
    public List<FoodItem> getGroceries() {
        return groceries;
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonDish = new JSONObject();
        jsonDish.put("name", this.name);
        jsonDish.put("mealType", this.mealType);
        jsonDish.put("rating", this.rating);
        JSONArray instructions = new JSONArray();
        JSONArray groceries = new JSONArray();
        for (String i: this.specialInstructions) {
            instructions.put(i);
        }
        jsonDish.put("specialInstructions", instructions);
        for (FoodItem f: this.groceries) {
            groceries.put(f.toJson());
        }
        jsonDish.put("groceries", groceries);
        return jsonDish;
    }
}
