package main.driver;

import main.HardCodedValue;
import main.handler.CustomException;

//contains display menu for the driverU
public class DriverDisplay {
    private DriverU driverU;
    DriverDisplay(DriverU driverU){
        this.driverU = driverU;
    }

    public void selectedOption( ){
        boolean login = true;
        do{
            int ans = driverDisplayMenu();
            switch(ans){
                case 1:
                    break;
                case 2:
                    settingsMenu();
                    break;
                case 3:
                    messagesMenu();
                    break;
                case 4:
//                    walletMenu();
                    break;
                case 5:
                    System.out.println("Successfully logout from the HY application!");
                    System.out.println("See you soon, "+ driverU.getName());
                    login = false;
                    break;
            }
        }while(login);

    }
    public int driverDisplayMenu(){
        System.out.println("==================================");
        System.out.println("Hello , "+ driverU.getName());
        System.out.println("Welcome to the HY Ride Hailing App, DriverU portal");

        System.out.println("==================================\n\n\n");

        int ans = 0;
        boolean checker = true;
        do{
            try{
                checker = false;

                System.out.println("1. Change status!");

                System.out.println("2. Settings");
//                System.out.println("3. Messages");
//                System.out.println("4. Wallet");
                System.out.println("3. LogOut");

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
        System.out.println("Name : "+ driverU.getName());
        System.out.println("Gender : "+ driverU.getGender());
        System.out.println("PhoneNumber : "+ driverU.getPhoneNumber());
        System.out.println("Email : "+ driverU.getEmail());
        System.out.println("Password : "+ driverU.getPassword());
        System.out.println("license : "+ driverU.getLicense());
        System.out.println("status : "+ driverU.isStatusAvailable());

//        System.out.println("Wallet : "+ driverU.getWallet());



        System.out.println("==================================");

    }
//    public void messagesMenu(){
//        System.out.println("====================");
//        System.out.println("Messages : ");
//        System.out.println("====================");
//
//    }
//    public void walletMenu(){
//        System.out.println("====================");
//        System.out.println("Wallet : "+ driverU.getWallet().getMoney());
//        System.out.println("====================\n\n");
//
//        int ans = 0;
//        boolean checker = true;
//        do{
//            try{
//                checker = false;
//
//
//                System.out.println("1. Add Money!");
//                System.out.println("2. back to the main page!");
//
//                System.out.println("Please enter the require option (1 to 2): ");
//                ans = Integer.parseInt(HardCodedValue.SCANNER.nextLine());
//
//                if(ans < 1 || ans > 2) throw new CustomException("error1");
//            }catch(CustomException ce){
//                checker = true;
//                ce.processError(2);
//            }catch (Exception e){
//                checker = true;
//                System.out.println(HardCodedValue.ERROR2);
//            }
//        }while(checker);
//
//    }
}
