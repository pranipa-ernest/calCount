package test;

import model.Goal;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGoal {

    private Goal customGoal;
    private Goal recommendedGoalLose;
    private Goal recommendedGoalMaintain;
    private Goal recommendedGoalGain;

    @BeforeEach
    public void setUp() {
        recommendedGoalLose = new Goal("Lose");
    }

    @Test
    public void testConstructor() {
        recommendedGoalMaintain = new Goal("Maintain");
        recommendedGoalGain = new Goal("Gain");

        customGoal = new Goal("Lose");

        assertEquals("Lose",customGoal.getGoalWeight());
        assertEquals("Lose",recommendedGoalLose.getGoalWeight());
        assertEquals("Maintain",recommendedGoalMaintain.getGoalWeight());
        assertEquals("Gain",recommendedGoalGain.getGoalWeight());
    }

    @Test
    public void testSetTdeeMale() {
        User testUser = new User(34,"M",87,62,"Lose");
        testUser.setActivityLevel("Lightly Active");

        int expectedTdee = (int)(10 * testUser.getWeight() + 6.25 * testUser.getHeight()
                - 5 * testUser.getAge() + 5);
        expectedTdee *= testUser.getActivityLevel();

        assertEquals(expectedTdee, recommendedGoalLose.setTdee(testUser));
    }

    @Test
    public void testSetTdeeFemale() {
        User testUser = new User(34,"F",62,62,"Lose");
        testUser.setActivityLevel("Lightly Active");

        int expectedTdee = (int)(10 * testUser.getWeight() + 6.25 * testUser.getHeight()
                - 5 * testUser.getAge() - 161);
        expectedTdee *= testUser.getActivityLevel();

        assertEquals(expectedTdee, recommendedGoalLose.setTdee(testUser));
    }

    @Test
    public void testFindTargetCalLose() {
        int testTdee = 2000;
        int expectedTarget = 1500;
        assertEquals(expectedTarget,recommendedGoalLose.findTargetCalWithGoal(testTdee));
    }

    @Test
    public void testFindTargetCalMaintain() {
        recommendedGoalMaintain = new Goal("Maintain");

        int testTdee = 2000;
        int expectedTarget = 2000;
        assertEquals(expectedTarget,recommendedGoalMaintain.findTargetCalWithGoal(testTdee));
    }

    @Test
    public void testFindTargetCalGain() {
        recommendedGoalGain = new Goal("Gain");

        int testTdee = 2000;
        int expectedTarget = 2500;
        assertEquals(expectedTarget,recommendedGoalGain.findTargetCalWithGoal(testTdee));
    }

    @Test
    public void testSetTargetProtein() {
        int testCal = 2000;
        int expectedTarget = (int)(0.3 * 2000);
        assertEquals(expectedTarget,recommendedGoalLose.setTargetProtein(testCal));
    }

    @Test
    public void testSetTargetFat() {
        int testCal = 2000;
        int expectedTarget = (int)(0.2 * 2000);
        assertEquals(expectedTarget,recommendedGoalLose.setTargetFat(testCal));
    }

    @Test
    public void testSetTargetCarbs() {
        int testCal = 2000;
        int expectedTarget = (int)(0.5 * 2000);
        assertEquals(expectedTarget,recommendedGoalLose.setTargetCarbs(testCal));
    }

    @Test
    public void testSetRecGoal() {
        User testUser = new User(34,"F",62,62,"Lose");
        testUser.setActivityLevel("Lightly Active");

        int testUserTdee = recommendedGoalLose.setTdee(testUser);
        int expectedCal = recommendedGoalLose.findTargetCalWithGoal(testUserTdee);
        int expectedProt = recommendedGoalLose.setTargetProtein(expectedCal);
        int expectedFat = recommendedGoalLose.setTargetFat(expectedCal);
        int expectedCarbs = recommendedGoalLose.setTargetCarbs(expectedCal);

        recommendedGoalLose.setRecommendedGoal(testUser);

        assertEquals(expectedCal,recommendedGoalLose.getTargetCalories());
        assertEquals(expectedProt,recommendedGoalLose.getTargetProtein());
        assertEquals(expectedFat,recommendedGoalLose.getTargetFat());
        assertEquals(expectedCarbs,recommendedGoalLose.getTargetCarbs());
    }

    @Test
    public void testCustomGoal() {
        customGoal = new Goal("Lose");
        customGoal.setCustomGoal(2500,80,25,67);

        int expectedCal = 2500;
        int expectedProt = 80;
        int expectedFat = 25;
        int expectedCarbs = 67;

        assertEquals(expectedCal,customGoal.getTargetCalories());
        assertEquals(expectedProt,customGoal.getTargetProtein());
        assertEquals(expectedFat,customGoal.getTargetFat());
        assertEquals(expectedCarbs,customGoal.getTargetCarbs());

    }






}
