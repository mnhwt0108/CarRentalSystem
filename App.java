public class App {
    public static void main(String[] args) throws Exception {
        Car c1 = new Car(1, 6, "ABC", "A", "1.5 litter", false, false);
        Customer cus1 = new Customer(1, "Sy", "123456", 100);
        System.out.println(c1.toString());
        System.out.println(cus1.toString());
    }
}
