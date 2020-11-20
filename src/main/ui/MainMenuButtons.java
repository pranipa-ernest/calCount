package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuButtons implements ActionListener {
    private MainPage mainPage;
    private JPanel jp;

    private JButton checkMacros;

    private final String checkMacrosString = "Check Macros";

    private final Dimension btnDimension;

    public MainMenuButtons(MainPage mainPage) {
        this.mainPage = mainPage;
        this.jp = new JPanel();

        this.btnDimension = new Dimension(50, 25);
    }

    public void setUpButtons() {
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));

        checkMacros = new JButton(checkMacrosString);
        checkMacros.setPreferredSize(btnDimension);
        checkMacros.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkMacros.setAlignmentY(Component.CENTER_ALIGNMENT);

        jp.add(checkMacros);

        checkMacros();
    }

    private void checkMacros() {
        checkMacros.setActionCommand(checkMacrosString);
        checkMacros.addActionListener(this);
    }

    public JPanel getButtonPanel() {
        return jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(checkMacrosString)) {
            JFrame frame = new JFrame("Macros");
            JOptionPane.showMessageDialog(frame,
                    "Protein: " + mainPage.getFoodLog().totalIntake("Protein")
                            + "\n" + "Fat: " + mainPage.getFoodLog().totalIntake("Fat")
                            + "\n" + "Carbs: " + mainPage.getFoodLog().totalIntake("Carbs"));
        }
    }
}
