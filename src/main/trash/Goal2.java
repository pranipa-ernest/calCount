package trash;

import model.User;

public abstract class Goal2 {

    protected User user;
    protected String goal;

    protected int targetCalories;
    protected int targetProtein;
    protected int targetFat;
    protected int targetCarbs;

    public Goal2(User user, String goal) {
        this.user = user;
        this.goal = goal;
    }

    protected abstract void setTargetCalories(int targetCal);

    protected abstract void setTargetProtein(int targetProtein);

    protected abstract void setTargetFat(int targetFat);

    protected abstract void setTargetCarbs(int targetCarbs);

    public int getTargetCalories() {
        return targetCalories;
    }

    public int getTargetProtein() {
        return targetProtein;
    }

    public int getTargetFat() {
        return targetFat;
    }

    public int getTargetCarbs() {
        return targetCarbs;
    }

    public User getUser() {
        return user;
    }

    public String getGoal() {
        return goal;
    }


}
