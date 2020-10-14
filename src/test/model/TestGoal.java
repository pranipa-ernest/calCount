package model;

import model.Goal;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGoal {

    private User testUserMLose;
    private User testUserFLose;
    private Goal testGoalM;
    private Goal testGoalF;

    @BeforeEach
    public void setUp() {
        testUserMLose = new User(30,"M",120,180,"Lose");
        testUserMLose.setActivityLevel("Sedentary");

        testUserFLose = new User(30,"F",120,180,"Lose");
        testUserFLose.setActivityLevel("Sedentary");

        testGoalM = new Goal(testUserMLose);
        testGoalF = new Goal(testUserFLose);
    }

    @Test
    public void testConstructor() {
        assertEquals(testUserMLose, testGoalM.getUser());
    }

    @Test
    public void testSetTdeeMaleSed() {
        int expectedTdee = (int)(13.7 * testUserMLose.getWeight() + 5 * testUserMLose.getHeight()
                - 6.8 * testUserMLose.getAge() + 66);
        expectedTdee *= testUserMLose.getActivityLevel();

        assertEquals(expectedTdee, testGoalM.setTdee());
    }

    @Test
    public void testSetTdeeMaleLightlyActive() {
        testUserMLose.setActivityLevel("Lightly Active");

        int expectedTdee = (int)(13.7 * testUserMLose.getWeight() + 5 * testUserMLose.getHeight()
                - 6.8 * testUserMLose.getAge() + 66);
        expectedTdee *= testUserMLose.getActivityLevel();
        assertEquals(expectedTdee, testGoalM.setTdee());
    }

    @Test
    public void testSetTdeeMaleModActive() {
        testUserMLose.setActivityLevel("Moderately Active");

        int expectedTdee = (int)(13.7 * testUserMLose.getWeight() + 5 * testUserMLose.getHeight()
                - 6.8 * testUserMLose.getAge() + 66);
        expectedTdee *= testUserMLose.getActivityLevel();
        assertEquals(expectedTdee, testGoalM.setTdee());
    }

    @Test
    public void testSetTdeeMaleExtremelyActive() {
        testUserMLose.setActivityLevel("Extremely Active");

        int expectedTdee = (int)(13.7 * testUserMLose.getWeight() + 5 * testUserMLose.getHeight()
                - 6.8 * testUserMLose.getAge() + 66);
        expectedTdee *= testUserMLose.getActivityLevel();
        assertEquals(expectedTdee, testGoalM.setTdee());
    }

    @Test
    public void testSetTdeeFemaleSed() {
        int expectedTdee = (int)(9.6 * testUserFLose.getWeight() + 1.8 * testUserFLose.getHeight()
                - 4.7 * testUserFLose.getAge() + 655);
        expectedTdee *= testUserFLose.getActivityLevel();

        assertEquals(expectedTdee, testGoalF.setTdee());
    }

    @Test
    public void testSetTdeeFemaleLightlyActive() {
        testUserFLose.setActivityLevel("Lightly Active");

        int expectedTdee = (int)(9.6 * testUserFLose.getWeight() + 1.8 * testUserFLose.getHeight()
                - 4.7 * testUserFLose.getAge() + 655);
        expectedTdee *= testUserFLose.getActivityLevel();
        assertEquals(expectedTdee, testGoalF.setTdee());
    }

    @Test
    public void testSetTdeeFemaleModActive() {
        testUserFLose.setActivityLevel("Moderately Active");

        int expectedTdee = (int)(9.6 * testUserFLose.getWeight() + 1.8 * testUserFLose.getHeight()
                - 4.7 * testUserFLose.getAge() + 655);
        expectedTdee *= testUserFLose.getActivityLevel();
        assertEquals(expectedTdee, testGoalF.setTdee());
    }

    @Test
    public void testSetTdeeFemaleExtremelyActive() {
        testUserFLose.setActivityLevel("Extremely Active");

        int expectedTdee = (int)(9.6 * testUserFLose.getWeight() + 1.8 * testUserFLose.getHeight()
                - 4.7 * testUserFLose.getAge() + 655);
        expectedTdee *= testUserFLose.getActivityLevel();
        assertEquals(expectedTdee, testGoalF.setTdee());
    }

    @Test
    public void testTargetCalLose() {
        assertEquals("Lose",testGoalM.getGoalWeight());
        int tdee = testGoalM.setTdee();
        int expectedCal = tdee - 500;
        assertEquals(expectedCal,testGoalM.findTargetCalWithGoal(tdee));
    }

    @Test
    public void testTargetCalLose1200() {
        assertEquals("Lose",testGoalM.getGoalWeight());
        User testUserSmall = new User(30,"F",43,180,"Lose");
        Goal testGoalSmall = new Goal(testUserSmall);

        assertEquals(1200,testGoalSmall.findTargetCalWithGoal(1700));
    }

    @Test
    public void testTargetCalLoseUnder1200() {
        assertEquals("Lose",testGoalM.getGoalWeight());
        User testUserSmall = new User(30,"F",43,180,"Lose");
        Goal testGoalSmall = new Goal(testUserSmall);

        assertEquals(1200,testGoalSmall.findTargetCalWithGoal(1300));
    }


    @Test
    public void testTargetCalGain() {
        User testUserMGain = new User(30,"M",120,180,"Gain");
        Goal testGoalMGain = new Goal(testUserMGain);
        assertEquals("Gain",testGoalMGain.getGoalWeight());

        int tdee = testGoalMGain.setTdee();
        int expectedCal = tdee + 500;

        assertEquals(expectedCal,testGoalMGain.findTargetCalWithGoal(tdee));
    }

    @Test
    public void testTargetCalMaintain() {
        User testUserMMaintain = new User(30,"M",120,180,"Maintain");
        Goal testGoalMMaintain = new Goal(testUserMMaintain);
        assertEquals("Maintain",testGoalMMaintain.getGoalWeight());
        int tdee = testGoalMMaintain.setTdee();

        assertEquals(tdee,testGoalMMaintain.findTargetCalWithGoal(tdee));
    }

    @Test
    public void testSetTargetProt() {

        int tdee = testGoalM.setTdee();
        int testTargetCal = testGoalM.findTargetCalWithGoal(tdee);
        int expectedProt = (int)(0.3 * testTargetCal);

        assertEquals(expectedProt,testGoalM.setTargetProtein(testTargetCal));
    }

    @Test
    public void testSetTargetFat() {

        int tdee = testGoalM.setTdee();
        int testTargetCal = testGoalM.findTargetCalWithGoal(tdee);
        int expectedFat = (int)(0.2 * testTargetCal);

        assertEquals(expectedFat,testGoalM.setTargetFat(testTargetCal));
    }

    @Test
    public void testTestTargetCarbs() {

        int tdee = testGoalM.setTdee();
        int testTargetCal = testGoalM.findTargetCalWithGoal(tdee);
        int expectedCarbs = (int)(0.5 * testTargetCal);

        assertEquals(expectedCarbs,testGoalM.setTargetCarbs(testTargetCal));
    }

    @Test
    public void testSetRecommendedGoal() {
        int tdee = testGoalM.setTdee();

        int expectedCal = testGoalM.findTargetCalWithGoal(tdee);
        int expectedProt = testGoalM.setTargetProtein(expectedCal);
        int expectedFat = testGoalM.setTargetFat(expectedCal);
        int expectedCarbs = testGoalM.setTargetCarbs(expectedCal);

        testGoalM.setRecommendedGoal();

        assertEquals(expectedCal,testGoalM.getTargetCalories());
        assertEquals(expectedProt,testGoalM.getTargetProtein());
        assertEquals(expectedFat,testGoalM.getTargetFat());
        assertEquals(expectedCarbs,testGoalM.getTargetCarbs());
    }

    @Test
    public void testSetCustomGoal() {
        testGoalM.setCustomGoal(2000,35,12,25);

        assertEquals(2000,testGoalM.getTargetCalories());
        assertEquals(35,testGoalM.getTargetProtein());
        assertEquals(12,testGoalM.getTargetFat());
        assertEquals(25,testGoalM.getTargetCarbs());

    }





}
