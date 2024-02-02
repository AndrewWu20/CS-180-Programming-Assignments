/**
 * Waterslide
 *
 * Program contains specifics for rides, specifically water park rides
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class Waterslide extends Ride {
    private double splashDepth;

    public Waterslide(String name, String color, int minHeight, int maxRiders, double splashDepth) {
        super(name, color, minHeight, maxRiders);
        this.splashDepth = splashDepth;
    }

    public double getSplashDepth() {
        return splashDepth;
    }

    public void setSplashDepth(double splashDepth) {
        this.splashDepth = splashDepth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Waterslide)) {
            return false;
        }
        Waterslide waterslide = (Waterslide) o;
        return this.getMaxRiders() == waterslide.getMaxRiders() &&
                this.getMinHeight() == waterslide.getMinHeight() &&
                this.getColor().equals(waterslide.getColor()) &&
                this.getName().equals(waterslide.getName()) &&
                this.getSplashDepth() == waterslide.getSplashDepth();
    }

    @Override
    public String toString() {
        String waterslide = "Name: %s\nColor: %s\nMinHeight: %d inches\nMaxRiders: %d\nSplashDepth: %.1f feet";
        return String.format(waterslide, super.getName(), super.getColor(),
                super.getMinHeight(), super.getMaxRiders(), this.splashDepth);
    }

}
