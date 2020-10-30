package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTotalFoodLog {

    private TotalFoodLog testLog;
    private DailyFoodLog testDailyLog;

    @BeforeEach
    public void setUp() {
        testLog = new TotalFoodLog();
        testDailyLog = new DailyFoodLog();
    }

    @Test
    public void testConstructor() {
        assertEquals(0,testLog.getSize());
    }

    @Test
    public void testAddOneEntry() {
        testLog.addLog(testDailyLog);

        assertEquals(1,testLog.getSize());
        assertEquals(testDailyLog,testLog.getLog(0));
    }

    @Test
    public void testManyEntries() {
        for (int i = 0; i < 100; i++) {
            testLog.addLog(testDailyLog);
        }
        assertEquals(100,testLog.getSize());
        for (int i = 0; i < 100; i++) {
            assertEquals(testDailyLog,testLog.getLog(i));
        }
    }

    @Test
    public void findFoodLogDoesNotExist() {
        testLog.addLog(testDailyLog);
        assertEquals(null,testLog.findLog("October 26, 1980"));
    }

    @Test
    public void findFoodLogDoesExist() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

        testLog.addLog(testDailyLog);
        assertEquals(testDailyLog,testLog.findLog(date));
    }

}
