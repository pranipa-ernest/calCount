package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//CalCount Application
public class CalCount {
    private static final String JSON_STORE = "./data/calCount.json";

    private User user;
    private DailyFoodLog foodLog;
    private DailyIntake dailyIntake;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the calorie counter application
    public CalCount() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runStartingMenu();
    }

    private void runStartingMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayStartMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("n")) {
                initCalCount();
                keepGoing = false;
            } else if (command.equals("l")) {
                loadCalCount();
                keepGoing = false;
            } else {
                System.out.println("\nPlease choose from the existing options");
            }
        }
    }

    private void loadCalCount() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded existing user from " + JSON_STORE);
            runCalCount();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("User may not exist");
        }
    }

    private void initCalCount() {
        initUser();
        setUpGoal();
        displayGoal();
        runCalCount();
    }


    //MODIFIES: this
    //EFFECTS: initializes application;
    //         processes user input
    private void runCalCount() {
        foodLog = new DailyFoodLog();

        boolean keepGoing = true;
        String command = null;
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
                user.addDailyFoodLog(foodLog);
                saveApp();
            } else {
                calCountOptions(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    private void saveApp() {
        boolean keepGoing = true;
        String command = null;
        while (keepGoing) {
            saveAppDisplay();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("n")) {
                keepGoing = false;
            } else if (command.equals("y")) {
                saveCalCount();
                keepGoing = false;
            } else {
                System.out.println("Please choose a valid option");
            }
        }
    }

    private void saveCalCount() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved application");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void saveAppDisplay() {
        System.out.println("\nWould you like to save your existing food logs?");
        System.out.println("Yes -> y");
        System.out.println("No -> n");
    }

    //EFFECTS: displays initial menu when running application
    private void displayStartMenu() {
        System.out.println("\nPlease choose from the following options:");
        System.out.println("Set up a new user -> n");
        System.out.println("Load existing user -> l");
        System.out.println("Quit app -> q");
    }

    //MODIFIES: this
    //EFFECTS: initializes user
    private void initUser() {
        String sex = processSex();
        int age = processAge();
        int weight = processWeight();
        int height = processHeight();
        String goal = processGoal();

        user = new User(age,sex,weight,height,goal);
    }

    //EFFECTS: processes user sex
    private String processSex() {
        String command;
        String sex = null;

        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\nPlease enter your sex (m/f)");
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("m") | command.equals("f")) {
                sex = command;
                keepGoing = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        }
        return sex;
    }

    //EFFECTS: processes user age
    private int processAge() {
        System.out.println("Please enter your age");
        int age = input.nextInt();
        return age;
    }

    //EFFECTS: processes user weight
    private int processWeight() {
        System.out.println("Please enter your weight (kg)");
        int weight = input.nextInt();
        return weight;
    }

    //EFFECTS: processes user height
    private int processHeight() {
        System.out.println("Please enter your height (cm)");
        int height = input.nextInt();
        return height;
    }

    //EFFECTS: processes user weight-related goal
    private String processGoal() {
        displayWeightGoalOptions();
        String goal = input.next();

        boolean keepGoing = true;
        while (keepGoing) {
            if (goal.equals("l")) {
                keepGoing = false;
                goal = "Lose";
            } else if (goal.equals("m")) {
                keepGoing = false;
                goal = "Maintain";
            } else if (goal.equals("g")) {
                keepGoing = false;
                goal = "Gain";
            } else {
                System.out.println("Please enter a valid input");
            }
        }
        return goal;
    }

    //EFFECTS: displays weight-related goal options available to user
    private void displayWeightGoalOptions() {
        System.out.println("\nWould you like to to:");
        System.out.println("Lose weight -> l");
        System.out.println("Maintain weight -> m");
        System.out.println("Gain weight -> g:");
    }

    //EFFECTS: sets up user goal to either a
    //         custom goal or a recommended goal
    private void setUpGoal() {
        displayGoalOptions();
        String goal = input.next();

        boolean keepGoing = true;
        while (keepGoing) {
            if (goal.equals("c")) {
                keepGoing = false;
                initCustomGoal();
            } else if (goal.equals("r")) {
                keepGoing = false;
                initRecommendedGoal();
            } else {
                System.out.println("Please enter a valid input");
            }
        }

    }

    //EFFECTS: displays options to set user goal
    private void displayGoalOptions() {
        System.out.println("\nWould you like to set your own calorie goal");
        System.out.println("or follow a recommended calorie goal?");
        System.out.println("Custom -> c");
        System.out.println("Recommended -> r");
    }

    //MODIFIES: user
    //EFFECTS: initializes a custom goal for the user
    private void initCustomGoal() {
        System.out.println("Please set your target calories");
        int cal = input.nextInt();

        System.out.println("Please set your target protein (g)");
        int prot = input.nextInt();

        System.out.println("Please set your target fat (g)");
        int fat = input.nextInt();

        System.out.println("Please set your target carbs (g)");
        int carbs = input.nextInt();

        user.setCustomGoal(cal,prot,fat,carbs);
    }

    //MODIFIES: user
    //EFFECTS: initializes a recommended goal for the user
    private void initRecommendedGoal() {
        setActivityLevel();
        user.setRecommendedGoal();
    }

    //MODIFIES: user
    //EFFECTS: sets an activity level for the user
    private void setActivityLevel() {
        setActivityLevelDisplay();
        String activity = input.next();

        boolean keepGoing = true;
        while (keepGoing) {
            if (activity.equals("s")) {
                keepGoing = false;
                user.setActivityLevel("Sedentary");
            } else if (activity.equals("l")) {
                keepGoing = false;
                user.setActivityLevel("Lightly Active");
            } else if (activity.equals("m")) {
                keepGoing = false;
                user.setActivityLevel("Moderately Active");
            } else if (activity.equals("e")) {
                keepGoing = false;
                user.setActivityLevel("Extremely Active");
            } else {
                System.out.println("Please enter a valid value");
                setActivityLevelDisplay();
            }
        }
    }

    //EFFECTS: displays options to set the user's activity-level
    private void setActivityLevelDisplay() {
        System.out.println("\nPlease set your activity level:");
        System.out.println("Sedentary -> s");
        System.out.println("Lightly Active -> l");
        System.out.println("Moderately Active -> m");
        System.out.println("Extremely Active -> e");
    }

    //EFFECTS: displays the target calorie, protein, fat, and carb
    //         amounts for the user according to the goal they chose
    private void displayGoal() {
        int calories = user.getGoal().getTargetCalories();
        System.out.println("\nThe amount of calories you should eat per day: " + calories);

        int protein = user.getGoal().getTargetProtein();
        System.out.println("The amount of protein you should eat per day (g): " + protein);

        int fat = user.getGoal().getTargetFat();
        System.out.println("The amount of fat you should eat per day (g): " + fat);

        int carbs = user.getGoal().getTargetCarbs();
        System.out.println("The amount of carbs you should eat per day: " + carbs);

    }

    //EFFECTS: displays menu of options for the user
    private void displayMenu() {
        System.out.println("\nPlease choose from the following options:");
        System.out.println("\nAdd an entry -> a");
        System.out.println("See total calorie intake -> c");
        System.out.println("See calories remaining -> r");
        System.out.println("View food log for today -> f");
        System.out.println("Quit app -> q");
    }

    //MODIFIES: foodLog
    //EFFECTS: initializes and adds an entry to daily food log
    private void addEntry() {
        Entry entry = makeEntry();
        foodLog.addEntry(entry);
    }

    //EFFECTS: processes user input for food entry
    private Entry makeEntry() {
        String meal = chooseMeal();
        String name = nameOfFood();
        int calories = numberOfCal();

        Entry entry = new Entry(meal,name,calories);

        if (addMacros()) {
            int protein = setProtein();
            int fat = setFat();
            int carbs = setCarbs();
            entry.setMacros(protein,fat,carbs);
        }

        return entry;
    }

    //EFFECTS: processes user input for the entry's meal
    private String chooseMeal() {
        displayMeal();
        String meal = input.next();

        boolean keepGoing = true;
        while (keepGoing) {
            if (meal.equals("b")) {
                keepGoing = false;
                meal = "BREAKFAST";
            } else if (meal.equals("l")) {
                keepGoing = false;
                meal = "LUNCH";
            } else if (meal.equals("d")) {
                keepGoing = false;
                meal = "DINNER";
            } else if (meal.equals("s")) {
                keepGoing = false;
                meal = "SNACK";
            } else {
                System.out.println("Please enter a valid value");
                displayMeal();
            }
        }
        return meal;
    }

    //EFFECTS: displays options for what meal the entry can be
    private void displayMeal() {
        System.out.println("Please enter a meal:");
        System.out.println("Breakfast -> b");
        System.out.println("Lunch -> l");
        System.out.println("Dinner -> d");
        System.out.println("Snack -> s");
    }

    //EFFECTS: processes user input for the name of the entry
    private String nameOfFood() {
        System.out.println("Name of the food you ate:");
        return input.next();
    }

    //EFFECTS: processes user input for the number of calories
    //         in the entry
    private int numberOfCal() {
        System.out.println("How many calories was in it?");
        int calories = input.nextInt();
        return calories;
    }

    //EFFECTS: allows user to choose if they want to add
    //         the number of macros that's in the entry
    private boolean addMacros() {
        System.out.println("Did you want to add the number of macros?");
        System.out.println("y or n");
        String answer = input.next();

        boolean addMacros = false;

        boolean keepGoing = true;
        while (keepGoing) {
            if (answer.equals("y")) {
                keepGoing = false;
                addMacros = true;
            } else if (answer.equals("n")) {
                keepGoing = false;
                addMacros = false;
            } else {
                System.out.println("Please enter a valid input: y or n");
            }
        }
        return addMacros;
    }

    //EFFECTS: processes user input for amount of protein in entry
    private int setProtein() {
        System.out.println("Please enter the amount of protein you ate (g):");
        return input.nextInt();
    }

    //EFFECTS: processes user input for amount of fat in entry
    private int setFat() {
        System.out.println("Please enter the amount of fat you ate (g):");
        return input.nextInt();
    }

    //EFFECTS: processes user input for amount of carbs in entry
    private int setCarbs() {
        System.out.println("Please enter the amount of carbs you ate (g):");
        return input.nextInt();
    }

    //EFFECTS: processes user input from main display menu
    private void calCountOptions(String command) {
        if (command.equals("a")) {
            addEntry();
        } else if (command.equals("c")) {
            calorieIntake();
        } else if (command.equals("r")) {
            caloriesRemaining();
        } else if (command.equals("f")) {
            printFoodLog();
        }
    }

    //EFFECTS: displays number of calories user has eaten so far
    private void calorieIntake() {
        System.out.println("\nNumber of calories eaten today:");
        System.out.println(foodLog.totalIntake("Calories"));
    }

    //EFFECTS: displays number of calories user has left to eat today
    private void caloriesRemaining() {
        dailyIntake = new DailyIntake(user.getGoal());
        int caloriesRemaining = dailyIntake.totalCaloriesRemaining(foodLog);
        System.out.println("\nNumber of calories remaining today:");
        System.out.println(caloriesRemaining);
    }

    //EFFECTS: displays entire food log for the day
    private void printFoodLog() {
        System.out.println(foodLog.printFullReport());
    }




}
