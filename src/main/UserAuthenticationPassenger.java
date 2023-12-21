package main;

import main.Repository.PassengerRepository;
import main.passenger.Passenger;
import main.passenger.PassengerRegister;

import java.util.*;
public class UserAuthenticationPassenger {

    private List<Passenger> passengers;
    private String username;
    private String password;

    private static UserAuthenticationPassenger userAuthentication;

    // Constructors, getters, and setters

    public UserAuthenticationPassenger(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuthenticationPassenger(){

    }

    public Passenger verifyPassengerAccount(String email, String password){
        return PassengerRepository.verifyPassenger(email, password);
    }
    public boolean verifyPassengerAccountByEmail(String email) {


        return PassengerRepository.verifyPassengerByEmail(email) ;
    }

    public boolean verifyPassengerAccountByPhone(long phone){

        return PassengerRepository.verifyPassengerByPhoneNumber(phone) ;
    }
    public void createPassenger(PassengerRegister register){



    }

    public static UserAuthenticationPassenger getInstance(){
        if(userAuthentication == null){
            userAuthentication = new UserAuthenticationPassenger();
        }
        return userAuthentication;
    }
}
