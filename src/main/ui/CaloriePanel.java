package ui;

import model.DailyFoodLog;
import model.DailyIntake;
import model.User;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel {

    private User user;
    private DailyIntake dailyIntake;
    private DailyFoodLog dailyFoodLog;

    private JLabel goal;
    private JLabel caloriesRemaining;
    private JLabel caloriesEaten;

    private JPanel jp;
    private final String[] labels = {"Goal: ", "Calories Remaining: ", "Calories Eaten: "};

    public CaloriePanel(User user, DailyFoodLog foodLog) {
        this.user = user;
        this.dailyFoodLog = foodLog;
        dailyIntake = new DailyIntake(this.user.getGoal());

        this.jp = new JPanel();
    }

    public void setUpTargetPanel() {
        jp.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            setUpLabels(labels[i],i,gc,jp);
        }

        firstColumn(gc);
        secondColumn(gc);
        thirdColumn(gc);
    }

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


    private void firstColumn(GridBagConstraints gc) {
        int targetCal = user.getGoal().getTargetCalories();
        goal = new JLabel(String.valueOf(targetCal));
        gc.gridy = 1;
        gc.gridx = 0;

        goal.setHorizontalAlignment(JLabel.CENTER);
        gc.weightx = 0.5;
        gc.ipady = 20;
        jp.add(goal,gc);
    }

    private void secondColumn(GridBagConstraints gc) {
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

    private void thirdColumn(GridBagConstraints gc) {
        int calories = dailyFoodLog.totalIntake("Calories");
        caloriesEaten = new JLabel(String.valueOf(calories));

        gc.gridy = 1;
        gc.gridx = 2;

        caloriesEaten.setHorizontalAlignment(JLabel.CENTER);
        gc.weightx = 0.5;
        gc.ipady = 20;
        jp.add(caloriesEaten,gc);
    }

    public void updateAll() {
        updateGoal();
        updateRemainingCal();
        updateCaloriesEaten();
    }

    private void updateGoal() {
        int targetCal = user.getGoal().getTargetCalories();
        goal.setText(String.valueOf(targetCal));
    }

    private void updateRemainingCal() {
        int calories = dailyIntake.totalCaloriesRemaining(dailyFoodLog);
        caloriesRemaining.setText(String.valueOf(calories));
    }

    private void updateCaloriesEaten() {
        int calories = dailyFoodLog.totalIntake("Calories");
        caloriesEaten.setText(String.valueOf(calories));
    }


    public JPanel getPanel() {
        return jp;
    }

}
