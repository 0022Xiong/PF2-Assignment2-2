package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WearableDeviceTest {

    private SmartBand validSmartBand, invalidSmartBand;
    private SmartWatch validSmartWatch,invalidSmartWatch;

    void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validSmartBand = new SmartBand("Galaxy Tab S7", 799.99, manufacturer, "123456", "123456789", "12345678901234567890", true);
        invalidSmartBand = new SmartBand("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", "123456789012345678901234567890",true);
        validSmartWatch = new SmartWatch("Galaxy Tab S7",799.99, manufacturer,"123456", "123456789","12345678901234567890", "LCD");
        invalidSmartWatch = new SmartWatch("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920","123456789012345678901234567890","DSSL");
    }
    void tearDown() {
        validSmartBand=invalidSmartBand=null;
        validSmartWatch=invalidSmartWatch=null;
    }

    @Test
    void getMaterial() {
        assertEquals("12345678901234567890", validSmartWatch.getMaterial());
        assertEquals("123456789012345678901234567890", invalidSmartWatch.getMaterial());
        assertEquals("12345678901234567890", validSmartBand.getMaterial());
        assertEquals("123456789012345678901234567890", invalidSmartBand.getMaterial());
    }

    @Test
    void setMaterial() {
        validSmartBand.setMaterial("12345678901234567890");
        assertEquals("12345678901234567890", validSmartBand.getMaterial());
        validSmartBand.setMaterial("123456789012345678901234567890");
        assertEquals("12345678901234567890", validSmartBand.getMaterial());
        validSmartWatch.setMaterial("12345678901234567890");
        assertEquals("12345678901234567890", validSmartWatch.getMaterial());
        validSmartWatch.setMaterial("123456789012345678901234567890");
        assertEquals("12345678901234567890", validSmartWatch.getMaterial());
    }
    @Test
    void getSize(){
        assertEquals("123456789", validSmartWatch.getSize());
        assertEquals("Snapdragon 8655678920", invalidSmartWatch.getSize());
        assertEquals("123456789", validSmartBand.getSize());
        assertEquals("Snapdragon 8655678920", invalidSmartBand.getSize());
    }
    @Test
    void setSize(){
        validSmartBand.setSize("123456789");
        assertEquals("123456789", validSmartBand.getSize());
        validSmartBand.setSize("123456789012345678901234567890");
        assertEquals("123456789", validSmartBand.getSize());
        validSmartWatch.setSize("123456789");
        assertEquals("123456789", validSmartWatch.getSize());
        validSmartWatch.setSize("123456789012345678901234567890");
        assertEquals("123456789", validSmartWatch.getSize());
    }
    @Test
    void testToString() {
        String expected = "Material: 12345678901234567890,Size: 123456789";
        assertTrue( validSmartBand.toString().contains(expected));
        expected = "Material: 123456789012345678901234567890,Size: 123456789012345678901234567890";
        assertTrue( invalidSmartBand.toString().contains(expected));

        String expected2 = "Material: 12345678901234567890,Size: 123456789";
        assertTrue( validSmartWatch.toString().contains(expected));
        expected2 = "Material: 123456789012345678901234567890,Size: 123456789012345678901234567890";
        assertTrue( invalidSmartWatch.toString().contains(expected));
    }
}