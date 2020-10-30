package persistence;

import model.DailyFoodLog;
import model.Entry;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter extends JsonTest{

    private User user;
    private final static String DATE_26 = "October 26, 2020";
    private final static String DATE_27 = "October 27, 2020";


    @BeforeEach
    public void setUp() {
        user = new User(35,"F",120,189,"Lose");
        user.setActivityLevel("Sedentary");
        user.setCustomGoal(1860,60,30,120);
    }
    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterModActivityLevel() {
        User userMod = new User(35,"F",120,189,"Lose");
        userMod.setActivityLevel("Moderately Active");
        userMod.setCustomGoal(1860,60,30,120);

        testDifferentActivityLevels(userMod,"./data/testWriterModUser.json");
    }

    @Test
    public void testWriterLightActivityLevel() {
        User userMod = new User(35,"F",120,189,"Lose");
        userMod.setActivityLevel("Lightly Active");
        userMod.setCustomGoal(1860,60,30,120);

        testDifferentActivityLevels(userMod,"./data/testWriterLightUser.json");
    }

    @Test
    public void testWriterExtremeActivityLevel() {
        User userMod = new User(35,"F",120,189,"Lose");
        userMod.setActivityLevel("Extremely Active");
        userMod.setCustomGoal(1860,60,30,120);

        testDifferentActivityLevels(userMod,"./data/testWriterExtremeUser.json");
    }

    public void testDifferentActivityLevels(User user, String destination) {
        try {
            writeFile(user,destination);
            JsonReader reader = new JsonReader(destination);
            User testUser = reader.read();
            checkUser(testUser,user);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterEmptyTotalFoodLog() {
        try {
            writeFile(user,"./data/testWriterEmptyTotalFoodLog.json");

            JsonReader reader = new JsonReader("./data/testWriterEmptyTotalFoodLog.json");
            User testUser = reader.read();
            checkUser(testUser,user);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    public void testWriterOneDailyFoodLogOneEntry() {
        try {
            DailyFoodLog log = writeOct26FoodLog(1);
            user.addDailyFoodLog(log);

            writeFile(user,"./data/testWriterOneDailyFoodLogOneEntry.json");

            JsonReader reader = new JsonReader("./data/testWriterOneDailyFoodLogOneEntry.json");
            User testUser = reader.read();
            DailyFoodLog testDailyLog = testUser.findDailyFoodLog(DATE_26);

            ArrayList<Entry> expectedEntries = getOct26Entries(1);
            checkDailyFoodLog(testDailyLog,expectedEntries,DATE_26);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterOneDailyFoodLogMultipleEntries() {
        try {
            DailyFoodLog log = writeOct26FoodLog(3);
            user.addDailyFoodLog(log);

            writeFile(user,"./data/testWriterOneDailyFoodLogMultipleEntries.json");

            JsonReader reader = new JsonReader("./data/testWriterOneDailyFoodLogMultipleEntries.json");
            User testUser = reader.read();
            DailyFoodLog testDailyLog = testUser.findDailyFoodLog(DATE_26);

            ArrayList<Entry> expectedEntries = getOct26Entries(3);
            checkDailyFoodLog(testDailyLog,expectedEntries,DATE_26);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterMultipleDailyFoodLogs() {
        try {
            DailyFoodLog log26 = writeOct26FoodLog(3);
            DailyFoodLog log27 = writeOct27FoodLog(3);

            user.addDailyFoodLog(log26);
            user.addDailyFoodLog(log27);

            writeFile(user,"./data/testWriterMultipleDailyFoodLogs.json");

            JsonReader reader = new JsonReader("./data/testWriterMultipleDailyFoodLogs.json");
            User testUser = reader.read();

            DailyFoodLog testDailyLog26 = testUser.findDailyFoodLog(DATE_26);
            DailyFoodLog testDailyLog27 = testUser.findDailyFoodLog(DATE_27);

            ArrayList<Entry> expectedEntries26 = getOct26Entries(3);
            checkDailyFoodLog(testDailyLog26,expectedEntries26,DATE_26);

            ArrayList<Entry> expectedEntries27 = getOct27Entries(3);
            checkDailyFoodLog(testDailyLog27,expectedEntries27,DATE_27);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    public void writeFile(User user, String destination) {
        try {
            JsonWriter writer = new JsonWriter(destination);
            writer.open();
            writer.write(user);
            writer.close();
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }
    }

    public DailyFoodLog writeOct26FoodLog(int num) {
        DailyFoodLog log = new DailyFoodLog();
        log.setDate(DATE_26);

        ArrayList<Entry> entries = getOct26Entries(num);

        log.addEntry(entries.get(0));
        if (num == 1) {
            return log;
        }
        log.addEntry(entries.get(1));
        log.addEntry(entries.get(2));
        return log;
    }

    public ArrayList<Entry> getOct26Entries(int num) {
        ArrayList<Entry> entries = new ArrayList<>();

        Entry entry = new Entry("BREAKFAST","Cereal",340);
        Entry entry2 = new Entry("LUNCH", "Salad",450);
        Entry entry3 = new Entry("DINNER", "Chicken",760);

        entries.add(entry);
        if (num == 1) {
            return entries;
        }
        entries.add(entry2);
        entries.add(entry3);

        return entries;
    }

    public DailyFoodLog writeOct27FoodLog(int num) {
        DailyFoodLog log = new DailyFoodLog();
        log.setDate(DATE_27);

        ArrayList<Entry> entries = getOct27Entries(num);

        log.addEntry(entries.get(0));
        if (num == 1) {
            return log;
        }
        log.addEntry(entries.get(1));
        log.addEntry(entries.get(2));
        return log;
    }

    private ArrayList<Entry> getOct27Entries(int num) {
        ArrayList<Entry> entries = new ArrayList<>();

        Entry entry = new Entry("BREAKFAST","Oatmeal",120);
        Entry entry2 = new Entry("LUNCH", "Sandwich",450);
        Entry entry3 = new Entry("DINNER", "Noodles",840);

        entries.add(entry);
        if (num == 1) {
            return entries;
        }
        entries.add(entry2);
        entries.add(entry3);

        return entries;
    }


}
