package api;

import org.json.JSONException;

public class Main {

    public static void main(String[] args) throws JSONException, NotDirectFlightException {
String departureDate = "2021-06-03";
String originAirport = "LAX";
	GetFlightData test = new GetFlightData(departureDate,originAirport);
        System.out.println("Carrier " + test.getCarrier(0));
        System.out.println("Price " + test.getPrice(0));
        System.out.println("Carrier ID " + test.getCarrierId(0));



	//test.getQuote("2019-09-01","SDF");
    }
}
