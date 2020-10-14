package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

/*
Represents an individual entry in the food log. Each entry has fields
for the date recorded, the type of meal, the name of the food, the number
of calories, and the number of macronutrients (optional).
 */
public class Entry {

    //meal of entry
    //is of type enum as I want to implement a sorting algo
    //later on in the project
    private Meals meal;

    private enum Meals {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }

    private String date;    //date of entry
    private String food;       //name of food eaten
    private int calories;      //amount of calories

    //Optional fields; initialized to 0.
    private int protein = 0;   //amount of protein (grams)
    private int fat = 0;       //amount of fat (grams)
    private int carbs = 0;     //amount of carbs (grams)


    /* Constructs a single entry
     * REQUIRES: meal to be either "BREAKFAST", "LUNCH",
     *           "DINNER" or "SNACK"
     * EFFECTS: sets up entry based on user-inputted meal, food,
     *          and calorie values.
     */
    public Entry(String meal, String food, int calories) {
        this.food = food;
        this.calories = calories;

        LocalDate localDate = LocalDate.now();
        this.date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        if (meal == "BREAKFAST") {
            this.meal = Meals.BREAKFAST;
        } else if (meal == "LUNCH") {
            this.meal = Meals.LUNCH;
        } else if (meal == "DINNER") {
            this.meal = Meals.DINNER;
        } else if (meal == "SNACK") {
            this.meal = Meals.SNACK;
        }
    }

    /*
     * REQUIRES: protein, fat, and carbs in grams
     * MODIFIES: this
     * EFFECTS: protein, fat, and carb amounts are updated
     */
    public void setMacros(int protein, int fat, int carbs) {
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    /*
     * EFFECTS: returns a string representation of the entry
     */
    public String printEntry() {

        String meal = this.getMeal();
        String food = "Food: " + this.food;
        String cal = "Calories: " + this.calories;
        String prot = "Protein:" + this.protein;
        String fat = "Fat:" + this.fat;
        String carbs = "Carbs:" + this.carbs;


        return (meal + "\n" + food + "\n" + cal + "\n" + prot + "\n" + fat + "\n" + carbs);
    }

    //GETTERS AND SETTERS
    public String getMeal() {
        return this.meal.name();
    }

    public String getFood() {
        return this.food;
    }

    public int getCalories() {
        return this.calories;
    }

    public int getProtein() {
        return this.protein;
    }

    public int getFat() {
        return this.fat;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public String getDate() {
        return this.date;
    }

    /*
     * REQUIRES: date to be in correct LocalDate LONG DateTime format
     *           ex. June 30, 2009
     * MODIFIES: this
     * EFFECTS: changes Entry date
     */
    public void setDate(String date) {
        this.date = date;
    }

}
