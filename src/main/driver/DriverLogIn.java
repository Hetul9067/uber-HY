package main.driver;

import main.HardCodedValue;
import main.handler.CustomException;
import main.passenger.Passenger;

public class DriverLogIn {
    private String email;
    private String password;
    private UserAuthenticationDriver userAuthenticationDriver;
    private Passenger passenger;
    private DriverU driverU;

    public DriverLogIn(){
        userAuthenticationDriver = UserAuthenticationDriver.getInstance();
    }
    public void verifyAuthentication(){

        driverU =  userAuthenticationDriver.verifyDriverAccount(email, password);
    }

    public void displayLoginMenu(){
        System.out.println("===========================");
        System.out.println("Welcome to LogIn Page of the DriverU portal : ");
        System.out.println("===========================\n\n\n");

        boolean checker = true;

        do{
            try{


                checker = false;
                System.out.println("Enter Email address : ");
                email = HardCodedValue.SCANNER.nextLine();
                if(email.isEmpty()) throw new CustomException("error2");
                System.out.println("Enter Password : ");
                password = HardCodedValue.SCANNER.nextLine();
                if(password.isEmpty()) throw new CustomException("error2");


                System.out.println("for going back to the main page");
                System.out.println("Enter y or n");
                String back = HardCodedValue.SCANNER.nextLine();
                if(back.toLowerCase().equals("y")){
                    return;
                }
                verifyAuthentication();
                if(driverU == null) throw new CustomException("error9");


            }catch(CustomException ce){
                checker = true;
                ce.processError(1);
            }catch (Exception e){
                checker = true;
                System.out.println(e.toString());
            }
        }while(checker);

        DriverDisplay driverDisplay = new DriverDisplay(driverU);
        driverDisplay.selectedOption();

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

    public UserAuthenticationDriver getUserAuthenticationDriver() {
        return userAuthenticationDriver;
    }

    public void setUserAuthenticationDriver(UserAuthenticationDriver userAuthenticationDriver) {
        this.userAuthenticationDriver = userAuthenticationDriver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public DriverU getDriver() {
        return driverU;
    }

    public void setDriver(DriverU driverU) {
        this.driverU = driverU;
    }
}
