package models;
import utils.DisplayTypeUtility;

import java.util.Objects;


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

    @Override
    public String toString(){
        return super.toString() + ", " + "DisplayType: " + displayType + ", " + "Insurance Premium: €" + getInsurancePremium() + ", " + connectToInternet();
    }

    public double getInsurancePremium() {
        return getPrice() * 0.06;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SmartWatch that = (SmartWatch) o;
        return Objects.equals(displayType, that.displayType);
    }

}
