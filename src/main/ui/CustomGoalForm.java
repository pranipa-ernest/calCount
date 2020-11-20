package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomGoalForm extends Form {

    private User user;

    private String[] labels = {"Target Calories: ", "Target Protein (g): ", "Target Fat (g): ",
            "Target Carbohydrates (g): "};
    private JTextField[] textFields = new JTextField[labels.length];

    public CustomGoalForm(RunCalCount runCalCount) {
        super(runCalCount);
        title = "Create a Custom Goal";
        this.user = runCalCount.getUser();
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
            gc.gridy = i;
            textFields[i] = makeTextField();
            jp.add(textFields[i],gc);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            int calories = Integer.parseInt(textFields[0].getText());
            int protein = Integer.parseInt(textFields[1].getText());
            int fat = Integer.parseInt(textFields[2].getText());
            int carbs = Integer.parseInt(textFields[3].getText());
            user.setCustomGoal(calories,protein,fat,carbs);
//            container.removeAll();
//            container.repaint();
            runCalCount.loadNewMainPage();
        }
    }
}
