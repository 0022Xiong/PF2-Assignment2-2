package models;

public class SmartBand extends WearableDevice{
    private boolean heartRateMonitor ;
    public SmartBand (double price, String id, String modelName, Manufacturer manufacturer, String material, String size, boolean heartRateMonitor){
        super(modelName, price, manufacturer, id, material, size);
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
    public String toString(){
        return super.toString() + "heartRateMonitor" + heartRateMonitor;
    }
}
