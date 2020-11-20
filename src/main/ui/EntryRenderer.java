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

public class EntryRenderer extends JTextPane implements ListCellRenderer<Entry> {


    @Override
    public Component getListCellRendererComponent(JList<? extends Entry> list,
                                                  Entry value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {


        styleMeal(value);
        SimpleAttributeSet attributeSet;

        attributeSet = new SimpleAttributeSet();
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

    protected void styleMeal(Entry value) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        setCharacterAttributes(attributeSet, true);

        String meal = value.getMeal();
        setText(meal);
    }
}
