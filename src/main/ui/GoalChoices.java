package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents panel with two buttons that allows users to
either set a custom goal or follow a recommended one
 */
public class GoalChoices extends ButtonMenu implements ActionListener {

    /*
     * EFFECTS: constructs button menu with given labels for the
     *          first button and second button and given title for panel
     */
    public GoalChoices(RunCalCount runCalCount) {
        super(runCalCount);
        firstBtnName = "Custom Goal";
        secondBtnName = "Recommended Goal";
        label = new JLabel("Would you like to create a custom goal or follow a recommended one?");
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates Custom Goal and Recommended Goal buttons
     */
    @Override
    protected void createBtns() {
        firstBtn = createBtn(firstBtnName);
        secondBtn = createBtn(secondBtnName);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up action commands and listeners for both buttons
     */
    @Override
    protected void setActionBtns() {
        setActionBtn(firstBtn,firstBtnName);
        setActionBtn(secondBtn,secondBtnName);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up action commands and listeners for given button
     *          according to given actionCommand
     */
    @Override
    protected void setActionBtn(JButton button, String actionCommand) {
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
    }

    /*
     * MODIFIES: user, runCalCount
     * EFFECTS: if Custom Goal button is pressed, directs user to custom goal
     *          page to set targets. if Recommended Goal button is pressed,
     *          sets up a recommended goal and directs user to main page
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(firstBtnName)) {
            runCalCount.createCustomGoal();
        } else if (e.getActionCommand().equals(secondBtnName)) {
            runCalCount.createRecommendedGoal();
        }
    }

}
