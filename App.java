
public class App {
    public static void main(String[] args) throws Exception {

        Car c1 = new Car(1, 6, "ABC", "A", "1.5 litter", false, false);
        Customer cus1 = new Customer(1, "Sy", "123456", 100);
        DateTime d1 = new DateTime(18, 12, 2021);
        DateTime d2 = new DateTime(20, 12, 2021);
        c1.rent(String.valueOf(cus1.getID()), d1, 3);
        System.out.println(c1.toString());
        System.out.println("\n" + cus1.toString());
        System.out.println("\nRental record of index " + c1.getLastElementIndex() + " of Car " + c1.getCarID() + ":\n"
                + c1.records[c1.getLastElementIndex()].getDetails());
        c1.records[c1.getLastElementIndex()].setActualReturnDate(d2);
        System.out.println("\nTotal bill: " + c1.records[c1.getLastElementIndex()].calculateBill());
    }
}
