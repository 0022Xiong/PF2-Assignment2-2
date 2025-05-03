package models;
import utils.DisplayTypeUtility;


public class SmartWatch extends WearableDevice{
    private String displayType="LCD";

    public SmartWatch(String modelName, double price, Manufacturer manufacturer, String id, String size, String material, String displayType){
        super(modelName, price, manufacturer, id, size, material);
        setDisplayType(displayType);
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
       if(DisplayTypeUtility.isValidDisplayType(displayType)){
        this.displayType = displayType;
       }
    }
    public String connectToInternet() {
        return "Connects to the internet via Companion App";
    }
    public String toString(){
        return super.toString()  + "DisplayType: " + displayType;
    }
    public double getInsurancePremium() {
        return getPrice() * 0.06;
    }
}
