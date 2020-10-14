package model;

/*
Represents how many calories, protein, fat, and
carbs the User has left to eat to meet their target
values as specified in their Goal.
 */
public class DailyIntake {

    private int targetCal;     //target calorie value for user
    private int targetProt;    //target protein value for user
    private int targetFat;     //target fat value for user
    private int targetCarbs;   //target carb value for user

    /* Sets up DailyIntake fields
     * EFFECTS: sets up the target nutrition values
     *          the user is aiming to reach
     */
    public DailyIntake(Goal goal) {
        targetCal = goal.getTargetCalories();
        targetProt = goal.getTargetProtein();
        targetFat = goal.getTargetFat();
        targetCarbs = goal.getTargetCarbs();
    }

    /*
     * EFFECTS: returns the number of calories the user
     *          has left to eat in the current day. Min
     *          remaining calories = 0;
     */
    public int totalCaloriesRemaining(DailyFoodLog foodLog) {
        int remainingCal = targetCal - foodLog.totalIntake("Calories");

        if (remainingCal < 0) {
            remainingCal = 0;
        }
        return remainingCal;
    }

    /*
     * REQUIRES: protein in all foodLog entries to be
     *           in g
     * EFFECTS: returns the amount of protein the user
     *          has left to eat in the current day. Min
     *          remaining protein = 0g;
     */
    public int totalProtRemaining(DailyFoodLog foodLog) {
        int remainingProt = targetProt - foodLog.totalIntake("Protein");

        if (remainingProt < 0) {
            remainingProt = 0;
        }
        return remainingProt;
    }

    /*
     * REQUIRES: fat in all foodLog entries to be
     *           in g
     * EFFECTS: returns the amount of fat the user
     *          has left to eat in the current day. Min
     *          remaining fat = 0g;
     */
    public int totalFatRemaining(DailyFoodLog foodLog) {
        int remainingFat = targetFat - foodLog.totalIntake("Fat");

        if (remainingFat < 0) {
            remainingFat = 0;
        }
        return remainingFat;
    }

    /*
     * REQUIRES: carbs in all foodLog entries to be
     *           in g
     * EFFECTS: returns the amount of carbs the user
     *          has left to eat in the current day. Min
     *          remaining carbs = 0g;
     */
    public int totalCarbsRemaining(DailyFoodLog foodLog) {
        int remainingCarbs = targetCarbs - foodLog.totalIntake("Carbs");

        if (remainingCarbs < 0) {
            remainingCarbs = 0;
        }
        return remainingCarbs;
    }

    //GETTERS AND SETTERS
    public int getTargetCal() {
        return targetCal;
    }

    public int getTargetProt() {
        return targetProt;
    }

    public int getTargetFat() {
        return targetFat;
    }

    public int getTargetCarbs() {
        return targetCarbs;
    }




}
