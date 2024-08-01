package flightreservationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Toolkit class. Contains all the functions needed in project.
public class Toolkit {
    
    private final String folderPath;
    private final String customersFilePath;
    private final String flightsFilePath;
    private final String reservationsFilePath;
    private final String adminPassFilePath;
    
    public Toolkit(){
        folderPath = "D:\\fiverr\\nori saudi arabia\\2\\delivery\\frs";
        customersFilePath = folderPath+"\\customers.txt";
        flightsFilePath = folderPath+"\\flights.txt";
        reservationsFilePath = folderPath+"\\reservations.txt";
        adminPassFilePath = folderPath+"\\adminpass.txt";
    }
    
    // function to load custonmer data from text file. Returns arraylist containing customer data
    public ArrayList<Customer> loadCustomersList(){
        ArrayList<Customer> customers = new ArrayList<>();
        String customer;
        Customer c;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(customersFilePath));
            boolean firstLine = true;
            
            while((customer = br.readLine()) != null){
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String[] customerData = customer.split(",");
                c = new Customer(customerData[0],customerData[1],Integer.parseInt(customerData[2]),customerData[3]);
                customers.add(c);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customers;
    }
    
    // function to load Flight data from text file. Returns arraylist containing Flight data
    public ArrayList<Flight> loadFlightsList(){
        ArrayList<Flight> flights = new ArrayList<>();
        String flight;
        Flight f;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(flightsFilePath));
            boolean firstLine = true;
            
            while((flight = br.readLine()) != null){
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String[] flightData = flight.split(",");
                f = new BasicFlight(flightData[0], flightData[1], flightData[2], flightData[3], 
                        flightData[4], Integer.parseInt(flightData[5]),
                        Integer.parseInt(flightData[6]) , Integer.parseInt(flightData[7]));
                flights.add(f);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flights;
    }
    
    // function to load reservation data from text file. Returns arraylist containing reservation data
    public ArrayList<Reservation> loadReservationsList(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String reservation;
        Reservation r;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(reservationsFilePath));
            boolean firstLine = true;
            
            while((reservation = br.readLine()) != null){
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                String[] reservationData = reservation.split(",");
                String[] customerData = reservationData[1].split("\\|");
                Customer c = new Customer(customerData[0],customerData[1],Integer.parseInt(customerData[2]),customerData[3]);
                String[] flightData = reservationData[2].split("\\|");
                Flight f = new BasicFlight(flightData[0], flightData[1], flightData[2], flightData[3],
                               flightData[4], Integer.parseInt(flightData[5]),Integer.parseInt(flightData[6]),
                               Integer.parseInt(flightData[7]));
                if(flightData[8].contains("Meal")){
                    f = new FlightWithMeal(f);
                }
                if(flightData[8].contains("Extra Legroom")){
                    f = new FlightWithExtraLegroom(f);
                }
                r = new Reservation(Integer.parseInt(reservationData[0]),c,f,
                                    Integer.parseInt(reservationData[3]),
                                    Integer.parseInt(reservationData[4]));
                reservations.add(r);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservations;
    }
    
    // function to search Flight between cities. Returns list of flights
    public ArrayList<Flight> searchFlights(String from, String to, ArrayList<Flight> flightList){
        
        ArrayList<Flight> flights = new ArrayList<>();
        
        for(int i = 0; i < flightList.size(); i++){
            if(flightList.get(i).getFrom().equalsIgnoreCase(from) && flightList.get(i).getTo().equalsIgnoreCase(to) 
               && flightList.get(i).getAvailableSeats() >= 1) {
                flights.add(flightList.get(i));
            }
        }
        
        return flights;
    }
    
    // function to update customer file. Deletes old file and creates new one with new data
    public void updateCustomersFile(ArrayList<Customer> list){
        File oldFile = new File(customersFilePath);
        oldFile.delete();
        
        File newFile = new File(folderPath+"\\customers.txt");
        try {
            newFile.createNewFile();
            newFile.setReadable(true);
            newFile.setWritable(true);
            try (FileWriter fw = new FileWriter(newFile)) {
                fw.write("name,email,age,nationality\n");
                for(int i = 0; i < list.size(); i++){
                    Customer c = list.get(i);
                    String data = c.getName()+","+c.getEmail()+","+Integer.toString(c.getAge())+","+c.getNationality();
                    fw.write(data);
                    fw.write("\n");
                }
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // function to update Flight file. Deletes old file and creates new one with new data
    public void updateFlightsFile(ArrayList<Flight> list){
        File oldFile = new File(flightsFilePath);
        oldFile.delete();
        
        File newFile = new File(folderPath+"\\flights.txt");
        try {
            newFile.createNewFile();
            newFile.setReadable(true);
            newFile.setWritable(true);
            try (FileWriter fw = new FileWriter(newFile)) {
                fw.write("flight number,departure time,arrival time,from,to,base price,price,available seats\n");
                for(int i = 0; i < list.size(); i++){
                    Flight f = list.get(i);
                    String data = f.toString();
                    fw.write(data);
                    fw.write("\n");
                }
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // function to update reservation file. Deletes old file and creates new one with new data
    public void updateReservationsFile(ArrayList<Reservation> list){
        File oldFile = new File(reservationsFilePath);
        oldFile.delete();
        
        File newFile = new File(folderPath+"\\reservations.txt");
        try {
            newFile.createNewFile();
            newFile.setReadable(true);
            newFile.setWritable(true);
            try (FileWriter fw = new FileWriter(newFile)) {
                fw.write("reservation number,customer details,flight details,seat number,price\n");
                for(int i = 0; i < list.size(); i++){
                    Reservation r = list.get(i);
                    String data = Integer.toString(r.getReservationNumber())
                            +","+r.getCustomerDetails().toStringForReservation()
                            +","+r.getFlightDetails().toStringForReservation()
                            +","+Integer.toString(r.getSeatNumber())
                            +","+Integer.toString(r.getPrice());
                    fw.write(data);
                    fw.write("\n");
                }
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // function to validate email. Returns true if email is in correct syntax otherwise false
    public boolean validateEmail(String email){
        
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
    
    // function to get admin pass. Returns admin pass from a text file
    public String getAdminPass(){
        try {
            File f = new File(adminPassFilePath);
            Scanner reader = new Scanner(f);
            return reader.nextLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
}
