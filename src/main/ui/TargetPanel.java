package ui;

import model.DailyFoodLog;
import model.DailyIntake;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public abstract class TargetPanel {

    protected final RunCalCount runCalCount;
    protected User user;
    protected DailyIntake dailyIntake;
    protected DailyFoodLog dailyFoodLog;
    protected JPanel jp;
    protected String[] labels;

    public TargetPanel(RunCalCount runCalCount, String[] labels) {
        this.runCalCount = runCalCount;
        user = runCalCount.getUser();
        this.jp = new JPanel();
        this.labels = labels;

        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        dailyFoodLog = user.findDailyFoodLog(date);
        if (dailyFoodLog == null) {
            dailyFoodLog = new DailyFoodLog();
        }

        dailyIntake = new DailyIntake(user.getGoal());
    }

    public void setUpTargetPanel() {
        jp.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < labels.length; i++) {
            setUpLabels(labels[i],i,gc,jp);
            setUpTargets(labels[i],i,gc,jp);
        }
    }

    protected void setUpLabels(String labelName, int gridx, GridBagConstraints gc, JPanel jp) {
        JLabel label = new JLabel(labelName);

        if (labelName.equals(labels[1])) {
            label.setFont(new Font(label.getFont().getName(),Font.PLAIN,16));
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        gc.ipady = 20;
        gc.weightx = 0.5;
        gc.gridy = 0;
        gc.gridx = gridx;
        jp.add(label,gc);
    }

    protected void setUpTargets(String target, int gridx, GridBagConstraints gc, JPanel jp) {
        gc.gridy = 1;
        JLabel label;

        if (target.equals(labels[0])) {
            label = firstColumn();
        } else if (target.equals(labels[1])) {
            label = secondColumn();
        } else {
            label = thirdColumn();
        }

        label.setHorizontalAlignment(JLabel.CENTER);
        gc.weightx = 0.5;
        gc.gridx = gridx;
        gc.ipady = 20;
        jp.add(label,gc);
    }

    protected abstract JLabel firstColumn();

    protected abstract JLabel secondColumn();

    protected abstract JLabel thirdColumn();

    public JPanel getPanel() {
        return jp;
    }

}
