package ui;

import model.DailyFoodLog;
import model.Entry;
import model.User;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    private RunCalCount runCalCount;
    private Container container;
    private JPanel mainPanel;

    private JPanel sidebar;
//    protected Container container;

    private User user;
    private DailyFoodLog foodLog;
    private String date;


    private EntryForm entryForm;
    private CaloriePanel caloriePanel;
    private DailyFoodLogUI dailyFoodLogUI;
    private MainMenuButtons mainMenuButtons;

    public MainPage(RunCalCount runCalCount) {
        this.runCalCount = runCalCount;
        this.container = runCalCount.getContentPane();

        this.user = runCalCount.getUser();
        this.foodLog = runCalCount.getFoodLog();

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        caloriePanel = new CaloriePanel(user, foodLog);
        dailyFoodLogUI = new DailyFoodLogUI(foodLog);
        mainMenuButtons = new MainMenuButtons(this);
        entryForm = new EntryForm(runCalCount,this);
    }

    public void setUpMainPage() {
        addTargetPanel();
        addDailyFoodLog();
        setUpSideBar();

        container.add(getMainPanel());
        container.revalidate();
        container.repaint();
    }

    protected void addTargetPanel() {
        caloriePanel.setUpTargetPanel();
        JPanel caloriePanel = this.caloriePanel.getPanel();
        mainPanel.add(caloriePanel,BorderLayout.PAGE_START);
    }


    private void addDailyFoodLog() {
        dailyFoodLogUI.setUpFoodLogUI();
        JPanel dailyFoodLogPanel = dailyFoodLogUI.getDailyFoodLogPanel();
        mainPanel.add(dailyFoodLogPanel,BorderLayout.CENTER);
    }

    private void setUpSideBar() {
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.PAGE_AXIS));
        sidebar.add(addEntryForm());
        sidebar.add(addButtons());
        mainPanel.add(sidebar,BorderLayout.LINE_START);
    }

    private JPanel addButtons() {
        mainMenuButtons.setUpButtons();
        return mainMenuButtons.getButtonPanel();
    }

    private JPanel addEntryForm() {
        entryForm.createForm();
        return entryForm.getPanel();
    }

    public void addEntry(String meal, String food, int calories) {
        Entry entry = new Entry(meal, food, calories);
        foodLog.addEntry(entry);
        dailyFoodLogUI.addEntry(entry);
    }

    public void removeEntry() {
        int index = dailyFoodLogUI.removeEntry();
        foodLog.removeEntry(index);
    }

    public void updateCaloriePanel() {
        caloriePanel.updateAll();
    }


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



