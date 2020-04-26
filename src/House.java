
public class House {

    private int beds;
    private int baths;
    private int squareFootage;
    private double price;
    
    public House(int beds, int baths, int squareFootage, double price) {
        this.setBeds(beds);
        this.setBaths(baths);
        this.setSquareFootage(squareFootage);
        this.setPrice(price);
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBaths() {
        return baths;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
