package api;

public class Flight {
    private final int carrierID;
    private final String carrier;
    private final int quote;

    //Constructor for FLight Object
    public Flight(int _carrierID, String _carrier, int _quote) {
        this.carrierID = _carrierID;
        this.carrier = _carrier;
        this.quote = _quote;
    }

    //=================  GETTERS ===============

    public int getCarrierID() {
        return carrierID;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getQuote() {
        return quote;
    }

}
