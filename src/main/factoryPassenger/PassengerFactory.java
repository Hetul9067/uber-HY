package main.factoryPassenger;

import main.builderPassenger.AdultPassengerBuilder;
import main.builderPassenger.TeenPassengerBuilder;
import main.passenger.Passenger;
import main.passenger.PassengerRegister;

public class PassengerFactory extends AbstractFactory{
    @Override
    public Passenger makeTeenPassenger(PassengerRegister passengerRegister) {
        TeenPassengerBuilder builder = new TeenPassengerBuilder();
        builder.buildNameAndGender(passengerRegister);
        builder.buildPhoneNumberAndEmail(passengerRegister);
        builder.buildPassword(passengerRegister);
        builder.buildWalletAndAdult(passengerRegister);
        return builder.getPassenger();
    }

    @Override
    public Passenger makeAdultPassenger(PassengerRegister passengerRegister) {
        AdultPassengerBuilder builder = new AdultPassengerBuilder();
        builder.buildNameAndGender(passengerRegister);
        builder.buildPhoneNumberAndEmail(passengerRegister);
        builder.buildPassword(passengerRegister);
        builder.buildWalletAndAdult(passengerRegister);
        return builder.getPassenger();


    }
}
