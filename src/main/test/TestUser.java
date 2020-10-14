package test;

import model.DailyFoodLog;
import model.Goal;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {

    private User testUserFemaleLose;
    private User testUserMaleLose;

    @BeforeEach
    public void setUp() {
        testUserFemaleLose = new User(21, "F", 50,158, "Lose");
        testUserMaleLose = new User(21,"M",70,180, "Lose");
    }

    @Test
    public void testConstructor() {
        assertEquals(21, testUserFemaleLose.getAge());
        assertEquals("F", testUserFemaleLose.getSex());
        assertEquals(50, testUserFemaleLose.getWeight());
        assertEquals(158, testUserFemaleLose.getHeight());
        assertEquals("Lose", testUserFemaleLose.getGoalWeight());

        assertEquals(0,testUserFemaleLose.getTotalFoodLog().getSize());
    }

    @Test
    public void testSetActivityLevelSed() {
        testUserFemaleLose.setActivityLevel("Sedentary");
        assertEquals(1.200, testUserFemaleLose.getActivityLevel());
    }

    @Test
    public void testSetActivityLevelLight() {
        testUserFemaleLose.setActivityLevel("Lightly Active");
        assertEquals(1.375, testUserFemaleLose.getActivityLevel());
    }

    @Test
    public void testSetActivityLevelMod() {
        testUserFemaleLose.setActivityLevel("Moderately Active");
        assertEquals(1.550, testUserFemaleLose.getActivityLevel());
    }

    @Test
    public void testSetActivityLevelExtreme() {
        testUserFemaleLose.setActivityLevel("Extremely Active");
        assertEquals(1.900, testUserFemaleLose.getActivityLevel());
    }


    @Test
    public void testUpdateWeight() {
        testUserFemaleLose.updateWeight(52);
        assertEquals(52, testUserFemaleLose.getWeight());
    }

    @Test
    public void testSetCustomGoal() {
        testUserFemaleLose.setCustomGoal(1500,12,35,30);

        Goal testGoal = new Goal(testUserFemaleLose);
        testGoal.setCustomGoal(1500,12,35,30);

        assertEquals(testGoal.getTargetCalories(), testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(testGoal.getTargetProtein(), testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(testGoal.getTargetFat(), testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(testGoal.getTargetCarbs(), testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testSetRecommendedGoal() {
        testUserFemaleLose.setRecommendedGoal();

        Goal testGoal = new Goal(testUserFemaleLose);
        testGoal.setRecommendedGoal();

        assertEquals(testGoal.getTargetCalories(), testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(testGoal.getTargetProtein(), testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(testGoal.getTargetFat(), testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(testGoal.getTargetCarbs(), testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testAddDailyFoodLog() {
        DailyFoodLog dailyFoodLog = new DailyFoodLog();
        testUserFemaleLose.addDailyFoodLog(dailyFoodLog);
        assertEquals(1,testUserFemaleLose.getTotalFoodLog().getSize());
        assertEquals(dailyFoodLog,testUserFemaleLose.getTotalFoodLog().getEntry(0));
    }

    @Test
    public void testAddDailyFoodLogMultiple() {
        DailyFoodLog dailyFoodLog = new DailyFoodLog();

        for (int i = 0; i < 5; i++) {
            testUserFemaleLose.addDailyFoodLog(dailyFoodLog);
        }
        assertEquals(5,testUserFemaleLose.getTotalFoodLog().getSize());
    }


}
