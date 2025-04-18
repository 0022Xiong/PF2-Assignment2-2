package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import utils.ISerializer;
import utils.OperatingSystemUtility;

import utils.Utilities;

import java.io.*;
import java.util.*;



//TODO - ensure that this class implements iSerializer
public class TechnologyDeviceAPI implements ISerializer {
    //TODO - create 2 fields
    private File file;
    private List<Technology> technologyList = new ArrayList<>();

    //TODO - create constructor

    public TechnologyDeviceAPI(File file) {
        this.file = file;
    }
   //TODO - CRUD Methods

    public boolean addTechnologyDevice(Technology technology) {
        if(isValidId(technology.getId())) {
            return false;
        }
        return technologyList.add(technology);
    }

    public boolean updateTablet(String id, Tablet updatedDetails) {
        int indexUpdate = -1;
        for(Technology technology : technologyList) {
            if(technology instanceof Tablet && technology.getId().equals(id)) {
                 indexUpdate = technologyList.indexOf(technology);
            }
        }
        if(indexUpdate != -1) {
            technologyList.set(indexUpdate, updatedDetails);
            return true;
        }
        return false;
    }

    public boolean updateSmartWatch(String id, SmartWatch updatedDetails) {
        int indexUpdate = -1;
        for(Technology technology : technologyList) {
            if(technology instanceof SmartWatch && technology.getId().equals(id)) {
                indexUpdate = technologyList.indexOf(technology);
            }
        }
        if(indexUpdate != -1) {
            technologyList.set(indexUpdate, updatedDetails);
            return true;
        }
        return false;
    }

    public boolean updateSmartBand(String id, SmartBand updatedDetails) {
        int indexUpdate = -1;
        for(Technology technology : technologyList) {
            if(technology instanceof SmartBand && technology.getId().equals(id)) {
                indexUpdate = technologyList.indexOf(technology);
            }
        }
        if(indexUpdate != -1) {
            technologyList.set(indexUpdate, updatedDetails);
            return true;
        }
        return false;
    }

    //TODO - delete methods

    public Technology deleteTechnologyByIndex(int index) {
        if(Utilities.isValidIndex(technologyList, index)) {
            return technologyList.remove(index);
        }
        return null;
    }

    public Technology deleteTechnologyById(String id) {
        for(Technology technology : technologyList) {
            if(technology.getId().equals(id)) {
                int indexDelete = technologyList.indexOf(technology);
                return technologyList.remove(indexDelete);
            }
        }
        return null;
    }

    //TODO get Technology methods

    public Technology getTechnologyByIndex(int index) {
        if(Utilities.isValidIndex(technologyList, index)) {
            return technologyList.get(index);
        }
        return null;
    }

    public Technology getTechnologyDeviceById(String id) {
        for(Technology technology : technologyList) {
            if(technology.getId().equals(id)) {
                int indexGet = technologyList.indexOf(technology);
                return technologyList.get(indexGet);
            }
        }
        return null;
    }

    //TODO - Number methods

    public int numberOfTechnologyDevices() {
        int number = 0;
        for(Technology technology : technologyList) {
            number++;
        }
        return number;
    }

    public int numberOfTechnologyByChosenManufacturer(Manufacturer manufacturer) {
        int number = 0;
        for(Technology technology : technologyList) {
            if(technology.getManufacturer().equals(manufacturer)){
                number++;
            }
        }
        return number;
    }

    public int numberOfSmartBands() {
        int number = 0;
        for(Technology technology : technologyList) {
            if(technology instanceof SmartBand) {
                number++;
            }
        }
        return number;
    }

    public int numberOfSmartwatch() {
        int number = 0;
        for(Technology technology : technologyList) {
            if(technology instanceof SmartWatch) {
                number++;
            }
        }
        return number;
    }

    public int numberOfTablet() {
        int number = 0;
        for(Technology technology : technologyList) {
            if(technology instanceof Tablet) {
                number++;
            }
        }
        return number;
    }

    // TODO Read/list methods
    public String listAllTechnologyDevices() {
        String listAllTechnologyDevices = "";
        for(Technology technology : technologyList) {
            listAllTechnologyDevices += technologyList.indexOf(technology) + ": " + technology + "\n";
        }
        if(listAllTechnologyDevices.isEmpty()) {
            return "No Technology Devices";
        }
        else {
            return listAllTechnologyDevices;
        }
    }

    public String listAllSmartBands() {
        String listAllSmartBands = "";
        for(Technology technology : technologyList) {
            if(technology instanceof SmartBand) {
                listAllSmartBands += technologyList.indexOf(technology) + ": " + technology + "\n";
            }
        }
        if(listAllSmartBands.isEmpty()) {
            return "No Smart Bands";
        }
        else {
            return listAllSmartBands;
        }
    }

    public String listAllSmartWatches() {
        String listAllSmartWatches = "";
        for(Technology technology : technologyList) {
            if(technology instanceof SmartWatch) {
                listAllSmartWatches += technologyList.indexOf(technology) + ": " + technology + "\n";
            }
        }
        if(listAllSmartWatches.isEmpty()) {
            return "No Smart Watches";
        }
        else {
            return listAllSmartWatches;
        }
    }

    public String listAllTablets() {
        String listAllTablets = "";
        for(Technology technology : technologyList) {
            if(technology instanceof SmartWatch) {
                listAllTablets += technologyList.indexOf(technology) + ": " + technology + "\n";
            }
        }
        if(listAllTablets.isEmpty()) {
            return "No Tablets";
        }
        else {
            return listAllTablets;
        }
    }

    public String listAllTechnologyAbovePrice(double price) {
        String listAllTechnologyAbovePrice = "";
        for(Technology technology : technologyList) {
            if(technology.getPrice() >= price) {
                listAllTechnologyAbovePrice += technologyList.indexOf(technology) + ": " + technology + "\n";
            }
        }
        if(listAllTechnologyAbovePrice.isEmpty()) {
            return "No Technology more expensive than " + price;
        }
        else {
            return listAllTechnologyAbovePrice;
        }
    }

    public String listAllTechnologyBelowPrice(double price) {
        String listAllTechnologyBelowPrice = "";
        for(Technology technology : technologyList) {
            if(technology.getPrice() <= price) {
                listAllTechnologyBelowPrice += technologyList.indexOf(technology) + ": " + technology + "\n";
            }
        }
        if(listAllTechnologyBelowPrice.isEmpty()) {
            return "No Technology more expensive than " + price;
        }
        else {
            return listAllTechnologyBelowPrice;
        }
    }

    public String listAllTechDevicesByChosenManufacturer(Manufacturer manufacturer) {
        String listAllTechDevicesByChosenManufacturer = "";
        for(Technology technology : technologyList) {
            if(technology.getManufacturer().equals(manufacturer)) {
                listAllTechDevicesByChosenManufacturer += technologyList.indexOf(technology) + ": " + technology + "\n";
            }
        }
        if(listAllTechDevicesByChosenManufacturer.isEmpty()) {
            return "No technology manufactured by"  + manufacturer;
        }
        else {
            return listAllTechDevicesByChosenManufacturer;
        }
    }

    public String listAllTabletsByOperatingSystem(String os) {
        if(OperatingSystemUtility.isValidOperatingSystem(os)) {
            String listAllTechDevicesByOperatingSystem = "";
            for(Technology technology : technologyList) {
                if(technology instanceof Tablet && ((Tablet) technology).getOperatingSystem().equals(os)) {
                    listAllTechDevicesByOperatingSystem += technologyList.indexOf(technology) + ": " + technology + "\n";
                }
            }
            if(listAllTechDevicesByOperatingSystem.isEmpty()) {
                return "No tablet with the operating system "+ os;
            }
            else {
                return listAllTechDevicesByOperatingSystem;
            }
        }
        else {
            return "Invalid Operating System";
        }
    }


    //TODO - sort methods

    //TODO Top 5 methods
//    public List<Technology> topFiveMostExpensiveTechnology() {
//        List<Technology> arrangeByPrice;
//        List<Technology> topFiveTech;
//
//        if(numberOfTechnologyDevices() >= 5){
//            int index = 0;
//            for(Technology technology : technologyList) {
//                if(technology.getPrice())
//            }
//            topFiveTech.add(technologyList.get(index));
//        }
//        return topFiveTech;
//    }

    public boolean isValidId(String id) {
        for (Technology technology : technologyList) {
            if (technology.getId().equals(id)){
                return true;
            }
        }
        return false;
    }


    // TODO Persistence methods

    @Override
    public String fileName() {
        return String.valueOf(file);
    }

    public void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(technologyList);
        os.close();
    }


    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{ Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        technologyList = (List<Technology>) in.readObject();
        in.close();
    }
}
