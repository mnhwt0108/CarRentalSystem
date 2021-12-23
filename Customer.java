public class Customer {
    private String ID, Name, contactNo;
    private int Bill;

    public Customer(String name, String contactNo) {
        ID = "Cus#" + IdProvider.getCusInstance().getCusUniqueId(); // auto increment of customer's ID
        Name = name;
        this.contactNo = contactNo;
    }

    public void setCus(String name, String contactNo) {
        this.Name = name;
        this.contactNo = contactNo;
    }

    public String getID() {
        return ID;
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
     * pay()
     */

}
