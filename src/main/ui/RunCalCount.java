package ui;

import model.DailyFoodLog;
import model.Entry;
import model.TotalFoodLog;
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

/*
Represents CalCount application
 */
public class RunCalCount extends JFrame {

    //Width and height of window
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;

    //File path for existing user information
    private static final String JSON_STORE = "./data/calCount.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //Container of JFrame
    private Container container;

    private User user;             //user of CalCount
    private DailyFoodLog foodLog;  //user's food log

    private MainPage mainPage;     //main page of application

    /*
     * EFFECTS: constructs frame for CalCount and runs the application
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: upon closing window, gives user the option to save their information
     */
    private void addWindowEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                        "Would you like to save your changes?");

                if (a == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else if (a == JOptionPane.YES_OPTION) {
                    saveApp();
                    System.exit(0);
                }
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: saves application
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: removes current contents from container
     */
    private void loadNewPage() {
        container.removeAll();
        container.repaint();
    }

    /*
     * MODIFIES: this
     * EFFECTS: revalidates content currently in the frame
     */
    private void revalidatePage() {
        this.revalidate();
        this.repaint();
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the start menu that user is initially presented with
     *          when they first open the application
     */
    private void runStartMenu() {
        StartMenu startMenu = new StartMenu(this);
        startMenu.initMenu();
        container.add(startMenu.getPanel());
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the NewUserForm and creates a new user
     *          with the given information
     */
    public void createNewUser() {
        loadNewPage();

        NewUserForm newUser = new NewUserForm(this);
        newUser.createForm();
        container.add(newUser.getPanel());

        revalidatePage();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up a new user with the given information;
     *          initializes user's food log
     */
    public void setUser(int age, String sex, int weight, int height,
                        String goalWeight, String activityLevel) {

        this.user = new User(age,sex,weight,height,goalWeight);
        user.setActivityLevel(activityLevel);
        setFoodLog();
        setUpNewGoal();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up user's food log. If a food log with the current
     *          date already exists, uses that object. If no such log
     *          exists yet, creates a new food log
     */
    public void setFoodLog() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        foodLog = user.findDailyFoodLog(date);

        if (foodLog == null) {
            foodLog = new DailyFoodLog();
            user.addDailyFoodLog(foodLog);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the GoalChoices frame; this allows users to either
     *          set up a custom goal or follow a recommended one
     */
    public void setUpNewGoal() {
        loadNewPage();

        GoalChoices goalChoices = new GoalChoices(this);
        goalChoices.initMenu();
        container.add(goalChoices.getPanel());

        revalidatePage();
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads an existing user of CalCount
     */
    public void loadExistingUser() {
        try {
            user = jsonReader.read();
            setFoodLog();
            loadNewMainPage();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("User may not exist");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the CustomGoalForm; allows user to set
     *          their own target calories, protein, fat, and carbs
     */
    public void createCustomGoal() {
        loadNewPage();

        CustomGoalForm customGoal = new CustomGoalForm(this);
        customGoal.createForm();
        container.add(customGoal.getPanel());

        revalidatePage();
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the MainPage
     */
    public void loadNewMainPage() {
        loadNewPage();
        mainPage = new MainPage(this);
        mainPage.setUpMainPage();
    }


    /*
     * MODIFIES: this
     * EFFECTS: creates a recommended goal for the user and then
     *          shows then the main page
     */
    public void createRecommendedGoal() {
        user.setRecommendedGoal();
        loadNewMainPage();
    }

    /*
     * EFFECTS: runs the CalCount application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            RunCalCount calCount = new RunCalCount();
            calCount.setVisible(true);
        });
    }

    //GETTERS AND SETTERS
    public User getUser() {
        return user;
    }

    public DailyFoodLog getFoodLog() {
        return foodLog;
    }


}
