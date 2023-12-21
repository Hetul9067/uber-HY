package main.driver;

import main.passenger.Passenger;

public class DriverU {

//    "CREATE TABLE IF NOT EXISTS DRIVERS( " +
//            " DRIVER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//            " NAME TEXT, AGE INTEGER, PHONENUMBER INTEGER UNIQUE," +
//            " EMAIL TEXT UNIQUE, RATING REAL,   " +
//            " LICENSE TEXT, " +
//            " VERIFIED_ACCOUNT BOOLEAN, DELETE_ACCOUNT BOOLEAN, " +
//            " STATUS_AVAILABLE BOOLEAN, PASSENGER_ID INTEGER, " +
//            " FOREIGN KEY (PASSENGER_ID) REFERENCES PASSENGERS(PASSENGER_ID);";



    private int driver_id;
    private String name;
    private int age;
    private String gender ;

    private long phoneNumber;
    private String email;

    private float rating;

//    private Wallet wallet;

    //private BankAccount bankAccount;

//    private NotificationDriver notification;

//    private float currentRideFare;

//    private double totalEarning;
    private String license;
    private boolean verifiedAccount;

    private boolean deleteAccount;

    private boolean statusAvailable;
    private String password;

    private Passenger passenger;
    private int passenger_id;

    public void updateDetails(){}
    /*public void checkNotification(){}
*/
    public boolean acceptPassenger(){return true;}

    public void changeDriverStatus(){}

    public void checkEarning(){}

    public void checkRating(){}

    public void dispalyDriverMenu(){}

    public boolean sendNotificationToPassenger(){
        return true;
    }

    public void updateRideStatus(){}

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

//    public Wallet getWallet() {
//        return wallet;
//    }
//
//    public void setWallet(Wallet wallet) {
//        this.wallet = wallet;
//    }
//
//    public NotificationDriver getNotification() {
//        return notification;
//    }
//
//    public void setNotification(NotificationDriver notification) {
//        this.notification = notification;
//    }
//
//    public float getCurrentRideFare() {
//        return currentRideFare;
//    }
//
//    public void setCurrentRideFare(float currentRideFare) {
//        this.currentRideFare = currentRideFare;
//    }
//
//    public double getTotalEarning() {
//        return totalEarning;
//    }
//
//    public void setTotalEarning(double totalEarning) {
//        this.totalEarning = totalEarning;
//    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean isVerifiedAccount() {
        return verifiedAccount;
    }

    public void setVerifiedAccount(boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }

    public boolean isDeleteAccount() {
        return deleteAccount;
    }

    public void setDeleteAccount(boolean deleteAccount) {
        this.deleteAccount = deleteAccount;
    }

    public boolean isStatusAvailable() {
        return statusAvailable;
    }

    public void setStatusAvailable(boolean statusAvailable) {
        this.statusAvailable = statusAvailable;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }
}
