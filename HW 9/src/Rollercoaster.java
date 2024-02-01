/**
 * Rollercoaster
 *
 * Program contains specifics for rides, specifically rollercoasters
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class Rollercoaster extends Ride {
    private boolean simulated;

    public Rollercoaster(String name, String color, int minHeight, int maxRiders, boolean simulated) {
        super(name, color, minHeight, maxRiders);
        this.simulated = simulated;
    }

    public boolean isSimulated() {
        return simulated;
    }

    public void setSimulated(boolean simulated) {
        this.simulated = simulated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rollercoaster)) {
            return false;
        }
        Rollercoaster rollercoaster = (Rollercoaster) o;
        return this.getMaxRiders() == rollercoaster.getMaxRiders() &&
                this.getMinHeight() == rollercoaster.getMinHeight() &&
                this.getColor().equals(rollercoaster.getColor()) &&
                this.getName().equals(rollercoaster.getName()) &&
                this.isSimulated() == rollercoaster.isSimulated();
    }

    @Override
    public String toString() {
        String rollercoaster = "Name: %s\nColor: %s\nMinHeight: %d inches\nMaxRiders: %d\nSimulated: %b";
        return String.format(rollercoaster, super.getName(), super.getColor(),
                super.getMinHeight(), super.getMaxRiders(), this.simulated);
    }
}
