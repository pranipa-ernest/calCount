package ui;

import model.DailyFoodLog;
import model.DailyIntake;

import javax.swing.*;
import java.awt.*;

public class MacrosPanel extends TargetPanel {

    public MacrosPanel(RunCalCount runCalCount, String[] labels) {
        super(runCalCount, labels);
    }



    @Override
    protected JLabel firstColumn() {
        int protein = dailyFoodLog.totalIntake("Protein");
        JLabel label = new JLabel(String.valueOf(protein));
        return label;
    }

    @Override
    protected JLabel secondColumn() {
        int fat = dailyFoodLog.totalIntake("Fat");
        JLabel label = new JLabel(String.valueOf(fat));
        return label;
    }

    @Override
    protected JLabel thirdColumn() {
        int carbs = dailyFoodLog.totalIntake("Carbs");
        JLabel label = new JLabel(String.valueOf(carbs));
        return label;
    }

}
