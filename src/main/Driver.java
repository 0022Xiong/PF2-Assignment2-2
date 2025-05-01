package main;

import controllers.ManufacturerAPI;
import controllers.TechnologyDeviceAPI;

import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;

public class Driver {

    private TechnologyDeviceAPI technologyDeviceAPI;
    private ManufacturerAPI manufacturerAPI;

    public static void main(String[] args) throws Exception {
            new Driver().start();
        }

        public void start() throws Exception {
            //TODO - construct fields
            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
            technologyDeviceAPI = new TechnologyDeviceAPI(new File("technologyDevices.xml"));

            //TODO - load all data once the serializers are set up
            runMainMenu();
        }

    private int mainMenu() {
        System.out.println("""
                         -------Technology Store---------
                        |  1) Manufacturer CRUD MENU     |
                        |  2) Technology  CRUD MENU      |
                        |  3) Reports MENU               |
                        |--------------------------------|
                        |  4) Search Manufacturers       |
                        |  5) Search Technology Devices  |
                        |  6) Sort Technology Devices    |
                        |--------------------------------|
                        |  10) Save all                  |
                        |  11) Load all                  |
                        |--------------------------------|
                        |  0) Exit                       |
                         --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }
    //// search todo by different criteria i.e. look at the list methods and give options based on that.
// sort todo (and give a list of options - not a recurring menu thou)
        private void runMainMenu() throws Exception {
            int option = mainMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> runManufacturerMenu();
                    case 2 -> runTechAPIMenu();
                    case 3 -> runReportsMenu();
                    case 4 -> searchManufacturers();
                    case 5 -> searchTechnologyDevices();
                    case 6 -> sortTechnologyDevices();
                    case 10 -> saveAll();
                    case 11 -> loadAll();
                    //TODO - Add options
                    default ->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = mainMenu();
            }
            exitApp();
        }

        private void searchManufacturers() {
            Manufacturer manufacturerFind = getManufacturerByName();
            if(manufacturerFind == null) {
                System.out.println("No such manufacturer in the list");
            }
            else {
                System.out.println(manufacturerFind);
            }
        }

        private void searchTechnologyDevices() {
            Technology technologyFind = getTechnologyById();
            if(technologyFind == null) {
                System.out.println("No such technology device in the list");
            }
            else {
                System.out.println(technologyFind);
            }
        }

        private void sortTechnologyDevices() throws Exception {
            int option = ScannerInput.readNextInt("""
                    You are going to sort the technology by:
                    1) Ascending by price
                    2) Descending by price
                    ==>>""");
            while (option != 0) {
                switch (option) {
                    case 1 -> technologyDeviceAPI.sortByPriceAscending();
                    case 2 -> technologyDeviceAPI.sortByPriceDescending();
                    default ->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = techAPIMenu();
            }
            runMainMenu();
        }

        private void saveAll() throws Exception {
            manufacturerAPI.save();
            technologyDeviceAPI.save();
        }

        private void loadAll() throws Exception {
            manufacturerAPI.load();
            technologyDeviceAPI.load();
        }

        private void exitApp(){
            //TODO - save all the data entered
            System.out.println("Exiting....");
            System.exit(0);
        }

        //----------------------
        //  Manufacturer Menu Items
        //----------------------

    private int manufacturerMenu() {
        System.out.println("""
                --------Manufacturer Menu---------
               |  1) Add a manufacturer           |
               |  2) Delete a manufacturer        |
               |  3) Update manufacturer details  |
               |  4) List all manufacturers       |
               |  5) Find a manufacturer          |
               |  0) Return to main menu          |
                ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

        private void runManufacturerMenu() throws Exception {
            int option = manufacturerMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 5 -> findManufacturer();
//                    case 6 -> listByManufacturerName();
                    default -> System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = manufacturerMenu();
            }
            runMainMenu();
        }

        private void addManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

            if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
                System.out.println("Add successful");
            }
            else{
                System.out.println("Add not successful");
            }
        }

        private void deleteManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null){
                System.out.println("Delete successful");
            }
            else{
                System.out.println("Delete not successful");
            }
        }

        private void updateManufacturer(){
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                int numEmployees= ScannerInput.readNextInt("Please enter number of Employees: ");
                if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                    System.out.println("Number of Employees Updated");
                else
                    System.out.println("Number of Employees NOT Updated");
            }
            else
                System.out.println("Manufacturer name is NOT valid");
        }

        private void findManufacturer(){
            Manufacturer developer = getManufacturerByName();
            if (developer == null){
                System.out.println("No such manufacturer exists");
            }
            else{
                System.out.println(developer);
            }
        }

        //---------------------
        //  Tech Store Menu
        //---------------------

    private int techAPIMenu() {
        System.out.println(""" 
                -----Technology Store Menu-----
               | 1) Add a Tech Device           |
               | 2) Delete a Tech Device        |
               | 3) List all Tech Devices       |
               | 4) Update Tech Device          |
               | 0) Return to main menu         |
                ----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

        private void runTechAPIMenu() throws Exception {
            int option = techAPIMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addTechDevice();
                    case 2 -> deleteTechDevice();
                    case 3 -> System.out.println(technologyDeviceAPI.listAllTechnologyDevices());
                    case 4 -> updateTechDevice();
                    default -> System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = techAPIMenu();
            }
            runMainMenu();
        }

        private void addTechDevice() throws Exception {
            int option = ScannerInput.readNextInt("""
                    You are going to add:
                    1) Smart Band
                    2) Smart Watch
                    3) Tablet
                    ==>>""");
            while (option != 0) {
                switch (option) {
                    case 1 -> addSmartBand();
                    case 2 -> addSmartWatch();
                    case 3 -> addTablet();
                    default -> System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = techAPIMenu();
            }
            runMainMenu();
        }

            private void addSmartBand() {
                SmartBand smartBand = newSmartBand();
                if (technologyDeviceAPI.addTechnologyDevice(smartBand)){
                    System.out.println("Add successful");
                }
                else{
                    System.out.println("Add not successful");
                }
            }

            private void addSmartWatch() {
                SmartWatch smartWatch = newSmartWatch();
                if (technologyDeviceAPI.addTechnologyDevice(smartWatch)){
                    System.out.println("Add successful");
                }
                else{
                    System.out.println("Add not successful");
                }
            }

            private void addTablet() {
                Tablet tablet = newTablet();
                if (technologyDeviceAPI.addTechnologyDevice(tablet)){
                    System.out.println("Add successful");
                }
                else{
                    System.out.println("Add not successful");
                }
            }

        private void deleteTechDevice() throws Exception {
            System.out.println(technologyDeviceAPI.listAllTechnologyDevices());
            int option = ScannerInput.readNextInt("""
                    Choose a way to delete technology devices
                    1) By index
                    2) By ID
                    """);
            while (option != 0) {
                switch (option) {
                    case 1 -> deleteByIndex();
                    case 2 -> deleteById();
                    default -> System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = techAPIMenu();
            }
            runMainMenu();
        }

            private void deleteByIndex() {
                int deleteIndex = ScannerInput.readNextInt("Enter the index of the technology device you want to delete: ");
                if(technologyDeviceAPI.deleteTechnologyByIndex(deleteIndex) != null) {
                    System.out.println("Delete successful");
                }
                else {
                    System.out.println("Delete not successful");
                }
            }

            private void deleteById() {
                String deleteId = ScannerInput.readNextLine("Enter the ID of the technology device you want to delete: ");
                if(technologyDeviceAPI.deleteTechnologyById(deleteId) != null) {
                    System.out.println("Delete successful");
                }
                else {
                    System.out.println("Delete not successful");
                }
            }

        private void updateTechDevice() throws Exception {
            int option = ScannerInput.readNextInt("""
                    You are going to update:
                    1) Smart Band
                    2) Smart Watch
                    3) Tablet
                    """);
            while (option != 0) {
                switch (option) {
                    case 1 -> updateSmartBand();
                    case 2 -> updateSmartWatch();
                    case 3 -> updateTablet();
                    default -> System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = techAPIMenu();
            }
            runMainMenu();
        }

            private void updateSmartBand() {
                technologyDeviceAPI.listAllSmartBands();
                String id = ScannerInput.readNextLine("The ID of smart band you want to update is: ");
                if(technologyDeviceAPI.isValidId(id)){
                    SmartBand smartBandUpdate = newSmartBand();
                    if(technologyDeviceAPI.updateSmartBand(id, smartBandUpdate)){
                        System.out.println("Technology Updated");
                    }
                    else {
                        System.out.println("Technology NOT Updated");
                    }
                }
                else {
                    System.out.println("Invalid ID");
                }
            }

            private void updateSmartWatch() {
                technologyDeviceAPI.listAllSmartWatches();
                String id = ScannerInput.readNextLine("The ID of smart watch you want to update is: ");
                if(technologyDeviceAPI.isValidId(id)){
                    SmartWatch smartWatchUpdate = newSmartWatch();
                    if(technologyDeviceAPI.updateSmartWatch(id, smartWatchUpdate)){
                        System.out.println("Technology Updated");
                    }
                    else {
                        System.out.println("Technology NOT Updated");
                    }
                }
                else {
                    System.out.println("Invalid ID");
                }
            }

            private void updateTablet() {
                technologyDeviceAPI.listAllTablets();
                String id = ScannerInput.readNextLine("The ID of tablet you want to update is: ");
                if(technologyDeviceAPI.isValidId(id)){
                    Tablet tabletUpdate = newTablet();
                    if(technologyDeviceAPI.updateTablet(id, tabletUpdate)){
                        System.out.println("Technology Updated");
                    }
                    else {
                        System.out.println("Technology NOT Updated");
                    }
                }
                else {
                    System.out.println("Invalid ID");
                }
            }

    private int reportsMenu() {
        System.out.println(""" 
                --------Reports Menu ---------
               | 1) Manufacturers Overview    |
               | 2) Technology Overview       |
               | 0) Return to main menu       |
                 -----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runReportsMenu() throws Exception {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> runManufacturerReports();
                case 2-> runTechReportMenu();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
        runMainMenu();
    }


    private int manufacturerReportsMenu() {
        System.out.println(""" 
                ---------- Manufacturers Reports Menu  -------------
               | 1) List Manufacturers                              |
               | 2) List Manufacturers from a given manufacturer    |
               | 3) List Manufacturers by a given name              |
               | 0) Return to main menu                             |
                 ---------------------------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runManufacturerReports() throws Exception {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
                case 2-> System.out.println(getManufacturerByName());//?
                case 3-> System.out.println(listManufacturerByName());//?
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
        runMainMenu();
    }

        private String listAllByManufacturer() {
            String name = ScannerInput.readNextLine("Enter the name of manufacturer: ");
            return manufacturerAPI.listAllByManufacturerName(name);
        }

        private String listManufacturerByName() {
            String name = ScannerInput.readNextLine("Enter the name of manufacturer: ");
            return manufacturerAPI.listAllByManufacturerName(name);
        }

    private int techReportMenu() {
        System.out.println(""" 
                ---------- Technology Reports Menu  -------------
               | 1) List all Technology                             |
               | 2) List all SmartBands                             |
               | 3) List all Smart watch                            |
               | 4) List all Tables                                 |
               | 5) List all devices above a price                  |
               | 6) List all devices below a price                  |
               | 7) List all tablets by operating system            |
               | 8) List the top five most expensive smart watches  |
               | 0) Return to main menu                             |
                 ---------------------------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runTechReportMenu() throws Exception {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(technologyDeviceAPI.listAllTechnologyDevices());
                case 2 -> System.out.println(technologyDeviceAPI.listAllSmartBands());
                case 3 -> System.out.println(technologyDeviceAPI.listAllSmartWatches());
                case 4 -> System.out.println(technologyDeviceAPI.listAllTablets());
                case 5 -> System.out.println(listAllTechnologyBelowPrice());
                case 6 -> System.out.println(listAllTechnologyAbovePrice());
                case 7 -> System.out.println(listAllTabletsByOperatingSystem());
                case 8 -> System.out.println(technologyDeviceAPI.topFiveMostExpensiveSmartWatch());
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = techReportMenu();
        }
        runMainMenu();
    }

        private String listAllTechnologyBelowPrice() {
            int price = ScannerInput.readNextInt("Enter the price: ");
            return technologyDeviceAPI.listAllTechnologyBelowPrice(price);
        }

        private String listAllTechnologyAbovePrice() {
            int price = ScannerInput.readNextInt("Enter the price: ");
            return technologyDeviceAPI.listAllTechnologyAbovePrice(price);
        }

        private String listAllTabletsByOperatingSystem() {
            String os = ScannerInput.readNextLine("Enter the operating system: ");
            return technologyDeviceAPI.listAllTabletsByOperatingSystem(os);
        }

//todo update methods counting methods
    private Manufacturer newManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerNumEmployees);
        return manufacturer;
    }

    private SmartBand newSmartBand() {
        String modelName = ScannerInput.readNextLine("Enter name: ");
        double price = ScannerInput.readNextDouble("Enter price: ");
        Manufacturer manufacturer = newManufacturer();
        String id = ScannerInput.readNextLine("Enter ID: ");
        String material = ScannerInput.readNextLine("Enter material: ");
        String size = ScannerInput.readNextLine("Enter size: ");
        boolean heartRateMonitor = Utilities.YNtoBoolean(ScannerInput.readNextChar("Have heartRateMonitor? (y/n): "));
        SmartBand smartBand = new SmartBand(modelName, price, manufacturer, id, size, material, heartRateMonitor);
        return smartBand;
    }

    private SmartWatch newSmartWatch() {
        String modelName = ScannerInput.readNextLine("Enter name: ");
        double price = ScannerInput.readNextDouble("Enter price: ");
        Manufacturer manufacturer = newManufacturer();
        String id = ScannerInput.readNextLine("Enter ID: ");

        String material = ScannerInput.readNextLine("Enter material: ");
        String size = ScannerInput.readNextLine("Enter size: ");
        String displayType = ScannerInput.readNextLine("Enter displayType: ");
        SmartWatch smartWatch = new SmartWatch(modelName, price, manufacturer, id, size, material, displayType);
        return smartWatch;
    }

    private Tablet newTablet() {
        String modelName = ScannerInput.readNextLine("Enter name: ");
        double price = ScannerInput.readNextDouble("Enter price: ");
        Manufacturer manufacturer = newManufacturer();
        String id = ScannerInput.readNextLine("Enter ID: ");
        String processor = ScannerInput.readNextLine("Enter processor: ");
        int storage = ScannerInput.readNextInt("Enter storage: ");
        String operatingSystem = ScannerInput.readNextLine("Enter OS: ");
        Tablet tablet = new Tablet(modelName, price, manufacturer, id, processor, storage, operatingSystem);
        return tablet;
    }

        //---------------------
        //  General Menu Items
        //---------------------

//TODO - write all the methods that are called from your menu

        //---------------------
        //  Helper Methods
        //---------------------

//TODO- write any helper methods that are required


    private Manufacturer getManufacturerByName(){
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
        if (manufacturerAPI.isValidManufacturer(manufacturerName)){
            return manufacturerAPI.getManufacturerByName(manufacturerName);
        }
        else{
            return null;
        }
    }

    private Technology getTechnologyById(){
        String technologyId = ScannerInput.readNextLine("Please enter the ID of technology: ");
        if (technologyDeviceAPI.isValidId(technologyId)){
            return technologyDeviceAPI.getTechnologyDeviceById(technologyId);
        }
        else{
            return null;
        }
    }

}
