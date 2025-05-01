package models;

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
    public String toString(){
        return super.toString() + "heartRateMonitor" + heartRateMonitor;
    }
}
