package main.factoryPassenger;

import main.passenger.Passenger;
import main.passenger.PassengerRegister;

public abstract class AbstractFactory {
    private static PassengerFactory passengerFactory;

    public abstract Passenger makeTeenPassenger(PassengerRegister passengerRegister);

    public abstract Passenger makeAdultPassenger(PassengerRegister passengerRegister);
    public static PassengerFactory getPassengerFactory(){
        if(passengerFactory == null){
            passengerFactory = new PassengerFactory();

        }
        return passengerFactory;
    }
}
