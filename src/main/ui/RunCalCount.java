package ui;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

public class RunCalCount extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;

    private static final String JSON_STORE = "./data/calCount.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private User user;

    public RunCalCount() {
        initJFrame();
    }

    private void initJFrame() {
        setTitle("CalCount");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        runStartMenu();
    }

    private void runStartMenu() {
        StartMenu startMenu = new StartMenu(this);
        startMenu.initMenu();
    }

    public void createNewUser() {
        NewUserForm newUser = new NewUserForm(this);
        newUser.createForm();

        this.revalidate();
        this.repaint();
    }

    public void setUser(int age, String sex, int weight, int height,
                        String goalWeight, String activityLevel) {
        this.user = new User(age,sex,weight,height,goalWeight);
        user.setActivityLevel(activityLevel);

    }

    public void setUpNewGoal() {
        GoalChoices goalChoices = new GoalChoices(this);
        goalChoices.initMenu();

        this.revalidate();
        this.repaint();
    }

    public void loadExistingUser() {
        //
    }

    public void createCustomGoal() {
        CustomGoalForm customGoal = new CustomGoalForm(this);
        customGoal.createForm();
        this.revalidate();
        this.repaint();
    }

    public void showMainPage() {
        MainPage mainPage = new MainPage(this);
        mainPage.showMainPage();
        this.revalidate();
        this.repaint();
    }

    public void createRecommendedGoal() {
        user.setRecommendedGoal();
        showMainPage();
    }

    public User getUser() {
        return user;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RunCalCount calCount = new RunCalCount();
            calCount.setVisible(true);
        });
    }

}
