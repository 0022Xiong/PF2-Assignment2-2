package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WearableDeviceTest {

    private WearableDevice validWearableDevice;
    private WearableDevice invalidWearableDevice;

    void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validWearableDevice = new SmartWatch("Galaxy Watch", 230, manufacturer, "123456", "Silicon", "Small", "AMOLED");
        invalidWearableDevice = new SmartWatch("Galaxy Watch version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Silicon", "TooLarge", "InvalidDisplay");
    }
    void tearDown() {
        validWearableDevice = invalidWearableDevice = null;
    }

    @Test
    void getMaterial() {
        assertEquals("Silicon", validWearableDevice.getMaterial());
        assertEquals("Silicon", invalidWearableDevice.getMaterial());
    }

    @Test
    void setMaterial() {
        validWearableDevice.setMaterial("Plastic");
        assertEquals("Plastic", validWearableDevice.getMaterial());
        invalidWearableDevice.setMaterial("Steel");
        assertEquals("Steel", invalidWearableDevice.getMaterial());
    }
    @Test
    void getSize(){
        assertEquals("Small", validWearableDevice.getSize());
        assertEquals("TooLarge", invalidWearableDevice.getSize());
    }
    @Test
    void setSize(){
        validWearableDevice.setSize("Medium");
        assertEquals("Medium", validWearableDevice.getSize());
        invalidWearableDevice.setSize("Large");
        assertEquals("Large", invalidWearableDevice.getSize());
    }
    @Test
    void testToString() {
        String expected = "material: Silicon, size: Small";
        assertEquals(expected, validWearableDevice.toString());

        expected = "material: Silicon, size: TooLarge";
        assertEquals(expected, invalidWearableDevice.toString());
    }
}