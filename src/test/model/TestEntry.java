package model;

import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.junit.jupiter.api.Assertions.*;

class TestEntry {

    private Entry testEntry;

    @BeforeEach
    public void runBefore() {
        testEntry = new Entry("BREAKFAST","Pancakes",450);
    }

    @Test
    public void testConstructor() {
        assertEquals(testEntry.getMeal(), "BREAKFAST");
        assertEquals(testEntry.getFood(),"Pancakes");
        assertEquals(testEntry.getCalories(),450);
    }

    @Test
    public void testSetMacros() {
        assertEquals(testEntry.getProtein(),0);
        assertEquals(testEntry.getFat(),0);
        assertEquals(testEntry.getCarbs(),0);

        testEntry.setMacros(10,12,36);

        assertEquals(testEntry.getProtein(),10);
        assertEquals(testEntry.getFat(),12);
        assertEquals(testEntry.getCarbs(),36);
    }

    @Test
    public void testPrintEntry() {
        String entryResult = "BREAKFAST" + "\n" + "Food: Pancakes"
                + "\n" + "Calories: 450" + "\n" + "Protein: 0" + "\n" + "Fat: 0" + "\n" + "Carbs: 0";
        assertEquals(testEntry.printEntry(),entryResult);
    }

}