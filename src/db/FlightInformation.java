package db;

import static db.ConnectToDB.insertUpdateDataCon;
import static db.ConnectToDB.selectDataCon;

public class FlightInformation {
    String carrierID;
    String clientUsername;
    String carrierName;
    String price;
    String isDirect;


    protected String getCarrierID() {
        return carrierID;
    }

    protected void setCarrierID(String clientID) {
        this.carrierID = carrierID;
    }

    protected String getClientUsername() {
        return clientUsername;
    }

    protected void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    protected String getCarrierName() {
        return carrierName;
    }

    protected void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    protected String getPrice() {
        return price;
    }

    protected void setPrice(String price) {
        this.price = price;
    }

    protected String getIsDirect() {
        return isDirect;
    }

    protected void setIsDirect(String isDirect) {
        this.isDirect = isDirect;
    }


    public static void saveFlight(String username, int carrierID) {
        //This is not working yet. Not sure why but it saves flight as null, null
        String form = "', '";
        String query = "INSERT INTO test_flights VALUES " + "('" + username + form + carrierID + "')";
        insertUpdateDataCon(query);
        System.out.println(username + form + carrierID);
    }

    /*public static void saveFlight(String username, String carrierID, String carrierName, String price ) {
        String form = "', '";
        String query = "INSERT INTO test_flight VALUES " + "('"+username+form+carrierID+form+carrierName+form+price+"')";
        QuereyFunk.insertUpdateDataCon(query);
        }
     */

    public static void removeFlight(String carrierID, String username) {
        //Not tested yet. Wanted to get save function right first.
        String query = "DELETE * FROM flights WHERE carrier_id = '" + carrierID + "' AND user_id = '" + username + "'";
        selectDataCon(query);
    }
}
