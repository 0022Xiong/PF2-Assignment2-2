package models;

import utils.Utilities;

import java.util.Objects;

public abstract class Technology {
    private double price = 20;
    private String id = "unknown";
    private String modelName;
    private Manufacturer manufacturer;
    public Technology(String modelName, double price, Manufacturer manufacturer, String id){
        this.modelName = Utilities.truncateString(modelName, 30);
        setPrice(price);
        this.manufacturer = manufacturer;
        setId(id);
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
        if(Utilities.validStringlength(modelName, 30))
            this.modelName = modelName;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString(){
        return "Model: " + modelName + ", " +
               "Price: €" + price + ", " +
               "Manufacturer Details: " + manufacturer + ", " +
               "ID: " + id;
    }
    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Technology that = (Technology) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(modelName, that.modelName) && Objects.equals(manufacturer, that.manufacturer);
    }

}
