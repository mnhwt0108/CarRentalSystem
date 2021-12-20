import java.util.Arrays;

public class Car {
    private int id, numOfDoor;
    private String model, group, fuel;
    private boolean rentalStatus, maintenanceStatus;
    private RentalRecord records[] = new RentalRecord[10];

    public Car(int id, int numOfDoor, String model, String group, String fuel, boolean rentalStatus,
            boolean maintenanceStatus) {
        this.id = id;
        this.numOfDoor = numOfDoor;
        this.model = model;
        this.group = group;
        this.fuel = fuel;
        this.rentalStatus = rentalStatus;
        this.maintenanceStatus = maintenanceStatus;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfDoor() {
        return numOfDoor;
    }

    public void setNumOfDoor(int numOfDoor) {
        this.numOfDoor = numOfDoor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public boolean isMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(boolean maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public RentalRecord[] getRecords() {
        return records;
    }

    public void setRecords(RentalRecord[] records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Car [fuel=" + fuel + ", group=" + group + ", id=" + id + ", maintenanceStatus=" + maintenanceStatus
                + ", model=" + model + ", numOfDoor=" + numOfDoor + ", records=" + Arrays.toString(records)
                + ", rentalStatus=" + rentalStatus + "]";
    }

    /*
     * TO DO:
     * add()
     * update()
     * remove()
     * SearchID()
     * View()
     * isIdValid()
     * isGroupValid()
     * isRented()
     * isInMaintenance()
     */

}
