package main;

import main.driver.DriverU;
import main.handler.CustomException;
import main.passenger.Passenger;
import main.passenger.PassengerUber;

import java.util.*;
public class Uber {

    private List<Passenger> passengers;
    private List<DriverU> driverUSES;

    //private static variable to hold the single instance of the class
    private static Uber uber;
    private double totalEarning;

    public Uber(){

    }

    public Uber getUberInstance(){
        return null;
    }



    //display the uber main menu for passenger and driver
    public int displayUberMenu(){
        System.out.println("###################");
        System.out.println("Welcome to the HY (Ride hailing service)");
        System.out.println("###################\n\n\n");
        System.out.println("1. Passenger application!");
        System.out.println("2. DriverU application!");
        System.out.println("3. LogOut from the main application!");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;
                System.out.println("Please enter the require option(1 to 3) : ");
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


    //for going to the passenger portal of this application
    public void passengerUber(){
        PassengerUber.getInstance().selectOption();
    }

    //for going to the driver portal of this application
    public void driverUber(){

    }
    //for calling passenger or driver functionality based on selected option in displayUberMenu()
    public void selectedOption(){
        boolean check = true;
        do{
            int ans = displayUberMenu();
            switch(ans){
                case 1:
                    passengerUber();
                    break;
                case 2:
                    driverUber();
                    break;
                case 3:
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    System.out.println("Thank you!!!");
                    System.out.println("Successfully logout from the HY (Ride hailing service)!");
                    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                    check = false;
                    break;
            }
        }while(check);
    }

    public static Uber getInstance(){
        if(uber == null){
            uber = new Uber();
        }
        return uber;
    }
    public double getTotalEarning(){
        return 0;
    }


}
