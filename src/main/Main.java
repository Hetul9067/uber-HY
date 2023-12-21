package main;

import main.handler.CustomException;
import main.passenger.BookRide;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

//        SQLiteConnection sql = new SQLiteConnection();
//        sql.createDatabase();
//        sql.insertData();
//

//        BookRide bookRide = new BookRide();
//        bookRide.displayBookRideMenu();1

//        double distance = bookRide.calculateTheDistance();
//        System.out.println("distance : "+ distance);


        Uber uber = Uber.getInstance();
        uber.selectedOption();
//        long l = 100L;
//        System.out.println(l);

//        try{
//            Integer.parseInt("hello");
//            boolean ans = true;
//            if(ans ) throw new CustomException("error1");
//        }catch (CustomException ce){
//            ce.processError(1);
//        }
//        System.out.println("gender is : "+ Gender.MALE);
    }
}