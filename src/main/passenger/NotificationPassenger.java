package main.passenger;

import java.util.ArrayList;
import java.util.List;

public class NotificationPassenger {

    private int Notification_id;
    private List<String> newMessages = new ArrayList<>();
    private List<String> readMessages = new ArrayList<>();



    public void sendAcceptPassengerMsg(String msg){

    }

    public void sendPickUpMessage(String msg){}
    public void sendDropOffMessage(String msg){}
    public void paymentDone(String msg){}

    public int getNotification_id() {
        return Notification_id;
    }

    public void setNotification_id(int notification_id) {
        Notification_id = notification_id;
    }
}
