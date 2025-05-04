package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WearableDeviceTest {

    private WearableDevice validWearableDevice;
    private WearableDevice invalidWearableDevice;
    @BeforeEach
    void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validWearableDevice = new SmartWatch("Galaxy Watch", 230, manufacturer, "123456", "1234567890", "12345678901234567890", "AMOLED");
        invalidWearableDevice = new SmartWatch("Galaxy Watch version 1 c.09462b", 19, invalidManufacturer, "12345678910", "12345678901234567890", "123456789012345678901234567890", "AMOLED");
    }
    @AfterEach
    void tearDown() {
        validWearableDevice = invalidWearableDevice = null;
    }

    @Test
    void getMaterial() {
        assertEquals("12345678901234567890", validWearableDevice.getMaterial());
        assertEquals("12345678901234567890", invalidWearableDevice.getMaterial());
    }

    @Test
    void setMaterial() {
        validWearableDevice.setMaterial("12345678901234567890");
        assertEquals("12345678901234567890", validWearableDevice.getMaterial());
        invalidWearableDevice.setMaterial("123456789012345678901234567890");
        assertEquals("12345678901234567890", invalidWearableDevice.getMaterial());
    }
    @Test
    void getSize(){
        assertEquals("1234567890", validWearableDevice.getSize());
        assertEquals("1234567890", invalidWearableDevice.getSize());
    }
    @Test
    void setSize(){
        validWearableDevice.setSize("1234567890");
        assertEquals("1234567890", validWearableDevice.getSize());
        invalidWearableDevice.setSize("12345678901234567890");
        assertEquals("1234567890", invalidWearableDevice.getSize());
    }
    @Test
    void testToString() {
        String expected = "material: 12345678901234567890, size: 1234567890";
        assertEquals(expected, validWearableDevice.toString());

        expected = "material: 123456789012345678901234567890, size: 12345678901234567890";
        assertEquals(expected, invalidWearableDevice.toString());
    }
}