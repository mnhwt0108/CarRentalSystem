public class Car {
    private int carID, numOfDoor;
    private String model, group, fuel;
    private boolean rentalStatus, maintenanceStatus;
    protected RentalRecord records[] = new RentalRecord[10];

    public Car(int carID, int numOfDoor, String model, String group, String fuel, boolean rentalStatus,
            boolean maintenanceStatus) {
        this.carID = carID;
        this.numOfDoor = numOfDoor;
        this.model = model;
        this.group = group;
        this.fuel = fuel;
        this.rentalStatus = rentalStatus;
        this.maintenanceStatus = maintenanceStatus;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public Car() {

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

    @Override
    public String toString() {
        return "Car [carID=" + carID + ", fuel=" + fuel + ", group=" + group + ", maintenanceStatus="
                + maintenanceStatus + ", model=" + model + ", numOfDoor=" + numOfDoor + ", rentalStatus=" + rentalStatus
                + "]";
    }

    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
        String groupOfCar = this.getGroup();
        int recordIndex = this.getLastElementIndex() + 1;

        if (this.isMaintenanceStatus() == true || this.isRentalStatus() == true) {
            return false;

        } else {
            String rentId = this.getCarID() +
                    "_" + customerId +
                    "_" + rentDate.getEightDigitDate();

            this.records[recordIndex] = new RentalRecord(rentId, rentDate,
                    new DateTime(rentDate, numOfRentDay));
            this.rentalStatus = true;
        }

        switch (groupOfCar) {
            case "A":
                this.records[recordIndex].setRentalFee(1.0);
                break;

            case "B":
                this.records[recordIndex].setRentalFee(2.0);
                break;

            case "C":
                this.records[recordIndex].setRentalFee(3.0);
                break;

            case "D":
                this.records[recordIndex].setRentalFee(4.0);
                break;

            case "E":
                this.records[recordIndex].setRentalFee(5.0);
                break;
        }

        return true;
    }

    /**
     * Method used to get last element index
     */
    public int getLastElementIndex() {
        int index = 0;
        for (index = 0; this.records[index] != null; index++)
            ;
        return index - 1;
    }

    public RentalRecord[] getRecords() {
        return records;
    }

    public void setRecords(RentalRecord[] records) {
        this.records = records;
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
