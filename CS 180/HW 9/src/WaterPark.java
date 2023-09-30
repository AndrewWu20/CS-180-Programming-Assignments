import java.util.ArrayList;

/**
 * WaterPark
 *
 * Program contains specifics for rides within the water park area of the park
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class WaterPark extends Object implements Park {
    private double admissionCost;
    private boolean	indoor;
    private double land;
    private boolean	lazyRiver;
    private String name;
    private boolean	outdoor;
    private ArrayList<Ride>	rides;
    private boolean[] seasons;
    private boolean	wavePool;

    public WaterPark(String name, double admissionCost, double land,
               ArrayList<Ride> rides, boolean indoor, boolean outdoor,
               boolean lazyRiver, boolean wavePool, boolean[] seasons) {
        this.admissionCost = admissionCost;
        this.indoor = indoor;
        this.land = land;
        this.lazyRiver = lazyRiver;
        this.name = name;
        this.outdoor = outdoor;
        this.rides = rides;
        this.seasons = seasons;
        this.wavePool = wavePool;
    }

    public void	addRide(Ride ride) throws WrongRideException {
        if (ride instanceof Waterslide) {
            rides.add(ride);
        } else {
            throw new WrongRideException("A waterpark can only have waterslide rides!");
        }
    }

    public void	close() {
        this.name = "";
        this.admissionCost = 0;
        this.land = 0;
        this.rides = null;
        this.seasons = null;
        this.indoor = false;
        this.outdoor = false;
        this.lazyRiver = false;
        this.wavePool = false;
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
            land = land + addedLand;
        }
    }

    public double getAdmissionCost() {
        return admissionCost;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public double getLand() {
        return land;
    }

    public boolean isLazyRiver() {
        return lazyRiver;
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

    public boolean isWavePool() {
        return wavePool;
    }

    public void	modifyRide(Ride ride, String newName, String newColor,
                              int newMinHeight, int newMaxRiders, double newSplashDepth) {
        Ride modify = new Waterslide(newName, newColor, newMinHeight, newMaxRiders, newSplashDepth);
        rides.set(rides.indexOf(ride), modify);
    }

    public void	removeRide(Ride ride) {
        if (ride instanceof Waterslide) {
            rides.remove(ride);
        }
    }

    public void setAdmissionCost(double admissionCost) {
        this.admissionCost = admissionCost;
    }

    public void setLazyRiver(boolean lazyRiver) {
        this.lazyRiver = lazyRiver;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeasons(boolean[] seasons) {
        this.seasons = seasons;
    }

    public void setWavePool(boolean wavePool) {
        this.wavePool = wavePool;
    }


}
