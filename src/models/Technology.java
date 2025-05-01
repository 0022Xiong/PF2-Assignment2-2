package models;

import utils.Utilities;

public abstract class Technology {
    private double price;
    private String id;
    private String modelName;
    private Manufacturer manufacturer;
    public Technology(String modelName, double price, Manufacturer manufacturer, String id){
        this.modelName = modelName;
        setPrice(price);
        this.manufacturer = manufacturer;
        this.id = Utilities.truncateString(id,10);
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
        this.modelName = modelName;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toString(){
        return "ID: " + id +"\n" +
               "Model name: " + modelName +"\n" +
               "Price: " + price + "\n" +
               "Name and number of employees: " + manufacturer;
    }
    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

}
