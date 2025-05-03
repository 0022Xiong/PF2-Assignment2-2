package models;

import utils.Utilities;

public abstract class ComputingDevice extends Technology{
    private int storage = 8 ;
    private String processor;
    public ComputingDevice(double price, String id, String modelName, Manufacturer manufacturer,int storage,String processor){
        super(modelName, price, manufacturer, id);
        setStorage(storage);
        this.processor = Utilities.truncateString(processor,20);
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if(Utilities.validRange(storage,8,128)&& storage%8 == 0)
            this.storage = storage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        if(Utilities.validStringlength(processor,20))
            this.processor = processor;
    }
   public String toString(){
        return  super.toString() + "Processor: Snapdragon " + processor + ", Storage: "+storage;
   }
}
