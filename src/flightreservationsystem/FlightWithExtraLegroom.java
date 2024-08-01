package flightreservationsystem;

// flight with extra legroom
public class FlightWithExtraLegroom extends FlightDecorator {
    
    public FlightWithExtraLegroom(Flight flight){
       super(flight); 
    }

    @Override
    public String getFlightNumber() {
        return super.getFlightNumber();
    }

    @Override
    public String getDepartureTime() {
        return super.getDepartureTime();
    }

    @Override
    public String getArrivalTime() {
        return super.getArrivalTime();
    }

    @Override
    public String getFrom() {
        return super.getFrom();
    }

    @Override
    public String getTo() {
        return super.getTo();
    }

    @Override
    public int getBasePrice(){
        return super.getBasePrice();
    }
    @Override
    public int getPrice() {
        if(super.getExtras().equals("none")){
            return super.getBasePrice() + 150;
        }else{
            return super.getBasePrice() + 250;
        }
    }

    @Override
    public int getAvailableSeats() {
        return super.getAvailableSeats();
    }

    @Override
    public void setAvailableSeats(int seats) {
        super.setAvailableSeats(seats);
    }

    // this function first checks if super object has extras equal to none
    // if extras is none, it means that user has not added any extra(meal) yet, in this condition we return extras as only extra legroom
    // if extras is not none, it means user has already added some extra(meal), in this condition we return extras as previous extra + extra legroom
    // in the second condition we directly return extra as extra legroom, then we will get "Extra legroom" but we want "Meal & extra legroom"
    @Override
    public String getExtras() {
       if(super.getExtras().equals("none")){
            return "Extra Legroom";
        }else{
            return super.getExtras() + " & Extra Legroom";
        }
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
