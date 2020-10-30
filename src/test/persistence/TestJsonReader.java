package persistence;

import model.DailyFoodLog;
import model.Entry;
import model.TotalFoodLog;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader extends JsonTest{

    private User user;

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyTotalFoodLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTotalFoodLog.json");
        try {
            user = reader.read();
            User expectedUser = new User(35,"F",120,189,"Lose");
            expectedUser.setActivityLevel("Sedentary");
            expectedUser.setCustomGoal(1860,30,60,120);

            checkUser(user,expectedUser);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderOneLogTotalFoodLog() {
        JsonReader reader = new JsonReader("./data/testReaderOneDailyFoodLogOneEntry.json");
        try {
            user = reader.read();
            TotalFoodLog testTotalFoodLog = user.getTotalFoodLog();
            assertEquals(1,testTotalFoodLog.getSize());

            DailyFoodLog testLog = testTotalFoodLog.findLog("October 26, 2020");
            assertEquals(1,testLog.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    public void testReaderOneEntryDailyFoodLog() {
        JsonReader reader = new JsonReader("./data/testReaderOneDailyFoodLogOneEntry.json");
        try {
            user = reader.read();
            TotalFoodLog testTotalFoodLog = user.getTotalFoodLog();
            DailyFoodLog testLog = testTotalFoodLog.findLog("October 26, 2020");

            Entry testEntry = new Entry("BREAKFAST","coffee",0);
            ArrayList<Entry> entries = new ArrayList<>();
            entries.add(testEntry);

            checkDailyFoodLog(testLog,entries,"October 26, 2020");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderMultipleEntriesDailyFoodLog() {
        JsonReader reader = new JsonReader("./data/testReaderOneDailyFoodLogMultipleEntries.json");
        try {
            user = reader.read();
            TotalFoodLog testTotalFoodLog = user.getTotalFoodLog();
            DailyFoodLog testLog = testTotalFoodLog.findLog("October 26, 2020");
            ArrayList<Entry> entries = checkOct26FoodLog(testLog);
            checkDailyFoodLog(testLog,entries,"October 26, 2020");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    public void testReaderMultipleDailyFoodLogs() {
        JsonReader reader = new JsonReader("./data/testReaderMultipleDailyFoodLogs.json");
        try {
            user = reader.read();
            TotalFoodLog testTotalFoodLog = user.getTotalFoodLog();

            DailyFoodLog testLog1 = testTotalFoodLog.findLog("October 26, 2020");
            ArrayList<Entry> entries1 = checkOct26FoodLog(testLog1);

            DailyFoodLog testLog2 = testTotalFoodLog.findLog("October 27, 2020");
            ArrayList<Entry> entries2 = checkOct27FoodLog(testLog2);

            checkDailyFoodLog(testLog1,entries1,"October 26, 2020");
            checkDailyFoodLog(testLog2,entries2,"October 27, 2020");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    public ArrayList<Entry> checkOct26FoodLog(DailyFoodLog foodLog) {
        Entry testEntry1 = new Entry("BREAKFAST","coffee",0);

        Entry testEntry2 = new Entry("SNACK","granola",250);
        testEntry2.setMacros(30,50,20);

        Entry testEntry3 = new Entry("DINNER","pasta",760);
        testEntry3.setMacros(203,70,500);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(testEntry1);
        entries.add(testEntry2);
        entries.add(testEntry3);

        return entries;
    }


    public ArrayList<Entry> checkOct27FoodLog(DailyFoodLog foodLog) {
        Entry testEntry1 = new Entry("BREAKFAST","Cereal",230);

        Entry testEntry2 = new Entry("LUNCH","Sandwich",500);
        testEntry2.setMacros(30,50,20);

        Entry testEntry3 = new Entry("DINNER","Soup",450);
        testEntry3.setMacros(203,70,500);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(testEntry1);
        entries.add(testEntry2);
        entries.add(testEntry3);

        return entries;
    }



}
