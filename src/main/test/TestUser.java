package test;

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

        assertEquals(1500, testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(12, testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(35, testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(30, testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalFemaleSedentary() {
        testUserFemaleLose.setActivityLevel("Sedentary");
        testUserFemaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserFemaleLose.getWeight()
                + 6.25 * testUserFemaleLose.getHeight()
                - 5 * testUserFemaleLose.getAge() - 161));
        int expectedCalories = (int)(tdee * 1.20) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalFemaleLightActive() {
        testUserFemaleLose.setActivityLevel("Lightly Active");
        testUserFemaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserFemaleLose.getWeight()
                + 6.25 * testUserFemaleLose.getHeight()
                - 5 * testUserFemaleLose.getAge() - 161));
        int expectedCalories = (int)(tdee * 1.375) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalFemaleModActive() {
        testUserFemaleLose.setActivityLevel("Moderately Active");
        testUserFemaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserFemaleLose.getWeight()
                + 6.25 * testUserFemaleLose.getHeight()
                - 5 * testUserFemaleLose.getAge() - 161));
        int expectedCalories = (int)(tdee * 1.550) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalFemaleExtremeActive() {
        testUserFemaleLose.setActivityLevel("Extremely Active");
        testUserFemaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserFemaleLose.getWeight()
                + 6.25 * testUserFemaleLose.getHeight()
                - 5 * testUserFemaleLose.getAge() - 161));
        int expectedCalories = (int)(tdee * 1.900) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserFemaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserFemaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserFemaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserFemaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalMaleSedentary() {
        testUserMaleLose.setActivityLevel("Sedentary");
        testUserMaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserMaleLose.getWeight()
                + 6.25 * testUserMaleLose.getHeight()
                - 5 * testUserMaleLose.getAge() + 5));
        int expectedCalories = (int)(tdee * 1.20) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserMaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserMaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserMaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserMaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalMaleLightActive() {
        testUserMaleLose.setActivityLevel("Lightly Active");
        testUserMaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserMaleLose.getWeight()
                + 6.25 * testUserMaleLose.getHeight()
                - 5 * testUserMaleLose.getAge() + 5));
        int expectedCalories = (int)(tdee * 1.375) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserMaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserMaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserMaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserMaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalMaleModActive() {
        testUserMaleLose.setActivityLevel("Moderately Active");
        testUserMaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserMaleLose.getWeight()
                + 6.25 * testUserMaleLose.getHeight()
                - 5 * testUserMaleLose.getAge() + 5));
        int expectedCalories = (int)(tdee * 1.550) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserMaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserMaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserMaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserMaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalMaleExtremelyActive() {
        testUserMaleLose.setActivityLevel("Extremely Active");
        testUserMaleLose.setRecommendedGoal();

        int tdee = (int)((10 * testUserMaleLose.getWeight()
                + 6.25 * testUserMaleLose.getHeight()
                - 5 * testUserMaleLose.getAge() + 5));
        int expectedCalories = (int)(tdee * 1.900) - 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserMaleLose.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserMaleLose.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserMaleLose.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserMaleLose.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalFemaleMaintain() {

        User testUserFemaleMaintain = new User(31,"F",67,165,"Maintain");
        testUserFemaleMaintain.setActivityLevel("Sedentary");
        testUserFemaleMaintain.setRecommendedGoal();

        int tdee = (int)((10 * testUserFemaleMaintain.getWeight()
                + 6.25 * testUserFemaleMaintain.getHeight()
                - 5 * testUserFemaleMaintain.getAge() - 161));
        int expectedCalories = (int)(tdee * 1.2);

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserFemaleMaintain.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserFemaleMaintain.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserFemaleMaintain.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserFemaleMaintain.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalFemaleGain() {
        User testUserFemaleGain = new User(31,"F",67,165,"Gain");
        testUserFemaleGain.setActivityLevel("Sedentary");
        testUserFemaleGain.setRecommendedGoal();

        int tdee = (int)((10 * testUserFemaleGain.getWeight()
                + 6.25 * testUserFemaleGain.getHeight()
                - 5 * testUserFemaleGain.getAge() - 161));
        int expectedCalories = (int)(tdee * 1.2) + 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserFemaleGain.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserFemaleGain.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserFemaleGain.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserFemaleGain.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalMaleMaintain() {

        User testUserMaleMaintain = new User(31,"M",83,165,"Maintain");
        testUserMaleMaintain.setActivityLevel("Sedentary");
        testUserMaleMaintain.setRecommendedGoal();

        int tdee = (int)((10 * testUserMaleMaintain.getWeight()
                + 6.25 * testUserMaleMaintain.getHeight()
                - 5 * testUserMaleMaintain.getAge() + 5));
        int expectedCalories = (int)(tdee * 1.2);

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserMaleMaintain.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserMaleMaintain.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserMaleMaintain.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserMaleMaintain.getGoal().getTargetCarbs());
    }

    @Test
    public void testRecommendedGoalMaleGain() {
        User testUserMaleGain = new User(31,"M",67,165,"Gain");
        testUserMaleGain.setActivityLevel("Sedentary");
        testUserMaleGain.setRecommendedGoal();

        int tdee = (int)((10 * testUserMaleGain.getWeight()
                + 6.25 * testUserMaleGain.getHeight()
                - 5 * testUserMaleGain.getAge() + 5));
        int expectedCalories = (int)(tdee * 1.2) + 500;

        int expectedProtein = (int)(0.3 * expectedCalories);
        int expectedFat = (int)(0.2 * expectedCalories);
        int expectedCarbs = (int)(0.5 * expectedCalories);

        assertEquals(expectedCalories, testUserMaleGain.getGoal().getTargetCalories());
        assertEquals(expectedProtein, testUserMaleGain.getGoal().getTargetProtein());
        assertEquals(expectedFat, testUserMaleGain.getGoal().getTargetFat());
        assertEquals(expectedCarbs, testUserMaleGain.getGoal().getTargetCarbs());
    }





}
