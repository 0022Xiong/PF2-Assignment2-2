package models;

import utils.Utilities;

import java.util.Objects;

public abstract class ComputingDevice extends Technology {
    private int storage = 8;
    private String processor;

    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, int storage, String processor) {
        super(modelName, price, manufacturer, id);
        setStorage(storage);
        this.processor = Utilities.truncateString(processor, 20);
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if (Utilities.validRange(storage, 8, 128) && storage % 8 == 0)
            this.storage = storage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        if (Utilities.validStringlength(processor, 20))
            this.processor = processor;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + "Processor: " + processor + ", " + "Storage: " + storage + "GB";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ComputingDevice that = (ComputingDevice) o;
        return storage == that.storage && Objects.equals(processor, that.processor);
    }

}
