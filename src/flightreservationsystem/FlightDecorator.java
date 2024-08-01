package flightreservationsystem;

// flight decorator class
// this class is abstract because we will not create object of this class
// this is only for decorated classes so that they can implement it
public abstract class FlightDecorator implements Flight {
    
    protected Flight flight;
    
    public FlightDecorator(Flight flight){
        this.flight = flight;
    }
    
    @Override
    public String getFlightNumber() {
        return this.flight.getFlightNumber();
    }

    @Override
    public String getDepartureTime() {
        return this.flight.getDepartureTime();
    }

    @Override
    public String getArrivalTime() {
        return this.flight.getArrivalTime();
    }

    @Override
    public String getFrom() {
        return this.flight.getFrom();
    }

    @Override
    public String getTo() {
        return this.flight.getTo();
    }

    @Override
    public int getPrice() {
        return this.flight.getPrice();
    }
    
    @Override
    public int getBasePrice(){
        return this.flight.getBasePrice();
    }

    @Override
    public int getAvailableSeats() {
        return this.flight.getAvailableSeats();
    }

    @Override
    public void setAvailableSeats(int seats) {
        this.flight.setAvailableSeats(seats);
    }

    @Override
    public String getExtras() {
        return this.flight.getExtras();
    }
    
    @Override
    public String toString(){
        return this.getFlightNumber() + 
            "," + this.getDepartureTime() + 
            "," + this.getArrivalTime() + 
            "," + this.getFrom() + 
            "," + this.getTo() + 
            "," + Integer.toString(this.getBasePrice()) +
            "," + Integer.toString(this.getPrice()) + 
            "," + Integer.toString(this.getAvailableSeats()) + 
            "," + this.getExtras();
    }
    
    @Override
    public String toStringForReservation() {
        return this.getFlightNumber() + 
            "|" + this.getDepartureTime() + 
            "|" + this.getArrivalTime() + 
            "|" + this.getFrom() + 
            "|" + this.getTo() + 
            "|" + Integer.toString(this.getBasePrice()) +
            "|" + Integer.toString(this.getPrice()) + 
            "|" + Integer.toString(this.getAvailableSeats()) + 
            "|" + this.getExtras();
    }
}
