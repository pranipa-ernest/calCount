package persistence;

import model.DailyFoodLog;
import model.Entry;
import model.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkUser(User user, User expectedUser) {

        assertEquals(expectedUser.getActivityLevel(),user.getActivityLevel());
        assertEquals(expectedUser.getGoal().getTargetCalories(),user.getGoal().getTargetCalories());
        assertEquals(expectedUser.getGoal().getTargetFat(),user.getGoal().getTargetFat());
        assertEquals(expectedUser.getGoal().getTargetProtein(),user.getGoal().getTargetProtein());
        assertEquals(expectedUser.getGoal().getTargetCarbs(),user.getGoal().getTargetCarbs());
        assertEquals(expectedUser.getSex(),user.getSex());
        assertEquals(expectedUser.getGoalWeight(),user.getGoalWeight());
        assertEquals(expectedUser.getWeight(),user.getWeight());
        assertEquals(expectedUser.getAge(),user.getAge());
        assertEquals(expectedUser.getHeight(),user.getHeight());

        assertEquals(expectedUser.getTotalFoodLog().getSize(),user.getTotalFoodLog().getSize());
    }

    protected void checkDailyFoodLog(DailyFoodLog foodLog, ArrayList<Entry> expectedEntries, String date) {
        assertEquals(date,foodLog.getDate());
        assertEquals(expectedEntries.size(),foodLog.getSize());

        for (int i=0; i < foodLog.getSize(); i++) {
            Entry testEntry = foodLog.getEntry(i);
            Entry expectedEntry = expectedEntries.get(i);

            assertEquals(expectedEntry.getMeal(),testEntry.getMeal());
            assertEquals(expectedEntry.getFood(),testEntry.getFood());
            assertEquals(expectedEntry.getCalories(),testEntry.getCalories());

            assertEquals(expectedEntry.getFat(),testEntry.getFat());
            assertEquals(expectedEntry.getProtein(),testEntry.getProtein());
            assertEquals(expectedEntry.getCarbs(),testEntry.getCarbs());
        }
    }
}
