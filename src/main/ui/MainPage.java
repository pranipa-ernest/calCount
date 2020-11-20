package ui;

import model.DailyFoodLog;
import model.Entry;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents main page of CalCount application. This is where an initialized user can
add entries to their food log, check how many calories they have left to eat, how
many calories they have already eaten, and how many macros they have eaten.
 */
public class MainPage extends JFrame implements ActionListener {

    private Container container; //container of RunCalCount
    private JPanel mainPanel;    //main panel

    private JPanel sidebar;      //panel for sidebar

    private User user;            //initialized user
    private DailyFoodLog foodLog; //user's current food log

    private EntryForm entryForm;           //form to allow users to add entries
    private CaloriePanel caloriePanel;     //top panel that shows users target calories
                                           //eaten calories, and remaining calories
    private DailyFoodLogUI dailyFoodLogUI; //GUI of food log
    private JButton checkMacros;           //button that allows users to check macros
    private final String checkMacrosString = "Check Macros"; //label for checkMacros button

    /*
     * EFFECTS: constructs main page of CalCount app with given user,
     *          and user's food log. Sets up layout of mainPanel and
     *          initializes all main components
     */
    public MainPage(RunCalCount runCalCount) {
        this.container = runCalCount.getContentPane();

        this.user = runCalCount.getUser();
        this.foodLog = runCalCount.getFoodLog();

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        caloriePanel = new CaloriePanel(user, foodLog);
        dailyFoodLogUI = new DailyFoodLogUI(foodLog);
        checkMacros = new JButton(checkMacrosString);
        entryForm = new EntryForm(runCalCount,this);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up main page. Adds and positions all main components.
     *          Adds main panel to frame
     */
    public void setUpMainPage() {
        addTargetPanel();
        addDailyFoodLog();
        setUpSideBar();

        container.add(getMainPanel());
        container.revalidate();
        container.repaint();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up target panel. Positions and adds to main panel.
     */
    protected void addTargetPanel() {
        caloriePanel.setUpTargetPanel();
        JPanel caloriePanel = this.caloriePanel.getPanel();
        mainPanel.add(caloriePanel,BorderLayout.PAGE_START);
    }


    /*
     * MODIFIES: this
     * EFFECTS: sets up daily food log ui. Positions and adds to main panel.
     */
    private void addDailyFoodLog() {
        dailyFoodLogUI.setUpFoodLogUI();
        JPanel dailyFoodLogPanel = dailyFoodLogUI.getDailyFoodLogPanel();
        mainPanel.add(dailyFoodLogPanel,BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up sidebar's entry form and check macro button.
     *          Positions sidebar and adds to main panel.
     */
    private void setUpSideBar() {
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.PAGE_AXIS));
        sidebar.add(addEntryForm());
        sidebar.add(checkMacroButton());
        mainPanel.add(sidebar,BorderLayout.LINE_START);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates checkMacro button, sets actionCommand
     *          and actionListener for button.
     */
    private JButton checkMacroButton() {
        checkMacros.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkMacros.setAlignmentY(Component.CENTER_ALIGNMENT);

        checkMacros.setActionCommand(checkMacrosString);
        checkMacros.addActionListener(this);
        return checkMacros;
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates EntryForm and returns panel.
     */
    private JPanel addEntryForm() {
        entryForm.createForm();
        return entryForm.getPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds an Entry to foodLog and dailyFoodLogUI.
     */
    public void addEntry(Entry entry) {
        foodLog.addEntry(entry);
        dailyFoodLogUI.addEntry(entry);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes selected Entry from foodLog and dailyFoodLogUI.
     */
    public void removeEntry() {
        int index = dailyFoodLogUI.removeEntry();
        foodLog.removeEntry(index);
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates caloriePanel values upon either adding
     *          or removing an Entry from the food log.
     */
    public void updateCaloriePanel() {
        caloriePanel.updateAll();
    }

    /*
     * EFFECTS: upon click checkMacros button, shows a dialog with the total
     *          amount of macros that the user has entered in their food log
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(checkMacrosString)) {
            JFrame frame = new JFrame("Macros");
            JOptionPane.showMessageDialog(frame,
                    "Protein: " + foodLog.totalIntake("Protein")
                            + "\n" + "Fat: " + foodLog.totalIntake("Fat")
                            + "\n" + "Carbs: " + foodLog.totalIntake("Carbs"));
        }
    }

    //GETTERS AND SETTERS
    public DailyFoodLogUI getDailyFoodLogUI() {
        return dailyFoodLogUI;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public User getUser() {
        return user;
    }

    public DailyFoodLog getFoodLog() {
        return foodLog;
    }

}



