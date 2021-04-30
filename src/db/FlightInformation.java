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


    public static void saveFlight(String _clientUsername, int _carrierID, String _carrier, int _price) {
        //This is will save the flight to the database
        String[] values = { _clientUsername, String.valueOf(_carrierID), _carrier, String.valueOf(_price)};
        String query = ConnectToDB.constructInsertQueryString(ConnectToDB.TBL_FLIGHTS, values);
        ConnectToDB.insertUpdateDataCon(query);
        System.out.println(query);
    }

    public static void removeFlight(String _carrierID, String _username) {
        //Not tested yet. Wanted to get save function right first.
        //String fUuid
        String query = "DELETE FROM flight_table WHERE carrier_id = '" + _carrierID + "' AND username = '" + _username + "'";
        ConnectToDB.insertUpdateDataCon(query);
    }
}
