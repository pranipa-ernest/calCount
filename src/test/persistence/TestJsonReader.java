package persistence;

import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
        JsonReader reader = new JsonReader("./data/emptyTotalFoodLog.json");
        try {
            user = reader.read();
            assertEquals(1.200,user.getActivityLevel());
            assertEquals(1860,user.getGoal().getTargetCalories());
            assertEquals(60,user.getGoal().getTargetFat());
            assertEquals(30,user.getGoal().getTargetProtein());
            assertEquals(120,user.getGoal().getTargetCarbs());
            assertEquals("Lose",user.getGoalWeight());
            assertEquals("F",user.getSex());
            assertEquals(120,user.getWeight());
            assertEquals(35,user.getAge());
            assertEquals(189,user.getHeight());
            assertEquals(0,user.getTotalFoodLog().getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderOneDailyFoodLog() {
        JsonReader reader = new JsonReader("./data/calCount.json");
        try {
            user = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
