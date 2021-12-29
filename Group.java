import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private String groupId;
    private Branch branch;
    private Set<Car> carList;

    /*
     * HashMap to store rental rate for each car group
     */
    HashMap<String, Double> rentRate = new HashMap<String, Double>() {
        {
            put("A", 1.0);
            put("B", 2.0);
            put("C", 3.0);
            put("D", 4.0);
            put("E", 5.0);
        }
    };

    public Group(String groupId, Branch branch) {
        if (isGroupValid(groupId) == false) {
            throw new IllegalArgumentException(
                    groupId + " is not a valid group. Group must be between A and E inclusive.");
        }
        this.groupId = groupId;
        this.setBranch(branch);
        carList = new HashSet<Car>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        if (this.branch != null) {
            this.branch.removeGr(this);
        }
        this.branch = branch;
        this.branch.addGr(this);
    }

    public void add(Car car) {
        carList.add(car);
    }

    public void remove(Car car) {
        carList.remove(car);
    }

    /*
     * Method used to check if the car's group attribute is within "A" and "E"
     */
    public Boolean isGroupValid(String gc) {
        for (String i : rentRate.keySet()) {
            if (gc == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Group [branch=" + branch.getBranchId() + ", groupId=" + groupId + "]";
    }

    public Collection<Car> getCarList() {
        return carList;
    }

    public String viewCarList() {
        return this.carList.toString();
    }

}
