package models;

import utils.Utilities;

public abstract class Technology {
    private double price;
    private String id;
    private String modelName;
    private Manufacturer manufacturer;
    public Technology(double price,String id,String modelName,Manufacturer manufacturer){
        this.price = price;
        this.id = Utilities.truncateString(id,10);
        this.modelName = modelName;
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
        this.modelName = modelName;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String toString(){
        return "id" + id +"\n" +
               "model name" + modelName +"\n" +
               "price" + price + "\n" +
                "name and number of employees" + manufacturer;
    }
    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

}
