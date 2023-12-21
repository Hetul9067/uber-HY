package main.builderPassenger;

import main.passenger.Passenger;
import main.passenger.PassengerRegister;

public interface IPassengerBuilder {
    public void buildNameAndGender(PassengerRegister passengerRegister);

    public void buildPhoneNumberAndEmail(PassengerRegister passengerRegister);

    public void buildPassword(PassengerRegister passengerRegister);



    void buildWalletAndAdult(PassengerRegister passengerRegister);

    public Passenger getPassenger();
}
