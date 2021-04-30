package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ClosestAirport {

    private static Location location;
    private static final String AIRPORTOPTIONS = "4";

    public ClosestAirport(Location _location){
        this.location = _location;
    }


    public static JSONArray getAirportInfo() throws JSONException {
        // Create a HTTP Connection.
        String baseUrl = "https://nearby-airport.p.rapidapi.com/airport/nearby?longitude=";
        String latitude = location.getLatitude();
        String longitude = location.getLongitude();
        String urlString = baseUrl + longitude + "&latitude=" + latitude + "&limit=" + AIRPORTOPTIONS;
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

    public String[] findAirports() throws JSONException, NoNearbyAirportsException {
        try {
            JSONArray array = getAirportInfo();
            String[] airports = new String[Integer.parseInt(AIRPORTOPTIONS)];
            ArrayList<String> airportCodes = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(AIRPORTOPTIONS); i++) {
                JSONObject object = array.getJSONObject(i);
                airports[i] = object.getString("code");
            }
            return airports;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}
