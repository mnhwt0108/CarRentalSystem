public class RentalRecord {
    private String rentId;
    private DateTime rentDate, ExpectedReturnDate, ActualReturnDate;
    private Double rentalFee, lateFee;

    public RentalRecord(String rentId, DateTime rentDate, DateTime expectedReturnDate) {
        this.rentId = rentId;
        this.rentDate = rentDate;
        this.ExpectedReturnDate = expectedReturnDate;
    }

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

    public String getDetails() {
        if (this.ActualReturnDate == null || this.rentalFee == null || this.lateFee == null) {
            String data = "Record ID           : " + this.rentId
                    + "\nRent Date           : " + this.rentDate.toString()
                    + "\nExpected Return Date: " + this.ExpectedReturnDate.toString();
            return data;
        }

        else {
            return "Record ID             :  " + this.rentId
                    + "\nRent Date           :  " + this.rentDate.toString()
                    + "\nExpected Return Date:  " + this.ExpectedReturnDate.toString()
                    + "\nActual Return Date  :  " + this.ActualReturnDate.toString()
                    + "\nRental Fee          :  " + String.format("%.2f", calculateBill())
                    + "\nLate Fee            :  " + String.format("%.2f", this.lateFee);
        }
    }

    public double calculateBill() {
        // rent calculation
        long totalTime = DateTime.diffDays(this.getRentDate(), this.getActualReturnDate());

        if (totalTime != 0) {
            return (double) (rentalFee * totalTime);
        }

        else {
            return this.rentalFee;
        }

    }

}
