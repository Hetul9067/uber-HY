package main.builderPassenger;

import main.passenger.Passenger;
import main.passenger.PassengerRegister;

public class AdultPassengerBuilder implements IPassengerBuilder{

    private Passenger passenger;

    public AdultPassengerBuilder(){
        passenger = new Passenger();
    }

    @Override
    public void buildNameAndGender(PassengerRegister passengerRegister) {
        passenger.setName(passengerRegister.getName());
        passenger.setGender(passengerRegister.getGender());

    }

    @Override
    public void buildPhoneNumberAndEmail(PassengerRegister passengerRegister) {
        passenger.setPhoneNumber(passengerRegister.getPhoneNumber());
        passenger.setEmail(passengerRegister.getEmail());
    }

    @Override
    public void buildPassword(PassengerRegister passengerRegister) {
        passenger.setPassword(passengerRegister.getPassword());
    }

    @Override
    public void buildWalletAndAdult(PassengerRegister passengerRegister) {
        passenger.setWallet(passengerRegister.getWallet());
        passenger.setAdult(passengerRegister.isAdult());
    }

    @Override
    public Passenger getPassenger() {
        return this.passenger;
    }
}
