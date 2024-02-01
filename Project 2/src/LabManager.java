/**
 * LabManager
 *
 * This program will handle many user interface methods and the three labs that must be managed
 *
 * @author Andrew WU, L10
 *
 * @version 3/11/22
 *
 */

public class LabManager {
    private Lab labOne;
    private Lab labTwo;
    private Lab labThree;

    public LabManager(Lab labOne, Lab labTwo, Lab labThree) {
        this.labOne = labOne;
        this.labTwo = labTwo;
        this.labThree = labThree;
    }

    public Lab getLabOne() {
        return labOne;
    }

    public Lab getLabTwo() {
        return labTwo;
    }

    public Lab getLabThree() {
        return labThree;
    }

    public void setLabOne(Lab labOne) {
        this.labOne = labOne;
    }

    public void setLabTwo(Lab labTwo) {
        this.labTwo = labTwo;
    }

    public void setLabThree(Lab labThree) {
        this.labThree = labThree;
    }

    public int calculateTotalCapacity() {
        return (this.labOne.getCapacity() * 2) + (this.labTwo.getCapacity() * 2) + (this.labThree.getCapacity() * 2);
    }

    public double calculateTotalUtilization() {
        double lOneReserve = (this.labOne.getMorning().getEnrollment()) +
                (this.labOne.getAfternoon().getEnrollment());
        double lTwoReserve = (this.labTwo.getMorning().getEnrollment()) +
                (this.labTwo.getAfternoon().getEnrollment());
        double lThreeReserve = (this.labThree.getMorning().getEnrollment()) +
                (this.labThree.getAfternoon().getEnrollment());
        double totalReserve = lOneReserve + lTwoReserve + lThreeReserve;
        double totalUtilization = totalReserve / calculateTotalCapacity();
        return totalUtilization;
    }

    public int calculateAvailableSeats() {
        int lOneAvailable = ((this.labOne.getCapacity() - this.labOne.getMorning().getEnrollment()) +
                (this.labOne.getCapacity() - this.labOne.getAfternoon().getEnrollment()));
        int lTwoAvailable = ((this.labTwo.getCapacity() - this.labTwo.getMorning().getEnrollment()) +
                (this.labTwo.getCapacity() - this.labTwo.getAfternoon().getEnrollment()));
        int lThreeAvailable = ((this.labThree.getCapacity() - this.labThree.getMorning().getEnrollment()) +
                (this.labThree.getCapacity() - this.labThree.getAfternoon().getEnrollment()));
        int totalAvailable = lOneAvailable + lTwoAvailable + lThreeAvailable;
        return totalAvailable;
    }

    public String listReservedLabs() {
        String labM = "";
        if (this.labOne.getMorning().getEnrollment() > 0) {
            labM += "Lab One\n Morning: Reserved\n";
        } else {
            labM += "Lab One\n No Reservations\n";
        }
        if (this.labTwo.getMorning().getEnrollment() > 0) {
            labM += "Lab Two\n Morning: Reserved\n";
        } else {
            labM += "Lab Two\n No Reservations\n";
        }
        if (this.labThree.getMorning().getEnrollment() > 0) {
            labM += "Lab Three\n Morning: Reserved\n";
        } else {
            labM += "Lab Three\n No Reservations\n";
        }
        return labM;
    }

    public String listAvailableLabs() {
        String labA = "";
        if (this.labOne.getAfternoon().getEnrollment() == 0) {
            labA += "Lab One\n Afternoon: Available\n";
        } else {
            labA += "Lab One\n No Availabilities\n";
        }
        if (this.labTwo.getAfternoon().getEnrollment() == 0) {
            labA += "Lab Two\n Afternoon: Available\n";
        } else {
            labA += "Lab Two\n No Availabilities\n";
        }
        if (this.labThree.getAfternoon().getEnrollment() == 0) {
            labA += "Lab Three\n Afternoon: Available\n";
        } else {
            labA += "Lab Three\n No Availabilities\n";
        }
        return labA;
    }

    public String addReservation(String location, String time, String name, int enrollment) {
        if (labOne.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labOne.getMorning().getEnrollment() == 0) {
                    if (labOne.getCapacity() >= enrollment) {
                        labOne.setMorning(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else if (time.equals("afternoon")) {
                if (labOne.getAfternoon().getEnrollment() == 0) {
                    if (enrollment <= labOne.getCapacity()) {
                        labOne.setAfternoon(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else {
                return "Error. Invalid time";
            }
        }
        if (labTwo.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labTwo.getMorning().getEnrollment() == 0) {
                    if (labTwo.getCapacity() >= enrollment) {
                        labTwo.setMorning(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else if (time.equals("afternoon")) {
                if (labTwo.getAfternoon().getEnrollment() == 0) {
                    if (enrollment <= labTwo.getCapacity()) {
                        labTwo.setAfternoon(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else {
                return "Error. Invalid time";
            }
        }
        if (labThree.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labThree.getMorning().getEnrollment() == 0) {
                    if (labThree.getCapacity() >= enrollment) {
                        labThree.setMorning(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else if (time.equals("afternoon")) {
                if (labThree.getAfternoon().getEnrollment() == 0) {
                    if (enrollment <= labThree.getCapacity()) {
                        labThree.setAfternoon(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else {
                return "Error. Invalid time";
            }
        }
        return "Reservation added!";
    }

    public String removeReservation(String location, String time) {
        if (labOne.getLocation().equals(location)) {
            if (time.equals("morning")) {
                labOne.setMorning(new Session("", 0));
            } else if (time.equals("afternoon")) {
                labOne.setAfternoon(new Session("", 0));
            } else {
                return "Error. Invalid time";
            }
        } else {
            return "Error. Invalid location";
        }
        if (labTwo.getLocation().equals(location)) {
            if (time.equals("morning")) {
                labTwo.setMorning(new Session("", 0));
            } else if (time.equals("afternoon")) {
                labTwo.setAfternoon(new Session("", 0));
            } else {
                return "Error. Invalid time";
            }
        } else {
            return "Error. Invalid location";
        }
        if (labThree.getLocation().equals(location)) {
            if (time.equals("morning")) {
                labThree.setMorning(new Session("", 0));
            } else if (time.equals("afternoon")) {
                labThree.setAfternoon(new Session("", 0));
            } else {
                return "Error. Invalid time";
            }
        } else {
            return "Error. Invalid location";
        }
        return "Reservation removed!";
    }

    public String modifyReservation(String location, String time, String name, int enrollment) {
        if (labOne.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labOne.getMorning().getEnrollment() == 0) {
                    if (labOne.getCapacity() >= enrollment) {
                        labOne.setMorning(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else if (time.equals("afternoon")) {
                if (labOne.getAfternoon().getEnrollment() == 0) {
                    if (enrollment <= labOne.getCapacity()) {
                        labOne.setAfternoon(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else {
                return "Error. Invalid time";
            }
        }
        if (labTwo.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labTwo.getMorning().getEnrollment() == 0) {
                    if (labTwo.getCapacity() >= enrollment) {
                        labTwo.setMorning(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else if (time.equals("afternoon")) {
                if (labTwo.getAfternoon().getEnrollment() == 0) {
                    if (enrollment <= labTwo.getCapacity()) {
                        labTwo.setAfternoon(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else {
                return "Error. Invalid time";
            }
        }
        if (labThree.getLocation().equals(location)) {
            if (time.equals("morning")) {
                if (labThree.getMorning().getEnrollment() == 0) {
                    if (labThree.getCapacity() >= enrollment) {
                        labThree.setMorning(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else if (time.equals("afternoon")) {
                if (labThree.getAfternoon().getEnrollment() == 0) {
                    if (enrollment <= labThree.getCapacity()) {
                        labThree.setAfternoon(new Session(name, enrollment));
                    } else {
                        return "Error. Capacity exceeded";
                    }
                } else {
                    return "Error. Invalid time";
                }
            } else {
                return "Error. Invalid time";
            }
        }
        return "Reservation modified!";
    }

    @Override
    public String toString() {
        String labManager = "{Lab{Capacity - %s, Location - %s, Morning: %s, Afternoon: %s}, " +
                "{Lab{Capacity - %s, Location - %s, Morning: %s, Afternoon: %s}, " +
                "{Lab{Capacity - %s, Location - %s, Morning: %s, Afternoon: %s}}";
        return String.format(String.valueOf(labOne.getCapacity()), labOne.getLocation(),
                labOne.getMorning(), labOne.getAfternoon(), labTwo.getCapacity(),
                labTwo.getLocation(), labTwo.getMorning(), labTwo.getAfternoon(),
                labThree.getCapacity(), labThree.getLocation(),
                labThree.getMorning(), labThree.getAfternoon());
    }
}
