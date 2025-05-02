package controllers;

import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyDeviceAPITest {
    private Manufacturer apple = new Manufacturer("Apple", 1020);
    private Manufacturer samsung = new Manufacturer("Samsung", 1200);
    private Manufacturer hitachi = new Manufacturer("Hitachi", 1325);
    private Manufacturer tesla = new Manufacturer("Tesla", 3245);

    private TechnologyDeviceAPI populatedDevices = new TechnologyDeviceAPI(new File("technologyDevicesTest.xml"));
    private TechnologyDeviceAPI emptyDevices = new TechnologyDeviceAPI(new File("technologyDevicesemptyTest.xml"));

    @BeforeEach
    void setUp() {
        try {
            populatedDevices.load();
            emptyDevices.load();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    @AfterEach
    void tearDown() {
        populatedDevices = emptyDevices = null;
        apple = samsung = hitachi = tesla = null;
    }
    @Nested
    class GettersAndSetters {
        //Index
        @Test
        void testGetModelNameByIndex() {
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());
        }

        @Test
        void testGetPriceByIndex() {
            assertEquals(129.99000549316406, populatedDevices.getTechnologyByIndex(0).getPrice());
            assertEquals(459.0, populatedDevices.getTechnologyByIndex(1).getPrice());
            assertEquals(678.0, populatedDevices.getTechnologyByIndex(2).getPrice());
            assertEquals(67.0, populatedDevices.getTechnologyByIndex(3).getPrice());
        }

        @Test
        void testGetManufacturerByIndex() {
            assertEquals(samsung, populatedDevices.getTechnologyByIndex(0).getManufacturer());
            assertEquals(samsung, populatedDevices.getTechnologyByIndex(1).getManufacturer());
            assertEquals(samsung, populatedDevices.getTechnologyByIndex(2).getManufacturer());
            assertEquals(samsung, populatedDevices.getTechnologyByIndex(3).getManufacturer());
        }

        @Test
        void testGetIDByIndex() {
            assertEquals("A123", populatedDevices.getTechnologyByIndex(0).getId());
            assertEquals("W1234", populatedDevices.getTechnologyByIndex(1).getId());
            assertEquals("T1223", populatedDevices.getTechnologyByIndex(2).getId());
            assertEquals("W3535", populatedDevices.getTechnologyByIndex(3).getId());
        }

        @Test
        void testGetSizeFromWearableDeviceByIndex() {
            assertEquals("large", ((WearableDevice) populatedDevices.getTechnologyByIndex(0)).getSize());
            assertEquals("large", ((WearableDevice) populatedDevices.getTechnologyByIndex(1)).getSize());
            assertEquals("small", ((WearableDevice) populatedDevices.getTechnologyByIndex(3)).getSize());
        }

        @Test
        void testGetMaterialFromWearableDeviceByIndex() {
            assertEquals("plastic", ((WearableDevice) populatedDevices.getTechnologyByIndex(0)).getMaterial());
            assertEquals("plastic", ((WearableDevice) populatedDevices.getTechnologyByIndex(1)).getMaterial());
            assertEquals("plastic", ((WearableDevice) populatedDevices.getTechnologyByIndex(3)).getMaterial());
        }

        @Test
        void testGetProcessorFromComputingDeviceByIndex() {
            assertEquals("Android", ((ComputingDevice) populatedDevices.getTechnologyByIndex(2)).getProcessor());
        }

        @Test
        void testGetStorageFromComputingDeviceByIndex() {
            assertEquals(64, ((ComputingDevice) populatedDevices.getTechnologyByIndex(2)).getStorage());
        }

        @Test
        void testGetHeartRateMonitorFromSmartBandByIndex() {
            assertTrue(((SmartBand) populatedDevices.getTechnologyByIndex(0)).isHeartRateMonitor());
        }

        @Test
        void testGetDisplayTypeFromSmartWatchByIndex() {
            assertEquals("LCD", ((SmartWatch) populatedDevices.getTechnologyByIndex(1)).getDisplayType());
            assertEquals("LCD", ((SmartWatch) populatedDevices.getTechnologyByIndex(3)).getDisplayType());
        }

        @Test
        void testGetOSFromTabletBuIndex() {
            assertEquals("Android", ((Tablet) populatedDevices.getTechnologyByIndex(2)).getOperatingSystem());
        }

        @Test
        void testGetTechnologyByIndexInvalid() {
            assertNull(populatedDevices.getTechnologyByIndex(5));//Beyond the index
        }
        //ID
        @Test
        void testGetModelNameById() {
            assertEquals("smart watch1", populatedDevices.getTechnologyDeviceById("A123").getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyDeviceById("W1234").getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyDeviceById("T1223").getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyDeviceById("W3535").getModelName());
        }

        @Test
        void testGetPriceById() {
            assertEquals(129.99000549316406, populatedDevices.getTechnologyDeviceById("A123").getPrice());
            assertEquals(459.0, populatedDevices.getTechnologyDeviceById("W1234").getPrice());
            assertEquals(678.0, populatedDevices.getTechnologyDeviceById("T1223").getPrice());
            assertEquals(67.0, populatedDevices.getTechnologyDeviceById("W3535").getPrice());
        }

        @Test
        void testGetManufacturerById() {
            assertEquals(samsung, populatedDevices.getTechnologyDeviceById("A123").getManufacturer());
            assertEquals(samsung, populatedDevices.getTechnologyDeviceById("W1234").getManufacturer());
            assertEquals(samsung, populatedDevices.getTechnologyDeviceById("T1223").getManufacturer());
            assertEquals(samsung, populatedDevices.getTechnologyDeviceById("W3535").getManufacturer());
        }

        @Test
        void testGetIDById() {
            assertEquals("A123", populatedDevices.getTechnologyDeviceById("A123").getId());
            assertEquals("W1234", populatedDevices.getTechnologyDeviceById("W1234").getId());
            assertEquals("T1223", populatedDevices.getTechnologyDeviceById("T1223").getId());
            assertEquals("W3535", populatedDevices.getTechnologyDeviceById("W3535").getId());
        }

        @Test
        void testGetSizeFromWearableDeviceById() {
            assertEquals("large", ((WearableDevice) populatedDevices.getTechnologyDeviceById("A123")).getSize());
            assertEquals("large", ((WearableDevice) populatedDevices.getTechnologyDeviceById("W1234")).getSize());
            assertEquals("small", ((WearableDevice) populatedDevices.getTechnologyDeviceById("W3535")).getSize());
        }

        @Test
        void testGetMaterialFromWearableDeviceById() {
            assertEquals("plastic", ((WearableDevice) populatedDevices.getTechnologyDeviceById("A123")).getMaterial());
            assertEquals("plastic", ((WearableDevice) populatedDevices.getTechnologyDeviceById("W1234")).getMaterial());
            assertEquals("plastic", ((WearableDevice) populatedDevices.getTechnologyDeviceById("W3535")).getMaterial());
        }

        @Test
        void testGetProcessorFromComputingDeviceById() {
            assertEquals("Android", ((ComputingDevice) populatedDevices.getTechnologyDeviceById("T1223")).getProcessor());
        }

        @Test
        void testGetStorageFromComputingDeviceById() {
            assertEquals(64, ((ComputingDevice) populatedDevices.getTechnologyDeviceById("T1223")).getStorage());
        }

        @Test
        void testGetHeartRateMonitorFromSmartBandById() {
            assertTrue(((SmartBand) populatedDevices.getTechnologyDeviceById("A123")).isHeartRateMonitor());
        }

        @Test
        void testGetDisplayTypeFromSmartWatchById() {
            assertEquals("LCD", ((SmartWatch) populatedDevices.getTechnologyDeviceById("W1234")).getDisplayType());
            assertEquals("LCD", ((SmartWatch) populatedDevices.getTechnologyDeviceById("W3535")).getDisplayType());
        }

        @Test
        void testGetOSFromTabletById() {
            assertEquals("Android", ((Tablet) populatedDevices.getTechnologyDeviceById("T1223")).getOperatingSystem());
        }

        @Test
        void testGetTechnologyDeviceByIdInvalid() {
            assertNull(populatedDevices.getTechnologyDeviceById("1234"));
        }

    }

    @Nested
    class CRUDMethods {
        @Test
        void addNewTechnologyDevicetoEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab = new Tablet("Galaxy Tab S7", 799.99, tesla, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab2 = new Tablet("Galaxy Tab S8", 799.99, samsung, "123457", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab2);
            assertEquals(2, emptyDevices.numberOfTechnologyDevices());

        }
        @Test
        void addNewTechnologySameId() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab = new Tablet("Galaxy Tab S7", 799.99, tesla, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab2 = new Tablet("Galaxy Tab S8", 799.99, samsung, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab2);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());

        }
        //getters are tested above

        @Test
        void testUpdateTablet() {
            Tablet updateTablet0 = new Tablet("ipad 10", 1112.0, apple, "K123", "amd", 128, "iPad");
            assertTrue(populatedDevices.updateTablet("T1223", updateTablet0));//normal

            Tablet updateTablet1 = new Tablet("ipad 10", 11.0, apple, "K123", "amd", 128, "iPad");
            populatedDevices.updateTablet("K123", updateTablet1);
            assertNotEquals(11.0, populatedDevices.getTechnologyDeviceById("K123").getPrice());//price below 20

            Tablet updateTablet2 = new Tablet("ipad 10", 1112.0, apple, "12345678901", "amd", 128, "iPad");
            populatedDevices.updateTablet("K123", updateTablet2);
            assertNotEquals("12345678901", populatedDevices.getTechnologyDeviceById("1234567890").getId());//id > 10

            Tablet updateTablet3 = new Tablet("ipad 10", 1112.0, apple, "K123", "amd", 127, "iPad");
            populatedDevices.updateTablet("1234567890", updateTablet3);
            assertNotEquals(127, ((Tablet)populatedDevices.getTechnologyDeviceById("K123")).getStorage());//Storage invalid

            Tablet updateTablet4 = new Tablet("ipad 10", 1112.0, apple, "K123", "amd", 128, "iPa");
            populatedDevices.updateTablet("K123", updateTablet4);
            assertNotEquals("iPa", ((Tablet)populatedDevices.getTechnologyDeviceById("K123")).getOperatingSystem());//OS invalid

            Tablet updateTablet5 = new Tablet("1234567890123456789012345678901", 1112.0, apple, "K123", "amd", 128, "iPa");
            populatedDevices.updateTablet("K123", updateTablet4);
            assertNotEquals("1234567890123456789012345678901", populatedDevices.getTechnologyDeviceById("K123").getModelName());//name > 30
        }

        @Test
        void testUpdateSmartWatch() {
            SmartWatch updateSmartWatch0 = new SmartWatch("watch0", 200.0, apple, "A114", "small", "alloy", "LCD");
            assertTrue(populatedDevices.updateSmartWatch("W1234", updateSmartWatch0));//normal

            SmartWatch updateSmartWatch1 = new SmartWatch("1234567890123456789012345678901", 200, apple, "A114", "small", "alloy", "LCD");
            populatedDevices.updateSmartWatch("A114", updateSmartWatch1);
            assertEquals("123456789012345678901234567890", populatedDevices.getTechnologyDeviceById("A114").getModelName());//name > 30

            SmartWatch updateSmartWatch2 = new SmartWatch("watch0", 2.0, apple, "A114", "small", "alloy", "LCD");
            populatedDevices.updateSmartWatch("A114", updateSmartWatch2);
            assertEquals(20.0, populatedDevices.getTechnologyDeviceById("A114").getPrice());//price < 20

            SmartWatch updateSmartWatch3 = new SmartWatch("watch0", 200.0, apple, "12345678901", "small", "alloy", "LCD");
            populatedDevices.updateSmartWatch("A114", updateSmartWatch3);
            assertEquals("1234567890", populatedDevices.getTechnologyDeviceById("1234567890").getId());//id > 10

            SmartWatch updateSmartWatch4 = new SmartWatch("watch0", 200.0, apple, "A114", "12345678901", "alloy", "LCD");
            populatedDevices.updateSmartWatch("1234567890", updateSmartWatch4);
            assertEquals("1234567890", ((SmartWatch)populatedDevices.getTechnologyDeviceById("A114")).getSize());//size > 10

            SmartWatch updateSmartWatch5 = new SmartWatch("watch0", 200.0, apple, "A114", "small", "123456789012345678901", "LCD");
            populatedDevices.updateSmartWatch("A114", updateSmartWatch5);
            assertEquals("12345678901234567890", ((SmartWatch)populatedDevices.getTechnologyDeviceById("A114")).getMaterial());//material > 20

            SmartWatch updateSmartWatch6 = new SmartWatch("watch0", 2.0, apple, "A114", "small", "alloy", "LC");
            populatedDevices.updateSmartWatch("A114", updateSmartWatch1);
            assertEquals("LCD", ((SmartWatch)populatedDevices.getTechnologyDeviceById("A114")).getDisplayType());//invalid display
        }

        @Test
        void testUpdateSmartBand() {
            SmartBand updateSmartBand0 = new SmartBand("band0", 100.0, apple, "Q11", "small", "alloy", false);
            assertTrue(populatedDevices.updateSmartBand("A123", updateSmartBand0));

            SmartBand smartBand1 = new SmartBand("1234567890123456789012345678901", 100.0, apple, "Q11", "small", "alloy", false);
            populatedDevices.updateSmartBand("Q11", smartBand1);
            assertEquals("123456789012345678901234567890", populatedDevices.getTechnologyDeviceById("Q11").getModelName());

            SmartBand smartBand2 = new SmartBand("band0", 10.0, apple, "Q11", "small", "alloy", false);
            populatedDevices.updateSmartBand("Q11", smartBand2);
            assertEquals(20, populatedDevices.getTechnologyDeviceById("Q11").getPrice());

            SmartBand smartBand3 = new SmartBand("band0", 100.0, apple, "12345678901", "small", "alloy", false);
            populatedDevices.updateSmartBand("Q11", smartBand3);
            assertEquals("1234567890", populatedDevices.getTechnologyDeviceById("1234567890").getId());

            SmartBand smartBand4 = new SmartBand("band0", 100.0, apple, "Q11", "12345678901", "alloy", false);
            populatedDevices.updateSmartBand("1234567890", smartBand4);
            assertEquals("1234567890", ((SmartBand)populatedDevices.getTechnologyDeviceById("Q11")).getSize());

            SmartBand smartBand5 = new SmartBand("band0", 100.0, apple, "Q11", "small", "123456789012345678901", false);
            populatedDevices.updateSmartBand("Q11", smartBand5);
            assertEquals("12345678901234567890", ((SmartBand)populatedDevices.getTechnologyDeviceById("Q11")).getMaterial());

            SmartBand smartBand6 = new SmartBand("band0", 100.0, apple, "Q11", "small", "alloy", true);
            populatedDevices.updateSmartBand("Q11", smartBand6);
            assertTrue(((SmartBand) populatedDevices.getTechnologyDeviceById("Q11")).isHeartRateMonitor());
        }

        @Test
        void deleteByIndex() {
            assertNull(emptyDevices.deleteTechnologyByIndex(0));//empty

            assertEquals("A123", populatedDevices.getTechnologyByIndex(0).getId());//noEmpty

            populatedDevices.deleteTechnologyByIndex(0);
            assertEquals("W1234", populatedDevices.getTechnologyByIndex(0).getId());
        }

        @Test
        void deleteById() {
            assertNull(emptyDevices.deleteTechnologyById("123"));

            assertEquals("A123", populatedDevices.getTechnologyDeviceById("A123").getId());

            populatedDevices.deleteTechnologyById("A123");
            assertNull(populatedDevices.getTechnologyDeviceById("A123"));
        }

    }

    @Nested
    class ListingMethods {

        @Test
        void listAllReturnsNoTechnologyStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            assertTrue(emptyDevices.listAllTechnologyDevices().toLowerCase().contains("no technology devices"));
        }

        @Test
        void listAllReturnsTechnologyDevicesStoredWhenArrayListHasTechnologyDevicesStored() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            String populatedDeviceStr = populatedDevices.listAllTechnologyDevices();
            //checks for objects in the string

            assertTrue(populatedDeviceStr.contains("ID: A123"));
            assertTrue(populatedDeviceStr.contains("ID: W1234"));
            assertTrue(populatedDeviceStr.contains("ID: T1223"));
            assertTrue(populatedDeviceStr.contains("ID: W3535"));

          
        }

        @Test
        void listBySelectedYearReturnsNoTechnologyDevicesWhenNoneExistForEnteredPrice() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            String populatedDeviceStr = populatedDevices.listAllTechnologyAbovePrice(10000.99);
            assertTrue(populatedDeviceStr.contains("No technology more expensive than"));
        }


    }

    @Nested
    class ReportingMethods {



    }

    @Nested
    class SearchingMethods {

    }

    @Nested
    class SortingMethods {

        @Test
        void sortByCostDescendingReOrdersList() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            //checks the order of the objects in the list

            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());

            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());
            populatedDevices.sortByPriceDescending();

            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(2).getModelName());

            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());

            populatedDevices.sortByPriceAscending();
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(3).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(0).getModelName());

        }

        @Test
        void sortByPriceDescendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            emptyDevices.sortByPriceDescending();
        }
    }

}

