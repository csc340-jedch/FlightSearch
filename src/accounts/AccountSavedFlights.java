package accounts;

import api.Flight;
import db.ConnectToDB;
import static db.ConnectToDB.getDatabaseValue;
import static db.ConnectToDB.insertUpdateDataCon;


public class AccountSavedFlights {



    public static Flight[] getSavedFlights(String _username) {
        // Returns the saved flights for the user

        // Gets the number of rows and the users first flight uuid
        int count = ConnectToDB.getNumberOfRows("flight_table", "username", _username);
        int fUuid = Integer.parseInt(getDatabaseValue("flight_table", "username", _username, "flight_uuid"));


        Flight[] flights = new Flight[count];
        for( int i = 0; i < count; i++){
            int carrierID = Integer.parseInt(getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "carrier_id"));
            String carrier = getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "carrier");
            int quote = Integer.parseInt(getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "quote"));
            flights[i] = new Flight(carrierID, carrier, quote);

            fUuid++;

        }
        return flights;
    }
}
