package main.driver;

import main.Repository.DriverRepository;

import java.util.List;

public class UserAuthenticationDriver {


    private List<DriverU> passengers;
    private String username;
    private String password;

    private static UserAuthenticationDriver userAuthentication;

    // Constructors, getters, and setters

    public UserAuthenticationDriver(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuthenticationDriver(){

    }
    public DriverU verifyDriverAccount(String email, String password){
        return DriverRepository.verifyDriver(email, password);
    }
    public boolean verifyDriverAccountByEmail(String email) {


        return DriverRepository.verifyDriverByEmail(email) ;
    }

    public boolean verifyDriverAccountByPhone(long phone){

        return DriverRepository.verifyDriverByPhoneNumber(phone) ;
    }

    public static UserAuthenticationDriver getInstance(){
        if(userAuthentication == null){
            userAuthentication = new UserAuthenticationDriver();
        }
        return userAuthentication;
    }
}

