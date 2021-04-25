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
    protected String getPrice(){
        return price;
    }
    protected void setPrice(String price) {
        this.price = price;
    }
    protected String getIsDirect(){
        return isDirect;
    }
    protected void setIsDirect(String isDirect){
        this.isDirect = isDirect;
    }


    public static void saveFlight(String username, String flightId) {
        String form = "', '";
        String query = "INSERT INTO test_flight VALUES " + "('"+username+form+flightId+"')";
        QuereyFunk.insertUpdateDataCon(query);
    }

    public static void removeFlight(String carrierID, String username){
        String query = "DELETE * FROM flights WHERE carrier_id = '" + carrierID + "' AND user_id = '" + username + "'";
        QuereyFunk.selectDataCon(query);
    }
}
