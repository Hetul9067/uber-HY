package main.passenger;

import jdk.jfr.Registered;
import main.HardCodedValue;
import main.handler.CustomException;
import main.logInRegister.ILogInOrRegister;

//singleton PassengerUber object
public class PassengerUber implements ILogInOrRegister {


    //private variable to hold the single instance of the class
    private static PassengerUber passengerUber;


    public void selectOption(){
        boolean check = true;
        do {
            int ans = displayMenu();
            switch(ans){
                case 1:
                    check = true;
                    login();
                    break;
                case 2:
                    check = true;
                    register();
                    break;
                case 3:
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    System.out.println("Thank you!!!");
                    System.out.println("Successfully logout from the Passenger Portal!");
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    check = false;
                    break;

            }
        }while(check);

    }
    //it will display menu to select login or register page
    public int displayMenu(){
        System.out.println("////////////////////////////////////////////////////////////////");
        System.out.println("Welcome to the HY Ride Hailing Application's Passenger portal: ");
        System.out.println("////////////////////////////////////////////////////////////////\n\n");

        System.out.println("We are providing service to find cab easily!");
        System.out.println("===================================================");
        System.out.println("1. LogIn");
        System.out.println("2. Register");
        System.out.println("3. LogOut from the Passenger Portal!");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;
                System.out.println("Please enter the require option(1 to 3): ");
                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());

                if(ans < 1 || ans > 3) throw new CustomException("error1");

            }catch(CustomException ce){
                checker = true;
                ce.processError(3);
            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);
            }
        }while(checker);

        return ans;
    }

    @Override
    public void login() {
        PassengerLogIn login = new PassengerLogIn();
        login.displayLoginMenu();

    }

    @Override
    public void register() {
        PassengerRegister register = new PassengerRegister();
        Passenger passenger =  register.displayRegistrationForm();
        if(passenger != null){
            PassengerDisplay passengerDisplay = new PassengerDisplay(passenger);
        }
        else {
            System.out.println("try to register again over here or login with different account!");
        }


    }

    //for creating singleton object of the PassengerObject class
    public static PassengerUber getInstance(){
        if(passengerUber == null){
            passengerUber = new PassengerUber();
        }
        return passengerUber;
    }


}
