package accounts;

import api.Flight;
import db.ConnectToDB;

import static db.ConnectToDB.getDatabaseValue;


public class AccountSavedFlights {


    public static Flight[] getSavedFlights(String _username) {
        // Gene: Logan, we will want to integrate this with the database and get the list of flights for this user
        // Below is an example of what we would need to do.

        //This is still getting worked on...
        int count = ConnectToDB.getNumberOfRows("flight_table", "username", _username);
        String uuid = getDatabaseValue("flight_table", "username", _username, "flight_uuid");

        if (uuid == null) {
            return new Flight[0];
        }

        int fUuid = Integer.parseInt(uuid);
        Flight[] flights = new Flight[count];
        for (int i = 0; i < count; i++) {
            int carrierID = Integer.parseInt(getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "carrier_id"));
            String carrier = getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "carrier");
            int quote = Integer.parseInt(getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "quote"));
            flights[i] = new Flight(carrierID, carrier, quote);
            fUuid++;

        }
        return flights;
    }
}
