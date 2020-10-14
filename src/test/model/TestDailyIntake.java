package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDailyIntake {

    private Goal testGoal;
    private User testUser;
    private DailyFoodLog testFoodLog;
    private DailyIntake testDailyIntake;
    private Entry testEntry;

    @BeforeEach
    public void setUp() {
        testUser = new User(27,"M",120,190,"Lose");
        testUser.setActivityLevel("Sedentary");

        testGoal = new Goal(testUser);
        testGoal.setCustomGoal(2000,80,67,200);
        testDailyIntake = new DailyIntake(testGoal);

        testFoodLog = new DailyFoodLog();
        testEntry = new Entry("BREAKFAST","Pancakes",550);
        testEntry.setMacros(20,10,50);
    }

    @Test
    public void testNoEntries() {
        assertEquals(2000, testDailyIntake.getTargetCal());
        assertEquals(80,testDailyIntake.getTargetProt());
        assertEquals(67,testDailyIntake.getTargetFat());
        assertEquals(200,testDailyIntake.getTargetCarbs());
    }

    @Test
    public void testOneEntryCalories() {
        testFoodLog.addEntry(testEntry);
        int expectedCal = testDailyIntake.getTargetCal() - testEntry.getCalories();
        assertEquals(expectedCal,testDailyIntake.totalCaloriesRemaining(testFoodLog));
    }

    @Test
    public void testMultipleEntries() {
        for (int i = 0; i < 3; i++) {
            testFoodLog.addEntry(testEntry);
        }
        int expectedCal = testDailyIntake.getTargetCal() - (testEntry.getCalories() * 3);
        assertEquals(expectedCal,testDailyIntake.totalCaloriesRemaining(testFoodLog));
    }

    @Test
    public void testOverateCalories() {
        for (int i = 0; i < 100; i++) {
            testFoodLog.addEntry(testEntry);
        }
        assertEquals(0,testDailyIntake.totalCaloriesRemaining(testFoodLog));
    }

    @Test
    public void testOverateCaloriesExact() {
        Entry testEntryCal = new Entry("Breakfast","Cereal",2000);
        testFoodLog.addEntry(testEntryCal);

        assertEquals(0,testDailyIntake.totalCaloriesRemaining(testFoodLog));
    }

    @Test
    public void testCaloriesOneRemaining() {
        Entry testEntryCal = new Entry("Breakfast","Cereal",1999);
        testFoodLog.addEntry(testEntryCal);

        assertEquals(1,testDailyIntake.totalCaloriesRemaining(testFoodLog));
    }


    @Test
    public void testOneEntryProtein() {
        testFoodLog.addEntry(testEntry);
        int expectedProt = testDailyIntake.getTargetProt() - testEntry.getProtein();
        assertEquals(expectedProt,testDailyIntake.totalProtRemaining(testFoodLog));
    }

    @Test
    public void testMultipleEntriesProt() {
        for (int i = 0; i < 3; i++) {
            testFoodLog.addEntry(testEntry);
        }
        int expectedProt = testDailyIntake.getTargetProt() - (testEntry.getProtein() * 3);
        assertEquals(expectedProt,testDailyIntake.totalProtRemaining(testFoodLog));
    }

    @Test
    public void testOverateProt() {
        for (int i = 0; i < 100; i++) {
            testFoodLog.addEntry(testEntry);
        }
        assertEquals(0,testDailyIntake.totalProtRemaining(testFoodLog));
    }

    @Test
    public void testOverateProtExact() {
        Entry testEntryProt = new Entry("Breakfast","Cereal",500);
        testEntryProt.setMacros(80,0,0);

        testFoodLog.addEntry(testEntryProt);

        assertEquals(0,testDailyIntake.totalProtRemaining(testFoodLog));
    }

    @Test
    public void testProtOneRemaining() {
        Entry testEntryProt = new Entry("Breakfast","Cereal",500);
        testEntryProt.setMacros(79,0,0);
        testFoodLog.addEntry(testEntryProt);

        assertEquals(1,testDailyIntake.totalProtRemaining(testFoodLog));
    }


    @Test
    public void testOneEntryFat() {
        testFoodLog.addEntry(testEntry);
        int expectedFat = testDailyIntake.getTargetFat() - testEntry.getFat();
        assertEquals(expectedFat,testDailyIntake.totalFatRemaining(testFoodLog));
    }

    @Test
    public void testMultipleEntriesFat() {
        for (int i = 0; i < 3; i++) {
            testFoodLog.addEntry(testEntry);
        }
        int expectedFat = testDailyIntake.getTargetFat() - (testEntry.getFat() * 3);
        assertEquals(expectedFat,testDailyIntake.totalFatRemaining(testFoodLog));
    }

    @Test
    public void testOverateFat() {
        for (int i = 0; i < 100; i++) {
            testFoodLog.addEntry(testEntry);
        }
        assertEquals(0,testDailyIntake.totalFatRemaining(testFoodLog));
    }

    @Test
    public void testOverateFatExact() {
        Entry testEntryFat = new Entry("Breakfast","Cereal",500);
        testEntryFat.setMacros(0,67,0);

        testFoodLog.addEntry(testEntryFat);

        assertEquals(0,testDailyIntake.totalFatRemaining(testFoodLog));
    }

    @Test
    public void testFatOneRemaining() {
        Entry testEntryFat = new Entry("Breakfast","Cereal",500);
        testEntryFat.setMacros(0,66,0);
        testFoodLog.addEntry(testEntryFat);

        assertEquals(1,testDailyIntake.totalFatRemaining(testFoodLog));
    }

    @Test
    public void testOneEntryCarbs() {
        testFoodLog.addEntry(testEntry);
        int expectedCarbs = testDailyIntake.getTargetCarbs() - testEntry.getCarbs();
        assertEquals(expectedCarbs,testDailyIntake.totalCarbsRemaining(testFoodLog));
    }

    @Test
    public void testMultipleEntriesCarbs() {
        for (int i = 0; i < 3; i++) {
            testFoodLog.addEntry(testEntry);
        }
        int expectedCarbs = testDailyIntake.getTargetCarbs() - (testEntry.getCarbs() * 3);
        assertEquals(expectedCarbs,testDailyIntake.totalCarbsRemaining(testFoodLog));
    }

    @Test
    public void testOverateCarbs() {
        for (int i = 0; i < 100; i++) {
            testFoodLog.addEntry(testEntry);
        }
        assertEquals(0,testDailyIntake.totalCarbsRemaining(testFoodLog));
    }

    @Test
    public void testOverateCarbsExact() {
        Entry testEntryCarbs = new Entry("Breakfast","Cereal",500);
        testEntryCarbs.setMacros(0,0,200);

        testFoodLog.addEntry(testEntryCarbs);

        assertEquals(0,testDailyIntake.totalCarbsRemaining(testFoodLog));
    }

    @Test
    public void testCarbsOneRemaining() {
        Entry testEntryCarbs = new Entry("Breakfast","Cereal",500);
        testEntryCarbs.setMacros(0,0,199);
        testFoodLog.addEntry(testEntryCarbs);

        assertEquals(1,testDailyIntake.totalCarbsRemaining(testFoodLog));
    }



}
