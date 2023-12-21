package main.driver;

import main.HardCodedValue;
import main.handler.CustomException;
import main.logInRegister.ILogInOrRegister;


public class DriverUber implements ILogInOrRegister {

    //private variable to hold the single instance of the class
    private static DriverUber driverUber;


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
//                    register();
                    break;
                case 3:
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    System.out.println("Thank you!!!");
                    System.out.println("Successfully logout from the DriverU Portal!");
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    check = false;
                    break;

            }
        }while(check);

    }
    //it will display menu to select login or register page
    public int displayMenu(){
        System.out.println("////////////////////////////////////////////////////////////////");
        System.out.println("Welcome to the HY Ride Hailing Application's DriverU portal: ");
        System.out.println("////////////////////////////////////////////////////////////////\n\n");

        System.out.println("Work based on your availability, No restriction!");
        System.out.println("===================================================");
        System.out.println("1. LogIn");
//        System.out.println("2. Register");
        System.out.println("2. LogOut from the Uber Portal!");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;
                System.out.println("Please enter the require option(1 to 2): ");
                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());

                if(ans < 1 || ans > 2) throw new CustomException("error1");

            }catch(CustomException ce){
                checker = true;
                ce.processError(2);
            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);
            }
        }while(checker);

        return ans;
    }

    @Override
    public void login() {
        DriverLogIn login = new DriverLogIn();
//        login.displayLoginMenu();

    }

    @Override
    public void register() {
//        DriverRegister register = new DriverRegister();
//        DriverU driver =  register.displayRegistrationForm();
//        if(driver != null){
//            DriverDisplay passengerDisplay = new DriverDisplay(DriverU);
//        }
//        else {
//            System.out.println("try to register again over here or login with different account!");
//        }


    }

    //for creating singleton object of the DriverObject class
    public static DriverUber getInstance(){
        if(driverUber == null){
            driverUber = new DriverUber();
        }
        return driverUber;
    }

}
