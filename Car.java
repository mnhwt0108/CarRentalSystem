import java.util.HashMap;

public class Car {
    private int             carID, numOfDoor; //carID auto increment
    private String          model, group, fuel;
    private boolean         rentalStatus, maintenanceStatus;
    protected RentalRecord  records[] = new RentalRecord[10];

    /*
     * HashMap to store rental rate for each car group
     */
    HashMap<String, Double> rentRate = new HashMap<String, Double>() {
        {
            put("A", 1.0);
            put("B", 2.0);
            put("C", 3.0);
            put("D", 4.0);
            put("E", 5.0);
        }
    };

    public Car(int carID, int numOfDoor, String model, String group, String fuel, boolean rentalStatus,
            boolean maintenanceStatus) {
        if (isGroupValid(group) == null) {
            throw new IllegalArgumentException(
                    group + " is not a valid group. Group must be between A and E inclusive");
        }
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
        return "Car [carID=" + carID + ", model=" + model
                + ", numOfDoor=" + numOfDoor + ", fuel=" + fuel
                + ", group=" + group + ", maintenanceStatus="
                + maintenanceStatus + ", rentalStatus=" + rentalStatus + "]";
    }

    /*
     * Method used to create a new rental record by renting
     */
    public void rent(String customerId, DateTime rentDate, int numOfRentDay) {
        String groupOfCar = this.getGroup();
        int recordIndex = this.getLastElementIndex() + 1;

        if (this.isMaintenanceStatus() == true || this.isRentalStatus() == true) {
            throw new IllegalArgumentException(this.carID + " is not available for rent");

        } else {
            String rentId = this.getCarID() +
                    "_" + customerId +
                    "_" + rentDate.getEightDigitDate();

            this.records[recordIndex] = new RentalRecord(rentId, rentDate,
                    new DateTime(rentDate, numOfRentDay));
            this.rentalStatus = true;
            this.records[recordIndex].setRentalFee(rentRate.get(groupOfCar));
        }
    }

    public void returnCar(int index, DateTime returnDate) {
        this.records[index].setActualReturnDate(returnDate);
        this.setRentalStatus(false);
    }

    /*
     * Method used to get last element index
     */
    public int getLastElementIndex() {
        int index = 0;
        for (index = 0; this.records[index] != null; index++)
            ;
        return index - 1;
    }

    /*
     * Method used to check if the car's group attribute is within "A" and "E"
     */
    public String isGroupValid(String gc) {
        for (String i : rentRate.keySet()) {
            if (gc == i) {
                return i;
            }
        }
        return null;
    }

    /*
     * TO DO:
     * add()
     * update()
     * remove()
     * SearchID()-----loop----usage of isIdValid()
     * View()----loop
     * isIdValid()
     * isGroupValid()--------DONE
     * isRented()
     * isInMaintenance()
     * returnCar()-----------DONE
     */

}
