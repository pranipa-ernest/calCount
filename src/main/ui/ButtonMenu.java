package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public abstract class ButtonMenu implements Serializable {
    protected final RunCalCount runCalCount;
    protected JLabel label;

    protected Dimension btnDimension;
    protected Container container;
    protected JPanel jp;

    protected JButton firstBtn;
    protected JButton secondBtn;

    protected String firstBtnName;
    protected String secondBtnName;


    public ButtonMenu(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        btnDimension = new Dimension(200, 100);
        this.container = runCalCount.getContentPane();
    }

    protected abstract void createBtns();

    protected abstract void setActionBtns();

    public void initMenu() {
        createBtns();
        setActionBtns();

        container.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;

        setUpLabel(gc);
        setUpButtons(gc);
    }

    protected void setUpButtons(GridBagConstraints gc) {
        jp = new JPanel();
        jp.setLayout(new GridBagLayout());

        gc.gridy = 1;
        gc.ipady = 0;
        container.add(jp, gc);
        addButtonsToJPanel(jp);
    }

    protected void setUpLabel(GridBagConstraints gc) {
        gc.gridy = 0;
        gc.ipady = 30;
        container.add(label, gc);
    }

    private void addButtonsToJPanel(JPanel jp) {
        jp.add(firstBtn, new GridBagConstraints());
        jp.add(secondBtn, new GridBagConstraints());
    }

    protected abstract void setActionBtn(JButton button, String actionCommand);

    protected JButton createBtn(String s) {
        JButton button = new JButton(s);
        button.setPreferredSize(btnDimension);
        return button;
    }
}
