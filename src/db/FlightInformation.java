package db;

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


    public static void saveFlight(String clientUsername, int carrierID, String carrier, int price) {
        //This is not working yet. Not sure why but it saves flight as null, null
        String[] values = { String.valueOf(clientUsername), String.valueOf(carrierID), String.valueOf(carrier), String.valueOf(price)};
        String query = ConnectToDB.constructFlightInsertQueryString("test_flights", values);
        ConnectToDB.insertUpdateDataCon(query);
        System.out.println(query);
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
        ConnectToDB.insertUpdateDataCon(query);
    }
}
