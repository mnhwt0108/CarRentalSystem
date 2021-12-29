/*
 * Class used to handle the IDs for all objects using singleton-pattern
 */
public class IdProvider {

    private static IdProvider instanceCar = null;
    private int nextCarId = 1;

    /*
     * Method used to generate instances of objects in order to manage all IDs
     */
    public static IdProvider getCarInstance() {
        if (instanceCar == null) {
            instanceCar = new IdProvider();
        }

        return instanceCar;
    }

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
    private int nextCusId = 1;

    public static IdProvider getCusInstance() {
        if (instanceCus == null) {
            instanceCus = new IdProvider();
        }

        return instanceCus;
    }

    public int getCusUniqueId() {
        if (nextCusId < 0) {
            throw new IllegalStateException("Out of IDs!");
        }

        int uniqueCusId = nextCusId;
        nextCusId++;

        return uniqueCusId;
    }

    /*
     * Same as above but for the "branch" class
     */
    private static IdProvider instanceBr = null;
    private int nextBrId = 1;

    public static IdProvider getBrInstance() {
        if (instanceBr == null) {
            instanceBr = new IdProvider();
        }

        return instanceBr;
    }

    public int getBrUniqueId() {
        if (nextBrId < 0) {
            throw new IllegalStateException("Out of IDs!");
        }

        int uniqueBrId = nextBrId;
        nextBrId++;

        return uniqueBrId;
    }

    /*
     * Same as above but for the "model" class
     */
    private static IdProvider instanceModel = null;
    private int nextModelId = 1;

    public static IdProvider getModelInstance() {
        if (instanceModel == null) {
            instanceModel = new IdProvider();
        }

        return instanceModel;
    }

    public int getModelUniqueId() {
        if (nextModelId < 0) {
            throw new IllegalStateException("Out of IDs!");
        }

        int uniqueModelId = nextModelId;
        nextModelId++;

        return uniqueModelId;
    }
}
