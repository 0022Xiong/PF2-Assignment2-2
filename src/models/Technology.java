package models;

import utils.Utilities;

public abstract class Technology {
    private String modelName;
    private double price = 20;
    private String id = "unknown";
    private Manufacturer manufacturer;
    public Technology(String modelName,double price,Manufacturer manufacturer,String id){
        setPrice(price);
        this.id = Utilities.truncateString(id,10);
        this.modelName = Utilities.truncateString(modelName,30);
        this.manufacturer = manufacturer;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 20)
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(Utilities.validStringlength(id,10))
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (Utilities.validStringlength(modelName, 30)) {
            this.modelName = modelName;
        }
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toString(){
        return "Model: " + modelName +", Price: " + price + ", Manufacturer Details: " + manufacturer + ", ID: " + id;
    }
    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

}
