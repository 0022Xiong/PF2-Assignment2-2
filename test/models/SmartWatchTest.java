package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchTest {
    private SmartWatch validSmartWatch;
    private SmartWatch invalidSmartWatch;
    @BeforeEach
    void setUp() { Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validSmartWatch = new SmartWatch("Galaxy Tab S7", 799.99, manufacturer, "123456", "12345678", "iron", "Android");
        invalidSmartWatch = new SmartWatch("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "12345678901234567890123", "iron12345678901234567890", "Android v1");
    }
    @Test
    public void testValidDisplayType(){
        assertEquals("LCD", validSmartWatch.getDisplayType());
    }
    @Test
    public void testInvalidDisplaySystem() {
        assertEquals("LCDD", invalidSmartWatch.getDisplayType()); // Invalid OS should default to "Windows OS"
    }
    @AfterEach
    void tearDown() {validSmartWatch = invalidSmartWatch=null;}

    @Test
    void setValidDisplay() {
        assertEquals("LCD", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("AMOLED");
        assertEquals("AMOLED", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("LCD");
        assertEquals("LCD", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("LED");
        assertEquals("LED", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("TFT");
        assertEquals("TFT", validSmartWatch.getDisplayType());
    }
    @Test
    void InvalidDisplaySystem(){
        assertEquals("LCD", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("AMOLED 1");
        assertEquals("LCD", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("LCD 1");
        assertEquals("LCD", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("LED 1");
        assertEquals("LED", validSmartWatch.getDisplayType());
        validSmartWatch.setDisplayType("TFT 1");
        assertEquals("LED", validSmartWatch.getDisplayType());
    }

    @Test
    void testToString() {
        String expected = "Type: AMOLED";
        assertEquals(expected, validSmartWatch.toString());

        expected = "Type: LCD";
        assertEquals(expected, invalidSmartWatch.toString());
    }
}