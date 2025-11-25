import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelApp {

    public static void main (String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your home airport?");
        String homeAirport = scanner.next();

        System.out.println ("When are you leaving? (format: 2025-10-25)"); //MUST be yyyy-mm-dd
        String leaveDate = scanner.next();

        //things that don't change: from airport, date


        // Flight f1 = new Flight ("Paris", 500 );

        UseApi apiData = new UseApi();
        apiData.testApi(homeAirport, leaveDate);

        FlightDatabase db = new FlightDatabase();
        db.loadFlights("FlightDatabase.txt");


        // Find flights that match input
        List<Flight> matches = db.findFlights(homeAirport, leaveDate);

        if (matches.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            for (int i =0; i< matches.size(); i++) {
                Flight f = matches.get(i);
                System.out.println("Flight to " + f.getTo() + " costs $" + f.getPrice());
            }
        }


        //compare prices of the ones we just returned in matches arrayList
        double lowestprice = 999999999;
        String AP ="";
        if (matches.isEmpty()) {
            return; }
        else {
            for (int i =0; i<matches.size(); i++){
                Flight f = matches.get(i);
                if (lowestprice > f.getPrice()) {
                    lowestprice = f.getPrice();
                    AP = f.getTo();
                }
            }
            System.out.println("Cheapest flight is to: " + AP + " for " + lowestprice);
        }
    

        
    }

}