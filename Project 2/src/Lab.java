/**
 * Lab
 *
 * This program will represent the lab and the characteristics of the lab
 *
 * @author Andrew WU, L10
 *
 * @version 3/11/22
 *
 */

public class Lab {
    private Session morning;
    private Session afternoon;
    private int capacity;
    private String location;

    public Lab(Session morning, Session afternoon, int capacity, String location) {
        this.morning = morning;
        this.afternoon = afternoon;
        this.capacity = capacity;
        this.location = location;
    }

    public Lab(int capacity, String location) {

    }

    public Session getMorning() {
        return morning;
    }

    public Session getAfternoon() {
        return afternoon;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setMorning(Session morning) {
        this.morning = morning;
    }

    public void setAfternoon(Session afternoon) {
        this.afternoon = afternoon;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String listAvailabilities() {
        if (this.getMorning().getEnrollment() == 0 && this.getAfternoon().getEnrollment() > 0) {
            return "Morning: Available\n";
        } else if (this.getMorning().getEnrollment() > 0 && this.getAfternoon().getEnrollment() == 0) {
            return "Afternoon: Available\n";
        } else if (this.getMorning().getEnrollment() > 0 && this.getAfternoon().getEnrollment() > 0) {
            return "No Availabilities";
        } else
            return "Morning: Available\n" + "Afternoon: Available\n";
    }

    public String listReservations() {
        if (this.getMorning().getEnrollment() == 0 && this.getAfternoon().getEnrollment() > 0) {
            return "Afternoon: Reserved\n";
        } else if (this.getMorning().getEnrollment() > 0 && this.getAfternoon().getEnrollment() == 0) {
            return "Morning: Reserved\n";
        } else if (this.getMorning().getEnrollment() == 0 && this.getAfternoon().getEnrollment() == 0) {
            return "No Reservations";
        } else
            return "Morning: Reserved\n" + "Afternoon: Reserved\n";
    }

    @Override
    public String toString() {
        Session morningSession = new Session(getMorning().getName(), getMorning().getEnrollment());
        String sessionMorning = morningSession.toString();

        Session afternoonSession = new Session(getAfternoon().getName(), getAfternoon().getEnrollment());
        String sessionAfternoon = afternoonSession.toString();

        if (this.getMorning().getEnrollment() == 0 && this.getAfternoon().getEnrollment() == 0) {
            String session = "Lab{Capacity - %s, Location - %s, Morning - Available, Afternoon - Available}";
            return String.format(session, this.capacity, this.location);
        } else if (this.getMorning().getEnrollment() > 0 && this.getAfternoon().getEnrollment() == 0) {
            String session = "Lab{Capacity - %s, Location - %s, Morning - %s, Afternoon - Available}";
            return String.format(session, this.capacity, this.location, sessionMorning);
        } else if (this.getMorning().getEnrollment() == 0 && this.getAfternoon().getEnrollment() > 0) {
            String session = "Lab{Capacity - %s, Location - %s, Morning - Available, Afternoon - %s}";
            return String.format(session, this.capacity, this.location, sessionAfternoon);
        } else {
            String session = "Lab{Capacity - %s, Location - %s, Morning - %s, Afternoon - %s}";
            return String.format(session, this.capacity, this.location, sessionMorning, sessionAfternoon);
        }
    }
}
