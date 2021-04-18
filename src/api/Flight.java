package api;

public class Flight {
    private int carrierID;
    private String carrier;
    private int price;

    public Flight(int carrierID, String carrier, int price) {
        this.carrierID = carrierID;
        this.carrier = carrier;
        this.price = price;
    }

    public int getCarrierID() {
        return carrierID;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getCost() {
        return price;
    }
}
