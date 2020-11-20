package ui;

import model.DailyFoodLog;
import model.DailyIntake;
import model.User;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel {

    private User user;                 //user of CalCount
    private DailyIntake dailyIntake;   //daily intake of user
    private DailyFoodLog dailyFoodLog; //daily food log of user

    private JLabel goal;               //label for target calories
    private JLabel caloriesRemaining;  //label for calories remaining
    private JLabel caloriesEaten;      //label for calories eaten so far

    private JPanel jp; //main panel
    private final String[] labels = {"Goal: ", "Calories Remaining: ", "Calories Eaten: "}; //labels for panel

    /*
     * REQUIRES: user and foodLog to have already been initialized
     * EFFECTS: constructs a panel reflecting
     *          user's current food log and daily intake;
     */
    public CaloriePanel(User user, DailyFoodLog foodLog) {
        this.user = user;
        this.dailyFoodLog = foodLog;
        dailyIntake = new DailyIntake(this.user.getGoal());

        this.jp = new JPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up panel layout, adds labels and values
     *          to all columns;
     */
    public void setUpTargetPanel() {
        jp.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            setUpLabels(labels[i],i,gc,jp);
        }

        targetCalories(gc);
        remainingCalories(gc);
        caloriesEaten(gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions label in panel according to
     *          gridx coordinate;
     */
    private void setUpLabels(String labelName, int gridx, GridBagConstraints gc, JPanel jp) {
        JLabel label = new JLabel(labelName);

        if (labelName.equals(labels[1])) {
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN,16));
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        gc.ipady = 20;
        gc.weightx = 0.5;
        gc.gridy = 0;
        gc.gridx = gridx;
        jp.add(label,gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions value of target calorie;
     */
    private void targetCalories(GridBagConstraints gc) {
        int targetCal = user.getGoal().getTargetCalories();
        goal = new JLabel(String.valueOf(targetCal));
        gc.gridy = 1;
        gc.gridx = 0;

        goal.setHorizontalAlignment(JLabel.CENTER);
        gc.weightx = 0.5;
        gc.ipady = 20;
        jp.add(goal,gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions value of remaining calories;
     */
    private void remainingCalories(GridBagConstraints gc) {
        int calories = dailyIntake.totalCaloriesRemaining(dailyFoodLog);
        caloriesRemaining = new JLabel(String.valueOf(calories));
        caloriesRemaining.setFont(new Font(caloriesRemaining.getFont().getName(),Font.PLAIN,16));

        gc.gridy = 1;
        gc.gridx = 1;

        caloriesRemaining.setHorizontalAlignment(JLabel.CENTER);
        gc.weightx = 0.5;
        gc.ipady = 20;
        jp.add(caloriesRemaining,gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions value of calories eaten;
     */
    private void caloriesEaten(GridBagConstraints gc) {
        int calories = dailyFoodLog.totalIntake("Calories");
        caloriesEaten = new JLabel(String.valueOf(calories));

        gc.gridy = 1;
        gc.gridx = 2;

        caloriesEaten.setHorizontalAlignment(JLabel.CENTER);
        gc.weightx = 0.5;
        gc.ipady = 20;
        jp.add(caloriesEaten,gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates panel values upon adding an entry to the food log;
     */
    public void updateAll() {
        updateGoal();
        updateRemainingCal();
        updateCaloriesEaten();
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates goal value upon adding an entry to the food log;
     */
    private void updateGoal() {
        int targetCal = user.getGoal().getTargetCalories();
        goal.setText(String.valueOf(targetCal));
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates remaining calorie value upon adding an entry to the food log;
     */
    private void updateRemainingCal() {
        int calories = dailyIntake.totalCaloriesRemaining(dailyFoodLog);
        caloriesRemaining.setText(String.valueOf(calories));
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates number of calories eaten value upon adding an entry to the food log;
     */
    private void updateCaloriesEaten() {
        int calories = dailyFoodLog.totalIntake("Calories");
        caloriesEaten.setText(String.valueOf(calories));
    }


    //GETTERS AND SETTERS
    public JPanel getPanel() {
        return jp;
    }

}
