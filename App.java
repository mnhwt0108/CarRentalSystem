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
                + "\nCar List       : " + carList
                + "\nCustomer List  :" + cusList + "\n]";
    }

    /*
     * Method used to add a car into the car list
     */
    public void addCar(int numOfDoor, String model, String group, String fuel, boolean rentalStatus,
            boolean maintenanceStatus) {
        Car car = new Car(numOfDoor, model, group, fuel, rentalStatus, maintenanceStatus);
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

    public static void main(String[] args) throws Exception {

        App test = new App();

        /*
         * Car c1 = new Car(6, "ABC", "A", "1.5 litters", false, false);
         * Car c2 = new Car(6, "DEF", "B", "6 cylinders", false, false);
         * Customer cus1 = new Customer("Sy", "123456");
         * Customer cus2 = new Customer("Sy2", "123456");
         * DateTime d1 = new DateTime(18, 12, 2021);
         * DateTime d3 = new DateTime(23, 12, 2021);
         * c1.rent(String.valueOf(cus1.getID()), d1, 3);
         * System.out.println(c1.toString());
         * System.out.println(c2.toString());
         * System.out.println("\n" + cus1.toString());
         * System.out.println("\n" + cus2.toString());
         * System.out.println("\nRental record of index " + c1.getLastElementIndex() +
         * " of Car " + c1.getCarID() + ":\n"
         * + c1.records[c1.getLastElementIndex()].getDetails());
         * 
         * c1.returnCar(c1.getLastElementIndex(), d3);
         * System.out.println("\n" + c1.records[c1.getLastElementIndex()].getDetails());
         * System.out.println("\nTotal bill: " +
         * c1.records[c1.getLastElementIndex()].calculateBill());
         * c1.setMaintenanceStatus(true);
         * System.out.println(c1.toString());
         * c1.performMaintenance();
         * System.out.println(c1.toString());
         */

        test.addCar(6, "ABC", "A", "1.5 litters", false, false);
        test.addCus("Sy", "123456");
        System.out.println(test);
        test.searchCarId(1);

    }
}

/*
 * TO DO:
 * addCar()----------DONE----NOT TESTED
 * updateCar()-------DONE----NOT TESTED
 * removeCar()-------DONE----NOT TESTED
 * searchIdCar()-----DONE----NOT TESTED
 * viewCar()---------DONE----NOT TESTED
 * 
 *
 * addCus()----------DONE----NOT TESTED
 * updateCus()-------DONE----NOT TESTED
 * removeCus()-------DONE----NOT TESTED
 * searchIdCus()-----DONE----NOT TESTED
 * viewCus()---------DONE----NOT TESTED
 * 
 * IMPLEMENT THE BLACKLIST, DISCOUNT
 */