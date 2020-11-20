package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
Represents a form that allows users to set a custom goal (target calories,
target protein, target fat, and target carbs)
 */
public class CustomGoalForm extends Form {

    private User user; //current user

    private String[] labels = {"Target Calories: ", "Target Protein (g): ", "Target Fat (g): ",  //labels for form
            "Target Carbohydrates (g): "};
    private JTextField[] textFields = new JTextField[labels.length]; //text fields for form

    /*
     * REQUIRES: user to have been loaded in runCalCount
     * EFFECTS: constructs a form with the given title for the given user;
     */
    public CustomGoalForm(RunCalCount runCalCount) {
        super(runCalCount);
        title = "Create a Custom Goal";
        this.user = runCalCount.getUser();
    }

    /*
     * MODIFIES: this
     * EFFECTS: makes and positions labels for first column of form;
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
     * EFFECTS: makes and positions text fields for second column of form;
     */
    @Override
    protected void setUpSecondColumn(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 1;
        for (int i = 0; i < labels.length; i++) {
            gc.gridy = i;
            textFields[i] = makeTextField();
            jp.add(textFields[i],gc);
        }
    }

    /*
     * REQUIRES: user to have entered values for all fields
     * MODIFIES: user, runCalCount
     * EFFECTS: sets target calories, protein, fat, and carb values for user;
     *          afterwards, loads main page of CalCount
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            int calories = Integer.parseInt(textFields[0].getText());
            int protein = Integer.parseInt(textFields[1].getText());
            int fat = Integer.parseInt(textFields[2].getText());
            int carbs = Integer.parseInt(textFields[3].getText());
            user.setCustomGoal(calories,protein,fat,carbs);
            runCalCount.loadNewMainPage();
        }
    }
}
