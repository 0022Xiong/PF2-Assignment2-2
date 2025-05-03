package models;

import utils.OperatingSystemUtility;
import utils.Utilities;

public class Tablet extends ComputingDevice {
    private String operatingSystem = "Windows";
    public Tablet(String id, double price, Manufacturer manufacturer, String modelName, String processor, int storage, String operatingSystem){
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
