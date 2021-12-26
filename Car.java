import java.util.HashMap;

public class Car {
    private int numOfDoor;
    private String carID, model, group, fuel;
    private boolean rentalStatus, maintenanceStatus;
    protected RentalRecord records[] = new RentalRecord[10];

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

    public Car(int numOfDoor, String model, String group, String fuel) {
        if (isGroupValid(group) == null) {
            throw new IllegalArgumentException(
                    group + " is not a valid group. Group must be between A and E inclusive");
        }
        if (isFuelValid(fuel) == false) {
            throw new IllegalArgumentException(
                    fuel + " is not a valid fuel. Fuel must be either '1.5 litters' or '6 cylinders' inclusive");
        }
        this.carID = "C#" + IdProvider.getCarInstance().getCarUniqueId(); // auto increment of car's ID
        this.numOfDoor = numOfDoor;
        this.model = model;
        this.group = group;
        this.fuel = fuel;
        this.rentalStatus = false;
        this.maintenanceStatus = false;
    }

    /*
     * Method used to update car's attribute
     */
    public void setCar(int numOfDoor, String model, String group, String fuel) {
        if (isGroupValid(group) == null) {
            throw new IllegalArgumentException(
                    group + " is not a valid group. Group must be between A and E inclusive");
        }
        this.numOfDoor = numOfDoor;
        this.model = model;
        this.group = group;
        this.fuel = fuel;
    }

    public String getCarID() {
        return carID;
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

    /*
     * Method used to return car after done renting
     */
    public RentalRecord returnC(int index, DateTime returnDate) {
        this.records[index].setActualReturnDate(returnDate);
        this.setRentalStatus(false);
        return records[index];
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
     * Method used to check if the car's fuel attribute is valid
     */
    public Boolean isFuelValid(String fuel) {
        if (fuel.equalsIgnoreCase("1.5 litters") || fuel.equalsIgnoreCase("6 cylinders")) {
            return true;
        }
        return false;
    }

    /*
     * Method to sets the car maintenance status to available after maintenance
     */
    public void performMaintenance() {
        if (this.maintenanceStatus == true || this.rentalStatus == true) {
            this.maintenanceStatus = false;
        } else {
            this.maintenanceStatus = true;
        }
    }

    /*
     * TO DO:
     * rent()------------------DONE
     * performMaintenance()----DONE
     * isGroupValid()----------DONE
     * returnCar()-------------DONE
     */
}
