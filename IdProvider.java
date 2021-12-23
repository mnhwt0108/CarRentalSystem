/*
 * Class used to manage the IDs for all objects using singleton-pattern
 */
public class IdProvider {

    private static IdProvider instanceCar = null;

    /*
     * Method used to generate instances of objects in order to manage all IDs
     */
    public static IdProvider getCarInstance() {
        if (instanceCar == null) {
            instanceCar = new IdProvider();
        }

        return instanceCar;
    }

    private int nextCarId = 0;

    /*
     * Method used to generate unique IDs
     */
    public int getCarUniqueId() {
        // in case of an overflow
        if (nextCarId < 0) {
            throw new IllegalStateException("Out of IDs!");
        }

        int uniqueCarId = nextCarId;
        nextCarId++;

        return uniqueCarId;
    }

    /*
     * Same as above but for the "customer" class
     */
    private static IdProvider instanceCus = null;

    public static IdProvider getCusInstance() {
        if (instanceCus == null) {
            instanceCus = new IdProvider();
        }

        return instanceCus;
    }

    private int nextCusId = 0;

    public int getCusUniqueId() {
        if (nextCusId < 0) {
            throw new IllegalStateException("Out of IDs!");
        }

        int uniqueCusId = nextCusId;
        nextCusId++;

        return uniqueCusId;
    }
}
