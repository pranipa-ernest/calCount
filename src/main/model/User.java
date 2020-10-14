package model;


/*
Represents a user of CalCount (name, age, sex, weight), their Goal,
and their associated TotalFoodLog
 */
public class User {

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
    }

    /*
     * REQUIRES: activityLevel is one of "Sedentary",
     *           "Lightly Active", "Moderately Active",
     *           or "Extremely Active"
     * MODIFIES: this
     * EFFECTS: sets user activity level
     */
    public void setActivityLevel(String activityLevel) {
        if (activityLevel == "Sedentary") {
            this.activityLevel = 1.200;
        } else if (activityLevel == "Lightly Active") {
            this.activityLevel = 1.375;
        } else if (activityLevel == "Moderately Active") {
            this.activityLevel = 1.550;
        } else if (activityLevel == "Extremely Active") {
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
        this.goal = new Goal(this.goalWeight);
        this.goal.setCustomGoal(cal,prot,fat,carbs);
    }

    /*
     * REQUIRES: activityLevel to be one of: "Sedentary", "Lightly
     *           Active", "Moderately Active", or "Extremely Active"
     * MODIFIES: this, goal
     * EFFECTS: constructs goal according to user activityLevel. User
     *          does not have to specify their own calorie, protein,
     *          fat, or carb targets
     */
    public void setRecommendedGoal(String activityLevel) {
        setActivityLevel(activityLevel);
        this.goal = new Goal(this.goalWeight);
        this.goal.setRecommendedGoal(this);
    }

    /*
     * REQUIRES: newWeight to be in kg
     * MODIFIES: this
     * EFFECTS: changes the weight of the user
     */
    public void updateWeight(int newWeight) {
        this.weight = newWeight;
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
}
