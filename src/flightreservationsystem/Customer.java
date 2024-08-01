package flightreservationsystem;

// class to hold customer data
public class Customer {
    private final String name;
    private final String email;
    private final int age;
    private final String nationality;
    
    public Customer(String name, String email, int age, String nationality){
        this.name = name;
        this.email = email;
        this.age = age;
        this.nationality = nationality;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public String getNationality(){
        return this.nationality;
    }
    
    @Override
    public String toString(){
        return this.name + 
               "," + this.email + 
               "," + Integer.toString(this.age) + 
               "," + this.nationality;
    }
    
    public String toStringForReservation(){
        return this.name + 
               "|" + this.email + 
               "|" + Integer.toString(this.age) + 
               "|" + this.nationality;    
    }
}
