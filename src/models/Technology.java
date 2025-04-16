package models;

public class Technology {
    private double price;
    private String id;
    private String modelName;
    private Manufacturer manufacturer;
    public Technology(double price,String id,String modelName,Manufacturer manufacturer){
        this.price = price;
        this.id = id;
        this.modelName = modelName;
       this.manufacturer = manufacturer;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public double getInsurancePremium(double exchangeRate){
        return price*exchangeRate;
    }
    public String connectToInternet(String connectStatus){
        return connectStatus;
    }
}
