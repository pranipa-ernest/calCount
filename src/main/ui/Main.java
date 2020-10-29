package ui;

import model.DailyFoodLog;
import model.Entry;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new CalCount();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
