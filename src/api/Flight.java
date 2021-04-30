package api;

public class Flight {
    private final int carrierID;
    private final String carrier;
    private final int quote;

    public Flight(int carrierID, String carrier, int quote) {
        this.carrierID = carrierID;
        this.carrier = carrier;
        this.quote = quote;
    }

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
