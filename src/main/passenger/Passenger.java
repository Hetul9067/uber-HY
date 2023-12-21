package main.passenger;

import main.driver.DriverU;

public class Passenger {

    private int passenger_id;
    private String name;
    private String gender;
    private long phoneNumber;
    private String email;
    private String password;
    private boolean adult;
    //private Payment payment;

    private NotificationPassenger notification;

    private Wallet wallet;
    private BookRide bookRide;

    private boolean isTeenAccount;
    private DriverU ride;

    public Passenger(){
    }
    public void addPayment(){}

    public void updateDetails(){}
    public void checkNotification(){
    }

    public void deleteMessage(){
    }

    public void markMessage(){}
    public void bookRide(){}
    public void displayPassengerMenu(){}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NotificationPassenger getNotification() {
        return notification;
    }

    public void setNotification(NotificationPassenger notification) {
        this.notification = notification;
    }

    public boolean isTeenAccount() {
        return isTeenAccount;
    }

    public void setTeenAccount(boolean teenAccount) {
        isTeenAccount = teenAccount;
    }

    public DriverU getRide() {
        return ride;
    }

    public void setRide(DriverU ride) {
        this.ride = ride;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public BookRide getBookRide() {
        return bookRide;
    }

    public void setBookRide(BookRide bookRide) {
        this.bookRide = bookRide;
    }
}
