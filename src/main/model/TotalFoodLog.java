package model;

import java.util.ArrayList;
import java.util.List;

/*
A collection of all DailyFoodLogs for a particular User
 */
public class TotalFoodLog {

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
    public void addEntry(DailyFoodLog foodLog) {
        totalFoodLog.add(foodLog);
    }

    //GETTERS AND SETTERS
    public int getSize() {
        return totalFoodLog.size();
    }

    public DailyFoodLog getEntry(int x) {
        return totalFoodLog.get(x);
    }


}
