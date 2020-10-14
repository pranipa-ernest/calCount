package ui;

import model.*;

import java.util.Scanner;

public class CalCount {
    private User user;
    private DailyFoodLog foodLog;

    private Scanner input;

    // EFFECTS: runs the teller application
    public CalCount() {
        runCalCount();
    }

    private void runCalCount() {
        input = new Scanner(System.in);

        initUser();
        initGoal();
        displayGoal();

        foodLog = new DailyFoodLog();

        boolean keepGoing = true;
        String command = null;
        while (keepGoing) {
            displayMenu();
            command = input.next();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                calCountOptions(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

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

    private void calorieIntake() {
        System.out.println(foodLog.totalIntake("Calories"));
    }

    private void caloriesRemaining() {
        ///
    }

    private void printFoodLog() {
        System.out.println(foodLog.printFullReport());
    }

    private void initUser() {
        String sex = processSex();
        int age = processAge();
        int weight = processWeight();
        int height = processHeight();
        String goal = processGoal();

        user = new User(age,sex,weight,height,goal);
    }

    private String processSex() {
        String command = null;
        String sex = null;
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Please write your sex (M/F)");
            command = input.next();
            if (command.equals("M") | command.equals("F")) {
                sex = command;
                keepGoing = false;
            } else {
                System.out.println("Please type in a valid input (M or F)");
            }
        }
        return sex;
    }

    private int processAge() {
        System.out.println("Please write your age");
        int age = input.nextInt();
        return age;
    }

    private int processWeight() {
        System.out.println("Please write your weight (kg)");
        int weight = input.nextInt();
        return weight;
    }

    private int processHeight() {
        System.out.println("Please write your height (cm)");
        int height = input.nextInt();
        return height;
    }

    private String processGoal() {
        System.out.println("\nDo you want to:");
        System.out.println("Lose weight -> l");
        System.out.println("Maintain weight -> m");
        System.out.println("Gain weight -> g:");

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
                System.out.println("Please print a valid input: l or m or g");
            }
        }
        return goal;
    }

    private void initGoal() {
        System.out.println("\nWould you like to set your own custom calorie goal");
        System.out.println("or a custom calorie goal?");
        System.out.println("Custom -> c");
        System.out.println("Recommended -> r");

        String goal = input.next();

        boolean keepGoing = true;
        while (keepGoing) {
            if (goal.equals("c")) {
                keepGoing = false;
                setCustomGoal();
            } else if (goal.equals("r")) {
                keepGoing = false;
                setRecommendedGoal();
            } else {
                System.out.println("Please print a valid input: c or r");
            }
        }

    }

    private void setCustomGoal() {
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

    private void setRecommendedGoal() {
        setActivityLevel();
        user.setRecommendedGoal();
    }

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

    private void setActivityLevelDisplay() {
        System.out.println("\nPlease set your activity level:");
        System.out.println("Sedentary -> s");
        System.out.println("Lightly Active -> l");
        System.out.println("Moderately Active -> m");
        System.out.println("Extremely Active -> e");
    }

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

    private void displayMenu() {
        System.out.println("\nPlease choose from the following options:");
        System.out.println("\nAdd an entry -> a");
        System.out.println("See total calorie intake -> c");
        System.out.println("See calories remaining -> r");
        System.out.println("View food log for today -> f");
        System.out.println("Quit app -> q");
    }

    private void addEntry() {
        Entry entry = makeEntry();
        foodLog.addEntry(entry);
    }

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

    private void displayMeal() {
        System.out.println("Please enter a meal:");
        System.out.println("Breakfast -> b");
        System.out.println("Lunch -> l");
        System.out.println("Dinner -> d");
        System.out.println("Snack -> s");
    }

    private String nameOfFood() {
        System.out.println("Name of the food you ate:");
        return input.next();
    }

    private int numberOfCal() {
        System.out.println("How many calories was in it?");
        int calories = input.nextInt();
        return calories;
    }

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

    private int setProtein() {
        System.out.println("Please enter the amount of protein you ate (g):");
        return input.nextInt();
    }

    private int setFat() {
        System.out.println("Please enter the amount of fat you ate (g):");
        return input.nextInt();
    }

    private int setCarbs() {
        System.out.println("Please enter the amount of carbs you ate (g):");
        return input.nextInt();
    }






}
