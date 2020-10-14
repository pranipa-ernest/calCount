package model;

import model.DailyFoodLog;
import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDailyFoodLog {

    private DailyFoodLog testFoodLog;
    private Entry testEntryBreakfast;
    private Entry testEntryLunch;
    private Entry testEntryDinner;
    private Entry testEntrySnack;

    @BeforeEach
    public void setUp() {
        testFoodLog = new DailyFoodLog();

        testEntryBreakfast = new Entry("BREAKFAST", "Pancakes", 350);
        testEntryLunch = new Entry("LUNCH", "Salad", 760);
        testEntryDinner = new Entry("DINNER", "Pasta", 860);
        testEntrySnack = new Entry("SNACK", "Chips",560);
    }

    @Test
    public void testConstructor() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        assertEquals(0, testFoodLog.getSize());
        assertEquals(date, testFoodLog.getDate());
    }

    @Test
    public void testAddOneEntryWrongDate() {
        Entry testEntryWrongDate = new Entry("Snack", "Ice Cream", 460);
        testEntryWrongDate.setDate("May 12, 1968");

        testFoodLog.addEntry(testEntryWrongDate);
        assertEquals(0, testFoodLog.getSize());
    }

    @Test
    public void testAddOneEntryRightDate() {
        testFoodLog.addEntry(testEntryBreakfast);
        assertEquals(1,testFoodLog.getSize());
        assertEquals(testEntryBreakfast,testFoodLog.getEntry(0));
    }

    @Test
    public void testAddMultipleEntries() {

        testFoodLog.addEntry(testEntryBreakfast);
        testFoodLog.addEntry(testEntryLunch);
        testFoodLog.addEntry((testEntryDinner));

        assertEquals(3,testFoodLog.getSize());
        assertEquals(testEntryBreakfast,testFoodLog.getEntry(0));
        assertEquals(testEntryLunch,testFoodLog.getEntry(1));
        assertEquals(testEntryDinner,testFoodLog.getEntry(2));
    }

    @Test
    public void testGetIntakeCalories() {
        int totalCal = testEntryBreakfast.getCalories() * 5;

        for (int i = 0; i < 5; i++) {
            testFoodLog.addEntry(testEntryBreakfast);
        }
        assertEquals(totalCal,testFoodLog.totalIntake("Calories"));
    }

    @Test
    public void testGetIntakeProtein() {
        testEntryBreakfast.setMacros(40,0,0);
        int totalProtein = testEntryBreakfast.getProtein() * 5;

        for (int i = 0; i < 5; i++) {
            testFoodLog.addEntry(testEntryBreakfast);
        }
        assertEquals(totalProtein,testFoodLog.totalIntake("Protein"));
    }

    @Test
    public void testGetIntakeFat() {
        testEntryBreakfast.setMacros(0,60,0);
        int totalFat = testEntryBreakfast.getFat() * 5;

        for (int i = 0; i < 5; i++) {
            testFoodLog.addEntry(testEntryBreakfast);
        }
        assertEquals(totalFat,testFoodLog.totalIntake("Fat"));
    }

    @Test
    public void testGetIntakeCarbs() {
        testEntryBreakfast.setMacros(0,0,90);
        int totalCarbs = testEntryBreakfast.getCarbs() * 5;

        for (int i = 0; i < 5; i++) {
            testFoodLog.addEntry(testEntryBreakfast);
        }
        assertEquals(totalCarbs,testFoodLog.totalIntake("Carbs"));
    }


    @Test
    public void testMealReportBreakfast() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        testFoodLog.addEntry(testEntryLunch);
        testFoodLog.addEntry(testEntryBreakfast);
        testFoodLog.addEntry(testEntryDinner);
        testFoodLog.addEntry(testEntrySnack);

        String expectedResult = date + "\n" + testEntryBreakfast.printEntry()
                + "\n" + "Total Calories: 350";

        assertEquals(expectedResult,testFoodLog.printMealReport("BREAKFAST"));
    }

    @Test
    public void testMealReportLunch() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        testFoodLog.addEntry(testEntryLunch);
        testFoodLog.addEntry(testEntryBreakfast);
        testFoodLog.addEntry(testEntryDinner);
        testFoodLog.addEntry(testEntrySnack);

        String expectedResult = date + "\n" + testEntryLunch.printEntry()
                + "\n" + "Total Calories: 760";

        assertEquals(expectedResult,testFoodLog.printMealReport("LUNCH"));
    }

    @Test
    public void testMealReportDinner() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        testFoodLog.addEntry(testEntryLunch);
        testFoodLog.addEntry(testEntryBreakfast);
        testFoodLog.addEntry(testEntryDinner);
        testFoodLog.addEntry(testEntrySnack);

        String expectedResult = date + "\n" + testEntryDinner.printEntry()
                + "\n" + "Total Calories: 860";

        assertEquals(expectedResult,testFoodLog.printMealReport("DINNER"));
    }

    @Test
    public void testMealReportSnack() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        testFoodLog.addEntry(testEntryLunch);
        testFoodLog.addEntry(testEntryBreakfast);
        testFoodLog.addEntry(testEntryDinner);
        testFoodLog.addEntry(testEntrySnack);

        String expectedResult = date + "\n" + testEntrySnack.printEntry()
                + "\n" + "Total Calories: 560";

        assertEquals(expectedResult,testFoodLog.printMealReport("SNACK"));
    }

    @Test
    public void testMealReportMultipleMealEntries() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        Entry secondBreakfast = new Entry("BREAKFAST", "Cereal", 320);

        testFoodLog.addEntry(testEntryBreakfast);
        testFoodLog.addEntry(testEntryLunch);
        testFoodLog.addEntry(secondBreakfast);

        String expectedResult = date + "\n" + testEntryBreakfast.printEntry()
                + "\n" + secondBreakfast.printEntry() + "\n"
                + "Total Calories: 670";

        assertEquals(expectedResult,testFoodLog.printMealReport("BREAKFAST"));
    }


    @Test
    public void testFullReportOneEntry() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        testFoodLog.addEntry(testEntryBreakfast);

        String expectedResult = date + "\n" + testEntryBreakfast.printEntry()
                + "\n" + "Total Calories: " + testFoodLog.totalIntake("Calories");

        assertEquals(expectedResult,testFoodLog.printFullReport());
    }

    @Test
    public void testFullReportManyEntries() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        for (int i = 0; i < 10; i++) {
            testFoodLog.addEntry(testEntryBreakfast);
            testFoodLog.addEntry(testEntrySnack);
        }

        String expectedResult = date;
        for (int i = 0; i < 10; i++) {
            expectedResult += "\n" + testEntryBreakfast.printEntry()
                    + "\n" + testEntrySnack.printEntry();
        }
        expectedResult += "\n" + "Total Calories: "
                + testFoodLog.totalIntake("Calories");

        assertEquals(expectedResult,testFoodLog.printFullReport());
    }

}
