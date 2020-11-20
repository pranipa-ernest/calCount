package ui;

import model.DailyFoodLog;
import model.User;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;


public class EntryForm extends Form {

    private String[] labels = {"Meal: ", "Food: ", "Calories: "};
    private JComboBox<String> meal;
    private JTextField food;
    private JTextField calories;

    private MainPage mainPage;

    private final String removeString = "Remove Entry";
    private JButton remove;

    public EntryForm(RunCalCount runCalCount, MainPage mainPage) {
        super(runCalCount);
        this.mainPage = mainPage;
        title = "Add an Entry";
    }

    @Override
    public void createForm() {
        super.createForm();
        removeEntryButton();
    }

    @Override
    protected void setUpFirstColumn(GridBagConstraints gc, JPanel jp) {
        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 0;
            gc.gridy = i;
            jp.add(makeLabel(labels[i]), gc);
        }
    }

    @Override
    protected void setUpSecondColumn(GridBagConstraints gc, JPanel jp) {
        gc.gridx = 1;
        makeMealComboBox(gc);

        food = makeTextField();
        gc.gridy = 1;
        jp.add(food,gc);

        calories = makeTextField();
        gc.gridy = 2;
        jp.add(calories,gc);
    }

    private void makeMealComboBox(GridBagConstraints gc) {
        String[] meals = {"BREAKFAST", "LUNCH", "DINNER", "SNACK"};
        meal = new JComboBox<>(meals);
        gc.gridy = 0;
        jp.add(meal,gc);
    }

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

    private void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(submitString)) {
            String mealInput = (String)meal.getSelectedItem();
            int calorieInput = Integer.parseInt(calories.getText());
            String foodInput = food.getText();

            mainPage.addEntry(mealInput,foodInput,calorieInput);
            mainPage.updateCaloriePanel();

            food.requestFocusInWindow();
            food.setText("");

            calories.requestFocusInWindow();
            calories.setText("");

            playSound("./data/soundEffect.wav");
        } else if (e.getActionCommand().equals(removeString)) {
            mainPage.removeEntry();
            mainPage.updateCaloriePanel();
        }
    }
}
