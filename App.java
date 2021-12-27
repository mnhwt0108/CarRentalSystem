import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class App {

    int nextIdCarList = 1;
    int nextIdCusList = 1;
    HashMap<Integer, Car> carList;
    HashMap<Integer, Customer> cusList;

    public App() {
        carList = new HashMap<Integer, Car>();
        cusList = new HashMap<Integer, Customer>();
    }

    @Override
    public String toString() {
        return "App ["
                + "\nCar List       : \n\t" + carList
                + "\nCustomer List  : \n\t" + cusList
                + "\n]";
    }

    public String viewCarList() {
        return "[Car List        :\n" + carList + "\n]";
    }

    /*
     * Method used to add a car into the car list
     */
    public void addCar(int numOfDoor, String model, String group, String fuel, String branch) {
        Car car = new Car(numOfDoor, model, group, fuel, branch);
        carList.put(nextIdCarList, car);
        nextIdCarList++;
    }

    /*
     * Method used to add a customer into the customer list
     */
    public void addCus(String name, String contactNo) {
        Customer cus = new Customer(name, contactNo);
        cusList.put(nextIdCusList, cus);
        nextIdCusList++;
    }

    /*
     * Method used to search a specific car with the input of id
     */
    public void searchCarId(int id) throws Exception {
        if (carList.containsKey(id)) {
            System.out.println(carList.get(id).toString()); // CONSIDER VIEW METHOD HERE
        } else {
            throw new IllegalStateException("Car C#" + id + " does NOT exist in the database");
        }
    }

    /*
     * Method used to search a specific customer with the input of id
     */
    public void searchCusId(int id) throws Exception {
        if (cusList.containsKey(id)) {
            System.out.println(cusList.get(id).toString()); // CONSIDER VIEW METHOD HERE
        }
    }

    /*
     * Method used to remove a specific car with the input of that customer's id
     */
    public void removeCar(int id) throws Exception {
        carList.remove(id);
    }

    /*
     * Method used to remove a specific customer with the input of that customer's
     * id
     */
    public void removeCus(int id) throws Exception {
        cusList.remove(id);
    }

    /*
     * Method used to update a specific car with the input of that car's id
     * along with the attribute needed to be update
     */
    public void updateCar(int id, int numOfDoor, String model, String group, String fuel) throws Exception {
        carList.get(id).setCar(numOfDoor, model, group, fuel);
    }

    /*
     * Method used to update a specific car with the input of that customer's id
     * along with the attribute needed to be update
     */
    public void updateCus(int id, String name, String contactNo) throws Exception {
        if (cusList.containsKey(id)) {
            cusList.get(id).setCus(name, contactNo);
        }
    }

    /*
     * Method used to view a specific car's details with the input of id
     */
    public void viewCar(int id) throws Exception {
        System.out.println(carList.get(id).toString());
    }

    /*
     * Method used to view a specific customer's details with the input of id
     */
    public void viewCus(int id) throws Exception {
        System.out.println(cusList.get(id).toString());
    }

    /*
     * Method used to view a specific rental record of a specific car with the input
     * of id of the car and index of the record
     */
    public void viewRecord(int id, int index) throws Exception {
        if (carList.containsKey(id)) {
            if (carList.get(id).records[index] != null)
                carList.get(id).records[index].getDetails();
        }
    }

    /*
     * Method used to rent a car
     */
    public void rentCar(int id, int cusId, int date, int month, int year, int numOfRentDay) throws Exception {
        if (carList.containsKey(id) && cusList.containsKey(cusId)) {
            String customerId = cusList.get(cusId).getID();
            DateTime rentDate = new DateTime(date, month, year);
            carList.get(id).rent(customerId, rentDate, numOfRentDay);
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
            obj.records[rentIndex].setRentalType("rent");
            obj.setStatus("PICKED-UP");
        }
    }

    /*
     * Method used to return a car by calling the method returnC in class Car and
     * then print out the bill by calling calculateBill in class RentalRecord
     */
    public void returnCar(int id, int index, int date, int month, int year, String branch) throws Exception {
        if (carList.containsKey(id)) {
            DateTime returnDate = new DateTime(date, month, year);
            carList.get(id).returnC(index, returnDate, branch).calculateBill();
            // Print out bill
            System.out.println("\nBill*********************************************");
            viewRecord(id, index);
            System.out.println("*************************************************\n");
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
     * Method used to inspect the whole car list
     */
    public void inspectList() throws Exception {
        System.out.println("\n1: RENT-READY\t2:SERVICE-NEEDED\t3:REMOVED");
        try (Scanner sc = new Scanner(System.in)) {
            for (Entry<Integer, Car> set : carList.entrySet()) {
                if (set.getValue().getStatus() != "RETURNED") {
                    continue;
                }
                System.out.print("Enter the status: ");
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
            }
        }
        System.out.println(viewCarList());
    }

    /*
     * Method used to check if a car is rented
     */
    public void checkRented(int id) throws Exception {
        if (carList.containsKey(id)) {
            if (carList.get(id).getStatus() != "RENT-READY") {
                System.out.println("Car " + carList.get(id).getCarID() + " is currently being rented.");
            } else {
                System.out.println("Car " + carList.get(id).getCarID() + " is NOT currently being rented.");
            }
        }
    }

    /*
     * Method used to check if a car is in maintenance
     */
    public void checkMaintenance(int id) throws Exception {
        if (carList.containsKey(id)) {
            if (carList.get(id).getStatus() == "SERVICE-NEEDED") {
                System.out.println("Car " + carList.get(id).getCarID() + " is currently in maintenance.");
            } else {
                System.out.println("Car " + carList.get(id).getCarID() + " is NOT currently in maintenance.");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        App test = new App();

        test.addCar(6, "ABC", "A", "1.5 litters", "A");
        test.addCus("Sy", "123456");
        test.addCar(6, "ABC2", "B", "1.5 litters", "B");

        // test.removeCar(1);
        System.out.println("*****");
        test.searchCarId(1);
        System.out.println("*****");
        System.out.println(test);

        test.reserveCar(1, 1, 28, 12, 2021, 3);
        test.rentCar(2, 1, 28, 12, 2021, 4);
        test.viewRecord(1, 0);
        test.pickUpCar(1, 0);
        System.out.println("\n*****");
        test.viewRecord(1, 0);
        System.out.println("\n*****");
        test.returnCar(1, 0, 30, 12, 2021, "B");

        test.inspectList();

    }
}

/*
 * TO DO:
 * addCar()----------DONE
 * updateCar()-------DONE
 * removeCar()-------DONE
 * searchIdCar()-----DONE
 * viewCar()---------DONE
 * 
 *
 * addCus()----------DONE
 * updateCus()-------DONE
 * removeCus()-------DONE
 * searchIdCus()-----DONE
 * viewCus()---------DONE
 * 
 * IMPLEMENT THE BLACKLIST, DISCOUNT-------
 */