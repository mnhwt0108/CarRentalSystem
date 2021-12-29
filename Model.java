import java.util.Collection;
import java.util.HashSet;

public class Model {
    private String modelNr;
    private String modelName, transmission, fuel;
    private int numOfDoor;
    private Collection<Car> carList;

    public Model(String modelName, String transmission, String fuel, int numOfDoor) {
        if (isFuelValid(fuel) == false) {
            throw new IllegalArgumentException(
                    fuel + " is not a valid fuel. Fuel must be either '1.5 liters' or '6 cylinders' inclusive.");
        }
        if (isTransValid(transmission) == false) {
            throw new IllegalArgumentException(
                    transmission
                            + " is not a valid transmission. Transmission must be either 'auto' or 'manual' inclusive.");
        }
        carList = new HashSet<Car>();
        this.modelNr = "M#" + IdProvider.getModelInstance().getModelUniqueId(); // auto increment ID
        this.modelName = modelName;
        this.transmission = transmission;
        this.fuel = fuel;
        this.numOfDoor = numOfDoor;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelId() {
        return modelNr;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getNumOfDoor() {
        return numOfDoor;
    }

    public void setNumOfDoor(int numOfDoor) {
        this.numOfDoor = numOfDoor;
    }

    @Override
    public String toString() {
        return "Model [modelId =" + modelNr + ", modelName=" + modelName + ", transmission="
                + transmission + ", fuel=" + fuel
                + ", numOfDoor=" + numOfDoor + "]";
    }

    /*
     * Methods used to represent the one-to-many relationship between car and model
     */
    public void addCar(Car car) {
        carList.add(car);
    }

    public void removeCar(Car car) {
        carList.remove(car);
    }

    /*
     * Method used to check if the car's fuel attribute is valid
     */
    public Boolean isFuelValid(String fuel) {
        if (fuel.equalsIgnoreCase("1.5 liters") || fuel.equalsIgnoreCase("6 cylinders")) {
            return true;
        }
        return false;
    }

    /*
     * Method used to check if the car's fuel attribute is valid
     */
    public Boolean isTransValid(String transmission) {
        if (transmission.equalsIgnoreCase("auto") || transmission.equalsIgnoreCase("manual")) {
            return true;
        }
        return false;
    }
}
