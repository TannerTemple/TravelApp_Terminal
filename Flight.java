/* 
Basic Flight class that constructs each flight and uses
getters and setters for each flights values
*/ 

public class Flight {
    private String from;
    private String dest;
    private String date;      
    private double price;
    private String airline;
    private int stops;

    public Flight (String from, String dest, String date, double price, String airline, int stops) {
        this.from = from;
        this.dest = dest;
        this.date = date;
        this.price = price;
        this.airline = airline;
        this.stops = stops;
    }

    public String getDest () {        
        return dest;
    }
    public void setDest(String newDest) {
        this.dest=newDest;
    }

    public String getFrom () {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate () {
        return date;
    }
    public void setDate(String newDate) {
        this.date = newDate;
    }

    public double getPrice () {
        return price;
    }
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public String getAirline() {
        return airline;
    }
    public void setAirline(String newAirline) {
        this.airline = newAirline;
    }

    public int getStops() {
        return stops;
    }
    public void setDest(int stops) {
        this.stops = stops;
    }


    @Override
    public String toString() {
        String stopText;
        if (stops == 1) {
            stopText = ("1 stop");
        }
        else stopText = (stops + " stops");
        return from + " -> " + dest +
            " | " + date +
            " | $" + price +
            " | " + airline +
            " | " + stopText;
}



}
