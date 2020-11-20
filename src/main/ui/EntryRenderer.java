package ui;

import model.DailyFoodLog;
import model.Entry;
import model.User;
import ui.RunCalCount;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/*
Represents styled cell of each Entry in the DailyFoodLogUI
 */
public class EntryRenderer extends JTextPane implements ListCellRenderer<Entry> {

    /*
     * EFFECTS: adds styling to each cell of DailyFoodLogUI (i.e. meal
     *          is in bold, line space between each value, etc.). If cell
     *          is selected, colours background of cell
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Entry> list,
                                                  Entry value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        styleMeal(value);
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        setCharacterAttributes(attributeSet, true);
        String food = value.getFood();
        String calories = String.valueOf(value.getCalories());

        Document doc = getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "\n" + food + "\n" + calories, attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEditable(false);
        return this;
    }

    /*
     * EFFECTS: adds styling to meal value of each Entry.
     *          Makes font of meal value bold.
     */
    protected void styleMeal(Entry value) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        setCharacterAttributes(attributeSet, true);

        String meal = value.getMeal();
        setText(meal);
    }
}
