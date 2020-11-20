package ui;

import model.DailyFoodLog;
import model.Entry;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

/*
Represents daily food log user interface. Displays a custom list
of all Entries in user's food log.
 */
public class DailyFoodLogUI {

    private JPanel jp; //main panel

    private DefaultListModel<Entry> listModel; //list model for list; holds Entries
    private JList<Entry> list;                 //list of Entries
    private JScrollPane listScrollPane;        //scroll pane for list

    private DailyFoodLog foodLog;  //food log that this represents

    /*
     * EFFECTS: constructs and displays GUI of given foodLog;
     */
    public DailyFoodLogUI(DailyFoodLog foodLog) {
        this.foodLog = foodLog;
        this.jp = new JPanel();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up list and layout of panel; positions list in main panel
     */
    public void setUpFoodLogUI() {
        jp.setLayout(new BorderLayout());
        setUpList();
        jp.add(listScrollPane, BorderLayout.CENTER);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up list with existing entries and layout of main panel;
     *          positions list in main panel
     */
    private void setUpList() {
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        listScrollPane = new JScrollPane(list);
        list.setCellRenderer(new EntryRenderer());
        list.setVisibleRowCount(5);
        list.setFixedCellHeight(75);

        addExistingEntries();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds existing entries to list
     */
    private void addExistingEntries() {
        for (Entry entry : foodLog.getFoodLog()) {
            listModel.addElement(entry);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds given entry to list
     */
    public void addEntry(Entry entry) {
        listModel.addElement(entry);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes selected entry to list
     */
    public int removeEntry() {
        int index = list.getSelectedIndex();
        listModel.remove(index);
        return index;
    }

    //GETTERS AND SETTERS
    public JPanel getDailyFoodLogPanel() {
        return jp;
    }

}
