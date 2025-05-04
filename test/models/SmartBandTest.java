package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartBandTest {
    private SmartBand validSmartBand;
    private SmartBand invalidSmartBand;
    @BeforeEach
    void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validSmartBand = new SmartBand("Galaxy Watch", 230, manufacturer, "123456", "Silicon", "Small", true);
        invalidSmartBand = new SmartBand("Galaxy Watch version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Silicon", "TooLarge", false);
    }

    @AfterEach
    void tearDown() {
        validSmartBand = invalidSmartBand = null;
    }

    @Test
    void isHeartRateMonitor() {
        assertTrue(validSmartBand.isHeartRateMonitor());
        assertFalse(invalidSmartBand.isHeartRateMonitor());
    }

    @Test
    void setHeartRateMonitor() {
        validSmartBand.setHeartRateMonitor(false);
        assertFalse(validSmartBand.isHeartRateMonitor());
        validSmartBand.setHeartRateMonitor(true);
        assertTrue(validSmartBand.isHeartRateMonitor());

        invalidSmartBand.setHeartRateMonitor(true);
        assertTrue(invalidSmartBand.isHeartRateMonitor());
        invalidSmartBand.setHeartRateMonitor(false);
        assertFalse(invalidSmartBand.isHeartRateMonitor());
    }

    @Test
    void testToString() {
        String expected = "WearableDevice{material='Silicon', size='Small', modelName='Galaxy Watch', price=230.0, manufacturer=Manufacturer{manufacturerName='Samsung', numEmployees=333 employees}, id='123456'}, Heart Rate Monitor: Includes Heart Rate Monitor, Insurance Premium: 16.1, Internet Connection: Connected";
        assertEquals(expected, validSmartBand.toString());

        expected = "WearableDevice{material='Silicon', size='TooLarge', modelName='Galaxy Watch version 1 c.09462', price=20.0, manufacturer=Manufacturer{manufacturerName='ABCDEFGHIJKLMNOPQRST', numEmployees=1 employee}, id='1234567891'}, Heart Rate Monitor: No Heart Rate Monitor included, Insurance Premium: 1.4, Internet Connection: Connected";
        assertEquals(expected, invalidSmartBand.toString());
    }
}