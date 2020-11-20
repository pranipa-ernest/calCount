package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

/*
Represents a form that allows users to input values
 */
public abstract class Form implements Serializable, ActionListener {
    protected RunCalCount runCalCount; //CalCount application
    protected JPanel panel;    //main panel
    protected JPanel jp;       //overlaying panel
    protected String title;    //title of form
    protected JButton submit;  //submit button

    protected final String submitString = "Submit";  //label of submit button
    protected GridBagConstraints gc;                 //constraints for overlaying panel

    /*
     * EFFECTS: constructs form for CalCount.
     */
    public Form(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        this.panel = new JPanel();
        this.jp = new JPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up panel layout, sets up form, positions form
     *          elements in the panel
     */
    public void createForm() {
        setUpPanel();
        setUpForm();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up up overlaying panel layout
     */
    protected void setUpPanel() {
        jp.setBorder(makeFormBorder());
        jp.setLayout(new GridBagLayout());
        setUpContentPane();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up up main panel layout.
     *          adds overlaying panel to main panel
     */
    protected void setUpContentPane() {
        panel.setLayout(new GridBagLayout());
        panel.add(jp);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions form elements
     */
    protected void setUpForm() {
        gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.EAST;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        setUpFirstColumn(gc, jp);

        gc.anchor = GridBagConstraints.WEST;
        setUpSecondColumn(gc, jp);
        submitButton(gc, jp);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions submit button
     */
    protected void submitButton(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        submit = new JButton(submitString);
        jp.add(submit, gc);
        submit.setActionCommand(submitString);
        submit.addActionListener(this);
    }

    /*
     * EFFECTS: makes label with given string
     */
    protected JLabel makeLabel(String label) {
        return new JLabel(label);
    }

    /*
     * EFFECTS: creates text field with 7 columns
     */
    protected JTextField makeTextField() {
        return new JTextField(7);
    }

    /*
     * EFFECTS: creates border for form
     */
    protected Border makeFormBorder() {
        return new CompoundBorder(
                new TitledBorder(null, title, TitledBorder.CENTER,
                        TitledBorder.TOP, null, null),
                new EmptyBorder(10, 10, 10, 10));
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up first column (labels) of form
     */
    protected abstract void setUpFirstColumn(GridBagConstraints gc, JPanel jp);

    /*
     * MODIFIES: this
     * EFFECTS: sets up second column (text fields/inputs) of form
     */
    protected abstract void setUpSecondColumn(GridBagConstraints gc, JPanel jp);

    //GETTERS AND SETTERS
    public JPanel getPanel() {
        return panel;
    }
}
