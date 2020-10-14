package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Represents a collection of Entries for a particular day.
 */

public class DailyFoodLog {

    private List<Entry> foodLog; //food log (list of Entries)
    private String date;         //date assoc with food log

    /*
     * EFFECTS: constructs an empty food log with the current date.
     */
    public DailyFoodLog() {
        this.foodLog = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        this.date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds an Entry to the food log if Entry date matches current date
     */
    public void addEntry(Entry entry) {
        String entryDate = entry.getDate();
        if (this.date.equals(entryDate)) {
            foodLog.add(entry);
        }
    }

    //TODO: WRITE TESTS FOR THIS
    /*
     * MODIFIES: this
     * EFFECTS: sorts food log according to meal of each entry
     *          (BREAKFAST > LUNCH > DINNER > SNACK)
     */
    public void sortLog() {
        Collections.sort(foodLog);
    }


    /*
     * REQUIRES: trackedStat to be one of "Calories",
     *           "Protein", "Fat", or "Carbs"
     * EFFECTS: returns total amount of the specified
     *          trackedStat that's currently in the foodLog
     *          i.e. "Calories" -> return total amount of consumed calories
     */
    public int totalIntake(String trackedStat) {
        int totalIntake = 0;

        if (trackedStat == "Calories") {
            for (Entry entry : foodLog) {
                totalIntake += entry.getCalories();
            }
        } else if (trackedStat == "Protein") {
            for (Entry entry : foodLog) {
                totalIntake += entry.getProtein();
            }
        } else if (trackedStat == "Fat") {
            for (Entry entry : foodLog) {
                totalIntake += entry.getFat();
            }
        } else {
            for (Entry entry : foodLog) {
                totalIntake += entry.getCarbs();
            }
        }
        return totalIntake;
    }


    /*
     * REQUIRES: meal to be one of "BREAKFAST", "LUNCH", "DINNER" or "SNACK"
     * EFFECTS: returns a string representation of requested meal entries in foodLog
     *          along with total amount of calories eaten that meal
     */
    public String printMealReport(String meal) {

        String mealReport = date;
        int mealCal = 0;

        for (Entry entry : foodLog) {
            if (entry.getMeal() == meal) {
                mealCal += entry.getCalories();
                mealReport += "\n" + entry.printEntry();
            }
        }
        mealReport += "\n" + "Total Calories: " + mealCal;
        return mealReport;
    }

    /*
     * REQUIRES: foodLog is not empty
     * EFFECTS: prints out string representation of entire food log
     */
    public String printFullReport() {
        String fullReport = date;
        for (Entry entry : foodLog) {
            fullReport += "\n" + entry.printEntry();
        }
        fullReport += "\n" + "Total Calories: " + totalIntake("Calories");
        return fullReport;
    }

    public int getSize() {
        return foodLog.size();
    }

    public String getDate() {
        return date;
    }

    public Entry getEntry(int i) {
        return foodLog.get(i);
    }
}





