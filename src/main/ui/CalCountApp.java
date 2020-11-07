package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.ArrayList;

public class CalCountApp extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private Dimension btnDimension = new Dimension(200,100);
    private Container container = this.getContentPane();

    public CalCountApp() {
        initCalCountApp();
    }

    private void initCalCountApp() {
        setTitle("CalCount");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();
    }

    private void initMenu() {
        JButton newUser = new JButton("New User");
        newUser.setPreferredSize(btnDimension);

        JButton existingUser = new JButton("Load Existing User");
        existingUser.setPreferredSize(btnDimension);

        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        container.add(jp);

        jp.add(newUser, new GridBagConstraints());
        jp.add(existingUser,new GridBagConstraints());

        newUser.setActionCommand("newUser");
        newUser.addActionListener(this);

        existingUser.setActionCommand("existingUser");
        existingUser.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        container.removeAll();
        container.repaint();
        if (e.getActionCommand().equals("newUser")) {
            createNewUser();
        } else if (e.getActionCommand().equals("existingUser")) {
            loadExistingUser();
        }
    }

    private JLabel makeLabel(String label) {
        JLabel newLbl = new JLabel(label);
        return newLbl;
    }

    private JTextField makeTextField(int columns) {
        JTextField txtField = new JTextField(columns);
        return txtField;
    }

    private JComboBox makeActivityLevelOptions() {
        String[] activityLvls = { "Sedentary", "Lightly Active", "Moderately Active",
                "Extremely Active"};

        JComboBox activityLevels = new JComboBox(activityLvls);
        return activityLevels;
    }

    private JComboBox makeGoalOptions() {
        String[] goal = { "Lose", "Maintain", "Gain"};

        JComboBox goalOptions = new JComboBox(goal);
        return goalOptions;
    }

    private Component makeSexOptions() {

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setMnemonic(KeyEvent.VK_M);
        maleButton.setActionCommand("Male");
        maleButton.setSelected(true);

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setMnemonic(KeyEvent.VK_F);
        femaleButton.setActionCommand("Female");

        maleButton.setHorizontalAlignment(SwingConstants.CENTER);
        femaleButton.setHorizontalAlignment(SwingConstants.CENTER);

        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);

        JPanel panel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(panelLayout);
        panel.add(maleButton);
        panel.add(femaleButton);

        return panel;
    }

    private Border createNewUserBorder() {
        Border border = new CompoundBorder(
                new TitledBorder(null, "User Information", TitledBorder.CENTER,
                        TitledBorder.TOP, null, null),
                new EmptyBorder(10, 10, 10, 10));
        return border;
    }

    private void createNewUserFirstCol(GridBagConstraints gc, JPanel jp, String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 0;
            gc.gridy = i;
            jp.add(makeLabel(labels[i]),gc);
        }
    }

    private void createNewUserSecondCol(GridBagConstraints gc, JPanel jp, String[] labels) {
        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 1;
            gc.gridy = i;

            if (i == 1) {
                jp.add(makeSexOptions(), gc);
            } else if (i == 4) {
                jp.add(makeActivityLevelOptions(), gc);
            } else if (i == 5) {
                jp.add(makeGoalOptions(), gc);
            } else {
                jp.add(makeTextField(7), gc);
            }
        }
    }

    private void createNewUserSubmitBtn(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        JButton submit = new JButton("Submit");
        jp.add(submit,gc);
    }

    private void createNewUser() {
        container.setLayout(new GridBagLayout());

        JPanel jp = new JPanel();
        jp.setBorder(createNewUserBorder());

        container.add(jp);
        jp.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.EAST;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.anchor = GridBagConstraints.WEST;
        String[] labels = {"Age: ", "Sex: ", "Weight (kg): ", "Height (cm): ",
                "Activity Level: ", "Goal: "};
        createNewUserFirstCol(gc,jp,labels);
        createNewUserSecondCol(gc,jp,labels);
        createNewUserSubmitBtn(gc,jp);

        revalidate();
        repaint();
    }

    private void loadExistingUser() {
        //
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CalCountApp calCount = new CalCountApp();
            calCount.setVisible(true);
        });
    }

}
