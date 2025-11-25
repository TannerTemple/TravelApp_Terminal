/* Flight Database has two methods
1) loadFlights: uses the flight database to build an 
   array list, flight, of Flight class objects. 
2) findFlights: takes two arguments and only adds
   results from the correct user inputted airport on the 
   correct user inputted date. 
   */


import java.io.*;
import java.util.*;

public class FlightDatabase {
    private List<Flight> flights = new ArrayList<>();

    public void loadFlights(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");  
                String date = parts[0];           
                String from = parts[1];           
                String to = parts[2];             
                double price = Double.parseDouble(parts[3]);
                String airline = parts[4];  

                Flight f = new Flight(date, from, to, price, airline);
                flights.add(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public List<Flight> findFlights(String currentInputFrom, String currentInputDate) {         //find flights **compares** date and homeAP columns of **database and Uinput**
    List<Flight> results = new ArrayList<>();
    for (int i = 0; i< flights.size(); i++) {
        Flight f = flights.get(i);
        if (f.getFrom().equalsIgnoreCase(currentInputFrom) && f.getDate().equals(currentInputDate)) {
            results.add(f);
        }
    }
    return results;
}



}
