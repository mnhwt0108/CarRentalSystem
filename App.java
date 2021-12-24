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
                System.out.println(carList.get(id).records[i].getDetails());
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
                System.out.println(carList.get(id).records[index].getDetails());
        }
    }

    /*
     * Method used to rent a car by calling the method rent in class Car
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
            viewRecord(id, index);
        }
    }

    public static void main(String[] args) throws Exception {

        App test = new App();

        test.addCar(6, "ABC", "A", "1.5 litters", false, false);
        test.addCus("Sy", "123456");
        // System.out.println("\n*********************\n" + test);
        // test.rentCar(1, 1, 18, 12, 2021, 3);
        System.out.println("\n*******************");
        test.viewCar(1);
        test.updateCar(1, 4, "CBA", "C", "6 cylinders");
        System.out.println("\n*******************");
        test.viewCar(1);
        System.out.println("\n\n\n*******************");
        test.viewCus(1);
        test.updateCus(1, "Syx", "654321");
        System.out.println("\n*******************");
        test.viewCus(1);
        // test.returnCar(1, 0, 21, 12, 2021);
        System.out.println("\n\n\n*********************\n" + test);
        test.removeCar(1);
        System.out.println("\n\n\n*********************\n" + test);
        test.removeCus(1);
        System.out.println("\n\n\n*********************\n" + test);

        // test.searchCarId(1);
        // System.out.println("\n*******************");
        // test.viewRentalRecord(1);
        // System.out.println("\n*******************");
        // test.viewCar(1);
        // System.out.println("\n*******************");
        // test.viewCus(1);
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