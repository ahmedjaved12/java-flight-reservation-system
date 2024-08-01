package flightreservationsystem;

// basic flight class
public class BasicFlight implements Flight {
    private final String flightNumber;
    private final String departureTime;
    private final String arrivalTime;
    private final String from;
    private final String to;
    private final int basePrice;
    private final int price;
    private int availableSeats;
    private final String extras;
    
    public BasicFlight(String fligthNumber, String departureTime, String arrivalTime, String from, String to ,int basePrice, int price, int availableSeats){
        this.flightNumber = fligthNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.basePrice = basePrice;
        this.price = price;
        this.availableSeats = availableSeats;
        this.extras = "none";
    }
    
    @Override
    public String getFlightNumber(){
        return this.flightNumber;
    }
    
    @Override
    public String getDepartureTime(){
        return this.departureTime;
    }
    
    @Override
    public String getArrivalTime(){
        return this.arrivalTime;
    }
    
    @Override
    public String getFrom(){
        return this.from;
    }
    
    @Override
    public String getTo(){
        return this.to;
    }
    
    @Override
    public int getPrice(){
        return this.price;
    }
    
    @Override
    public int getBasePrice(){
        return this.basePrice;
    }
    
    @Override
    public int getAvailableSeats(){
        return this.availableSeats;
    }
    
    @Override
    public void setAvailableSeats(int seats){
        this.availableSeats = seats;
    }
    
    @Override
    public String getExtras(){
        return this.extras;
    }
    
    @Override
    public String toString(){
        return this.flightNumber + 
            "," + this.departureTime + 
            "," + this.arrivalTime + 
            "," + this.from + 
            "," + this.to + 
            "," + Integer.toString(this.basePrice) +
            "," + Integer.toString(this.price) + 
            "," + Integer.toString(this.availableSeats) + 
            "," + this.extras;
    }
    
    @Override
    public String toStringForReservation(){
        return this.flightNumber + 
            "|" + this.departureTime + 
            "|" + this.arrivalTime + 
            "|" + this.from + "|" + this.to + 
            "|" + Integer.toString(this.basePrice) +
            "|" + Integer.toString(this.price) + 
            "|" + Integer.toString(this.availableSeats)+ 
            "|" + this.extras;
    }

}
