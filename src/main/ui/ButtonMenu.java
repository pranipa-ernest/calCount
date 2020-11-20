package ui;

import ui.RunCalCount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/*
Represents a menu with two buttons, side-by-side.
 */

public abstract class ButtonMenu implements Serializable {

    protected RunCalCount runCalCount; //CalCount application
    protected JLabel label;            //Question related to buttons

    protected final Dimension btnDimension;  //dimension of buttons

    protected JPanel panel; //main panel
    protected JPanel jp;    //overlying panel

    protected JButton firstBtn;   //first button
    protected JButton secondBtn;  //second button

    protected String firstBtnName;  //name of first button
    protected String secondBtnName; //name of second button

    /*
     * REQUIRES: runCalCount to have been initialized
     * EFFECTS: constructs ButtonMenu
     */
    public ButtonMenu(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        btnDimension = new Dimension(200, 100);
        panel = new JPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: constructs both buttons
     */
    protected abstract void createBtns();

    /*
     * MODIFIES: this
     * EFFECTS: sets action listeners for both buttons
     */
    protected abstract void setActionBtns();

    /*
     * MODIFIES: this
     * EFFECTS: sets up panel layout; sets up buttons and action listeners;
     */
    public void initMenu() {
        createBtns();
        setActionBtns();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;

        setUpLabel(gc);
        setUpButtons(gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: positions both buttons in panel;
     */
    protected void setUpButtons(GridBagConstraints gc) {
        jp = new JPanel();
        jp.setLayout(new GridBagLayout());

        gc.gridy = 1;
        gc.ipady = 0;
        panel.add(jp, gc);
        addButtonsToJPanel(jp);
    }

    /*
     * MODIFIES: this
     * EFFECTS: positions label in panel;
     */
    protected void setUpLabel(GridBagConstraints gc) {
        gc.gridy = 0;
        gc.ipady = 30;
        panel.add(label, gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds both buttons to the panel;
     */
    private void addButtonsToJPanel(JPanel jp) {
        jp.add(firstBtn, new GridBagConstraints());
        jp.add(secondBtn, new GridBagConstraints());
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up action listener for button;
     */
    protected abstract void setActionBtn(JButton button, String actionCommand);

    /*
     * EFFECTS: creates a button with the specified label and dimension;
     */
    protected JButton createBtn(String s) {
        JButton button = new JButton(s);
        button.setPreferredSize(btnDimension);
        return button;
    }

    //GETTERS AND SETTERS
    public JPanel getPanel() {
        return panel;
    }

}
