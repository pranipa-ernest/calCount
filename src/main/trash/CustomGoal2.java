package trash;

import model.User;

public class CustomGoal2 extends Goal2 {

    public CustomGoal2(User user, String goal) {
        super(user, goal);
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
