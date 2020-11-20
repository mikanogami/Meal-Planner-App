package persistence;

import model.calendar.Day;
import model.calendar.Month;
import model.calendar.Months;
import model.dish.Dish;
import model.dish.MealType;
import model.grocery.FoodItem;
import model.grocery.FoodType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

// Reads from json file to construct objects that make up meal plan
// this meal plan is the most recent saved meal plan

public class JsonReader {

    private String savedFile;

    // EFFECTS: creates new JsonReader and assigns file that we want to read from to this.savedFile
    public JsonReader(String savedFile) {
        this.savedFile = savedFile;
    }

    // EFFECTS: reads from json file and returns the Months object (list of months in meal plan)
    public Months read() throws IOException {
        String jsonData = readFile(savedFile);
        JSONArray jsonCalendar = new JSONArray(jsonData);
        return buildCalendar(jsonCalendar);
    }


    // The code for readFile method was written by Paul Carter
    // from project JsonSerializationDemo from Github
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Takes JsonArray which is list of JsonObjects, each representing one month
    //          Builds new Months() from the JSONArray and returns it
    public Months buildCalendar(JSONArray jsonCalendar) {
        Months calendar = new Months();
        for (Object m: jsonCalendar) {
            calendar.addMonth(buildMonth((JSONObject)m));
        }
        return calendar;
    }

    // EFFECTS: creates new month, and iterates through listOfDays in each month and adds all dishes
    public Month buildMonth(JSONObject month) {
        Month newMonth = new Month(month.getInt("year"), month.getInt("month"));
        Iterator nextDay = month.getJSONArray("days").iterator();
        while (nextDay.hasNext()) {
            Object object = nextDay.next();
            JSONObject jsonDay = (JSONObject)object;
            addDishToDay(jsonDay, newMonth.getListDaysInMonth().get(jsonDay.getInt("date") - 1));
        }
        return newMonth;
    }

    // EFFECTS: takes Day Object, and JSONObject jsonDay and iterates through listOfDishes in jsonDay
    //          Constructs dish and adds it to Day Object
    public void addDishToDay(JSONObject day, Day dayInMonth) {
        JSONArray jsonDishes = day.getJSONArray("listOfDishes");
        for (Object dish: jsonDishes) {
            JSONObject jsonDish = (JSONObject) dish;
            String dishName = jsonDish.getString("name");
            MealType mealType = MealType.valueOf(jsonDish.getString("mealType"));

            Dish newDish = new Dish(dishName, mealType);

            Iterator nextGrocery = jsonDish.getJSONArray("groceries").iterator();
            while (nextGrocery.hasNext()) {
                Object object = nextGrocery.next();
                JSONObject grocery = (JSONObject) object;
                FoodItem food = fillDishWithGrocery(grocery);
                newDish.addToGroceries(food);
            }

            JSONArray listInstruct = jsonDish.getJSONArray("specialInstructions");
            for (int i = 0; i < listInstruct.length(); i++) {
                String instruct = listInstruct.getString(i);
                newDish.addSpecialInstructions(instruct);
            }
            dayInMonth.addDish(newDish);
        }
    }

    // EFFECTS: constructs FoodItem and returns it
    public FoodItem fillDishWithGrocery(JSONObject grocery) {
        String foodName = grocery.getString("name");
        Boolean foodIsCompleted = grocery.getBoolean("com");
        Boolean foodIsFavourite = grocery.getBoolean("fav");
        FoodType foodType = FoodType.valueOf(grocery.getString("foodType"));

        FoodItem newGrocery = new FoodItem(foodName, foodType);
        newGrocery.setIsFavourite(foodIsFavourite);
        newGrocery.setIsCompleted(foodIsCompleted);

        return newGrocery;
    }

}
