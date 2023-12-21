package main.passenger;

import main.HardCodedValue;
import main.handler.CustomException;

public class PassengerDisplay {
    private Passenger passenger;
    PassengerDisplay(Passenger passenger){
        this.passenger = passenger;
    }

    public void selectedOption( ){
        boolean login = true;
        do{
            int ans = passengerDisplayMenu();
            switch(ans){
                case 1:
                    bookRideMenu();

                    break;
                case 2:
                    settingsMenu();
                    break;
                case 3:
                    messagesMenu();
                    break;
                case 4:
                    walletMenu();
                    break;
                case 5:
                    System.out.println("Successfully logout from the HY application!");
                    System.out.println("See you soon, "+ passenger.getName());
                    login = false;
                    break;
            }
        }while(login);

    }

    public void bookRideMenu(){
        passenger.setBookRide(new BookRide());
        passenger.getBookRide().displayBookRideMenu();
    }
    public int passengerDisplayMenu(){
        System.out.println("==================================");
        System.out.println("Hello , "+ passenger.getName());
        System.out.println("Welcome to the HY Ride Hailing App");
        System.out.println("Wallet : "+ passenger.getWallet().getMoney());
        System.out.println("==================================\n\n\n");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;

                System.out.println("1. For Booking the ride!");
                System.out.println("2. Settings");
                System.out.println("3. Messages");
                System.out.println("4. Wallet");
                System.out.println("5. LogOut");

                System.out.println("Please enter the require option(1 to 5) : ");
                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());

                if(ans < 1 || ans > 5) throw new CustomException("error1");


            }catch(CustomException ce){
                checker = true;
                ce.processError(5);
            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);
            }
        }while(checker);

        return ans;
    }

    public void backMenu(){
        int ans = 0;
        boolean checker = true;
        do {
            try{
                checker = false;
                System.out.println("");
                throw new CustomException("error2");
            }catch (CustomException ce ){
                checker = true;
            }
        }while(checker);

    }
    public void settingsMenu(){
        System.out.println("==============");
        System.out.println("Settings : ");
        System.out.println("==============\n\n\n");
        System.out.println("Name : "+ passenger.getName());
        System.out.println("Gender : "+ passenger.getGender());
        System.out.println("PhoneNumber : "+ passenger.getPhoneNumber());
        System.out.println("Email : "+ passenger.getEmail());
        System.out.println("Password : "+ passenger.getPassword());
        System.out.println("Wallet : "+ passenger.getWallet());
        System.out.println("IsAdult : "+ passenger.isAdult());


        System.out.println("==================================");

    }
    public void messagesMenu(){
        System.out.println("====================");
        System.out.println("Messages : ");
        System.out.println("====================");

    }
    public void walletMenu(){
        System.out.println("====================");
        System.out.println("Wallet : "+ passenger.getWallet().getMoney());
        System.out.println("====================\n\n");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;


                System.out.println("1. Add Money!");
                System.out.println("2. back to the main page!");

                System.out.println("Please enter the require option (1 to 2): ");
                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());

                if(ans < 1 || ans > 2) throw new CustomException("error1");
            }catch(CustomException ce){
                checker = true;
                ce.processError(2);
            }catch (Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);
            }
        }while(checker);

    }
}
