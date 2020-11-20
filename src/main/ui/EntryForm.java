package ui;

import model.Entry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

/*
Represents a form that allows user to add an Entry to their food log
 */

public class EntryForm extends Form {

    private String[] labels = {"Meal: ", "Food: ", "Calories: ",
            "Protein: ", "Fat: ", "Carbs: "}; //labels for form
    private JComboBox<String> meal; //meal input combo-box

    private ArrayList<JTextField> fields = new ArrayList<>(); //list of all text fields
    private JTextField food;        //food input text field
    private JTextField calories;    //calories input text field

    private JTextField protein; //protein input text field
    private JTextField fat;     //fat input text field
    private JTextField carbs;   //carbs input text field

    private MainPage mainPage;  //main page of CalCount

    private final String removeString = "Remove Entry";  //label for remove entry button
    private JButton remove; //remove entry button

    /*
     * EFFECTS: constructs and displays a form with the given title;
     */
    public EntryForm(RunCalCount runCalCount, MainPage mainPage) {
        super(runCalCount);
        this.mainPage = mainPage;
        title = "Add an Entry";
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up form according to super class;
     *          adds additional remove entry button
     */
    @Override
    public void createForm() {
        super.createForm();
        removeEntryButton();
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions labels for form;
     */
    @Override
    protected void setUpFirstColumn(GridBagConstraints gc, JPanel jp) {
        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 0;
            gc.gridy = i;
            jp.add(makeLabel(labels[i]), gc);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions text fields for form;
     */
    @Override
    protected void setUpSecondColumn(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 1;
        makeMealComboBox(gc);

        makeAllTextFields();
        setUpFieldsList();

        for (int i = 0; i < fields.size(); i++) {
            addTextFieldToPanel(gc, jp, i + 1, fields.get(i));
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds all text fields to list of fields;
     */
    private void setUpFieldsList() {
        fields.add(food);
        fields.add(calories);
        fields.add(protein);
        fields.add(fat);
        fields.add(carbs);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates all text fields in form;
     */
    private void makeAllTextFields() {
        food = makeTextField();
        calories = makeTextField();
        protein = makeTextField();
        fat = makeTextField();
        carbs = makeTextField();
    }

    /*
     * MODIFIES: this
     * EFFECTS: positions text field in panel;
     */
    private void addTextFieldToPanel(GridBagConstraints gc, JPanel jp, int i, JTextField food) {
        gc.gridy = i;
        jp.add(food, gc);
    }


    /*
     * MODIFIES: this
     * EFFECTS: creates combo-box for meal field;
     *          positions in panel
     */
    private void makeMealComboBox(GridBagConstraints gc) {
        String[] meals = {"BREAKFAST", "LUNCH", "DINNER", "SNACK"};
        meal = new JComboBox<>(meals);
        gc.gridy = 0;
        jp.add(meal,gc);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and positions remove entry button in form;
     */
    private void removeEntryButton() {
        gc.gridx = 0;
        gc.gridy = 8;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;

        remove = new JButton(removeString);
        remove.setActionCommand(removeString);
        remove.addActionListener(this);
        jp.add(remove,gc);

    }

    /*
     * EFFECTS: plays audio once submit button is pressed;
     */
    private void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File("./data/soundEffect.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /*
     * REQUIRES: user to have entered values for calories and food fields
     * MODIFIES: this, mainPage
     * EFFECTS: creates Entry according to user input, adds Entry to
     *          food log and updates calorie panel
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(submitString)) {
            String mealInput = (String)meal.getSelectedItem();
            int calorieInput = Integer.parseInt(calories.getText());
            String foodInput = food.getText();

            int proteinInput = getMacroInput(protein);
            int fatInput = getMacroInput(fat);
            int carbsInput = getMacroInput(carbs);

            Entry entry = new Entry(mealInput,foodInput,calorieInput);
            entry.setMacros(proteinInput,fatInput,carbsInput);

            mainPage.addEntry(entry);
            mainPage.updateCaloriePanel();

            for (JTextField f : fields) {
                resetTextFields(f);
            }

            playSound();
        } else if (e.getActionCommand().equals(removeString)) {
            mainPage.removeEntry();
            mainPage.updateCaloriePanel();
        }
    }

    /*
     * EFFECTS: returns input from macro input fields; if no input
     *          returns 0;
     */
    protected int getMacroInput(JTextField macro) {
        if (!macro.getText().equals("")) {
            return Integer.parseInt(macro.getText());
        } else {
            return 0;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: resets all text fields upon pressing of submit;
     */
    private void resetTextFields(JTextField f) {
        f.requestFocusInWindow();
        f.setText("");
    }
}
