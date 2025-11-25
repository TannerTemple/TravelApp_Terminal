/* 
Basic Flight class that constructs each flight and uses
getters and setters for each flights values
*/ 

public class Flight {
    private String to;
    private String date;
    private String from;
    private double price;

    public Flight (String date, String from, String to, double price) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public String getTo () {
        return to;
    }
    public String getFrom () {
        return from;
    }
    public String getDate () {
        return date;
    }
    public double getPrice () {
        return price;
    }

    public void setDest(String newDest) {
        this.to = newDest;
    }


}
