package trash;

import model.User;

public class RecommendedGoal2 extends Goal2 {

    private int tdee;

    public RecommendedGoal2(User user, String goal) {
        super(user, goal);
    }

    public void setTdee() {
        if (user.getSex() == "M") {
            tdee = (int)(10 * user.getWeight() + 6.25 * user.getHeight()
                    - 5 * this.user.getAge() + 5);
        } else {
            tdee = (int)(10 * user.getWeight() + 6.25 * user.getHeight()
                    - 5 * user.getAge() - 161);
        }
        this.tdee *= user.getActivityLevel();
    }

    public void findTargetCalWithGoal() {
        int targetCal = tdee;
        if (goal == "Lose") {
            targetCal -= 500;
        } else if (goal == "Gain") {
            targetCal += 500;
        }
        setTargetCalories(targetCal);
    }

    public void setTargetMacros(int targetCalories) {
        int targetCarbs = (int) (0.5 * targetCalories);
        setTargetCarbs(targetCarbs);

        int targetProt = (int) (0.3 * targetCalories);
        setTargetProtein(targetProt);

        int targetFat = (int)(0.2 * targetCalories);
        setTargetFat(targetFat);
    }

    @Override
    protected void setTargetCalories(int targetCal) {
        this.targetCalories = targetCal;
    }

    @Override
    protected void setTargetProtein(int targetProtein) {
        this.targetProtein = targetProtein;
    }

    @Override
    protected void setTargetFat(int targetFat) {
        this.targetFat = targetFat;
    }

    @Override
    protected void setTargetCarbs(int targetCarbs) {
        this.targetCarbs = targetCarbs;
    }

}
