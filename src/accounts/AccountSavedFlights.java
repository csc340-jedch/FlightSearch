package accounts;

import api.Flight;
import db.ConnectToDB;
import ui.ConfigManager;

import static db.ConnectToDB.getDatabaseValue;


public class AccountSavedFlights {



    public static Flight[] getSavedFlights(String username) {
        // Gene: Logan, we will want to integrate this with the database and get the list of flights for this user
        // Below is an example of what we would need to do.

        //This is still getting worked on...
        int count = ConnectToDB.getNumberOfRows("test_flights", "username", username);
        System.out.println(count);
        int fUuid = Integer.valueOf(getDatabaseValue("test_flights", "username", username, "flight_uuid"));
        System.out.println(fUuid);
        Flight[] flights = new Flight[count];
        for( int i = 0; i < count; i++){
            int carrierID = Integer.valueOf(getDatabaseValue("test_flights", "flight_uuid", String.valueOf(fUuid), "carrier_id"));
            String carrier = getDatabaseValue("test_flights", "flight_uuid", String.valueOf(fUuid), "carrier");
            int quote = Integer.valueOf(getDatabaseValue("test_flights", "flight_uuid", String.valueOf(fUuid), "quote"));
            System.out.println(carrierID + ", " + carrier + ", " + quote);
            fUuid++;

        }
        System.out.println(count);
        Flight flight1 = new Flight(1, "A", 450);
        Flight flight2 = new Flight(2, "B", 320);
        //Flight[] flights = { flight1, flight2 };
        return flights;
    }
}
