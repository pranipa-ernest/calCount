package model;

import org.json.JSONArray;
import persistence.WritableArray;

import java.util.ArrayList;
import java.util.List;

/*
A collection of all DailyFoodLogs for a particular User
 */
public class TotalFoodLog implements WritableArray {

    private List<DailyFoodLog> totalFoodLog;  //list of DailyFoodLogs

    /* Constructs a TotalFoodLog
     * EFFECTS: sets up an empty TotalFoodLog
     */
    public TotalFoodLog() {
        totalFoodLog = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a single DailyFoodLog to totalFoodLog
     */
    public void addLog(DailyFoodLog foodLog) {
        totalFoodLog.add(foodLog);
    }

    public DailyFoodLog findLog(String date) {
        for (DailyFoodLog foodLog : totalFoodLog) {
            if (foodLog.getDate().equals(date)) {
                return foodLog;
            }
        }
        return null;
    }

    /*
     * EFFECTS: converts this to json format
     */
    @Override
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (DailyFoodLog foodLog : totalFoodLog) {
            jsonArray.put(foodLog.toJson());
        }
        return jsonArray;
    }

    //GETTERS AND SETTERS
    public int getSize() {
        return totalFoodLog.size();
    }

    public DailyFoodLog getLog(int x) {
        return totalFoodLog.get(x);
    }

    public List<DailyFoodLog> getTotalFoodLog() {
        return totalFoodLog;
    }
}
