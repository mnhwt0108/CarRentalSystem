import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.HashMap;

public class VinaRentSystem {
    int nextBrId = 1;
    int nextGrId = 1;
    int nextModelId = 1;
    int nextCarId = 1;
    int nextCusId = 1;
    Map<Integer, Branch> brList;
    Map<Integer, Group> grList;
    Map<Integer, Model> modelList;
    Map<Integer, Car> carList;
    Map<Integer, Customer> cusList;

    public VinaRentSystem() {
        brList = new HashMap<Integer, Branch>();
        grList = new HashMap<Integer, Group>();
        modelList = new HashMap<Integer, Model>();
        carList = new HashMap<Integer, Car>();
        cusList = new HashMap<Integer, Customer>();
    }

    @Override
    public String toString() {
        return "VinaRentSystemTest [\nbrList=" + brList + "\ngrList=" + grList + "\nmodelList="
                + modelList + "\ncarList=" + carList + "\ncusList=" + cusList + "\n]";
    }

    /*
     * Method used to add a branch into the branch list
     */
    public void addBranch(String address, String phone) throws Exception {
        Branch br = new Branch(address, phone);
        brList.put(nextBrId, br);
        nextBrId++;
    }

    /*
     * Method used to add a group into the group list
     */
    public void addGroup(String groupId, String branchId) throws Exception {
        validBranch(branchId);
        String temp = branchId.replaceAll("\\D+", "");
        int numb = Integer.parseInt(temp);
        Branch br = this.brList.get(numb);
        Group gr = new Group(groupId, br);
        grList.put(nextGrId, gr);
        brList.get(numb).addGr(gr);
        nextGrId++;
    }

    /*
     * Method used to add a model into the model list
     */
    public void addModel(String modelName, String transmission, String fuel, int numOfDoor) throws Exception {
        Model model = new Model(modelName, transmission, fuel, numOfDoor);
        modelList.put(nextModelId, model);
        nextModelId++;
    }

    /*
     * Method used to add a car into the car list
     */
    public void addCar(int modelId, int groupId) throws Exception {
        Model model = this.modelList.get(modelId);
        Group group = this.grList.get(groupId);
        Car car = new Car(model, group);
        carList.put(nextCarId, car);
        grList.get(groupId).add(car);
        modelList.get(modelId).addCar(car);
        nextCarId++;
    }

    /*
     * Method used to add a customer into the customer list
     */
    public void addCus(String name, String contactNo) {
        Customer cus = new Customer(name, contactNo);
        cusList.put(nextCusId, cus);
        nextCusId++;
    }

    /*
     * Method used to pair neighboring branches with each other
     */
    public void pairNeighborBr(String br1, String br2) throws Exception {
        validBranch(br1);
        validBranch(br2);
        String temp = br1.replaceAll("\\D+", "");
        int numb = Integer.parseInt(temp);
        String temp2 = br2.replaceAll("\\D+", "");
        int numb2 = Integer.parseInt(temp2);
        Branch tempBr1 = this.brList.get(numb);
        Branch tempBr2 = this.brList.get(numb2);

        tempBr1.setNeighborBrach(tempBr2.getBranchId());
        tempBr2.setNeighborBrach(tempBr1.getBranchId());
    }

    /*
     * Method used to view a specific rental record of a specific car with the input
     * of id of the car and index of the record
     */
    public void viewRecord(int carId, int index) throws Exception {
        if (carList.containsKey(carId)) {
            if (carList.get(carId).records[index] != null)
                carList.get(carId).records[index].getDetails();
        }
    }

    /*
     * Method used to rent a car
     */
    public void rentCar(int carId, int cusId, int date, int month, int year, int numOfRentDay) throws Exception {
        if (carList.containsKey(carId) && cusList.containsKey(cusId)) {
            Car temp = carList.get(carId);
            String customerId = cusList.get(cusId).getID();
            DateTime rentDate = new DateTime(date, month, year);
            temp.rent(customerId, rentDate, numOfRentDay);
        }
    }

    /*
     * Method used to return a car by calling the method returnC in class Car and
     * then print out the bill by calling calculateBill in class RentalRecord
     */
    public void returnCar(int id, int index, int date, int month, int year, String branch) throws Exception {
        validBranch(branch);
        if (carList.containsKey(id)) {
            DateTime returnDate = new DateTime(date, month, year);
            Car temp = carList.get(id);
            if (branch != temp.getBranchId()) {
                temp.setBranchId(branch);
                temp.getGroup().remove(temp);
            }
            temp.returnC(index, returnDate, branch);
            // Print out bill
            System.out.println("\nBill*********************************************");
            viewRecord(id, index);
            System.out.println("*************************************************\n");
        }
    }

    /*
     * Method used to check if the input branch ID is valid
     */
    public void validBranch(String branch) {
        String temp = branch.replaceAll("\\D+", "");
        int numb = Integer.parseInt(temp);
        if (brList.containsKey(numb) != true) {
            throw new IllegalArgumentException(branch + " is not a valid branch.");
        }
    }

    /*
     * Method used to view a specific car's details with the input of id
     */
    public void viewCar(int id) throws Exception {
        System.out.println(carList.get(id).toString());
    }

    /*
     * Method used to check if the input group ID is valid
     */
    public int validGroup(String group) {
        int numb2 = 0;
        switch (group) {
            case "A":
                numb2 = 1;
                break;
            case "B":
                numb2 = 2;
                break;
            case "C":
                numb2 = 3;
                break;
            case "D":
                numb2 = 4;
                break;
            case "E":
                numb2 = 5;
                break;
        }
        return numb2;
    }

    /*
     * Method used to view specific cars details from specific branch and group
     */
    public void viewCarSpecific(String branch, String group) throws Exception {
        validBranch(branch);
        String temp = branch.replaceAll("\\D+", "");
        int numb = Integer.parseInt(temp);
        int numb2 = validGroup(group);
        if (brList.containsKey(numb) && grList.containsKey(numb2)) {
            System.out.println(grList.get(numb2).getCarList().toString());
        }

    }

    /*
     * Method used to reserve a car
     */
    public void reserveCar(int id, int cusId, int date, int month, int year, int numOfRentDay) throws Exception {
        if (carList.containsKey(id) && cusList.containsKey(cusId)) {
            String customerId = cusList.get(cusId).getID();
            DateTime rentDate = new DateTime(date, month, year);
            carList.get(id).reserve(customerId, rentDate, numOfRentDay);
        }
    }

    /*
     * Method used to pick up a reserved car
     */
    public void pickUpCar(int carId, int rentIndex) {
        if (carList.containsKey(carId)) {
            Car obj = carList.get(carId);
            obj.pickUp();
            obj.records[rentIndex].setRentalType("rent");
            obj.records[rentIndex].getDetails();
        }
    }

    /*
     * Method used to pick up a reserved car
     */
    public void noPickUpCar(int carId, int rentIndex) {
        if (carList.containsKey(carId)) {
            Car obj = carList.get(carId);
            obj.records[rentIndex].setRentalType("no pick up");
            obj.setStatus("RENT-READY");
            obj.records[rentIndex].setActualReturnDate(obj.records[rentIndex].getRentDate());
        }
    }

    /*
     * Method used to move a list of car to another branch
     */
    public void moveCars(String branch, int[] id) throws Exception {
        int i = 0;
        for (Entry<Integer, Car> set : carList.entrySet()) {
            int nextId = id[i];
            if (set.getKey() != nextId) {
                continue;
            }
            set.getValue().setBranchId(branch);
            System.out.print("*");
            i++;
            if (i > id.length) {
                break;
            }
        }
    }

    /*
     * Method used to check if a car is rented
     */
    public void checkRented(int id) throws Exception {
        if (carList.containsKey(id)) {
            if (carList.get(id).getStatus() != "RENT-READY") {
                System.out.println("Car " + carList.get(id).getCarNr() + " is currently being rented.");
            } else {
                System.out.println("Car " + carList.get(id).getCarNr() + " is NOT currently being rented.");
            }
        }
    }

    /*
     * Method used to check if a car is in maintenance
     */
    public void checkMaintenance(int id) throws Exception {
        if (carList.containsKey(id)) {
            if (carList.get(id).getStatus() == "SERVICE-NEEDED") {
                System.out.println("Car " + carList.get(id).getCarNr() + " is currently in maintenance.");
            } else {
                System.out.println("Car " + carList.get(id).getCarNr() + " is NOT currently in maintenance.");
            }
        }
    }

    /*
     * Method used to inspect a car
     */
    public void inspectCar(String status, int id) throws Exception {
        if (status != "RENT-READY" && status != "SERVICE-NEEDED" && status != "REMOVED") {
            throw new IllegalArgumentException(
                    status + " is not a valid status. Status must be either 'RENT-READY', 'SERVICE-NEEDED' or 'REMOVED' inclusive");
        } else {
            carList.get(id).setStatus(status);
        }

    }

    /*
     * Method used to inspect every RETURNED car in the car list
     */
    public void inspectList() throws Exception {
        System.out.println("\n1: RENT-READY\t2:SERVICE-NEEDED\t3:REMOVED");
        try (Scanner sc = new Scanner(System.in)) {
            for (Entry<Integer, Car> set : carList.entrySet()) {
                if (set.getValue().getStatus() != "RETURNED") {
                    continue;
                }
                System.out.print("\nEnter the status for car " + set.getValue().getCarNr() + " : ");
                int temp = sc.nextInt();
                String input = "test";
                switch (temp) {
                    case 1:
                        input = "RENT-READY";
                        break;
                    case 2:
                        input = "SERVICE-NEEDED";
                        break;
                    case 3:
                        input = "REMOVED";
                        break;
                }
                inspectCar(input, set.getKey());
                System.out.println(set.getValue().toString());
            }
        }
    }

}
