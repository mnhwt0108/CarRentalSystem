public class RentalRecord {
    private String rentId;
    private DateTime rentDate, ExpectedReturnDate, ActualReturnDate;
    private Double rentalFee, lateFee;

    // use when a new rental record is generated or when the car haven't been returned
    public RentalRecord(String rentId, DateTime rentDate, DateTime expectedReturnDate) {
        this.rentId = rentId;
        this.rentDate = rentDate;
        this.ExpectedReturnDate = expectedReturnDate;
    }

    // use when a car have been returned
    public void setData(DateTime actualReturnDate, Double rentalFee, Double lateFee) {
        this.ActualReturnDate = actualReturnDate;
        this.rentalFee = rentalFee;
        this.lateFee = lateFee;
    }

    public DateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(DateTime rentDate) {
        this.rentDate = rentDate;
    }

    public DateTime getExpectedReturnDate() {
        return ExpectedReturnDate;
    }

    public void setExpectedReturnDate(DateTime expectedReturnDate) {
        ExpectedReturnDate = expectedReturnDate;
    }

    public DateTime getActualReturnDate() {
        return ActualReturnDate;
    }

    public void setActualReturnDate(DateTime actualReturnDate) {
        ActualReturnDate = actualReturnDate;
        this.setLateFee(5.0 * DateTime.diffDays(ExpectedReturnDate, ActualReturnDate));
    }

    public Double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Double rentalFee) {
        this.rentalFee = rentalFee;
    }

    @Override
    public String toString() {
        if (this.ActualReturnDate == null && this.rentalFee == null & this.lateFee == null) {
            String data = this.rentId + ":" + this.rentDate.toString() + ":" + this.ExpectedReturnDate.toString()
                    + ":none:none:none";
            return data;
        }

        else {
            return this.rentId + ":" + this.rentDate.toString() + ":" + this.ExpectedReturnDate.toString() + ":"
                    + this.ActualReturnDate.toString() + ":" + this.rentalFee.toString() + ":"
                    + this.lateFee.toString();
        }
    }

    
    public void getDetails() {
        if (this.ActualReturnDate == null || this.rentalFee == null || this.lateFee == null) {
            String data = "Record ID           : " + this.rentId
                    + "\nRent Date           : " + this.rentDate.toString()
                    + "\nExpected Return Date: " + this.ExpectedReturnDate.toString();
            System.out.println(data);
        }

        else {
            System.out.printf("%-22s%-18s  *\n", "*   Record ID           : ", this.rentId);
            System.out.printf("%-22s%-18s  *\n", "*   Rent Date           : ", this.rentDate.toString());
            System.out.printf("%-22s%-18s  *\n", "*   Expected Return Date: ", this.ExpectedReturnDate.toString());
            System.out.printf("%-22s%-18s  *\n", "*   Actual Return Date  : ", this.ActualReturnDate.toString());
            System.out.printf("%-22s%-18s  *\n", "*   Rental Fee          : ",
                    String.format("%.2f", this.rentalFee * DateTime.diffDays(rentDate, ExpectedReturnDate)));
            System.out.printf("%-22s%-18s  *\n", "*   Late Fee            : ", String.format("%.2f", this.lateFee));
        }
    }

    public double calculateBill() {
        // rent calculation
        long totalTime = DateTime.diffDays(rentDate, ActualReturnDate);
        long expected = DateTime.diffDays(rentDate, ExpectedReturnDate);
        double late = 5.0 * DateTime.diffDays(ExpectedReturnDate, ActualReturnDate);

        if (totalTime <= expected) {
            return (double) (rentalFee * totalTime);
        } else if (totalTime > expected) {
            return (double) ((rentalFee * expected) + late);
        }
        return rentalFee;
    }

    public Double getLateFee() {
        return lateFee;
    }

    private void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

}
