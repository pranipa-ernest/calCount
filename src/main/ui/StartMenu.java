package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends ButtonMenu implements ActionListener {

    public StartMenu(RunCalCount runCalCount) {
        super(runCalCount);
        firstBtnName = "New User";
        secondBtnName = "Existing User";
        label = new JLabel("Are you a new user or an existing user?");
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
            runCalCount.createNewUser();
        } else if (e.getActionCommand().equals(secondBtnName)) {
            runCalCount.loadExistingUser();
        }
    }


}