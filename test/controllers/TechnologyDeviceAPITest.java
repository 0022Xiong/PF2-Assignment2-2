package controllers;

import models.Manufacturer;
import models.SmartWatch;
import models.Tablet;
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
        @Test
        void getModelName() {
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());
        }

        @Test
        void getPrice() {
            assertEquals(129.99000549316406, populatedDevices.getTechnologyByIndex(0).getPrice());
            assertEquals(459.0, populatedDevices.getTechnologyByIndex(1).getPrice());
            assertEquals(678.0, populatedDevices.getTechnologyByIndex(2).getPrice());
            assertEquals(67.0, populatedDevices.getTechnologyByIndex(3).getPrice());
        }

//        void getManufacturer() {
//            assertEquals();
//            assertEquals();
//            assertEquals();
//            assertEquals();
//        }

        @Test
        void getID() {
            assertEquals("A123", populatedDevices.getTechnologyByIndex(0).getId());
            assertEquals("W1234", populatedDevices.getTechnologyByIndex(1).getId());
            assertEquals("T1223", populatedDevices.getTechnologyByIndex(2).getId());
            assertEquals("W3535", populatedDevices.getTechnologyByIndex(3).getId());
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

        }

        @Test
        void sortByPriceDescendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            emptyDevices.sortByPriceDescending();
        }
    }

}

