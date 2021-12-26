import java.util.HashMap;

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

    /*
     * Method used to add a car into the car list
     */
    public void addCar(int numOfDoor, String model, String group, String fuel) {
        Car car = new Car(numOfDoor, model, group, fuel);
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
        if (carList.containsKey(id)) {
            carList.remove(id);
        }
    }

    /*
     * Method used to remove a specific customer with the input of that customer's
     * id
     */
    public void removeCus(int id) throws Exception {
        if (cusList.containsKey(id)) {
            cusList.remove(id);
        }
    }

    /*
     * Method used to update a specific car with the input of that car's id
     * along with the attribute needed to be update
     */
    public void updateCar(int id, int numOfDoor, String model, String group, String fuel) throws Exception {
        if (carList.containsKey(id)) {
            carList.get(id).setCar(numOfDoor, model, group, fuel);
        }
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
     * Method used to view a specific car's rental records details with the input of
     * id
     */
    public void viewRentalRecord(int id) throws Exception {
        if (carList.containsKey(id)) {
            for (int i = 0; i < 10; i++) {
                if (carList.get(id).records[i] == null) {
                    break;
                }
                carList.get(id).records[i].getDetails();
            }
        }
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
     * Method used to return a car by calling the method returnC in class Car and
     * then print out the bill by calling calculateBill in class RentalRecord
     */
    public void returnCar(int id, int index, int date, int month, int year) throws Exception {
        if (carList.containsKey(id)) {
            DateTime returnDate = new DateTime(date, month, year);
            carList.get(id).returnC(index, returnDate).calculateBill();
            // Print out bill
            System.out.println("\nBill*******************************************");
            viewRecord(id, index);
            System.out.println("***********************************************");
        }
    }

    /*
     * Method used to perform a maintenance on a car
     */
    public void maintenanceCar(int id) throws Exception {
        if (carList.containsKey(id)) {
            carList.get(id).performMaintenance();
        }
    }

    /*
     * Method used to check if a car is rented
     */
    public void checkRented(int id) throws Exception {
        if (carList.containsKey(id)) {
            if (carList.get(id).isRentalStatus() == true) {
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
            if (carList.get(id).isMaintenanceStatus() == true) {
                System.out.println("Car " + carList.get(id).getCarID() + " is currently in maintenance.");
            } else {
                System.out.println("Car " + carList.get(id).getCarID() + " is NOT currently in maintenance.");
            }
        }
    }

    public static void main(String[] args) throws Exception {

        App test = new App();

        test.addCar(6, "ABC", "A", "1.5 litters");
        test.addCus("Sy", "123456");
        System.out.println(test);

        try {
            test.addCar(6, "ABC", "A", "1 litters");
        } catch (Exception e) {
            System.out.println(e);
        }

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