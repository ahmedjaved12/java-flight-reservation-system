package flightreservationsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class FlightReservationSystem {
    
    // main function starts here
    public static void main(String[] args) {
        
        // creating toolkit object. Toolkit class contains all the necessary functions
        Toolkit tk = new Toolkit();
        
        // loading data into arraylists
        // calls functions from toolkit class with toolkit object
        ArrayList<Customer> customerList = tk.loadCustomersList();
        ArrayList<Flight> flightList = tk.loadFlightsList();
        ArrayList<Reservation> reservationList = tk.loadReservationsList();
        Scanner in = new Scanner(System.in);
        int intOption;
        String to, from, stringOption;
        char charOption;
        
        System.out.println("***************************************************");
        System.out.println("WELCOME TO FLIGHT RESERVATION SYSTEM");
        System.out.println("***************************************************");
        
        // infinte while loop for menu
        while(true){
          intOption = userMenu(); 
          switch(intOption){
              case 1:
                  // case 1: search flights
                  System.out.println("***************************************************");
                  System.out.println("Enter departure city: ");
                  from = in.nextLine();
                  System.out.println("Enter destination city: ");
                  to = in.nextLine();
                  ArrayList<Flight> availableFlights = tk.searchFlights(from, to, flightList);
                  if(availableFlights.isEmpty()){
                      System.out.println("***************************************************");
                      System.out.println("Not flights found for given route.");
                      System.out.println("***************************************************");
                  }else{
                      System.out.println("***************************************************");
                      System.out.println("Flights from "+from+" to "+to+".");
                      System.out.println("************************************************************************************************");
                      System.out.println("FLIGHT NO    FROM        TO          DEPARTURE TIME           ARRIVAL TIME             PRICE");
                      System.out.println("*************************************************************************************************");
                      for(int i = 0; i < availableFlights.size(); i++){
                          System.out.printf("%-13s%-12s%-12s%-25s%-25s%-10s\n",availableFlights.get(i).getFlightNumber(), availableFlights.get(i).getFrom(), availableFlights.get(i).getTo(), availableFlights.get(i).getDepartureTime(), availableFlights.get(i).getArrivalTime(), Integer.toString(availableFlights.get(i).getPrice()));
                        }
                    }
                  break; // case 1 ends here
              case 2:
                  // case 2: reserve a seat
                  System.out.println("***************************************************");
                  System.out.println("Enter departure city: ");
                  from = in.nextLine();
                  System.out.println("Enter destination city: ");
                  to = in.nextLine();
                  availableFlights = tk.searchFlights(from, to, flightList);
                  if(availableFlights.isEmpty()){
                      System.out.println("***************************************************");
                      System.out.println("Not flights found for given route.");
                      System.out.println("***************************************************");
                  }else{
                      System.out.println("***************************************************");
                      System.out.println("Availabe flights from "+from+" to "+to+".");
                      System.out.println("************************************************************************************************");
                      System.out.println("FLIGHT NO    FROM        TO          DEPARTURE TIME           ARRIVAL TIME             PRICE");
                      System.out.println("*************************************************************************************************");
                      for(int i = 0; i < availableFlights.size(); i++){
                          System.out.printf("%-13s%-12s%-12s%-25s%-25s%-10s\n",availableFlights.get(i).getFlightNumber(), availableFlights.get(i).getFrom(), availableFlights.get(i).getTo(), availableFlights.get(i).getDepartureTime(), availableFlights.get(i).getArrivalTime(), Integer.toString(availableFlights.get(i).getPrice()));
                        }
                      System.out.println("***************************************************");
                      System.out.println("1.Continue booking.");
                      System.out.println("2.Go back.");
                      System.out.println("Enter your choice");
                      intOption = in.nextInt();
                      in.nextLine();
                      
                      if(intOption != 1){
                          
                      }else{
                          boolean flag = true;
                          Flight f = null;
                          while(flag){
                            System.out.println("***************************************************");
                            System.out.println("Enter flight number to book>");
                            stringOption = in.nextLine();
                            for(int i = 0; i < availableFlights.size(); i++){
                              if(availableFlights.get(i).getFlightNumber().equals(stringOption)){
                                  f = availableFlights.get(i);
                              }
                            }
                            if(f != null){
                                flag = false;
                            }else{
                                System.out.println("***************************************************");
                                System.out.println("Invalid flight number. Try again.");
                            }
                          }
                          
                          System.out.println("***************************************************");
                          System.out.println("Enter your name>");
                          String name = in.nextLine();
                          String email = "";
                          flag = true;
                          while(flag){
                            System.out.println("Enter email>");
                            email = in.nextLine();
                            if(tk.validateEmail(email)){
                                
                                flag = false;
                            }else{
                                System.out.println("***************************************************");
                                System.out.println("Invalid email. Try again. Example: email@domain.com");
                            }
                          }
                          System.out.println("Enter your age>");
                          int age = in.nextInt();
                          in.nextLine();
                          System.out.println("Enter country>");
                          String country = in.nextLine();
                          Customer c = new Customer(name,email,age,country);
                          System.out.println("Add flight meal? y/n");
                          charOption = in.nextLine().charAt(0);
                          if(charOption == 'y' || charOption == 'Y'){
                              f = new FlightWithMeal(f);
                          }
                          System.out.println("Add Extra Legroom? y/n");
                          charOption = in.nextLine().charAt(0);
                          if(charOption == 'y' || charOption == 'Y'){
                              f = new FlightWithExtraLegroom(f);
                          }
                          // generating reservation number. gets reservation number of last reservation in list and increments it by one
                          int reservationNumber = reservationList.get(reservationList.size()-1).getReservationNumber() + 1;
                          // generating seat number. total seats = 120
                          int seatNumber = 120 - f.getAvailableSeats();
                          int price = f.getPrice();
                          Reservation r = new Reservation(reservationNumber, c, f, seatNumber, price);
                          reservationList.add(r);
                          customerList.add(c);
                          f.setAvailableSeats(f.getAvailableSeats() - 1);
                          tk.updateReservationsFile(reservationList);
                          tk.updateCustomersFile(customerList);
                          tk.updateFlightsFile(flightList);
                          System.out.println("***************************************************");
                          System.out.println("Reservation added. Details are below.");
                          System.out.println("Name:               "+r.getCustomerDetails().getName());
                          System.out.println("Reservation number: "+r.getReservationNumber());
                          System.out.println("From:               "+r.getFlightDetails().getFrom());
                          System.out.println("To:                 "+r.getFlightDetails().getTo());
                          System.out.println("Departure time:     "+r.getFlightDetails().getDepartureTime());
                          System.out.println("Arrival time:       "+r.getFlightDetails().getArrivalTime());
                          System.out.println("Extras:             "+r.getFlightDetails().getExtras());
                      }
                    }
                  break;// case 2 ends here
              case 3:
                  // case 3: cancel reservation
                  System.out.println("***************************************************");
                  System.out.println("Enter reservation number: ");
                  intOption = in.nextInt();
                  in.nextLine();
                  boolean found = false;
                  for(int i = 0; i < reservationList.size(); i++){
                      if(reservationList.get(i).getReservationNumber() == intOption){
                          found = true;
                          reservationList.remove(i);
                          break;
                      }
                  }
                  if(found == false){
                     System.out.println("***************************************************");
                     System.out.println("No reservation found for given reservation number.");
                  }else{
                     tk.updateReservationsFile(reservationList);
                     System.out.println("***************************************************");
                     System.out.println("Reservation cancelled successfully.");
                  }
                  break;// case 3 ends here
              case 4:
                  // case 4: see your reservations
                  System.out.println("***************************************************");
                  System.out.println("Enter your email: ");
                  String email = in.nextLine();
                  ArrayList<Reservation> rl = new ArrayList<>();
                  for(int i = 0; i < reservationList.size(); i++){
                      if(reservationList.get(i).getCustomerDetails().getEmail().equals(email)){
                          rl.add(reservationList.get(i));
                      }
                  }
                  if(rl.isEmpty()){
                     System.out.println("***************************************************");
                     System.out.println("No reservation found for given email.");
                  }else{
                      System.out.println("***************************************************");
                      System.out.println("SR NO.    DEPARTURE TIME           ARRIVAL TIME             FLIGHT NUMBER  SEAT NUMBER   Extras");
                      for(int i = 0; i < rl.size(); i++){
                          System.out.printf("%-10s%-25s%-25s%-15s%-14s%-20s\n", i+1, rl.get(i).getFlightDetails().getDepartureTime(), rl.get(i).getFlightDetails().getArrivalTime(), rl.get(i).getFlightDetails().getFlightNumber(), rl.get(i).getSeatNumber(), rl.get(i).getFlightDetails().getExtras());
                      }
                  }
                  break;// case 4 ends here
              case 5:
                  // case 5: admin login
                  System.out.println("***************************************************");
                  System.out.println("Enter admin password>");
                  String pass = in.nextLine();
                  if(pass.equals(tk.getAdminPass())){
                      admin adm = admin.getInstance();
                      System.out.println("********************************************************");
                      System.out.println("Logged in as admin.");
                      inner : while(true){
                          intOption = adminMenu();
                          switch (intOption) {
                              case 1:
                                  // case 1: add Flight
                                  System.out.println("***************************************************");
                                  System.out.println("Enter flight number: ");
                                  String fl = in.nextLine();
                                  System.out.println("Enter from: ");
                                  from = in.nextLine();
                                  System.out.println("Enter to:");
                                  to = in.nextLine();
                                  System.out.println("Enter departure time(yyyy-mm-dd HH:mm:ss):");
                                  String dt = in.nextLine();
                                  System.out.println("Enter arrival time(yyyy-mm-dd HH:mm:ss):");
                                  String at = in.nextLine();
                                  System.out.println("Enter price:");
                                  int p = in.nextInt();
                                  System.out.println("Enter available seats:");
                                  int as = in.nextInt();
                                  in.nextLine();
                                  BasicFlight f = new BasicFlight(fl,dt,at,from,to,p,p,as);
                                  flightList.add(f);
                                  tk.updateFlightsFile(flightList);
                                  System.out.println("***************************************************");
                                  System.out.println("Flight added succesfully.");
                                  break;// case 1 ends here
                              case 2:
                                  // case 2: see all flights
                                  System.out.println("***********************************************************************************************************");
                                  System.out.println("FLIGHT NO    FROM        TO          DEPARTURE TIME           ARRIVAL TIME             PRICE");
                                  System.out.println("***********************************************************************************************************");
                                  for(int i = 0; i < flightList.size(); i++){
                                      System.out.printf("%-13s%-12s%-12s%-25s%-25s%-10s\n",flightList.get(i).getFlightNumber(), flightList.get(i).getFrom(), flightList.get(i).getTo(), flightList.get(i).getDepartureTime(), flightList.get(i).getArrivalTime(), Integer.toString(flightList.get(i).getPrice()));
                                    }
                                  break;// case 2 ends here
                              case 3:
                                  // case 3: see all reservations
                                  System.out.println("******************************************************************************************************************");
                                  System.out.println("RESERVATION NO FROM        TO          DEPARTURE TIME           ARRIVAL TIME             CUSTOMER NAME  EXTRAS");
                                  System.out.println("******************************************************************************************************************");
                                  for(int i = 0; i < reservationList.size(); i++){
                                      System.out.printf("%-15s%-12s%-12s%-25s%-25s%-15s%-20s\n",reservationList.get(i).getReservationNumber(), reservationList.get(i).getFlightDetails().getFrom(), reservationList.get(i).getFlightDetails().getTo(), reservationList.get(i).getFlightDetails().getDepartureTime(), reservationList.get(i).getFlightDetails().getArrivalTime(), reservationList.get(i).getCustomerDetails().getName(), reservationList.get(i).getFlightDetails().getExtras());
                                    }
                                  break;// case 3 ends here
                              case 4:
                                  // case 4: see all customers
                                  System.out.println("********************************************************");
                                  System.out.println("CUSTOMER NAME  EMAIL               AGE  COUNTRY        ");
                                  System.out.println("********************************************************");
                                  for(int i = 0; i < customerList.size(); i++){
                                      System.out.printf("%-15s%-20s%-5s%-15s\n", customerList.get(i).getName(), customerList.get(i).getEmail(), customerList.get(i).getAge(), customerList.get(i).getNationality());
                                  }
                                  break;// case 4 ends here
                              case 5: 
                                  // case 5: change admin pass
                                  System.out.println("********************************************************");
                                  String oldPass, newPass;
                                  boolean flag = true;
                                  while(flag){
                                      System.out.println("Enter old password: ");
                                      oldPass = in.nextLine();
                                      if(!oldPass.equals(tk.getAdminPass())){
                                          System.out.println("********************************************************");
                                          System.out.println("Invalid password. Try again.");
                                      }else{
                                          break;
                                      }
                                  }
                                  System.out.println("Enter new password: ");
                                  newPass = in.nextLine();
                                  adm.changeAdminPass(newPass);
                                  System.out.println("********************************************************");
                                  System.out.println("Password changed successfully.");
                                  break;
                              case 6:
                                  adm = null;
                                  break inner;
                              case 7:
                                  System.exit(0);
                              default:
                                  System.out.println("********************************************************");
                                  System.out.println("Invalid entry! Try again.");
                            } // switch 2 ends here
                        } // admin menu inifinte while loop ends here // admin menu inifinte while loop ends here
                    }else{
                        System.out.println("********************************************************");
                        System.out.println("Invalid Login!");
                    }
                  break;// case 5 ends here
              case 6:
                  System.exit(0);
                  break;
              default:
                  System.out.println("********************************************************");
                  System.out.println("Inalid Entry! try again.");
            } // switch 1 ends here
        } // user menu infinite while loop
        
    } // main function ends here
    
    // function to print user menu. Returns the option selected by user
    public static int userMenu()      {
        System.out.println("---CUSTOMER MENU---");
        System.out.println("1.Search Flights.");
        System.out.println("2.Reserve a seat.");
        System.out.println("3.Cancel reservation.");
        System.out.println("4.See your reservations.");
        System.out.println("5.Admin login.");
        System.out.println("6.Exit.");
        System.out.println("Enter your choice>");
        Scanner in = new Scanner(System.in);
        int option;
        if(in.hasNextInt()){
            option = in.nextInt();
            return option;
        }else{
            System.out.println("********************************************************");
            System.out.println("Inalid Entry! try again.");
            return userMenu();
        }
    }
    
    // function to print admin menu. Returns the option selected by admin
    public static int adminMenu(){
        System.out.println("---ADMIN MENU---");
        System.out.println("1.Add flight.");
        System.out.println("2.See all flights.");
        System.out.println("3.See all reservations.");
        System.out.println("4.See all customers.");
        System.out.println("5.Change admin password.");
        System.out.println("6.Logout as admin.");
        System.out.println("7.Exit");
        System.out.println("Enter your choice>");
        Scanner in = new Scanner(System.in);
        int option;
        if(in.hasNextInt()){
            option = in.nextInt();
            return option;
        }else{
            System.out.println("********************************************************");
            System.out.println("Inalid Entry! try again.");
            return adminMenu();
        }
    }
}
