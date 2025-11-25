import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelApp {

    //flights is an array list of flight class objects
    static ArrayList<Flight> flights = new ArrayList<>();


    public static void main (String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your home airport?");       //add profile in the future so you don't need to input this every time
        String homeAirport = scanner.next();

        System.out.println ("When are you leaving? (format: 2025-10-25)"); //MUST be yyyy-mm-dd (gui in future to select date on calendar)
        String leaveDate = scanner.next();

        //added below

        List <String> europeDests = new ArrayList<>() { {
            add("LGW");
            add("DUB");
            add("LIS");
            add("BCN");
            // add("MXP");
            // add("CPH");
            // add("MAD");
            // add("CDG");
            // add("AMS");
            // add("FCO");
            // add("BER");
            // add("ATH");
            // add("FRA");
            // add("OSL");
            // add("BRU");
            // add("ZRH");
            // add("WAW");
            // add("MUC");
            // add("BUD");
            // add("PRG");
            // add("LHR");
            // add("IST");
            // add("ORY");
            // add("PMI");
        }   
        };

        //uses api to find flight info, returns flight object, and prints 
        for (int i = 0; i< europeDests.size(); i++) {
            String dest = europeDests.get(i);
            Flight f = UseApi.testApi(homeAirport, leaveDate, dest);
            try {
                Thread.sleep(300);  // 0.3 seconds
            } catch (InterruptedException e) {
                // ignore
            }
            if (f != null) {
                flights.add(f);
                System.out.println(f);  //formatted by toString in Flight class
            } else {
                System.out.println("No flights from " + homeAirport + " to " + dest);
            }
        }

        SelectionSortPrices(flights);

        Flight cheapestFlight = flights.get(0);
        System.out.println("Cheapest flight found:");
        System.out.println(cheapestFlight.toString());


        // FlightDatabase db = new FlightDatabase();
        // db.loadFlights("FlightDatabase.txt");


        // // Find flights that match input
        // List<Flight> matches = db.findFlights(homeAirport, leaveDate);

        // if (matches.isEmpty()) {
        //     System.out.println("No flights found.");
        // } else {
        //     for (int i =0; i< matches.size(); i++) {
        //         Flight f = matches.get(i);
        //         System.out.println("Flight to " + f.getDest() + " costs $" + f.getPrice());
        //     }
        // }


        // //compare prices of the ones we just returned in matches arrayList
        // double lowestprice = 999999999;
        // String AP ="";
        // if (matches.isEmpty()) {
        //     return; }
        // else {
        //     for (int i =0; i<matches.size(); i++){
        //         Flight f = matches.get(i);
        //         if (lowestprice > f.getPrice()) {
        //             lowestprice = f.getPrice();
        //             AP = f.getDest();
        //         }
        //     }
        //     System.out.println("Cheapest flight is to: " + AP + " for " + lowestprice);
        // }
    
    }

    //flights is sorted by price after this 
    public static void SelectionSortPrices(List <Flight> flights) {
        int n = flights.size();
        for (int i = 0; i<n-1; i++) {
            int minIndex = i;

            //after full loop iteration, minindex is actual min 
            for (int j = i+1; j<n; j++) {
                if (flights.get(j).getPrice() < flights.get(minIndex).getPrice() ) {
                    minIndex = j;
                }
            }
        //swap minindex with i 
        Flight temp = flights.get(i);
        flights.set(i, flights.get(minIndex));
        flights.set(minIndex, temp);

        }

    }



}