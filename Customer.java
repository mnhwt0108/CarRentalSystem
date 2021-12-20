public class Customer {
    private int ID;
    private String Name, contactNo;
    private int Bill;

    public Customer(int iD, String name, String contactNo, int bill) {
        ID = iD;
        Name = name;
        this.contactNo = contactNo;
        Bill = bill;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int bill) {
        Bill = bill;
    }

    @Override
    public String toString() {
        return "Customer [ ID=" + ID + ", Name=" + Name + ", contactNo=" + contactNo + ", Bill=" + Bill + "]";
    }

    /*
     * TO DO:
     * add()
     * update()
     * remove()
     * SearchID()
     * View()
     */

}
