public class RentalRecord {
    private String rentId;
    private Car car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
        if (this.ActualReturnDate == null && this.rentalFee == null && this.lateFee == null) {
            String data = "Record ID:\t" + this.rentId
                    + "\nRent Date:\t" + this.rentDate.toString()
                    + "\nExpected Return Date:" + this.ExpectedReturnDate.toString();
            return data;
        }

        else {
            return "Record ID             :  " + this.rentId
                    + "\nRent Date           :  " + this.rentDate.toString()
                    + "\nExpected Return Date:  " + this.ExpectedReturnDate.toString()
                    + "\nActual Return Date  :  " + this.ActualReturnDate.toString()
                    + "\nRental Fee          :  " + String.format("%.2f", this.rentalFee)
                    + "\nLate Fee            :  " + String.format("%.2f", this.lateFee);
        }
    }

    /*
     * public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
     * String groupOfCar = this.getCar().getGroup();
     * switch (groupOfCar) {
     * case "A":
     * this.rentalFee = 1.0;
     * break;
     * 
     * case "B":
     * this.rentalFee = 2.0;
     * break;
     * 
     * case "C":
     * this.rentalFee = 3.0;
     * break;
     * 
     * case "D":
     * this.rentalFee = 4.0;
     * break;
     * 
     * case "E":
     * this.rentalFee = 5.0;
     * break;
     * }
     * 
     * }
     */

    /*
     * public double calculateBill(){
     * // rent calculation
     * long rentTime = this.getRentDate();
     * long returnTime = this.getActualReturnDate();
     * long totalTime = returnTime - rentTime;
     * 
     * if (totalTime != 0){
     * return (double) (rentalFee*totalTime);
     * }
     * 
     * else{
     * return this.rentalFee;
     * }
     * 
     * }
     */

}
