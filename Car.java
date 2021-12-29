
public class Car {

    private String carNr, Status, branchId, grId;
    private Model model;
    private Group group;
    protected RentalRecord records[] = new RentalRecord[10];

    public Car(Model model, Group group) {
        this.carNr = "C#" + IdProvider.getCarInstance().getCarUniqueId(); // auto increment ID
        this.model = model;
        this.group = group;
        this.setGrId(group.getGroupId());
        this.Status = "RENT-READY";
        this.branchId = group.getBranch().getBranchId();
    }

    public String getGrId() {
        return grId;
    }

    public void setGrId(String grId) {
        this.grId = grId;
    }

    public Group getGroup() {
        return group;
    }

    public String getCarNr() {
        return carNr;
    }

    public Model getModel() {
        return model;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setGroup(Group group) {
        if (this.group != null) {
            this.group.remove(this);
        }
        this.group = group;
        this.group.add(this);
    }

    public void setModel(Model model) {
        if (this.model != null) {
            this.model.removeCar(this);
        }
        this.model = model;
        this.model.addCar(this);
    }

    /*
     * Method used to create a new rental record by renting
     */
    public void rent(String customerId, DateTime rentDate, int numOfRentDay) {
        String groupOfCar = this.group.getGroupId();
        int recordIndex = this.getLastElementIndex() + 1;
        String temp = "rent";

        if (this.getStatus() != "RENT-READY") {
            throw new IllegalArgumentException(this.carNr + " is not available for rent");

        } else {
            String rentId = this.getCarNr() +
                    "_" + customerId +
                    "_" + rentDate.getEightDigitDate() +
                    "_" + recordIndex;

            this.records[recordIndex] = new RentalRecord(rentId, temp, rentDate,
                    new DateTime(rentDate, numOfRentDay));
            this.Status = "PICKED-UP";
            this.records[recordIndex].setRentalFee(this.group.rentRate.get(groupOfCar));
            this.records[recordIndex].getDetails();
        }
    }

    /*
     * Method used to create a new rental record by renting
     */
    public void reserve(String customerId, DateTime rentDate, int numOfRentDay) {
        String groupOfCar = this.group.getGroupId();
        int recordIndex = this.getLastElementIndex() + 1;
        String temp = "reserve";

        if (this.getStatus() != "RENT-READY") {
            throw new IllegalArgumentException(this.carNr + " is not available for rent");

        } else {
            String rentId = this.getCarNr() +
                    "_" + customerId +
                    "_" + rentDate.getEightDigitDate() +
                    "_" + recordIndex;

            this.records[recordIndex] = new RentalRecord(rentId, temp, rentDate,
                    new DateTime(rentDate, numOfRentDay));
            this.Status = "RESERVED";
            this.records[recordIndex].setRentalFee(this.group.rentRate.get(groupOfCar));
        }
    }

    public void pickUp() {
        if (this.getStatus() != "RENT-READY") {
            throw new IllegalArgumentException(this.carNr + " is not available for pickup");
        }
        this.setStatus("PICKED-UP");
    }

    /*
     * Method used to return car after done renting
     */
    public RentalRecord returnC(int index, DateTime returnDate, String returnBranch) {
        this.records[index].setActualReturnDate(returnDate);
        this.setStatus("RETURNED");
        return records[index];
    }

    @Override
    public String toString() {
        return "Car [carNr=" + carNr + ", modelName=" + this.model.getModelName() + ", Branch="
                + this.getBranchId() + ", Group=" + this.getGrId() + ", Status=" + Status + "]";
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branch) {
        this.branchId = branch;
    }
}