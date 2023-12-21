package main.Repository;

import main.SQLiteConnection;
import main.handler.CustomException;
import main.passenger.Passenger;
import main.passenger.PassengerRegister;
import main.passenger.Wallet;

import java.io.File;
import java.sql.*;

public class PassengerRepository {
    public static void savePassenger(Passenger passenger){
//        SQLiteConnection connection = new SQLiteConnection();
//        Connection connection = SQLiteConnection.connectDatabase();
        insertData(passenger);
//        connection.insertData(passenger);



    }

    public static String selectCheckPassengersByEmail(){
        return "SELECT COUNT(*) AS EMAIL_COUNT" +
                " FROM PASSENGERS WHERE EMAIL = ? ";
    }

    public static String selectCheckPassengersByPhoneNumber(){
        return "SELECT COUNT(*) AS PHONE_COUNT FROM PASSENGERS WHERE PHONE_NUMBER = ? ";
    }

    public static String selectRegisterPassengersByEmail(){
        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
                " P.EMAIL, P.PASSWORD, P.ADULT, W.WALLET_ID, W.MONEY " +
                " FROM PASSENGERS P LEFT JOIN" +
                "   WALLETS W " +
                "   ON (P.WALLET_ID = W.WALLET_ID)" +
                "   WHERE P.EMAIL = ? ";
    }
    public static String selectPassengersByEmail(){
        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
                " P.EMAIL, P.PASSWORD, P.ADULT, W.WALLET_ID, W.MONEY, N.NOTIFICATION_ID " +
                " FROM PASSENGERS P LEFT JOIN" +
                "   WALLETS W " +
                "   ON (P.WALLET_ID = W.WALLET_ID)" +
                "   LEFT JOIN NOTIFICATION_PASSENGERS " +
                "   ON (P.NOTIFICATION_ID = N.NOTIFICATION_ID) WHERE P.EMAIL = ? ";
    }

    public static String selectPassengersByPhoneNumber(){
        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
                " P.EMAIL, P.PASSWORD, P.ADULT, W.WALLET_ID, W.MONEY, N.NOTIFICATION_ID " +
                " FROM PASSENGERS P LEFT JOIN" +
                "   WALLETS W " +
                "   ON (P.WALLET_ID = W.WALLET_ID)" +
                "   LEFT JOIN NOTIFICATION_PASSENGERS " +
                "   ON (P.NOTIFICATION_ID = N.NOTIFICATION_ID) WHERE P.PHONE_NUMBER = ? ";
    }

    public static String selectLoginPassengerByEmailPassword(){
        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
                " P.EMAIL, P.PASSWORD, P.ADULT, P.NOTIFICATION_ID, W.WALLET_ID, W.MONEY" +
                " FROM PASSENGERS P LEFT JOIN" +
                " WALLETS W " +
                "   ON P.WALLET_ID = W.WALLET_ID" +
                " WHERE P.EMAIL = ? AND P.PASSWORD = ? ";
    }

    public static String selectWalletsForId(){
        return "SELECT WALLET_ID FROM WALLETS ORDER BY WALLET_ID DESC LIMIT 1";
    }

    public static boolean verifyPassengerByEmail(String email){
        Passenger passenger  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try{
            connection = SQLiteConnection.connectDatabase();

            //check if the table exists in the database
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "PASSENGERS", null);

            if(!tables.next()){
                //the table doesn't exist;
                return false;

            }
            preparedStatement = connection.prepareStatement(selectCheckPassengersByEmail());
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

//            int count = 0;
            if(resultSet.next()){
                count = resultSet.getInt("EMAIL_COUNT");

            }

        }catch (SQLException e){

            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(count == 0){
            return false;
        }
        return true;
    }

    public static boolean verifyPassengerByPhoneNumber(long phoneNumber){
        Passenger passenger  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int count = 0;
        try{
            connection = SQLiteConnection.connectDatabase();

            //check if the table exists in the database
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "PASSENGERS", null);

            if(!tables.next()){
                //the table doesn't exist;
                return false;

            }
            preparedStatement = connection.prepareStatement(selectCheckPassengersByPhoneNumber());
            preparedStatement.setLong(1,phoneNumber);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                count = resultSet.getInt("PHONE_COUNT");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(count == 0){
            return false;
        }
        return true;
    }

    //for verifing customer in the database
    public static Passenger verifyPassenger(String email, String password){
        Passenger passenger = null;
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            //check if the table exists in the database
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "PASSENGERS", null);

            if(!tables.next()){
                //the table doesn't exist;
                return null;

            }
            preparedStatement = connection.prepareStatement(selectLoginPassengerByEmailPassword());
            preparedStatement.setString(1,email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
//                populate passenger details from the resultset
                passenger = new Passenger();
                passenger.setPassenger_id(resultSet.getInt("PASSENGER_ID"));
                passenger.setName(resultSet.getString( "NAME"));
                passenger.setGender(resultSet.getString("GENDER"));
                passenger.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
                passenger.setEmail(resultSet.getString("EMAIL"));
                passenger.setPassword(resultSet.getString("PASSWORD"));
                passenger.setAdult(resultSet.getBoolean("ADULT"));

                //wallet details
                Wallet wallet = new Wallet();
                wallet.setWallet_id(resultSet.getInt("WALLET_ID"));
                wallet.setMoney(resultSet.getDouble("MONEY"));
                passenger.setWallet(wallet);
            }

        }catch (SQLException e){

            System.out.println(e.toString());
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return passenger;
    }
    public static Passenger findRegisterPassengerByEmail(String email){
        Passenger passenger  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            preparedStatement = connection.prepareStatement(selectRegisterPassengersByEmail());
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                //populate passenger details from the resultset
                passenger = new Passenger();
                passenger.setPassenger_id(resultSet.getInt("PASSENGER_ID"));
                passenger.setName(resultSet.getString("NAME"));
                passenger.setGender(resultSet.getString("GENDER"));
                passenger.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
                passenger.setEmail(resultSet.getString("EMAIL"));
                passenger.setPassword(resultSet.getString("PASSWORD"));
                passenger.setAdult(resultSet.getBoolean("ADULT"));

                //wallet details
                Wallet wallet = new Wallet();
                wallet.setWallet_id(resultSet.getInt("WALLET_ID"));
                wallet.setMoney(resultSet.getDouble("MONEY"));
                passenger.setWallet(wallet);
            }

        }catch (SQLException e){

            System.out.println(e.toString());
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return passenger;
    }
    public static Passenger findPassengerByEmail(String email){
        Passenger passenger  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            preparedStatement = connection.prepareStatement(selectPassengersByEmail());
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                //populate passenger details from the resultset
                passenger = new Passenger();
                passenger.setPassenger_id(resultSet.getInt("PASSENGER_ID"));
                passenger.setName(resultSet.getString("NAME"));
                passenger.setGender(resultSet.getString("GENDER"));
                passenger.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
                passenger.setEmail(resultSet.getString("EMAIL"));
                passenger.setPassword(resultSet.getString("PASSWORD"));
                passenger.setAdult(resultSet.getBoolean("ADULT"));

                //wallet details
                Wallet wallet = new Wallet();
                wallet.setWallet_id(resultSet.getInt("WALLET_ID"));
                wallet.setMoney(resultSet.getDouble("MONEY"));
                passenger.setWallet(wallet);
            }

        }catch (SQLException e){

            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        return passenger;
    }

    public static Passenger findPassengerByPhoneNumber(long phoneNumber){
        Passenger passenger  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            preparedStatement = connection.prepareStatement(selectPassengersByPhoneNumber());
            preparedStatement.setLong(1,phoneNumber);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                //populate passenger details from the resultset
                passenger = new Passenger();
                passenger.setPassenger_id(resultSet.getInt("PASSENGER_ID"));
                passenger.setName(resultSet.getString("NAME"));
                passenger.setGender(resultSet.getString("GENDER"));
                passenger.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
                passenger.setEmail(resultSet.getString("EMAIL"));
                passenger.setPassword(resultSet.getString("PASSWORD"));
                passenger.setAdult(resultSet.getBoolean("ADULT"));

                //wallet details
                Wallet wallet = new Wallet();
                wallet.setWallet_id(resultSet.getInt("WALLET_ID"));
                wallet.setMoney(resultSet.getDouble("MONEY"));
                passenger.setWallet(wallet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        return passenger;
    }



    public static int findCurrentWalletId(){
        int wallet_id = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(selectWalletsForId());

            if(resultSet.next()){
                wallet_id = resultSet.getInt("WALLET_ID");

            }
            else{
                System.out.println("no rows found in the wallets table.");
            }

        }catch (SQLException e){

            System.out.println(e.toString());
        }finally{
            try{
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return wallet_id;
    }

//
//    public static String createNotificataions(){
//        return "CREATE TABLE IF NOT EXISTS NOTIFICATIONS"
//    }
    public static String createPassenger(){

        return "CREATE TABLE IF NOT EXISTS PASSENGERS (PASSENGER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT, GENDER TEXT, PHONE_NUMBER INTEGER UNIQUE, EMAIL TEXT UNIQUE, PASSWORD TEXT," +
                " ADULT BOOLEAN, NOTIFICATION_ID INTEGER, WALLET_ID INTEGER, DRIVER_ID INTEGER," +
                " FOREIGN KEY (NOTIFICATION_ID) REFERENCES NOTIFICATION_PASSENGERS(ID), " +
                " FOREIGN KEY (WALLET_ID) REFERENCES WALLETS(WALLET_ID), " +
                " FOREIGN KEY (DRIVER_ID) REFERENCES DRIVERS(DRIVER_ID) );";

    }

    public static String createWallet(){
        return "CREATE TABLE IF NOT EXISTS WALLETS (WALLET_ID INTEGER PRIMARY KEY AUTOINCREMENT, MONEY REAL)";
    }

    public static String createNotificationPassenger(){
        return "CREATE TABLE IF NOT EXISTS NOTIFICATION_PASSENGERS (NOTIFICATION_ID INTEGER PRIMARY KEY AUTOINCREMENT)";

    }



    public static String insertPassengers(){
        return "INSERT INTO PASSENGERS " +
                " (NAME, GENDER, PHONE_NUMBER, EMAIL, PASSWORD, ADULT, " +
                " NOTIFICATION_ID, WALLET_ID, DRIVER_ID) " +
                " VALUES (?, ?, ?, ?, ?, ? , ?, ?, ?);";
    }

    public static String insertIntoWallet(){
        return "INSERT INTO WALLETS (MONEY) VALUES (?);";
    }

    public static String insertIntoNotificationPassengers(){
        return "INSERT INTO NOTIFICATION_PASSENGERS DEFAULT VALUES;";
    }
//    public static void insertData1(Passenger passenger){
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
    public static void insertData(Passenger passenger){
        //sqlite database url
        String url = "jdbc:sqlite:data/uber.db";


        //Initialize the connection and preparedStatement variables
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        try{
            //register sqlite jdbc driver
//            Class.forName("org.sqlite.JDBC");

            //create a connection to the database
//            connection = DriverManager.getConnection(url);
            connection = SQLiteConnection.connectDatabase();
            DatabaseMetaData metaData = connection.getMetaData();
            String tableName = "PASSENGERS";

            //check if the table exists in the database
            ResultSet tables = metaData.getTables(null, null, tableName, null);

//            System.out.println(tables.next()+ " tables.next()");
            boolean tableExists = false;
            while (tables.next()) {
                String tableName1 = tables.getString("TABLE_NAME");
                if ("PASSENGERS".equalsIgnoreCase(tableName1)) {
                    tableExists = true;
                    break;
                }
            }
            if(!tableExists){

                //table does not exist, create the table
                String createPassengersTableSQL = createPassenger();
                String createWalletsTableSQL = createWallet();
                String createNotificationPassengersTableSQL = createNotificationPassenger();
                Statement statement = connection.createStatement();
                statement.execute(createNotificationPassengersTableSQL);
                statement.execute(createWalletsTableSQL);
                statement.execute(createPassengersTableSQL);

                statement.close();
            }

            String insertWalletSQL = insertIntoWallet();
            String insertNotificationsPassenger = insertIntoNotificationPassengers();
            String insertPassengerSQL = insertPassengers();

            System.out.println("Connected to the SQLite database.");
            preparedStatement1 = connection.prepareStatement(insertWalletSQL);
            preparedStatement2 = connection.prepareStatement(insertNotificationsPassenger);
            preparedStatement3 = connection.prepareStatement(insertPassengerSQL);



            //wallets table
            preparedStatement1.setDouble(1, passenger.getWallet().getMoney());


            int wallet_id = 0;
            int rowInserted1 = preparedStatement1.executeUpdate();
            if(rowInserted1 > 0){
                wallet_id = findCurrentWalletId();
            }


            //set parameters for the PreparedStatement of passengers table
            preparedStatement3.setString(1, passenger.getName());
            preparedStatement3.setString(2, passenger.getGender());
            preparedStatement3.setLong(3, passenger.getPhoneNumber());
            preparedStatement3.setString(4, passenger.getEmail());
            preparedStatement3.setString(5, passenger.getPassword());
            preparedStatement3.setBoolean(6, passenger.isAdult());
            preparedStatement3.setNull(7, Types.INTEGER);
            preparedStatement3.setInt(8, wallet_id);
            preparedStatement3.setNull(9, Types.INTEGER);

//            preparedStatement.setString(2, email);

            //execute the insert statement
            int rowsInserted1 = preparedStatement1.executeUpdate();
            int rowsInserted3 = preparedStatement3.executeUpdate();

            if(rowsInserted1 > 0){
                System.out.println("Data inserted into the Wallets table successfully");
            }else throw new SQLException("Error in insertion operation for Wallets table ");
            if(rowsInserted3 > 0){
                System.out.println("Data inserted into the Passengers table successfully");
            }else throw new SQLException("Error in insertion operation for Passengers table ");

//                connection.close();
//            }

        }catch( SQLException e){
            System.out.println(e.toString());
        }finally {
            //close the preparedstatement and connection in a finally
            try{
                if (preparedStatement1 != null){
                    preparedStatement1.close();
                }
                if(preparedStatement2 != null){
                    preparedStatement2.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }

    }

}
