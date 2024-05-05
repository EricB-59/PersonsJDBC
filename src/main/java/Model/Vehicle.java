package Model;

public class Vehicle {
    final private String licensePlate;
    private String color;

    public Vehicle(String licensePlate, String color) {
        this.licensePlate = licensePlate;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
