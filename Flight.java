/* 
Basic Flight class that constructs each flight and uses
getters and setters for each flights values
*/ 

public class Flight {
    private String to;
    private String date;
    private String from;
    private double price;
    private String airline;
    // private String dest;

    public Flight (String date, String from, String to, double price, String airline) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.price = price;
        this.airline = airline;
    }

    public String getTo () {        //not sure if needed
        return to;
    }
    public void setTo(String newTo) {
        this.to=newTo;
    }

    public String getFrom () {
        return from;
    }
    public void setFrom(String newFrom) {
        this.from = newFrom;
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

    // public String getDest() {
    //     return dest;
    // }
    // public void setDest(String newDest) {
    //     this.to = newDest;
    // }




}
