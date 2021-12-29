public class VinaRentSystemTest {

    public static void main(String[] args) throws Exception {
        VinaRentSystem test = new VinaRentSystem();
        /*
         * Atomic use case 1: add a branch
         */
        test.addBranch("ABC", "123");
        /*
         * Atomic use case 2: add a group
         */
        test.addGroup("A", "Br#1");
        /*
         * Atomic use case 3: add a model
         */
        test.addModel("Model#1", "auto", "1.5 liters", 4);
        /*
         * Atomic use case 4: add a customer
         */
        test.addCus("Sy", "147258369");
        /*
         * Atomic use case 5: add a car
         */
        test.addCar(1, 1);
        /*
         * Proof
         */
        System.out.println("*****************************\nATOMIC USE CASE 1 TO 5\n");
        System.out.println(test);

        /*
         * Atomic use case 6: view cars that are in specific branch and group
         */
        test.addModel("Model#2", "auto", "1.5 liters", 4);
        test.addModel("Model#3", "manual", "6 cylinders", 6);
        test.addCar(2, 1);
        test.addCar(3, 1);
        System.out.println("\n\n*****************************\nATOMIC USE CASE 6\n");
        test.viewCarSpecific("Br#1", "A");

        /*
         * Atomic use case 7: record a return of a car
         */
        System.out.println("*****************************\nATOMIC USE CASE 7\n");
        test.rentCar(1, 1, 29, 12, 2021, 4);
        test.returnCar(1, 0, 30, 12, 2021, "Br#1");

        /*
         * Atomic use case 8: pair neighbor of branches
         */
        test.addBranch("DEF", "456");
        System.out.println("*****************************\nATOMIC USE CASE 8\n");
        test.pairNeighborBr("Br#1", "Br#2");
        System.out.println(test.brList);

        /*
         * Attempts of in-valid input
         */
        System.out.println("\n\n*****************************\nIN-VALID ATTEMPTS\n");
        try {
            test.addGroup("G", "Br#1");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.addModel("Model#2", "nope", "1.5 liters", 4);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.addModel("Model#2", "auto", "1 liters of water", 4);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.rentCar(1, 1, 20, 12, 2021, 4);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.returnCar(1, 0, 30, 12, 2021, "Br#4");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.rentCar(1, 1, 29, 12, 2021, 4);
            test.rentCar(1, 1, 29, 12, 2021, 4);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.viewCarSpecific("Br#4", "A");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            test.pairNeighborBr("Br#3", "Br#5");
        } catch (Exception e) {
            System.out.println(e);
        }

        /*
         * EXTRA METHODS
         */
        System.out.println("\n\n*****************************\nEXTRA METHODS\n");
        test.addCar(3, 1);
        System.out.println("BEFORE PICK UP:");
        test.reserveCar(3, 1, 30, 12, 2021, 3);
        System.out.println("\nAFTER PICK UP:");
        test.pickUpCar(3, 0);

        test.inspectList();

        System.out.println("\n\nAFTER MOVED:");
        int[] a = new int[] { 0, 1, 2 };
        test.moveCars("A", a);
        System.out.println(test.carList);
        test.checkMaintenance(1);
        test.checkRented(2);
    }
}
