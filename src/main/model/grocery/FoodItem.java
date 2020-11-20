package model.grocery;

// Represents food item that has a name, a food type
// Food item can either be a favourite food item or not

import org.json.JSONObject;
import persistence.Writable;

public class FoodItem implements Writable {

    private String name;
    private FoodType type;
    private Boolean isFavourite;
    private Boolean isCompleted;

    // MODIFIES: this
    // EFFECTS: constructs FoodItem Object
    public FoodItem(String name, FoodType type) {
        this.name = name;
        this.type = type;
        this.isFavourite = false;
        this.isCompleted = false;
    }

    // setters:
    public void setIsFavourite(Boolean isFav) {
        this.isFavourite = isFav;
    }

    public void setIsCompleted(Boolean isCom) {
        this.isCompleted = isCom;
    }

    // getters
    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    // EFFECTS: builds JSONObject that contains the information in this
    @Override
    public JSONObject toJson() {
        JSONObject jsonFood = new JSONObject();
        jsonFood.put("name", this.name);
        jsonFood.put("foodType", this.type);
        jsonFood.put("fav", this.isFavourite);
        jsonFood.put("com", this.isCompleted);
        return jsonFood;
    }
}
