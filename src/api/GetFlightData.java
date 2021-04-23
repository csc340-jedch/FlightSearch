package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetFlightData implements FlightDataInterface {

    private static String departureDate;
    private static String originAirport;

    //Constructor
    public GetFlightData(String _departureDate, String _originAirport){
        this.departureDate = _departureDate;
        this.originAirport = _originAirport;
    }

    //API connection
    public static JSONObject findFlightInformation() {

        //URL concatenation
        String baseURl = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US" +
                "/USD/en-US/SFO-sky/";
        String airportPiece = originAirport + "-sky/";
        String datePiece = departureDate;
        String finalURL = baseURl + airportPiece + datePiece;
        System.out.println("Final URL: " + finalURL);
        URL url;

        try {
            //URL connection
            url = new URL(finalURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-rapidapi-key","1c51e09a6dmshbf06f77f423fa6ap14adfdjsne9e38d557ee6");
            con.setRequestProperty("x-rapidapi-host","skyscanner-skyscanner-flight-search-v1.p.rapidapi.com" );

            //Test URL connection
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not load. Status: " + status);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader((con.getInputStream())));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                //Close connection
                in.close();
                con.disconnect();

                //Parse JSON
                JSONObject obj = new JSONObject(content.toString());
                String quotes = obj.getString("Quotes");
                String carriers = obj.getString("Carriers");

                //System.out.println("This is quote: " + quotes + "This is carriers: " + carriers);

                return obj;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String getCarrier(int _index) throws JSONException, NotDirectFlightException {
        JSONArray carrierInfoArray = getCarriers();

            JSONObject carrierInfoObject = carrierInfoArray.getJSONObject(_index);
            String carrierID = carrierInfoObject.getString("CarrierId");
            int carrierIDInteger = Integer.parseInt(carrierID);
            if (getCarrierId(0) == carrierIDInteger){
                return carrierInfoObject.getString("Name");

        }
        return "getCarrier failed";
    }

    //Checks if a flight is a direct flight. Returns the index of the flight if it is direct or an exception if it is not.
    public int isDirectFlight() throws JSONException, NotDirectFlightException {
        JSONArray quotesInfoArray = getQuotes();
        for(int i = 0; i < quotesInfoArray.length(); i++) {
            JSONObject quotesInfoObject = quotesInfoArray.getJSONObject(i);
            if (quotesInfoObject.getString("Direct").equalsIgnoreCase("true")){
                int output = Integer.parseInt(quotesInfoObject.getString("QuoteId"));
                return output;
            } else {
                throw new NotDirectFlightException("No direct flights. Contact representative for help");
            }
        }
        throw new NotDirectFlightException("No direct flights. Contact representative for help");
    }

    //Checks the price of the chosen flight that is direct.
    public String getPrice() throws JSONException, NotDirectFlightException {
        JSONArray quoteInfoArray = getQuotes();
            JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(isDirectFlight() - 1);
            return carrierInfoObject.getString("MinPrice");

    }

    public int getPrice(int _index) throws JSONException, NotDirectFlightException {
        JSONArray quoteInfoArray = getQuotes();
        JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(_index);
        return Integer.parseInt(carrierInfoObject.getString("MinPrice"));

    }

    public int getCarrierId(int _index) throws JSONException, NotDirectFlightException {
        JSONArray quoteInfoArray = getQuotes();
        JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(_index);
        JSONObject outboundLeg = carrierInfoObject.getJSONObject("OutboundLeg");
        String idWithBrackets = outboundLeg.getString("CarrierIds");
        String idWithoutBrackets = idWithBrackets.substring(1,idWithBrackets.length() - 1);
        int idAsInteger = Integer.parseInt(idWithoutBrackets);
        return idAsInteger;

    }

    //Retrieves information from the API and returns the "Quotes" section.
    public JSONArray getQuotes() throws JSONException {
        JSONObject input = findFlightInformation();
        return input.getJSONArray("Quotes");
    }

    //Retrieves information from the API and returns the "Carriers" section.
    public JSONArray getCarriers() throws JSONException {
        JSONObject input = findFlightInformation();
        return input.getJSONArray("Carriers");
    }

    public List<Flight> getFlights() throws JSONException, NotDirectFlightException {
        List<Flight> output = new ArrayList<>();
        JSONArray input = getQuotes();
        for (int i = 0; i < input.length(); i++) {
            JSONObject quotesInfoObject = input.getJSONObject(i);
            if (quotesInfoObject.getString("Direct").equalsIgnoreCase("true")) {
                Flight validFlight = new Flight(getCarrierId(i), getCarrier(i),getPrice(i));
                output.add(validFlight);
            }
        }
        return output;
    }

    public static String[] getLocalAirports(String zipcode) {
        // TODO: Gene->Thomas: If you could implement this method that would be great.
        // This should get all airports within a certain zip-code, and return as a String array or list.

        String[] airports = { "LAX" };

        return airports;
    }
}
