package main.passenger;

import main.HardCodedValue;
import main.UserAuthenticationPassenger;
import main.handler.CustomException;

public class PassengerLogIn {
    private String email;
    private long phoneNumber;
    private String password;
    private Passenger passenger;

    private UserAuthenticationPassenger userAuthenticationPassenger;

    public PassengerLogIn(){
        userAuthenticationPassenger = UserAuthenticationPassenger.getInstance();
    }
    public void verifyAuthentication(){

        passenger =  userAuthenticationPassenger.verifyPassengerAccount(email, password);
    }

    public void displayLoginMenu(){
        System.out.println("===========================");
        System.out.println("Welcome to LogIn Page : ");
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
                if(passenger == null) throw new CustomException("error9");


            }catch(CustomException ce){
                checker = true;
                ce.processError(1);
            }catch (Exception e){
                checker = true;
                System.out.println(e.toString());
            }
        }while(checker);

        PassengerDisplay passengerDisplay = new PassengerDisplay(passenger);
        passengerDisplay.selectedOption();

    }

}
