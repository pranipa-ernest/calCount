package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalChoices extends ButtonMenu implements ActionListener {

    public GoalChoices(RunCalCount runCalCount) {
        super(runCalCount);
        firstBtnName = "Custom Goal";
        secondBtnName = "Recommended Goal";
        label = new JLabel("Would you like to create a custom goal or follow a recommended one?");
    }

    @Override
    protected void createBtns() {
        firstBtn = createBtn(firstBtnName);
        secondBtn = createBtn(secondBtnName);
    }

    @Override
    protected void setActionBtns() {
        setActionBtn(firstBtn,firstBtnName);
        setActionBtn(secondBtn,secondBtnName);
    }

    @Override
    protected void setActionBtn(JButton button, String actionCommand) {
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        container.removeAll();
//        container.repaint();
        if (e.getActionCommand().equals(firstBtnName)) {
            runCalCount.createCustomGoal();
        } else if (e.getActionCommand().equals(secondBtnName)) {
            runCalCount.createRecommendedGoal();
        }
    }

}
