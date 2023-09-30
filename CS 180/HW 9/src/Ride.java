/**
 * Ride
 *
 * Program contains specifics for rides
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class Ride {
    private String color;
    private int maxRiders;
    private int minHeight;
    private String name;

    public Ride() {
        color = "";
        name = "";
        maxRiders = 0;
        minHeight = 0;
    }

    public Ride(String name, String color, int minHeight, int maxRiders) {
        this.name = name;
        this.color = color;
        this.minHeight = minHeight;
        this.maxRiders = maxRiders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ride)) {
            return false;
        }
        Ride ride = (Ride) o;
        return maxRiders == ride.maxRiders && minHeight == ride.minHeight &&
                color.equals(ride.color) && name.equals(ride.name);
    }

    public String getColor() {
        return color;
    }

    public int getMaxRiders() {
        return maxRiders;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxRiders(int maxRiders) {
        this.maxRiders = maxRiders;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String rideRep = "Name: %s\nColor: %s\nMinHeight: %d inches\nMaxRiders: %d";
        return String.format(rideRep, this.name, this.color, this.minHeight, this.maxRiders);
    }
}
