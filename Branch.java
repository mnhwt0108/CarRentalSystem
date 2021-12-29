import java.util.Collection;
import java.util.HashSet;

public class Branch {
    private String branchId, address, phone, neighborBrach;
    private Collection<Group> grList;

    public Branch(String address, String phone) {
        grList = new HashSet<Group>();
        this.branchId = "Br#" + IdProvider.getBrInstance().getBrUniqueId(); // auto increment ID
        this.address = address;
        this.phone = phone;
    }

    public String getNeighborBrach() {
        return neighborBrach;
    }

    public void setNeighborBrach(String neighborBrach) {
        this.neighborBrach = neighborBrach;
    }

    @Override
    public String toString() {
        return "Branch [address=" + address + ", branchId=" + branchId + ", phone=" + phone + ", neighborBranch="
                + neighborBrach + "]";
    }

    public void addGr(Group gr) {
        grList.add(gr);
    }

    public void removeGr(Group gr) {
        grList.remove(gr);
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
