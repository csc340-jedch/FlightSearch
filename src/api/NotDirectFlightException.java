package api;

//Signifies a FLight is not Direct
public class NotDirectFlightException extends Exception {
    public NotDirectFlightException(String message){
        super(message);
    }
}
