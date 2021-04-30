package api;

import org.json.JSONException;
import org.json.JSONObject;

public interface FlightDataInterface {

    public int getPrice(int _index) throws JSONException, NotDirectFlightException;

    public String getCarrier(int _index) throws JSONException, NotDirectFlightException;

    public int getCarrierId(int _index) throws JSONException, NotDirectFlightException;

    public static JSONObject findFlightInformation() throws JSONException, NotDirectFlightException {
        return null;
    }

}
