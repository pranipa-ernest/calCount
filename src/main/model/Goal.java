package model;

import org.json.JSONObject;
import persistence.Writable;

/*
Represents a Goal specific to the user. Sets calorie, protein,
fat, and carbohydrate targets that the user aims to meet
every day. Users can set their own custom nutrient targets
or they can choose to follow recommended targets based on user's
stats (i.e. weight, height, age, sex) and goal (lose weight, maintain
weight, or gain weight)
 */
public class Goal implements Writable {

    private User user;   // One of: "Lose", "Maintain", or "Gain"

    private int targetCalories; //target calorie value for user
    private int targetProtein;  //target protein value for user (grams)
    private int targetFat;      //target fat value for user (grams)
    private int targetCarbs;    //target carbohydrate value for (grams)

    /* Constructs a Goal
     * REQUIRES: user to have valid fields (i.e. weight in kg);
     * EFFECTS: initializes Goal specific to that User
     */
    public Goal(User user) {
        this.user = user;
    }

    /*
     * REQUIRES: user to have valid fields according to
     *           User class (i.e. height in cm) && user
     *           to have set up their activityLevel to a
     *           valid value according to User class.
     * MODIFIES: this
     * EFFECTS: sets the target calorie, protein, fat,
     *          and carb values according to user stats
     */
    public void setRecommendedGoal() {
        int tdee = setTdee();
        this.targetCalories = findTargetCalWithGoal(tdee);
        this.targetProtein = setTargetProtein(targetCalories);
        this.targetFat = setTargetFat(targetCalories);
        this.targetCarbs = setTargetCarbs(targetCalories);
    }

    /*
     * REQUIRES: user to have valid fields (i.e. weight in kg)
     * EFFECTS: returns total daily energy expenditure of user
     */
    public int setTdee() {
        int tdee;

        if (user.getSex() == "M") {
            tdee = (int)(13.7 * user.getWeight() + 5 * user.getHeight()
                    - 6.8 * user.getAge() + 66);
        } else {
            tdee = (int)(9.6 * user.getWeight() + 1.8 * user.getHeight()
                    - 4.7 * user.getAge() + 655);
        }
        tdee *= user.getActivityLevel();
        return tdee;
    }

    /*
     * EFFECTS: returns the number of calories that the user
     *          is recommended to consume per day in order to
     *          reach their goal (lose, maintain, or gain weight).
     *          Min number of calories = 1200.
     */
    public int findTargetCalWithGoal(int tdee) {
        int targetCal = tdee;
        if (user.getGoalWeight() == "Lose") {
            targetCal -= 500;
            if (targetCal < 1200) {
                targetCal = 1200;
            }
        } else if (user.getGoalWeight() == "Gain") {
            targetCal += 500;
        }
        return targetCal;
    }

    /*
     * EFFECTS: returns the amount of protein (g) that the user is
     *          recommended to consume per day according to their
     *          recommended target calorie value
     */
    public int setTargetProtein(int targetCalories) {
        int targetProt = (int) (0.3 * targetCalories);
        return targetProt;
    }

    /*
     * EFFECTS: returns the amount of fat (g) that the user is
     *          recommended to consume per day according to their
     *          recommended target calorie value
     */
    public int setTargetFat(int targetCalories) {
        int targetFat = (int)(0.2 * targetCalories);
        return targetFat;
    }

    /*
     * EFFECTS: returns the amount of fat (g) that the user is
     *          recommended to consume per day according to their
     *          recommended target calorie value
     */
    public int setTargetCarbs(int targetCalories) {
        int targetCarbs = (int) (0.5 * targetCalories);
        return targetCarbs;
    }

    /*
     * REQUIRES: protein, fat, and carbs to be in grams
     * MODIFIES: this
     * EFFECTS: sets target calories, protein, fat, and
     *          carb values to user-input
     */
    public void setCustomGoal(int calories, int protein, int fat, int carbs) {
        this.targetCalories = calories;
        this.targetProtein = protein;
        this.targetFat = fat;
        this.targetCarbs = carbs;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("target calories", targetCalories);
        json.put("target protein", targetProtein);
        json.put("target fat", targetFat);
        json.put("target carbs", targetCarbs);
        return json;
    }

    //GETTERS AND SETTERS
    public int getTargetCalories() {
        return targetCalories;
    }

    public int getTargetProtein() {
        return targetProtein;
    }

    public int getTargetFat() {
        return targetFat;
    }

    public int getTargetCarbs() {
        return targetCarbs;
    }

    public String getGoalWeight() {
        return user.getGoalWeight();
    }

    public User getUser() {
        return user;
    }
}
