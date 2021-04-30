package api;

import org.json.JSONException;

import java.util.List;

public interface FlightDataInterface {
    List<Flight> getFlights() throws JSONException;
}
