package ui;

import model.DailyFoodLog;
import model.DailyIntake;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel extends TargetPanel {

    public CaloriePanel(RunCalCount runCalCount, String[] labels) {
        super(runCalCount, labels);
    }

    @Override
    protected JLabel firstColumn() {
        int targetCal = user.getGoal().getTargetCalories();
        JLabel label = new JLabel(String.valueOf(targetCal));
        return label;
    }

    @Override
    protected JLabel secondColumn() {
        int caloriesRemaining = dailyIntake.totalCaloriesRemaining(dailyFoodLog);
        JLabel label = new JLabel(String.valueOf(caloriesRemaining));
        label.setFont(new Font(label.getFont().getName(),Font.PLAIN,16));
        return label;
    }

    @Override
    protected JLabel thirdColumn() {
        int caloriesEaten = dailyFoodLog.totalIntake("Calories");
        JLabel label = new JLabel(String.valueOf(caloriesEaten));
        return label;
    }


}
