package api;

//Signifies there are no nearby airports
public class NoNearbyAirportsException extends Exception {
        public NoNearbyAirportsException(String message){
            super(message);
        }
}
