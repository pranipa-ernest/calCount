package ui;

import model.DailyFoodLog;
import model.Entry;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class DailyFoodLogUI {

    private JPanel jp;

    private DefaultListModel<Entry> listModel;
    private JList<Entry> list;
    private JScrollPane listScrollPane;

    private User user;
    private DailyFoodLog foodLog;

    public DailyFoodLogUI(DailyFoodLog foodLog) {
        this.foodLog = foodLog;
        this.jp = new JPanel();
    }

    public void setUpFoodLogUI() {
        jp.setLayout(new BorderLayout());
        setUpList();
        jp.add(listScrollPane, BorderLayout.CENTER);
    }

    private void setUpList() {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        listScrollPane = new JScrollPane(list);
        list.setCellRenderer(new EntryRenderer());
        list.setVisibleRowCount(5);
        list.setFixedCellHeight(75);

        addExistingEntries();
    }

    private void addExistingEntries() {
        for (Entry entry : foodLog.getFoodLog()) {
            addEntry(entry);
        }
    }

    public void addEntry(Entry entry) {
        listModel.addElement(entry);
    }

    public int removeEntry() {
        int index = list.getSelectedIndex();
        listModel.remove(index);
        return index;
    }

    public JPanel getDailyFoodLogPanel() {
        return jp;
    }

}
