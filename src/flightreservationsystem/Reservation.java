package flightreservationsystem;

// class to hold reservation data
// contains builder class to build reservations
public class Reservation {
    private final int reservationNumber;
    private final Customer customerDetails;
    private final Flight flightDetails;
    private final int seatNumber;
    private final int price;
    
    public Reservation(int reservationNumber, Customer customer, Flight flight, int seatNumber, int price){
        this.reservationNumber = reservationNumber;
        this.customerDetails = customer;
        this.flightDetails = flight;
        this.seatNumber = seatNumber;
        this.price = price;
    }
    
    public int getReservationNumber(){
        return this.reservationNumber;
    }
    
    public Customer getCustomerDetails(){
        return this.customerDetails;
    }
    
    public Flight getFlightDetails(){
        return this.flightDetails;
    }
    
    public int getSeatNumber(){
        return this.seatNumber;
    }
    
    public int getPrice(){
        return this.price;
    }
    
    @Override
    public String toString(){
        String c = this.customerDetails.toString();
        c = c.replace(',', '|');
        String f = this.flightDetails.toString();
        f = f.replace(',', '|');
        return Integer.toString(this.reservationNumber) 
                + "," + c + "," + f + "," 
                + Integer.toString(this.seatNumber) 
                + "," + Integer.toString(this.price);
    }
}
