package flightreservationsystem;

// interface for flights
public interface Flight {
    
    String getFlightNumber();
    String getDepartureTime();
    String getArrivalTime();
    String getFrom();
    String getTo();
    int getBasePrice();
    int getPrice();
    int getAvailableSeats();
    void setAvailableSeats(int seats);
    String getExtras();
    String toStringForReservation();
    
}
