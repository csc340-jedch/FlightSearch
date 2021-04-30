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
    private JSONObject jsonObject;

    //Constructor
    public GetFlightData(String _departureDate, String _originAirport){
        findFlightInformation(_departureDate, _originAirport);
    }

    //API connection
    private void findFlightInformation(String _departureDate, String _originAirport) {

        //URL concatenation
        String baseURl = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US" +
                "/USD/en-US/SFO-sky/";
        String airportPiece = _originAirport + "-sky/";
        String finalURL = baseURl + airportPiece + _departureDate;
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
                System.out.printf("Error: Could not load. Status: %s", status);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader((con.getInputStream())));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                System.out.println("content: " + content);

                //Close connection
                in.close();
                con.disconnect();

                //Parse JSON and save it
                jsonObject = new JSONObject(content.toString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Retrieves Carrier name
    private String getCarrier(int _index) throws JSONException {
        JSONArray carrierInfoArray = getCarriers();

            JSONObject carrierInfoObject = carrierInfoArray.getJSONObject(_index);
            String carrierID = carrierInfoObject.getString("CarrierId");
            int carrierIDInteger = Integer.parseInt(carrierID);
            if (getCarrierId(0) == carrierIDInteger){
                return carrierInfoObject.getString("Name");

        }
        return "getCarrier failed";
    }

    //Retrieves price of a flight
    private int getPrice(int _index) throws JSONException {
        JSONArray quoteInfoArray = getQuotes();
        JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(_index);
        return Integer.parseInt(carrierInfoObject.getString("MinPrice"));

    }

    //Retrieves flight number
    private int getCarrierId(int _index) throws JSONException {
        JSONArray quoteInfoArray = getQuotes();
        JSONObject carrierInfoObject = quoteInfoArray.getJSONObject(_index);
        JSONObject outboundLeg = carrierInfoObject.getJSONObject("OutboundLeg");
        String idWithBrackets = outboundLeg.getString("CarrierIds");
        String idWithoutBrackets = idWithBrackets.substring(1,idWithBrackets.length() - 1);
        return Integer.parseInt(idWithoutBrackets);
    }

    //Retrieves information from the API and returns the "Quotes" section.
    private JSONArray getQuotes() throws JSONException {
        return jsonObject.getJSONArray("Quotes");
    }

    //Retrieves information from the API and returns the "Carriers" section.
    private JSONArray getCarriers() throws JSONException {
        return jsonObject.getJSONArray("Carriers");
    }

    //returns valid, direct flights for a given date and destination
    public List<Flight> getFlights() throws JSONException {
        List<Flight> output = new ArrayList<>();
        JSONArray input = getQuotes();
        for (int i = 0; i < input.length(); i++) {
            //JSONObject quotesInfoObject = input.getJSONObject(i);
            Flight validFlight = new Flight(getCarrierId(i), getCarrier(i),getPrice(i));
            output.add(validFlight);
        }
        return output;
    }
}
