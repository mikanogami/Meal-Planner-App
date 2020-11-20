package persistence;

import model.calendar.Months;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Writes information in MealPlannerApp.calendar to a given file

public class JsonWriter {

    private String savedFile;
    private PrintWriter writer;
    private static final int TAB = 4;

    // EFFECTS: creates new JsonReader and assigns file that we want to write to, to this.savedFile
    public JsonWriter(String savedFile) {
        this.savedFile = savedFile;
    }

    // EFFECTS: opens this.savedFile
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(savedFile));
    }

    // EFFECTS: Given Months object and converts it to a JSONArray
    //          calls saveToFile
    public void write(Months months) {
        JSONArray json = months.toJson();
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: closes this.savedFile
    public void close() {
        writer.close();
    }

    // EFFECTS: writes to file
    public void saveToFile(String json) {
        writer.print(json);
    }

}
