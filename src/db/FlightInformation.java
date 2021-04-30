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
        //This is will save the flight to the database
        String[] values = { clientUsername, String.valueOf(carrierID), carrier, String.valueOf(price)};
        String query = ConnectToDB.constructInsertQueryString(ConnectToDB.TBL_FLIGHTS, values);
        ConnectToDB.insertUpdateDataCon(query);
        System.out.println(query);
    }

    public static void removeFlight(String carrierID, String username) {
        //Not tested yet. Wanted to get save function right first.
        //String fUuid
        String query = "DELETE FROM flight_table WHERE carrier_id = '" + carrierID + "' AND username = '" + username + "'";
        ConnectToDB.insertUpdateDataCon(query);
    }
}
