package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class NewUserForm extends Form {

    private JTextField age;
    private ButtonGroup sex;
    private JTextField weight;
    private JTextField height;
    private JComboBox<String> activityLevel;
    private JComboBox<String> goal;

    String[] labels = {"Age: ", "Sex: ", "Weight (kg): ", "Height (cm): ",
            "Activity Level: ", "Goal: "};

    public NewUserForm(RunCalCount runCalCount) {
        super(runCalCount);
        title = "Create a New User";
    }

    @Override
    protected void setUpFirstColumn(GridBagConstraints gc, JPanel jp) {
        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 0;
            gc.gridy = i;
            jp.add(makeLabel(labels[i]), gc);
        }
    }

    @Override
    protected void setUpSecondColumn(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 1;
        for (int i = 0; i < labels.length; i++) {
            secondColumnFields(gc, jp, i, labels[i]);
        }
    }

    private JComboBox<String> makeActivityLevelOptions() {
        String[] activityLvls = {"Sedentary", "Lightly Active", "Moderately Active",
                "Extremely Active"};

        return new JComboBox<>(activityLvls);
    }

    private JComboBox<String> makeGoalOptions() {
        String[] goal = {"Lose", "Maintain", "Gain"};
        return new JComboBox<>(goal);
    }

    private Component makeSexOptions(ButtonGroup btnGroup) {
        ArrayList<AbstractButton> group = Collections.list(btnGroup.getElements());

        JPanel panel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(panelLayout);
        panel.add(group.get(0));
        panel.add(group.get(1));

        return panel;
    }

    private ButtonGroup makeSexRadioButtonGroup() {
        JRadioButton maleButton = makeSexButton("Male");
        maleButton.setActionCommand("M");
        JRadioButton femaleButton = makeSexButton("Female");
        femaleButton.setActionCommand("F");
        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);

        return group;
    }

    private JRadioButton makeSexButton(String name) {
        JRadioButton radioBtn = new JRadioButton(name);
        radioBtn.setActionCommand(name);
        radioBtn.setHorizontalAlignment(SwingConstants.CENTER);
        return radioBtn;
    }

    private void secondColumnFields(GridBagConstraints gc, JPanel jp, int gridY, String var) {
        gc.gridy = gridY;
        switch (var) {
            case "Age: ":
                makeSecondColAge(gc, jp);
                break;
            case "Sex: ":
                makeSecondColSex(gc, jp);
                break;
            case "Weight (kg): ":
                makeSecondColWeight(gc, jp);
                break;
            case "Height (cm): ":
                makeSecondColHeight(gc, jp);
                break;
            case "Activity Level: ":
                makeSecondColActivityLevel(gc, jp);
                break;
            case "Goal: ":
                goal = makeGoalOptions();
                jp.add(goal, gc);
                break;
        }
    }

    private void makeSecondColActivityLevel(GridBagConstraints gc, JPanel jp) {
        activityLevel = makeActivityLevelOptions();
        jp.add(activityLevel, gc);
    }

    private void makeSecondColHeight(GridBagConstraints gc, JPanel jp) {
        height = makeTextField();
        jp.add(height, gc);
    }

    private void makeSecondColWeight(GridBagConstraints gc, JPanel jp) {
        weight = makeTextField();
        jp.add(weight, gc);
    }

    private void makeSecondColSex(GridBagConstraints gc, JPanel jp) {
        sex = makeSexRadioButtonGroup();
        jp.add(makeSexOptions(sex), gc);
    }

    private void makeSecondColAge(GridBagConstraints gc, JPanel jp) {
        age = makeTextField();
        jp.add(age, gc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            int userAge = Integer.parseInt(age.getText());
            int userWeight = Integer.parseInt(weight.getText());
            int userHeight = Integer.parseInt(height.getText());
            String userSex = sex.getSelection().getActionCommand();
            String userActivityLevel = (String)activityLevel.getSelectedItem();
            String userGoal = (String)goal.getSelectedItem();
            runCalCount.setUser(userAge,userSex,userWeight,userHeight,userGoal,userActivityLevel);
//            container.removeAll();
//            container.repaint();
//            runCalCount.setUpNewGoal();
        }
    }
}