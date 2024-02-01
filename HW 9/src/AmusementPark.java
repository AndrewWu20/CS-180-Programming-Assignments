import java.util.ArrayList;

/**
 * AmusementPark
 *
 * Program contains specifics for rides within the amusement park area of the park
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class AmusementPark extends Object implements Park {
    private double admissionCost;
    private boolean	arcade;
    private boolean	bowling;
    private boolean	indoor;
    private double land;
    private String name;
    private boolean	outdoor;
    private ArrayList<Ride>	rides;
    private boolean[] seasons;

    public AmusementPark(String name,
                          double admissionCost,
                          double land,
                          ArrayList<Ride> rides,
                          boolean indoor,
                          boolean outdoor,
                          boolean arcade,
                          boolean bowling,
                          boolean[] seasons) {
        this.admissionCost = admissionCost;
        this.arcade = arcade;
        this.bowling = bowling;
        this.indoor = indoor;
        this.land = land;
        this.name = name;
        this.outdoor = outdoor;
        this.rides = rides;
        this.seasons = seasons;
    }

    public void	addRide(Ride ride) throws WrongRideException {
        if (ride instanceof Rollercoaster) {
            rides.add(ride);
        } else {
            throw new WrongRideException("An amusement park can only have rollercoaster rides!");
        }
    }

    public void	close() {
        this.admissionCost = 0;
        this.arcade = false;
        this.bowling = false;
        this.indoor = false;
        this.land = 0;
        this.name = "";
        this.outdoor = false;
        this.rides = null;
        this.seasons = null;
    }

    public void	enlarge(double addedLand, double maxLand, boolean addedIndoor,
                           boolean addedOutdoor) throws SpaceFullException {
        if ((land + addedLand) > maxLand) {
            throw new SpaceFullException("There is no more land to use for this park!");
        } else {
            if (addedIndoor) {
                indoor = true;
            }
            if (addedOutdoor) {
                outdoor = true;
            }
            land += addedLand;
        }
    }

    public double getAdmissionCost() {
        return admissionCost;
    }

    public boolean isArcade() {
        return arcade;
    }

    public boolean isBowling() {
        return bowling;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public double getLand() {
        return land;
    }

    public String getName() {
        return name;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public boolean[] getSeasons() {
        return seasons;
    }

    public void	modifyRide(Ride ride, String newName, String newColor,
                              int newMinHeight, int newMaxRiders, boolean newSimulated) {
        Ride modify = new Rollercoaster(newName, newColor, newMinHeight, newMaxRiders, newSimulated);
        rides.set(rides.indexOf(ride), modify);
    }

    public void	removeRide(Ride ride) {
        if (ride instanceof Rollercoaster) {
            rides.remove(ride);
        }
    }

    public void setAdmissionCost(double admissionCost) {
        this.admissionCost = admissionCost;
    }

    public void setArcade(boolean arcade) {
        this.arcade = arcade;
    }

    public void setBowling(boolean bowling) {
        this.bowling = bowling;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeasons(boolean[] seasons) {
        this.seasons = seasons;
    }

}
