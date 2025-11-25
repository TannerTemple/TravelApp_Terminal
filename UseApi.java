// Followed tutorial to open a connection to RapidAPI

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.*; //for array lists
import com.google.gson.*; 
import java.io.BufferedWriter;  //for filewriter
import java.io.FileWriter;      //for filewriter
import java.io.IOException;     //for filewriter

public class UseApi {
    public static void main(String[] args) {


        testApi("DFW", "2025-11-26", "LGW");

        }
        
        public static Flight testApi (String origin, String departDate, String dest) {

            //String dest = "LGW";    //test destination
            //System.out.println("DEBUG: origin=" + origin + " dest=" + dest + " date=" + departDate);
            int MAX_RETRIES = 3;
            int attempt = 0;
            int cheapest = -1;
            int numStops = -1;
            String airline = null;
            
            while (attempt < MAX_RETRIES) {
                attempt++;
                try {
                    System.out.println("DEBUG attempt " + attempt + ": origin=" + origin +
                                       " dest=" + dest + " date=" + departDate);
            
                String urlString = "https://booking-com15.p.rapidapi.com/api/v1/flights/searchFlights"
                        + "?fromId=" + origin + ".AIRPORT"
                        + "&toId=" + dest + ".AIRPORT"
                        + "&departDate=" + departDate  //yyyy-mm-dd
                        + "&stops=none"
                        + "&pageNo=1"
                        + "&adults=1"
                        + "&sort=CHEAPEST"      //can change these in future for best 
                        + "&cabinClass=ECONOMY"                            //for first class
                        + "&currency_code=USD";                            //for different currency
    
                        URL url = new URL(urlString);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("x-rapidapi-key", "3417bb4662msh73ecd325e2e37b1p137ab5jsnfecf9eb42b4e");
                        conn.setRequestProperty("x-rapidapi-host", "booking-com15.p.rapidapi.com");
                        
            
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        StringBuilder response = new StringBuilder();
            
                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
            
                        in.close();
            
                        // System.out.println(response.toString());        //prints all the JSON
                        String json = response.toString();                 //saves json in string

                        //System.out.println("RAW JSON => " + json.substring(0, 200));

                        //below we have a check that a flight exists
                        JsonObject root = JsonParser.parseString(json).getAsJsonObject();

                        // If API returned status:false
                        if (!root.has("status") || !root.get("status").getAsBoolean()) {
                            System.out.println("NO FLIGHTS or API error for " + origin + " → " + dest);
                            Thread.sleep(300);
                            continue;
                        }

                        // If "data" doesn't exist
                        if (!root.has("data") || root.get("data").isJsonNull()) {
                            System.out.println("No DATA field → No flights for " + origin + " → " + dest);
                            Thread.sleep(300);
                            continue;
                        }
                        //above we have a check that a flight exists

                    //From here until end, we parse the json to get min price only
                    // Navigate down the tree:
                    JsonObject data        = root.getAsJsonObject("data");
                    JsonObject aggregation = data.getAsJsonObject("aggregation");
                    JsonArray stops        = aggregation.getAsJsonArray("stops");
                    JsonObject stop0       = stops.get(0).getAsJsonObject();
                    numStops    = stop0.get("numberOfStops").getAsInt();
                    JsonObject minPrice    = stop0.getAsJsonObject("minPrice");

                    // Extract cheapest units
                    cheapest = minPrice.get("units").getAsInt();

                    // Extract cheapest airline name
                    JsonObject cheapestAirline = stop0.getAsJsonObject("cheapestAirline");
                    airline = cheapestAirline.get("name").getAsString();

                    break;

                    // Output PRINT STATEMENTS WHEN DEBUGGING 



                    // System.out.println("Cheapest price: $" + cheapest);
                    // System.out.println("Airline: " + airline);
                    // System.out.println("# of stops: " + numStops);

                    
          
                } catch (Exception e) {
                    // API glitch → retry
                    try { Thread.sleep(300); } catch (InterruptedException ignored) {}
                }
            }

                    return new Flight(origin, dest, departDate, cheapest, airline, numStops);

                }

    }