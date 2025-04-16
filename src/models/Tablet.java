package models;

import utils.OperatingSystemUtility;

public class Tablet extends ComputingDevice {
    private String operatingSystem = "Window";
    public Tablet(double price, String id, String modelName, Manufacturer manufacturer,int storage,String processor,String operatingSystem){
        super(price, id, modelName, manufacturer, storage, processor);
        setOperatingSystem(operatingSystem);
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if(OperatingSystemUtility.isValidOperatingSystem(operatingSystem))
        this.operatingSystem = operatingSystem;
    }
    public String connectToInternet() {
        return "Connects to the internet via Wi-Fi";
    }
    public String toString(){
        return super.toString() + "operatingSystem" + operatingSystem;
    }
    public double getInsurancePremium() {
        return getPrice() * 0.01;
    }
}
