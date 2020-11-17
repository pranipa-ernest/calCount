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
    protected final RunCalCount runCalCount;
    protected Container container;
    protected JPanel jp;
    protected String title;

    public Form(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        this.container = runCalCount.getContentPane();
        this.jp = new JPanel();
    }

    protected void createForm() {
        setUpContainer();
        setUpForm();
    }

    protected void setUpContainer() {
        container.setLayout(new GridBagLayout());
        jp.setBorder(makeFormBorder());
        jp.setLayout(new GridBagLayout());
        container.add(jp);
    }

    protected void setUpForm() {
        GridBagConstraints gc = new GridBagConstraints();
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
        JButton submit = new JButton("Submit");
        jp.add(submit, gc);
        submit.setActionCommand("Submit");
        submit.addActionListener(this);
    }


    protected JLabel makeLabel(String label) {
        return new JLabel(label);
    }

    protected JTextField makeTextField() {
        return new JTextField(7);
    }

    protected Border makeFormBorder() {
        Border border = new CompoundBorder(
                new TitledBorder(null, title, TitledBorder.CENTER,
                        TitledBorder.TOP, null, null),
                new EmptyBorder(10, 10, 10, 10));
        return border;
    }

    protected abstract void setUpFirstColumn(GridBagConstraints gc, JPanel jp);

    protected abstract void setUpSecondColumn(GridBagConstraints gc, JPanel jp);

}
