package ui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    private final String[] caloriePanelNames = {"Goal: ", "Calories Remaining: ", "Calories Eaten: "};
    private final String[] macroPanelNames = {"Protein: ", "Fat: ", "Carbs: "};

    private RunCalCount runCalCount;
    private User user;
    protected Container container;

    public MainPage(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        this.user = runCalCount.getUser();
        this.container = runCalCount.getContentPane();
    }

    public void showMainPage() {
        container.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        addTargetPanel(gc);
    }

    protected void addTargetPanel(GridBagConstraints gc) {

        JPanel targetPanel = setUpTopPanel();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridwidth = 3;
        gc.gridx = 0;
        gc.gridy = 0;

        container.add(targetPanel, gc);
    }

    private JPanel setUpTopPanel() {
        JPanel targetsPanel = new JPanel();
        targetsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JPanel caloriePanel = setUpTargetPanel(caloriePanelNames,"Calories");

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridwidth = 3;
        gc.gridx = 0;
        gc.gridy = 0;
        targetsPanel.add(caloriePanel, gc);

//        JPanel macroPanel = setUpTargetPanel(macroPanelNames, "Macros");
//        gc.fill = GridBagConstraints.HORIZONTAL;
//        gc.weightx = 1;
//        gc.weighty = 1;
//        gc.gridwidth = 3;
//        gc.gridx = 0;
//        gc.gridy = 1;
//        targetsPanel.add(macroPanel, gc);

        return targetsPanel;
    }

    private JPanel setUpTargetPanel(String[] labels, String target) {
        TargetPanel panel;
        if (target.equals("Calories")) {
            panel = new CaloriePanel(runCalCount,labels);
        } else {
            panel = new MacrosPanel(runCalCount,labels);
        }
        panel.setUpTargetPanel();
        JPanel jp = panel.getPanel();
        return jp;
    }
}



