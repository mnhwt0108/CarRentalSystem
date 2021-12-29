public class VinaRentSystemTest {

    public static void main(String[] args, VinaRentSystem App) throws Exception {
        VinaRentSystem test = new VinaRentSystem();


        test.addCar(6, "ABC", "A", "1.5 litters", "A");
        test.addCus("Sy", "123456");
        test.addCar(6, "ABC-2", "B", "1.5 litters", "B");
        test.addCar(6, "ABC-3", "B", "1.5 litters", "B");
        test.addCar(6, "ABC-4", "B", "1.5 litters", "B");
        test.addCar(6, "ABC-5", "B", "1.5 litters", "B");

        // test.removeCar(1);
        System.out.println("*****");
        test.searchCarId(1);
        System.out.println("*****");
        System.out.println(test);

        test.reserveCar(1, 1, 28, 12, 2021, 3);
        test.rentCar(2, 1, 28, 12, 2021, 4);
        test.viewRecord(1, 0);
        test.noPickUpCar(1, 0);
        System.out.println("\n*****");
        test.viewRecord(1, 0);
        System.out.println("\n*****");
        test.returnCar(1, 0, 30, 12, 2021, "B");

        // test.inspectList();
        int[] a = new int[] { 3, 4, 5 };
        test.moveCars("A", a);
        System.out.println(test.viewCarList());
    }

}
