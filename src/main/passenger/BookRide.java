package main.passenger;

import main.HardCodedValue;
import main.driver.DriverU;
import main.handler.CustomException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BookRide {
    private String pickUpLocation ;
    private String destinationLocation;
    private List<DriverU> availableDriverU = new ArrayList<>();
    private DriverU requestDriverU;

    private float fare;
    private double distance;

    public BookRide(){

    }

    public void displayBookRideMenu(){
        System.out.println("================");
        System.out.println("Book Ride menu: ");
        System.out.println("================\n\n\n");


        int ans = 0;
        boolean checker = true;

        do{
            try{
                checker = false;
                System.out.println("enter pick-up location : ");
                pickUpLocation = HardCodedValue.SCANNER.nextLine();

                if(pickUpLocation.isEmpty()) throw new CustomException("error10");

                System.out.println("enter destination location : ");
                destinationLocation = HardCodedValue.SCANNER.nextLine();

                if(destinationLocation.isEmpty()) throw new CustomException("error11");

                System.out.println("for go to the Main page (y/n): ");
                String goBack = HardCodedValue.SCANNER.nextLine();
                if(goBack.toLowerCase().equals("y")){
                    return;
                }

                distance = calculateTheDistance();
//                distanceFinder(pickUpLocation, destinationLocation);


            }catch(CustomException ce){
                checker = true;
                ce.processError(1);
            }catch(Exception e){
                checker = true;
                System.out.println(HardCodedValue.ERROR2);
            }

        }while(checker);

    }
    public boolean verifyPickUpLocation(String pickUpLocation){
        return false;
    }

    public boolean verifyDestinationLocation(String destination){
        return false;
    }

    //try to find latitude and longitude using geocoding api

    public double[] latLongFinder(String address){
        double latitude = 0;
        double longitude = 0;
        try{


            String apiUrl = HardCodedValue.URL + address + HardCodedValue.API_KEY;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
            String jsonString = response.toString();
            try{
                JsonReader reader = Json.createReader(new StringReader(jsonString));
                JsonObject jsonObject = reader.readObject();
                JsonArray resultsArray = jsonObject.getJsonArray("results");
                if(resultsArray.size()>0){
                    JsonObject location = resultsArray.getJsonObject(0).getJsonObject("geometry").getJsonObject("location");
                    latitude = location.getJsonNumber("lat").doubleValue();
                    longitude = location.getJsonNumber("lng").doubleValue();

//                    System.out.println("Latitude : "+ latitude);
//                    System.out.println("Longitude : "+ longitude);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println(response.toString());
        }catch (Exception e){
            e.printStackTrace();
//            System.out.println(e.toString());
        }
        double[] latLong ={latitude, longitude};
        return latLong;

    }

    public double distanceFinder(String pickUpLocation, String destinationLocation) {
        double distanceInMeters = 0.0;
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            String urlStr = HardCodedValue.DISTURL + HardCodedValue.ORIGINS + pickUpLocation + HardCodedValue.DESTINATION + destinationLocation + HardCodedValue.API_KEY;
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

//                System.out.println(response.toString());

                // Parse JSON response
                try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                    JsonObject jsonResponse = jsonReader.readObject();

                    // Extract distance from JSON response
                    String distanceStr = jsonResponse.getJsonArray("rows")
                            .getJsonObject(0)
                            .getJsonArray("elements")
                            .getJsonObject(0)
                            .getJsonObject("distance")
                            .getString("text");

                    // Parse distance string to get the value in meters
                    distanceInMeters = Double.parseDouble(distanceStr.replaceAll("[^\\d.]", ""));
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("HTTP error: " + conn.getResponseCode());
            }
        }catch (NullPointerException n){
            System.out.println("Please enter correct location!");
        }
        catch (IOException io) {
            io.printStackTrace();
        } catch (JsonParsingException jsonParsingException) {
            jsonParsingException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the connection and reader
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return distanceInMeters;
    }


    //for calculating the distance
//    public double distanceFinder(String pickUpLocation, String destinationLocation){
//
//        HttpURLConnection conn= null;
//        BufferedReader reader = null;
//        double distanceInMeters = 0.0;
//        try{
//            String urlStr = HardCodedValue.DISTURL+  HardCodedValue.ORIGINS + pickUpLocation + HardCodedValue.DESTINATION + destinationLocation + HardCodedValue.API_KEY ;
//
//            //create an url object and open a connection
//            URL url = new URL(urlStr);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
//                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                StringBuilder response = new StringBuilder();
//
//                String line;
//                while((line = reader.readLine()) != null){
//                    response.append(line);
//                }
//
//
//                System.out.println(response.toString());
//                //pase json response to get the distance
////            JsonObject jsonObject = Json.createReader(new InputStreamReader(conn.getInputStream())).readObject();
//                try(JsonReader jsonReader = Json.createReader(new InputStreamReader(conn.getInputStream()));){
//                    JsonObject jsonResponse = jsonReader.readObject();
//
//
//                    //Extract distance from JSON response
//                    String distanceStr= jsonResponse.getJsonArray("rows").getJsonObject(0).getJsonArray("elements").getJsonObject(0).getJsonObject("distance").getString("text");
//                    //parse distance string to get the value in meters
//                    distanceInMeters = Double.parseDouble(distanceStr.replaceAll("[^\\d.]", ""));
//
//                }
//
//
//
//            }else{
//                System.out.println("HTTP error: "+ conn.getResponseCode());
//            }
//            //get the response
//
//            //close the connection
////            conn.disconnect();
////            reader.close();
//
//
//        }catch (IOException io){
//            io.printStackTrace();
//        }catch (JsonParsingException jsonParsingException){
//            jsonParsingException.printStackTrace();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            try{
//                //close the connection and reader
//                if(conn != null){
//                    conn.disconnect();
//                }
//                if(reader != null) reader.close();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        return distanceInMeters;
//    }
    public double calculateTheDistance(){

        double distance = 0;
        try{
            pickUpLocation = URLEncoder.encode(pickUpLocation, "UTF-8");
            destinationLocation = URLEncoder.encode(destinationLocation, "UTF-8");

            double[] latLongPickUp = latLongFinder(pickUpLocation);
            double[] latLongDestination = latLongFinder(destinationLocation);

            distance = distanceFinder(pickUpLocation, destinationLocation);
            System.out.println(" distance total(km) : "+distance );
        }catch (Exception e){
            e.printStackTrace();
//            System.out.println(e.toString());
        }
//            pickUpLocation = URLEncoder.encode(pickUpLocation, "UTF-8");
//            destinationLocation = URLEncoder.encode(destinationLocation, "UTF-8");
//
//            String apiUrl = HardCodedValue.URL + pickUpLocation + HardCodedValue.API_KEY;
//
//            URL url = new URL(apiUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while((inputLine = in.readLine()) != null){
//                response.append(inputLine);
//            }
//            in.close();
//            String jsonString = response.toString();
//            try{
//                JsonReader reader = Json.createReader(new StringReader(jsonString));
//                JsonObject jsonObject = reader.readObject();
//                JsonArray resultsArray = jsonObject.getJsonArray("results");
//                if(resultsArray.size()>0){
//                    JsonObject location = resultsArray.getJsonObject(0).getJsonObject("geometry").getJsonObject("location");
//                    double latitude = location.getJsonNumber("lat").doubleValue();
//                    double longitude = location.getJsonNumber("lng").doubleValue();
//
//                    System.out.println("Latitude : "+ latitude);
//                    System.out.println("Longitude : "+ longitude);
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            System.out.println(response.toString());
//        }catch (Exception e){
//            e.printStackTrace();
////            System.out.println(e.toString());
//        }

        return distance;
    }

    public List<DriverU> findOutAvailableRide(){

        return null;
    }

    public boolean cancelRide(){
        return false;
    }

    public void requestRide(){

    }

    public void payPayment(){

    }

}
