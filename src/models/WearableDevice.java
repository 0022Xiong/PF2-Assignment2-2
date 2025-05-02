package models;
import utils.Utilities;

public abstract class WearableDevice extends Technology{

    private String material;
    private String size;
    public WearableDevice(String modelName, double price, Manufacturer manufacturer, String id, String size,  String material) {
        super(modelName, price, manufacturer, id);
        this.size=Utilities.truncateString(size,10);
        this.material= Utilities.truncateString(material,20);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if(Utilities.validStringlength(material,20))
            this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if(Utilities.validStringlength(size,10))
            this.size = size;
    }
    public abstract double getInsurancePremium();
    public abstract String connectToInternet();
    public String toString(){
        return super.toString() + "\n" + "Material: " + material + "\n" +
                "Size: " + size;
    }
}
