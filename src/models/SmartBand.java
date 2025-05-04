package models;

import utils.Utilities;

import java.util.Objects;

public class SmartBand extends WearableDevice{
    private boolean heartRateMonitor ;
    public SmartBand (String modelName, double price, Manufacturer manufacturer, String id, String size, String material, boolean heartRateMonitor){
        super(modelName, price, manufacturer, id, size, material);
        this.heartRateMonitor = heartRateMonitor;
    }

    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }
    public double getInsurancePremium() {
        return getPrice() * 0.07;
    }
    public String connectToInternet() {
        return "Connects to the internet via bluetooth";
    }

    @Override
    public String toString(){
        return super.toString() + ", " + "HeartRateMonitor: " + Utilities.booleanToYN(heartRateMonitor) + ", " + connectToInternet();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SmartBand smartBand = (SmartBand) o;
        return heartRateMonitor == smartBand.heartRateMonitor;
    }

}
