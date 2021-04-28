package accounts;

import api.Flight;

public class AccountSavedFlights {
    public static Flight[] getSavedFlights(String username) {
        // Gene: Logan, we will want to integrate this with the database and get the list of flights for this user
        // Below is an example of what we would need to do.

        Flight flight1 = new Flight(1, "A", 450);
        Flight flight2 = new Flight(2, "B", 320);
        Flight[] flights = { flight1, flight2 };
        return flights;
    }
}
