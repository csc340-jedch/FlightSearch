package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClosestAirport {

    private final String zipCode;
    private final int MAX_AIRPORT_OPTIONS = 4;

    public ClosestAirport(String _zipCode) {
        this.zipCode = _zipCode;
    }


    private JSONArray getAirportInfo() throws JSONException {
        // Create new location
        Location loc = new Location(zipCode);

        // Create a HTTP Connection.
        String baseUrl = "https://nearby-airport.p.rapidapi.com/airport/nearby?longitude=";
        String latitude = loc.getLatitude();
        String longitude = loc.getLongitude();

        // Check if latitude and longitude are valid before using API.
        if (latitude == null || longitude == null) {
            return null;
        }

        String urlString = baseUrl + longitude + "&latitude=" + latitude + "&limit=" + MAX_AIRPORT_OPTIONS;
        URL url;
        try {
            // Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-rapidapi-key", "1c51e09a6dmshbf06f77f423fa6ap14adfdjsne9e38d557ee6");
            con.setRequestProperty("x-rapidapi-host", "nearby-airport.p.rapidapi.com");

            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not load movie: %s", status);
            } else {
                // Parsing input stream into a text string.
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();

                // Parse that object into a usable Java JSON object.
                JSONArray obj = new JSONArray(content.toString());
                return obj;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);

        }
        return null;
    }

    public String[] getLocalAirports() throws JSONException {
        JSONArray array = getAirportInfo();

        // If we do not have valid airport info, there is no airport info to show.
        if (array == null) {
            return new String[0];
        }

        String[] airports = new String[MAX_AIRPORT_OPTIONS];

        for (int i = 0; i < airports.length; i++) {
            JSONObject object = array.getJSONObject(i);
            airports[i] = object.getString("code");
        }
        return airports;
    }
}
