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
        String[] values = { String.valueOf(clientUsername), String.valueOf(carrierID), String.valueOf(carrier), String.valueOf(price)};
        String query = ConnectToDB.constructInsertQueryString("flight_table", values);
        ConnectToDB.insertUpdateDataCon(query);
        System.out.println(query);
    }

    public static void removeFlight(int carrierID, String username) {
        //Not tested yet. Wanted to get save function right first.
        String query = "DELETE * FROM flight_table WHERE carrier_id = '" + carrierID + "' AND user_id = '" + username + "'";
        ConnectToDB.insertUpdateDataCon(query);
    }
}
