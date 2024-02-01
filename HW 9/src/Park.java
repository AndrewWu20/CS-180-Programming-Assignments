import java.util.ArrayList;

/**
 * Park
 *
 * An interface that other classes can use
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public interface Park {
    void addRide(Ride ride) throws WrongRideException;
    void close();
    void enlarge(double addedLand, double maxLand, boolean addedIndoor, boolean addedOutdoor) throws SpaceFullException;
    double getAdmissionCost();
    double getLand();
    String getName();
    ArrayList<Ride> getRides();
    boolean[] getSeasons();
    boolean isIndoor();
    boolean isOutdoor();
    void removeRide(Ride ride);
    void setAdmissionCost(double admissionCost);
    void setName(String name);
    void setSeasons(boolean[] seasons);

}
