package test;

import model.DailyFoodLog;
import model.TotalFoodLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        testLog.addEntry(testDailyLog);

        assertEquals(1,testLog.getSize());
        assertEquals(testDailyLog,testLog.getEntry(0));
    }

    @Test
    public void testManyEntries() {
        for (int i = 0; i < 100; i++) {
            testLog.addEntry(testDailyLog);
        }
        assertEquals(100,testLog.getSize());
        for (int i = 0; i < 100; i++) {
            assertEquals(testDailyLog,testLog.getEntry(i));
        }
    }
}
