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
        System.out.println(count);
        int fUuid = Integer.parseInt(getDatabaseValue("flight_table", "username", _username, "flight_uuid"));
        System.out.println(fUuid);
        Flight[] flights = new Flight[count];
        for( int i = 0; i < count; i++){
            int carrierID = Integer.parseInt(getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "carrier_id"));
            String carrier = getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "carrier");
            int quote = Integer.parseInt(getDatabaseValue("flight_table", "flight_uuid", String.valueOf(fUuid), "quote"));
            System.out.println(carrierID + ", " + carrier + ", " + quote);
            flights[i] = new Flight(carrierID, carrier, quote);
            System.out.println(flights[i]);
            fUuid++;
            System.out.println(fUuid);

        }
        System.out.println(count);
        /*Flight flight1 = new Flight(1, "A", 450);
        Flight flight2 = new Flight(2, "B", 320);
        Flight[] flights = { flight1, flight2 };*/
        return flights;
    }
}
