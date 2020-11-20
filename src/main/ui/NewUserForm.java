package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

/*
Represents a form that allows the initialization of new user in CalCount
 */
public class NewUserForm extends Form {

    private JTextField age;                   //text field to enter user's age
    private ButtonGroup sex;                  //JRadioButton Group to enter user's sex
    private JTextField weight;                //text field to enter user's weight
    private JTextField height;                //text field to enter user's height
    private JComboBox<String> activityLevel;  //JComboBox to allow user to select an activity level
    private JComboBox<String> goal;           //JComboBox to allow user to select a goal

    String[] labels = {"Age: ", "Sex: ", "Weight (kg): ", "Height (cm): ", //labels for the form
            "Activity Level: ", "Goal: "};

    /*
     * EFFECTS: constructs a form with given title
     */
    public NewUserForm(RunCalCount runCalCount) {
        super(runCalCount);
        title = "Create a New User";
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up first column of form with labels
     */
    @Override
    protected void setUpFirstColumn(GridBagConstraints gc, JPanel jp) {
        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 0;
            gc.gridy = i;
            jp.add(makeLabel(labels[i]), gc);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up second column of form with inputs
     */
    @Override
    protected void setUpSecondColumn(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 1;
        for (int i = 0; i < labels.length; i++) {
            secondColumnFields(gc, jp, i, labels[i]);
        }
    }

    /*
     * EFFECTS: returns a JComboBox with given options for activity levels
     */
    private JComboBox<String> makeActivityLevelOptions() {
        String[] activityLvls = {"Sedentary", "Lightly Active", "Moderately Active",
                "Extremely Active"};

        return new JComboBox<>(activityLvls);
    }

    /*
     * EFFECTS: returns a JComboBox with given options for goal
     */
    private JComboBox<String> makeGoalOptions() {
        String[] goal = {"Lose", "Maintain", "Gain"};
        return new JComboBox<>(goal);
    }

    /*
     * EFFECTS: positions JRadioButton group  in panel; returns panel
     */
    private Component makeSexOptions(ButtonGroup btnGroup) {
        ArrayList<AbstractButton> group = Collections.list(btnGroup.getElements());

        JPanel panel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(panelLayout);
        panel.add(group.get(0));
        panel.add(group.get(1));

        return panel;
    }

    /*
     * EFFECTS: makes JRadioButtons for both sex options;
     *          adds both to a ButtonGroup
     */
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

    /*
     * EFFECTS: returns JRadioButton for given sex. Sets actionCommand for button
     */
    private JRadioButton makeSexButton(String name) {
        JRadioButton radioBtn = new JRadioButton(name);
        radioBtn.setActionCommand(name);
        radioBtn.setHorizontalAlignment(SwingConstants.CENTER);
        return radioBtn;
    }

    /*
     * MODIFIES: this
     * EFFECTS: makes and positions second column fields in main panel
     *          according to what field it is
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: makes activityLevel field and adds to main panel
     */
    private void makeSecondColActivityLevel(GridBagConstraints gc, JPanel jp) {
        activityLevel = makeActivityLevelOptions();
        jp.add(activityLevel, gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: makes height field and adds to main panel
     */
    private void makeSecondColHeight(GridBagConstraints gc, JPanel jp) {
        height = makeTextField();
        jp.add(height, gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: makes weight field and adds to main panel
     */
    private void makeSecondColWeight(GridBagConstraints gc, JPanel jp) {
        weight = makeTextField();
        jp.add(weight, gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: makes sex field and adds to main panel
     */
    private void makeSecondColSex(GridBagConstraints gc, JPanel jp) {
        sex = makeSexRadioButtonGroup();
        jp.add(makeSexOptions(sex), gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: makes age field and adds to main panel
     */
    private void makeSecondColAge(GridBagConstraints gc, JPanel jp) {
        age = makeTextField();
        jp.add(age, gc);
    }

    /*
     * EFFECTS: creates a new user user according to input from form
     */
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
        }
    }
}