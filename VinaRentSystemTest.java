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
        test.addGroup("A", 1);
        /*
         * Atomic use case 3: add a model
         */
        test.addModel("Model#1", "auto", "1.5 litters", 4);
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
        System.out.println("*****************************\nATOMIC USE CASE 1 TO 5");
        System.out.println(test);

        /*
         * Atomic use case 6: view cars that are in specific branch and group
         */
        test.addModel("Model#2", "auto", "1.5 litters", 4);
        test.addModel("Model#3", "manual", "6 cylinders", 6);
        test.addCar(2, 1);
        test.addCar(3, 1);
        System.out.println("\n\n*****************************\nATOMIC USE CASE 6");
        test.viewCarSpecific("Br#1", "A");
        /*
         * Atomic use case 7: record a return of a car
         */
        System.out.println("*****************************\nATOMIC USE CASE 7");
        test.rentCar(1, 1, 29, 12, 2021, 4);
        test.returnCar(1, 0, 30, 12, 2021, "Br#1");
        /*
         * Atomic use case 8: pair neighbor of branches
         */
        test.addBranch("DEF", "456");
        System.out.println("*****************************\nATOMIC USE CASE 8");
        test.pairNeighborBr(1, 2);
        System.out.println(test.brList);
    }
}
