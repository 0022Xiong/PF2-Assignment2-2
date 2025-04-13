package main;

import controllers.ManufacturerAPI;
import controllers.TechnologyDeviceAPI;

import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;

public class Driver {



    private TechnologyDeviceAPI techAPI;
    private ManufacturerAPI manufacturerAPI;


    public static void main(String[] args) throws Exception {
            new Driver().start();
        }

        public void start() {

            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
            //TODO - construct fields

            //TODO - load all data once the serializers are set up
            runMainMenu();
        }

    private int mainMenu() {
        System.out.println("""
                         -------Technology Store-------------
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
        private void runMainMenu() {
            int option = mainMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> runManufacturerMenu();
                    case 2 -> runTechAPIMenu();
                    case 3 -> runReportsMenu();
                    case 4 -> ;
                    case 5 -> ;
                    case 6 -> ;
                    case 10 -> ;
                    case 11 -> ;
                    //TODO - Add options
                    default ->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = mainMenu();
            }
            exitApp();
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

        private void runManufacturerMenu() {
            int option = manufacturerMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 5 -> findManufacturer();
                    case 6 -> listByManufacturerName();
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

        private void listByManufacturerName(){
            String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

            System.out.println(manufacturerAPI.listAllByManufacturerName(manufacturer));
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

        private void runTechAPIMenu() {
            int option = techAPIMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addTechDevice;
                    case 2 -> deleteTechDevice;
                    case 3 -> listTechDevice;
                    case 4 -> updateTechDevice;
                    default -> System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = reportsMenu();
            }
            runMainMenu();
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

    public void runReportsMenu(){
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> runManufacturerReports();
                case 2-> System.out.println("TODO - case 2");
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

    public void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
                case 2-> System.out.println("todo - Case 2");
                case 3-> System.out.println(manufacturerAPI.listAllByManufacturerName());
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
        runMainMenu();
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

    public void runTechReportMenu() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(TechnologyDeviceAPI.listAllTech);
                case 2 -> System.out.println(TechnologyDeviceAPI.listAllSmartBands);
                case 3 -> System.out.println(TechnologyDeviceAPI.listAllSmartWatches);
                case 4 -> System.out.println(TechnologyDeviceAPI.listAllTables);
                case 5 -> System.out.println(TechnologyDeviceAPI.listTechDeviceBelowAPrice());
                case 6 -> System.out.println(TechnologyDeviceAPI.listTechDeviceAboveAPrice());
                case 7 -> System.out.println(TechnologyDeviceAPI.listAllTabletsByOS);
                case 8 -> System.out.println(TechnologyDeviceAPI.listTopFiveExpensiveSmartWatches);
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = techReportMenu();
        }
        runMainMenu();
    }

//todo update methods counting methods




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

}
