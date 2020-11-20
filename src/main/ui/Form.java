package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public abstract class Form implements Serializable, ActionListener {
    protected RunCalCount runCalCount;
//    protected Container container;
    protected JPanel panel;
    protected JPanel jp;
    protected String title;
    protected JButton submit;

    protected final String submitString = "Submit";
    protected GridBagConstraints gc;

    public Form(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        this.panel = new JPanel();
        this.jp = new JPanel();
    }

    public void createForm() {
        setUpPanel();
        setUpForm();
    }

    protected void setUpPanel() {
        jp.setBorder(makeFormBorder());
        jp.setLayout(new GridBagLayout());
        setUpContentPane();
    }

    protected void setUpContentPane() {
        panel.setLayout(new GridBagLayout());
        panel.add(jp);
    }

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


    protected JLabel makeLabel(String label) {
        return new JLabel(label);
    }

    protected JTextField makeTextField() {
        return new JTextField(7);
    }

    protected Border makeFormBorder() {
        return new CompoundBorder(
                new TitledBorder(null, title, TitledBorder.CENTER,
                        TitledBorder.TOP, null, null),
                new EmptyBorder(10, 10, 10, 10));
    }

    protected abstract void setUpFirstColumn(GridBagConstraints gc, JPanel jp);

    protected abstract void setUpSecondColumn(GridBagConstraints gc, JPanel jp);

    public JPanel getPanel() {
        return panel;
    }
}
