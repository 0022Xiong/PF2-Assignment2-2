package models;

import utils.OperatingSystemUtility;

public class Tablet extends ComputingDevice {
    private String operatingSystem = "Windows";
    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem){
        super(modelName, price, manufacturer, id, storage, processor);
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
        return super.toString() + ", " + "Operating System: " + operatingSystem + ", " + "Insurance Premium: €" + getInsurancePremium();
    }
    public double getInsurancePremium() {
        return getPrice() * 0.01;
    }
}
