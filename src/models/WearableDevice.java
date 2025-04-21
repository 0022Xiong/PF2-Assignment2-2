package models;
import utils.Utilities;

public abstract class WearableDevice extends Technology{

    private String material;
    private String size;
    public WearableDevice(double price, String id, String modelName, Manufacturer manufacturer,String material,String size) {
        super(price, id, modelName, manufacturer);
        this.material= Utilities.truncateString(material,20);
        this.size=Utilities.truncateString(size,10);
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
        return super.toString() + "material" + material + "\n" +
                "size" + size;
    }
}
