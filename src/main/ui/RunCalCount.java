package ui;

import model.DailyFoodLog;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class RunCalCount extends JFrame {


    private static final int WIDTH = 500;
    private static final int HEIGHT = WIDTH;

    private static final String JSON_STORE = "./data/calCount.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Container container;

    private User user;
    private DailyFoodLog foodLog;

    private MainPage mainPage;

    public RunCalCount() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("CalCount");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.container = getContentPane();

        addWindowEvent();
        runStartMenu();
    }

    protected void addWindowEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                        "Would you like to save your changes?");

                if (a == JOptionPane.NO_OPTION) {
                    setVisible(false);
                    dispose();
                } else if (a == JOptionPane.YES_OPTION) {
                    user.addDailyFoodLog(foodLog);
                    saveApp();
                    setVisible(false);
                    dispose();
                }
            }
        });
    }

    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved application");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadNewPage() {
        container.removeAll();
        container.repaint();
    }

    private void revalidatePage() {
        this.revalidate();
        this.repaint();
    }

    private void runStartMenu() {
        StartMenu startMenu = new StartMenu(this);
        startMenu.initMenu();
        container.add(startMenu.getPanel());
    }

    public void createNewUser() {
        loadNewPage();

        NewUserForm newUser = new NewUserForm(this);
        newUser.createForm();
        container.add(newUser.getPanel());

        revalidatePage();
    }

    public void setUser(int age, String sex, int weight, int height,
                        String goalWeight, String activityLevel) {
        this.user = new User(age,sex,weight,height,goalWeight);
        user.setActivityLevel(activityLevel);
        setFoodLog();
        setUpNewGoal();
    }

    public void setFoodLog() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        foodLog = user.findDailyFoodLog(date);

        if (foodLog == null) {
            foodLog = new DailyFoodLog();
        }
    }

    public void setUpNewGoal() {
        loadNewPage();

        GoalChoices goalChoices = new GoalChoices(this);
        goalChoices.initMenu();
        container.add(goalChoices.getPanel());

        revalidatePage();
    }

    public void loadExistingUser() {
        try {
            user = jsonReader.read();

            LocalDate localDate = LocalDate.now();
            String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

            foodLog = user.findDailyFoodLog(date);

            if (foodLog == null) {
                foodLog = new DailyFoodLog();
            }

            loadNewMainPage();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("User may not exist");
        }
    }

    public void createCustomGoal() {
        loadNewPage();

        CustomGoalForm customGoal = new CustomGoalForm(this);
        customGoal.createForm();
        container.add(customGoal.getPanel());

        revalidatePage();
    }

    public void loadNewMainPage() {
        loadNewPage();
        mainPage = new MainPage(this);
        mainPage.setUpMainPage();
    }


    public void createRecommendedGoal() {
        user.setRecommendedGoal();
        loadNewMainPage();
    }


    public User getUser() {
        return user;
    }

    public DailyFoodLog getFoodLog() {
        return foodLog;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RunCalCount calCount = new RunCalCount();
            calCount.setVisible(true);
        });
    }

}
