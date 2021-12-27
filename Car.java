import java.util.HashMap;

public class Car {
    private int numOfDoor;
    private String carID, model, group, fuel, branch;
    private String Status;
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

    public Car(int numOfDoor, String model, String group, String fuel, String branch) {
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
        this.branch = branch;
        this.Status = "RENT-READY";
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public RentalRecord[] getRecords() {
        return records;
    }

    public void setRecords(RentalRecord[] records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Car [carID=" + carID + ", fuel=" + fuel + ", group="
                + group + ", model=" + model + ", numOfDoor=" + numOfDoor + ", branch=" + branch
                + ", Status=" + Status + "]";
    }

    /*
     * Method used to create a new rental record by renting
     */
    public void rent(String customerId, DateTime rentDate, int numOfRentDay) {
        String groupOfCar = this.getGroup();
        int recordIndex = this.getLastElementIndex() + 1;
        String temp = "rent";

        if (this.getStatus() != "RENT-READY") {
            throw new IllegalArgumentException(this.carID + " is not available for rent");

        } else {
            String rentId = this.getCarID() +
                    "_" + customerId +
                    "_" + rentDate.getEightDigitDate();

            this.records[recordIndex] = new RentalRecord(rentId, temp, rentDate,
                    new DateTime(rentDate, numOfRentDay));
            this.Status = "PICKED-UP";
            this.records[recordIndex].setRentalFee(rentRate.get(groupOfCar));
        }
    }

    /*
     * Method used to create a new rental record by renting
     */
    public void reserve(String customerId, DateTime rentDate, int numOfRentDay) {
        String groupOfCar = this.getGroup();
        int recordIndex = this.getLastElementIndex() + 1;
        String temp = "reserve";

        if (this.getStatus() != "RENT-READY") {
            throw new IllegalArgumentException(this.carID + " is not available for rent");

        } else {
            String rentId = this.getCarID() +
                    "_" + customerId +
                    "_" + rentDate.getEightDigitDate() +
                    "_" + recordIndex;

            this.records[recordIndex] = new RentalRecord(rentId, temp, rentDate,
                    new DateTime(rentDate, numOfRentDay));
            this.Status = "RESERVED";
            this.records[recordIndex].setRentalFee(rentRate.get(groupOfCar));
        }
    }

    public void pickUp() {
        if (this.getStatus() != "RENT-READY") {
            throw new IllegalArgumentException(this.carID + " is not available for pickup");
        }
        this.setStatus("PICKED-UP");
    }

    /*
     * Method used to return car after done renting
     */
    public RentalRecord returnC(int index, DateTime returnDate, String returnBranch) {
        this.records[index].setActualReturnDate(returnDate);
        this.setStatus("RETURNED");
        if (returnBranch != this.branch) {
            this.branch = returnBranch;
        }
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
     * TO DO:
     * rent()------------------DONE
     * performMaintenance()----DONE
     * isGroupValid()----------DONE
     * returnCar()-------------DONE
     */
}
