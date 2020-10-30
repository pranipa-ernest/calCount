package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
Represents a user of CalCount (name, age, sex, weight), their Goal,
and their associated TotalFoodLog
 */
public class User implements Writable {

    private int age;           //user age
    private String sex;        //user sex: "M" - males
                               //          "F" - females;
    private int weight;        //user weight (kg)
    private int height;        //user height (cm)


    private double activityLevel; //user activity level. One of:
                                  //            1.200 - Sedentary
                                  //            1.375 - Lightly active
                                  //            1.550 - Moderately active
                                  //            1.900 - Extremely active

    private String goalWeight;    //user goal in terms of their weight.
                                  //one of: "Lose", "Maintain", or "Gain"

    private Goal goal;            //user Goal (defines target calories,
                                  //protein, fat, and carb values specific
                                  //to the user and their goalWeight)

    private TotalFoodLog totalFoodLog;

    /* Constructs a user
     * REQUIRES: age >= 18 years && sex to be either "M" or "F"
     *           && weight to be in kg && height to be in cm
     *           && goalWeight to be one of "Lose", "Maintain",
     *           or "Gain"
     * EFFECTS: sets up a user profile based on user stats
     */
    public User(int age, String sex, int weight, int height, String goalWeight) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.goalWeight = goalWeight;
        this.activityLevel = 0;

        this.totalFoodLog = new TotalFoodLog();
    }

    /*
     * REQUIRES: activityLevel is one of "Sedentary",
     *           "Lightly Active", "Moderately Active",
     *           or "Extremely Active"
     * MODIFIES: this
     * EFFECTS: sets user activity level
     */
    public void setActivityLevel(String activityLevel) {
        if (activityLevel.equals("Sedentary")) {
            this.activityLevel = 1.200;
        } else if (activityLevel.equals("Lightly Active")) {
            this.activityLevel = 1.375;
        } else if (activityLevel.equals("Moderately Active")) {
            this.activityLevel = 1.550;
        } else {
            this.activityLevel = 1.900;
        }
    }

    /*
     * REQUIRES: protein, fat, and carbs to be in grams
     * MODIFIES: this, goal
     * EFFECTS: constructs goal according to calorie, protein, fat,
     *          and carbohydrate values as specified by the user
     */
    public void setCustomGoal(int cal, int prot, int fat, int carbs) {
        this.goal = new Goal(this);
        this.goal.setCustomGoal(cal,prot,fat,carbs);
    }

    /*
     * REQUIRES: user activity level to have been set to a valid value
     * MODIFIES: this, goal
     * EFFECTS: constructs goal according to user activityLevel. User
     *          does not have to specify their own calorie, protein,
     *          fat, or carb targets
     */
    public void setRecommendedGoal() {
        this.goal = new Goal(this);
        this.goal.setRecommendedGoal();
    }

    /*
     * REQUIRES: newWeight to be in kg
     * MODIFIES: this
     * EFFECTS: changes the weight of the user
     */
    public void updateWeight(int newWeight) {
        this.weight = newWeight;
    }

    /*
     * MODIFIES: this, totalFoodLog
     * EFFECTS: adds a DailyFoodLog to the TotalFoodLog of the user
     */
    public void addDailyFoodLog(DailyFoodLog dailyFoodLog) {
        totalFoodLog.addLog(dailyFoodLog);
    }

    /*
     * REQUIRES: date to be in long form (i.e. October 30, 2020)
     * EFFECTS: finds and returns daily food log with specified date
     *          in user's total food log collection
     */
    public DailyFoodLog findDailyFoodLog(String date) {
        return totalFoodLog.findLog(date);
    }


    /*
     * EFFECTS: converts user information, goal, and associated
     *          total food log to json format
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("age", this.age);
        json.put("sex", this.sex);
        json.put("weight", this.weight);
        json.put("height",this.height);
        json.put("goal weight", this.goalWeight);
        json.put("activity level", activityLevelToJson());

        json.put("goal", goalToJson());

        json.put("total food log", totalFoodLogToJson());
        return json;
    }

    /*
     * EFFECTS: returns string representation of activityLevel
     */
    private String activityLevelToJson() {
        if (activityLevel == 1.200) {
            return "Sedentary";
        } else if (activityLevel == 1.375) {
            return "Lightly Active";
        } else if (activityLevel == 1.550) {
            return "Moderately Active";
        } else {
            return "Extremely Active";
        }
    }

    /*
     * EFFECTS: converts user's total food log to json format
     */
    private JSONArray totalFoodLogToJson() {
        JSONArray jsonArray = this.totalFoodLog.toJson();
        return jsonArray;
    }

    /*
     * EFFECTS: converts user's goal to json format
     */
    private JSONObject goalToJson() {
        JSONObject json = this.goal.toJson();
        return json;
    }

    //GETTERS AND SETTERS
    public int getAge() {
        return this.age;
    }

    public String getSex() {
        return this.sex;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getHeight() {
        return this.height;
    }

    public String getGoalWeight() {
        return goalWeight;
    }

    public double getActivityLevel() {
        return this.activityLevel;
    }

    public Goal getGoal() {
        return this.goal;
    }

    public TotalFoodLog getTotalFoodLog() {
        return totalFoodLog;
    }

}
