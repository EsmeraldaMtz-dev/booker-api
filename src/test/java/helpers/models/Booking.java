package helpers.models;

public class Booking {
    private int roomId;
    private String firstName;
    private String lastName;
    private boolean depositPaid;
    private String checkIn;
    private String checkOut;
    private String email;
    private String phone;
    private Double totalPrice;

    public Booking(int roomId, String firstname, String lastname, boolean depositpaid, String checkin, String checkout, String email, String phone, Double totalprice) {
        this.roomId = roomId;
        this.firstName = firstname;
        this.lastName = lastname;
        this.depositPaid = depositpaid;
        this.checkIn = checkin;
        this.checkOut = checkout;
        this.email = email;
        this.phone = phone;
        this.totalPrice = totalprice;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "{" +
                "roomid='" + roomId + '\'' +
                "firstname='" + firstName + '\'' +
                "lastname='" + lastName + '\'' +
                "depositpaid='" + depositPaid + '\'' +
                "bookingdates= {"+
                "checkin='" + checkIn + '\'' +
                "checkout='" + checkOut + '\'' +
                "}," +
                "email='" + email + '\'' +
                "phone='" + phone + '\'' +
                ", totalprice='" + totalPrice + '\'' +'}';
    }
}
