package main.Repository;

import main.SQLiteConnection;
import main.driver.DriverU;
import main.passenger.Passenger;
import main.passenger.Wallet;

import java.sql.*;

public class DriverRepository {

    private static DriverU driverU;
    public static String createDriver(){
        return "CREATE TABLE IF NOT EXISTS DRIVERS( " +
                " DRIVER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT, AGE INTEGER, PHONE_NUMBER INTEGER UNIQUE," +
                " EMAIL TEXT UNIQUE, RATING REAL,   " +
                " LICENSE TEXT, " +
                " VERIFIED_ACCOUNT BOOLEAN, DELETE_ACCOUNT BOOLEAN, " +
                " STATUS_AVAILABLE BOOLEAN, PASSENGER_ID INTEGER, " +
                " FOREIGN KEY (PASSENGER_ID) REFERENCES PASSENGERS(PASSENGER_ID);";

    }

//    public static String createWallet(){
//        return "CREATE TABLE IF NOT EXISTS WALLETS (WALLET_ID INTEGER PRIMARY KEY AUTOINCREMENT, MONEY REAL)";
//    }
//
//    public static String createNotificationPassenger(){
//        return "CREATE TABLE IF NOT EXISTS NOTIFICATION_PASSENGERS (NOTIFICATION_ID INTEGER PRIMARY KEY AUTOINCREMENT)";
//
//    }

    public static void savePassenger(DriverU driver){
//        SQLiteConnection connection = new SQLiteConnection();
//        Connection connection = SQLiteConnection.connectDatabase();
        insertData(driver);
//        connection.insertData(passenger);



    }

    public static String selectCheckDriverByEmail(){
        return "SELECT COUNT(*) AS EMAIL_COUNT" +
                " FROM DRIVERS WHERE EMAIL = ? ";
    }

    public static String selectCheckDrierByPhoneNumber(){
        return "SELECT COUNT(*) AS PHONE_COUNT FROM DRIVERS WHERE PHONE_NUMBER = ? ";
    }

    public static String selectRegisterDriversByEmail(){
        return "SELECT DRIVER_ID, NAME, AGE, GENDER, PHONE_NUMBER, " +
                " EMAIL, RATING, LICENSE, STATUS_AVAILABLE, , PASSWORD, PASSENGER_ID " +
                " FROM DRIVERS WHERE EMAIL = ? ";
    }
//    public static String selectRegisterDriversByEmail(){
//        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
//                " P.EMAIL, P.PASSWORD, P.ADULT, W.WALLET_ID, W.MONEY " +
//                " FROM DRIVERS D LEFT JOIN" +
//                "   WALLETS W " +
//                "   ON (P.WALLET_ID = W.WALLET_ID)" +
//                "   WHERE P.EMAIL = ? ";
//    }
    public static String selectDriversByEmail(){
        return "SELECT DRIVER_ID, NAME, AGE, GENDER, PHONE_NUMBER, " +
                " EMAIL, RATING, LICENSE, STATUS_AVAILABLE, , PASSWORD, PASSENGER_ID " +
                " FROM DRIVERS WHERE EMAIL = ? ";
    }

//    public static String selectPassengersByPhoneNumber(){
//        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
//                " P.EMAIL, P.PASSWORD, P.ADULT, W.WALLET_ID, W.MONEY, N.NOTIFICATION_ID " +
//                " FROM PASSENGERS P LEFT JOIN" +
//                "   WALLETS W " +
//                "   ON (P.WALLET_ID = W.WALLET_ID)" +
//                "   LEFT JOIN NOTIFICATION_PASSENGERS " +
//                "   ON (P.NOTIFICATION_ID = N.NOTIFICATION_ID) WHERE P.PHONE_NUMBER = ? ";
//
////        return "SELECT P.PASSENGER_ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
////                " P.EMAIL, P.PASSWORD, P.ADULT, W.WALLET_ID, W.MONEY, N.NOTIFICATION_ID " +
////                " FROM PASSENGERS P LEFT JOIN" +
////                "   WALLETS W " +
////                "   ON (P.WALLET_ID = W.WALLET_ID)" +
////                "   LEFT JOIN NOTIFICATION_PASSENGERS " +
////                "   ON (P.NOTIFICATION_ID = N.NOTIFICATION_ID) WHERE P.PHONE_NUMBER = ? ";
//    }

    public static String selectLoginDriverByEmailPassword(){
        return "SELECT DRIVER_ID, NAME, AGE, GENDER, PHONE_NUMBER, " +
                " EMAIL, RATING, LICENSE, STATUS_AVAILABLE, , PASSWORD, PASSENGER_ID " +
                " FROM DRIVERS WHERE EMAIL = ? AND PASSWORD = ?";
//        return "SELECT _ID, P.NAME, P.GENDER, P.PHONE_NUMBER, " +
//                " P.EMAIL, P.PASSWORD, P.ADULT, P.NOTIFICATION_ID, W.WALLET_ID, W.MONEY" +
//                " FROM PASSENGERS P LEFT JOIN" +
//                " WALLETS W " +
//                "   ON P.WALLET_ID = W.WALLET_ID" +
//                " WHERE P.EMAIL = ? AND P.PASSWORD = ? ";
    }

    public static String selectWalletsForId(){
        return "SELECT WALLET_ID FROM WALLETS ORDER BY WALLET_ID DESC LIMIT 1";
    }

    public static boolean verifyDriverByEmail(String email){
        Driver Driver  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try{
            connection = SQLiteConnection.connectDatabase();

            //check if the table exists in the database
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "DRIVERS", null);

            if(!tables.next()){
                //the table doesn't exist;
                return false;

            }
            preparedStatement = connection.prepareStatement(selectCheckDriverByEmail());
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

//    public static boolean verifyDriverByPhoneNumber(long phoneNumber){
//        Passenger passenger  = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        int count = 0;
//        try{
//            connection = SQLiteConnection.connectDatabase();
//
//            //check if the table exists in the database
//            DatabaseMetaData meta = connection.getMetaData();
//            ResultSet tables = meta.getTables(null, null, "DRIVERS", null);
//
//            if(!tables.next()){
//                //the table doesn't exist;
//                return false;
//
//            }
//            preparedStatement = connection.prepareStatement(selectCheckDriverByPhoneNumber());
//            preparedStatement.setLong(1,phoneNumber);
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                count = resultSet.getInt("PHONE_COUNT");
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(resultSet != null) resultSet.close();
//                if(preparedStatement != null) preparedStatement.close();
//                if(connection != null) connection.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//        if(count == 0){
//            return false;
//        }
//        return true;
//    }

    //for verifing customer in the database
    public static DriverU verifyDriver(String email, String password){
        DriverU driverU = null;
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            //check if the table exists in the database
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet tables = meta.getTables(null, null, "DRIVERS", null);

            if(!tables.next()){
                //the table doesn't exist;
                return null;

            }
            preparedStatement = connection.prepareStatement(selectLoginDriverByEmailPassword());
            preparedStatement.setString(1,email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
//                populate passenger details from the resultset
                driverU = new DriverU();
                driverU.setDriver_id(resultSet.getInt("PASSENGER_ID"));
                driverU.setName(resultSet.getString( "NAME"));
                driverU.setAge(resultSet.getInt("AGE"));
                driverU.setGender(resultSet.getString("GENDER"));
                driverU.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
                driverU.setEmail(resultSet.getString("EMAIL"));
                driverU.setPassword(resultSet.getString("PASSWORD"));
                driverU.setRating(resultSet.getFloat("RATING"));
                driverU.setLicense(resultSet.getString("LICENSE"));
                driverU.setVerifiedAccount(resultSet.getBoolean("VERIFIEDACCOUNT"));
                driverU.setDeleteAccount(resultSet.getBoolean("DELETEACCOUNT"));
                driverU.setStatusAvailable(resultSet.getBoolean("STATUSAVAILABLE"));
                driverU.setPassenger_id(resultSet.getInt("PASSENGER_ID"));
                //wallet details
//                Wallet wallet = new Wallet();
//                wallet.setWallet_id(resultSet.getInt("WALLET_ID"));
//                wallet.setMoney(resultSet.getDouble("MONEY"));
//                passenger.setWallet(wallet);
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
        return driverU;
    }
    public static DriverU findRegisterDriverByEmail(String email){
        DriverU driverU  = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteConnection.connectDatabase();
            preparedStatement = connection.prepareStatement(selectRegisterDriversByEmail());
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                //populate driver details from the resultset
                driverU = new DriverU();
                driverU.setDriver_id(resultSet.getInt("PASSENGER_ID"));
                driverU.setName(resultSet.getString( "NAME"));
                driverU.setAge(resultSet.getInt("AGE"));
                driverU.setGender(resultSet.getString("GENDER"));
                driverU.setPhoneNumber(resultSet.getLong("PHONE_NUMBER"));
                driverU.setEmail(resultSet.getString("EMAIL"));
                driverU.setPassword(resultSet.getString("PASSWORD"));
                driverU.setRating(resultSet.getFloat("RATING"));
                driverU.setLicense(resultSet.getString("LICENSE"));
                driverU.setVerifiedAccount(resultSet.getBoolean("VERIFIEDACCOUNT"));
                driverU.setDeleteAccount(resultSet.getBoolean("DELETEACCOUNT"));
                driverU.setStatusAvailable(resultSet.getBoolean("STATUSAVAILABLE"));
                driverU.setPassenger_id(resultSet.getInt("PASSENGER_ID"));
                //wallet details
//                Wallet wallet = new Wallet();
//                wallet.setWallet_id(resultSet.getInt("WALLET_ID"));
//                wallet.setMoney(resultSet.getDouble("MONEY"));
//                passenger.setWallet(wallet);
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
        return driverU;
    }








    public static String insertDrivers(){
        return "INSERT INTO DRIVERS " +
                " (NAME, AGE, GENDER, PHONE_NUMBER, EMAIL, RATING, LICENSE, VERIFIED_ACCOUNT, DELETE_ACCOUNT, STATUS_AVAILABLE, PASSWORD, PASSENGER_ID) " +
                " VALUES (?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?);";
    }


    public static void insertData(DriverU driverU){
        //sqlite database url
        String url = "jdbc:sqlite:data/uber.db";


        //Initialize the connection and preparedStatement variables
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
//        PreparedStatement preparedStatement2 = null;
//        PreparedStatement preparedStatement3 = null;

        try{
            //register sqlite jdbc driver
//            Class.forName("org.sqlite.JDBC");

            //create a connection to the database
//            connection = DriverManager.getConnection(url);
            connection = SQLiteConnection.connectDatabase();
            DatabaseMetaData metaData = connection.getMetaData();
            String tableName = "DRIVERS";

            //check if the table exists in the database
            ResultSet tables = metaData.getTables(null, null, tableName, null);

//            System.out.println(tables.next()+ " tables.next()");
            boolean tableExists = false;
            while (tables.next()) {
                String tableName1 = tables.getString("TABLE_NAME");
                if ("DRIVERS".equalsIgnoreCase(tableName1)) {
                    tableExists = true;
                    break;
                }
            }
            if(!tableExists){

                //table does not exist, create the table
                String createDriversTableSQL = createDriver();
//                String createWalletsTableSQL = createWallet();
//                String createNotificationPassengersTableSQL = createNotificationPassenger();
                Statement statement = connection.createStatement();
//                statement.execute(createNotificationPassengersTableSQL);
//                statement.execute(createWalletsTableSQL);
                statement.execute(createDriversTableSQL);

                statement.close();
            }

//            String insertWalletSQL = insertIntoWallet();
//            String insertNotificationsPassenger = insertIntoNotificationPassengers();
            String insertDriversSQL = insertDrivers();

            System.out.println("Connected to the SQLite database.");
//            preparedStatement1 = connection.prepareStatement(insertWalletSQL);
//            preparedStatement2 = connection.prepareStatement(insertNotificationsPassenger);
            preparedStatement1 = connection.prepareStatement(insertDriversSQL);



            //wallets table
//            preparedStatement1.setDouble(1, passenger.getWallet().getMoney());


//            int wallet_id = 0;
//            int rowInserted1 = preparedStatement1.executeUpdate();
//            if(rowInserted1 > 0){
//                wallet_id = findCurrentWalletId();
//            }


            //set parameters for the PreparedStatement of passengers table
//            preparedStatement3.setString(1, passenger.getName());
//            preparedStatement3.setString(2, passenger.getGender());
//            preparedStatement3.setLong(3, passenger.getPhoneNumber());
//            preparedStatement3.setString(4, passenger.getEmail());
//            preparedStatement3.setString(5, passenger.getPassword());
//            preparedStatement3.setBoolean(6, passenger.isAdult());
//            preparedStatement3.setNull(7, Types.INTEGER);
//            preparedStatement3.setInt(8, wallet_id);
//            preparedStatement3.setNull(9, Types.INTEGER);

//            preparedStatement.setString(2, email);

            //execute the insert statement
            int rowsInserted1 = preparedStatement1.executeUpdate();
//            int rowsInserted3 = preparedStatement3.executeUpdate();

//            if(rowsInserted1 > 0){
//                System.out.println("Data inserted into the Wallets table successfully");
//            }else throw new SQLException("Error in insertion operation for Wallets table ");
            if(rowsInserted1 > 0){
                System.out.println("Data inserted into the Drivers table successfully");
            }else throw new SQLException("Error in insertion operation for Drivers table ");

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
//                if(preparedStatement2 != null){
//                    preparedStatement2.close();
//                }
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }

    }

}
