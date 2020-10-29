package persistence;

import model.DailyFoodLog;
import model.Entry;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    private User parseUser(JSONObject jsonObject) {
        int age = jsonObject.getInt("age");
        String sex = jsonObject.getString("sex").toUpperCase();
        int weight = jsonObject.getInt("weight");
        int height = jsonObject.getInt("height");
        String goalWeight = jsonObject.getString("goal weight");
        String activityLevel = jsonObject.getString("activity level");

        User user = new User(age,sex,weight,height,goalWeight);
        user.setActivityLevel(activityLevel);

        parseGoal(user, jsonObject);
        parseFoodLog(user, jsonObject);

        return user;
    }

    private void parseGoal(User user, JSONObject jsonObject) {
        JSONObject goalJsonObject = jsonObject.getJSONObject("goal");
        int targetCalories = goalJsonObject.getInt("target calories");
        int targetProtein = goalJsonObject.getInt("target protein");
        int targetFat = goalJsonObject.getInt("target fat");
        int targetCarbs = goalJsonObject.getInt("target carbs");

        user.setCustomGoal(targetCalories,targetProtein,targetFat,targetCarbs);
    }

    private void parseFoodLog(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("total food log");

        for (Object json : jsonArray) {
            JSONObject foodLogObject = (JSONObject) json;
            addDailyFoodLog(user, foodLogObject);
        }
    }

    private void addDailyFoodLog(User user, JSONObject foodLogObject) {
        DailyFoodLog dailyFoodLog = new DailyFoodLog();

        String date = foodLogObject.getString("date");
        dailyFoodLog.setDate(date);

        JSONArray jsonArray = foodLogObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject jsonEntry = (JSONObject) json;
            String meal = jsonEntry.getString("meal");
            String food = jsonEntry.getString("food");
            int calories = jsonEntry.getInt("calories");
            int protein = jsonEntry.getInt("protein");
            int fat = jsonEntry.getInt("fat");
            int carbs = jsonEntry.getInt("carbs");

            Entry entry = new Entry(meal,food,calories);
            entry.setMacros(protein,fat,carbs);
            dailyFoodLog.addEntry(entry);
        }
        user.addDailyFoodLog(dailyFoodLog);
    }


}
