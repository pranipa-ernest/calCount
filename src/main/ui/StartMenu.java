package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents the menu that the user is initially presented with
when they first open the application. The user can either create a
new profile or load an existing profile
 */
public class StartMenu extends ButtonMenu implements ActionListener {

    /*
     * EFFECTS: constructs a button menu with two buttons,
     *          New User and Existing User, and the given title
     */
    public StartMenu(RunCalCount runCalCount) {
        super(runCalCount);
        firstBtnName = "New User";
        secondBtnName = "Existing User";
        label = new JLabel("Are you a new user or an existing user?");
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates New User and Existing User buttons
     */
    @Override
    protected void createBtns() {
        firstBtn = createBtn(firstBtnName);
        secondBtn = createBtn(secondBtnName);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets actionListeners for New User and Existing User buttons
     */
    @Override
    protected void setActionBtns() {
        setActionBtn(firstBtn,firstBtnName);
        setActionBtn(secondBtn,secondBtnName);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets actionListeners for given button
     */
    @Override
    protected void setActionBtn(JButton button, String actionCommand) {
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
    }

    /*
     * EFFECTS: if the user clicked the New User button,
     *          this will allow them to create a new profile.
     *          If the user clicked the Existing User button,
     *          this will allow them to load an existing profile.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(firstBtnName)) {
            runCalCount.createNewUser();
        } else if (e.getActionCommand().equals(secondBtnName)) {
            runCalCount.loadExistingUser();
        }
    }


}