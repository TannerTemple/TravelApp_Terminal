// Followed tutorial to open a connection to RapidAPI


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UseApi {

    public static void main(String[] args) {


    //do i need a main? maybe to call a loop or something

        }
        
        public void testApi (String origin, String departDate) {

            String dest = "LGW";

            try {
                // String orig = "DFW";  //origin will be chosen by user
                // String dest = "DUB";    //destination will be chosen by user
    
    
                String urlString = "https://booking-com15.p.rapidapi.com/api/v1/flights/searchFlights"
                        + "?fromId=" + origin + ".AIRPORT"
                        + "&toId=" + dest + ".AIRPORT"
                        + "&departDate=" + departDate  //yyyy-mm-dd
                        + "&stops=none"
                        + "&pageNo=1"
                        + "&adults=1"
                        + "&sort=CHEAPEST"
                        + "&cabinClass=ECONOMY"
                        + "&currency_code=USD";
    
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
            
                        System.out.println(response.toString());        //prints all the JSON
            
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

    }