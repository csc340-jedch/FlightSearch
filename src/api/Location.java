package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Location {
    private final String zipcode;
    private static final int CORRECTARRAYLOCATION = 0;

        public Location(String _zipcode){
            this.zipcode = _zipcode;
        }

        private JSONObject getLocationInfo(){
            // Create a HTTP Connection.
            String baseUrl = "https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=";
            String lastPart = "&language=en";
            String urlString = baseUrl + zipcode + lastPart;
            URL url;
            try {
                // Make the connection.
                url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("x-rapidapi-key", "1c51e09a6dmshbf06f77f423fa6ap14adfdjsne9e38d557ee6");
                con.setRequestProperty("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com");


                // Examine the response code.
                int status = con.getResponseCode();
                if (status != 200) {
                    System.out.printf("Error: Could not load movie: %s", status);
                } else {
                    // Parsing input stream into a text string.
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    // Close the connections.
                    in.close();
                    con.disconnect();

                    // Parse that object into a usable Java JSON object.
                    return new JSONObject(content.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private JSONObject getUsableObject() throws JSONException {
            JSONObject object = getLocationInfo();
            JSONArray array = object.getJSONArray("results");

            if (array.length() == 0) {
                // There are no results
                return null;
            }
            JSONObject stepOne = array.getJSONObject(CORRECTARRAYLOCATION);
            JSONObject stepTwo = stepOne.getJSONObject("geometry");
            return stepTwo.getJSONObject("location");
        }

        protected String getLatitude() throws JSONException {
            JSONObject object = getUsableObject();
            return object != null ? object.getString("lat") : null;
        }

        protected String getLongitude() throws JSONException {
            JSONObject object = getUsableObject();
            return object != null ? object.getString("lng") : null;
        }
    }
/**
[{"formatted_address":"BRWNSBORO VLG, KY 40207, USA","types":["postal_code"],
        "geometry":{"viewport":{"southwest":{"lng":-85.705247,"lat":38.228465},"northeast":{"lng":-85.61896,"lat":38.2998}},"bounds":{"southwest":{"lng":-85.705247,"lat":38.228465},"northeast":{"lng":-85.61896,"lat":38.2998}},"location":{"lng":-85.64862509999999,"lat":38.262469},"location_type":"APPROXIMATE"},
        "address_components":[{"types":["postal_code"],"short_name":"40207","long_name":"40207"},{"types":["locality","political"],"short_name":"BRWNSBORO VLG","long_name":"BRWNSBORO VLG"},{"types":["administrative_area_level_2","political"],"short_name":"Jefferson County","long_name":"Jefferson County"},{"types":["administrative_area_level_1","political"],"short_name":"KY","long_name":"Kentucky"},{"types":["country","political"],"short_name":"US","long_name":"United States"}],"place_id":"ChIJqWly4Oh0aYgR83T5fOOIOUU","postcode_localities":["BRWNSBORO VLG","Bellewood","Brownsboro Village","Druid Hills","Indian Hills","Louisville","Maryhill Estates","Mockingbird Valley","Norbourne Estates","Richlawn","Riverwood","Rolling Fields","Saint Matthews","Windy Hills","Woodlawn Park"]}]
**/