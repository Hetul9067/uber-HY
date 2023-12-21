package main;

import java.io.File;
import java.sql.*;
import java.lang.*;

import main.passenger.Passenger;
import org.sqlite.JDBC;
public class SQLiteConnection {


    public static Connection connectDatabase(){
        //get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        System.out.println(currentDirectory+" : see here");
        File directory = new File(currentDirectory+"\\data");
        if(!directory.exists()){
            directory.mkdirs();//creating the directory
            System.out.println("directory created");
        }
        //specify the relative path within the project to the database
        String relativePath = "data\\uber.db";

        //Combine the current directory with the relative path
        String databasePath = currentDirectory + File.separator + relativePath;

        //create a connection to the sqlite database ( this will create the database if it doesn't exist)
        String url = "jdbc:sqlite:" + databasePath;
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
            if(connection != null){
                System.out.println("Connected to the SQLite database.");
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return connection;
    }


//    public static String createPassenger(){
//        return "CREATE TABLE IF NOT EXISTS PASSENGERS (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " NAME TEXT, GENDER TEXT, PHONENUMBER INTEGER, EMAIL TEXT, PASSWORD TEXT," +
//                " ADULT BOOLEAN, NOTIFICATION_ID INTEGER, WALLET_ID INTEGER, RIDE_ID INTEGER," +
//                " FOREIGN KEY (NOTIFICATION_ID) REFERENCES NOTIFICATIONPASSENGER(ID), " +
//                " FOREIGN KEY (WALLET_ID) REFERENCES WALLET(ID), " +
//                " FOREIGN KEY (RIDE_ID) REFERENCES DRIVER(ID) );";
//
//    }
//
//    public static String createWallet(){
//        return "CREATE TABLE IF NOT EXISTS WALLET (WALLET_ID INTEGER PRIMARY KEY AUTOINCREMENT, MONEY REAL)";
//    }
//
//    public static String createNotificationPassenger(){
//        return "CREATE TABLE IF NOT EXISTS NOTIFICATION_PASSENGER (NOTIFICATION_ID INTEGER PRIMARY KEY AUTOINCREMENT)";
//
//    }


//    public static void insertData(Passenger passenger){
//        String url = "jdbc:sqlite:data/passenger.db";
//
//        try{
//            Connection connection = DriverManager.getConnection(url);
//            DatabaseMetaData metaData = connection.getMetaData();
//            String tableName = "PASSENGERS";
//
//            //check if the table exists in the database
//            ResultSet tables = metaData.getTables(null, null, tableName, null);
//
//            if(!tables.next()){
//                //table does not exist, create the table
//                String createTableSQL = "CREATE TABLE " +
//            }
//        }catch(Exception e){
//
//        }
//    }
//    public void insertData(Passenger passenger){
//        //sqlite database url
//        String url = "jdbc:sqlite:data/passenger.db";
//
//
//        //Initialize the connection and preparedStatement variables
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try{
//            //register sqlite jdbc driver
//            Class.forName("org.sqlite.JDBC");
//
//            //create a connection to the database
//            connection = DriverManager.getConnection(url);
//
//            DatabaseMetaData metaData = connection.getMetaData();
//            String tableName = "PASSENGERS";
//
//            //check if the table exists in the database
//            ResultSet tables = metaData.getTables(null, null, tableName, null);
//
//            if(!tables.next()){
//
//                //table does not exist, create the table
//                String createTableSQL = "CREATE TABLE "+ tableName + " ( "
//            }
//
//            String insertSQL = "INSERT INTO  (NAME, EMAIL) VALUES (?, ? )";
//
////            if(connection != null){
//                System.out.println("Connected to the SQLite database.");
//                preparedStatement = connection.prepareStatement(insertSQL);
//
//
////                }
//            //data to be inserted
//            String name = "Hetul Chaudhary";
//            String email = "hetul@gmail.com";
//
//            //set parameters for the PreparedStatement
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, email);
//
//            //execute the insert statement
//            int rowsInserted = preparedStatement.executeUpdate();
//
//            if(rowsInserted > 0){
//                System.out.println("Data inserted successfully");
//            }
//
////                connection.close();
////            }
//
//        }catch(ClassNotFoundException | SQLException e){
//            e.printStackTrace();
//        }finally {
//            //close the preparedstatement and connection in a finally
//            try{
//                if (preparedStatement != null){
//                    preparedStatement.close();
//                }
//                if(connection != null){
//                    connection.close();
//                }
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//
//    }



}
