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
    private String date;

    @BeforeEach
    public void runBefore() {
        testEntry = new Entry("BREAKFAST","Pancakes",450);
        LocalDate localDate = LocalDate.now();
        date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    @Test
    public void testConstructor() {
        assertEquals(testEntry.getMeal(), "BREAKFAST");
        assertEquals(testEntry.getFood(),"Pancakes");
        assertEquals(testEntry.getCalories(),450);
        assertEquals(testEntry.getDate(),date);
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
                + "\n" + "Calories: 450";
        assertEquals(testEntry.printEntry(),entryResult);
    }

    @Test
    public void testSetDate() {
        String testDate = "June 30, 2009";
        testEntry.setDate(testDate);
        assertEquals("June 30, 2009",testEntry.getDate());
    }
}