import java.util.Scanner;

/**
 * TimeKeeper
 *
 * This program lists available lab sections and allows the user to enroll in a lab section,
 * depending on the user's lab preferences and whether the lab is at capacity.
 *
 * @author Andrew WU, L10
 *
 * @version 3/11/22
 *
 */

public class TimeKeeper {

    private static String welcomePrompt = "Welcome to the TimeKeeper application!";
    private static String invalidInput = "Invalid input. Please try again.";
    private static String enterLabCapacity = "Enter the capacity for Lab ";
    private static String enterLabLocation = "Enter the location for Lab ";
    private static String labLocationPrompt = "Enter the location of the lab:";
    private static String reservationTimePrompt = "Enter the time of the reservation:";
    private static String reservationNamePrompt = "Enter the name of the reservation:";
    private static String reservationEnrollmentPrompt = "Enter the expected enrollment:";
    private static String reservationNameUpdate = "Enter the updated name of the reservation:";
    private static String reservationEnrollmentUpdate = "Enter the updated enrollment:";
    private static String totalCapacity = "Total Capacity: ";
    private static String totalUtilization = "Total Utilization: ";
    private static String availableSeats = "Available seats: ";


    private static String initializeMenu = "1. Initialize Application\n" +
            "2. Exit";
    private static String ongoingMenu = "1. View Current Lab Schedule\n" +
            "2. List Labs by Availability\n" +
            "3. List Labs by Reservation\n" +
            "4. Add a Reservation\n" +
            "5. Remove a Reservation\n" +
            "6. Modify a Reservation\n" +
            "7. Calculate Statistics\n" +
            "8. Exit";
    private static String statisticsMenu = "1. Total Capacity\n" +
            "2. Total Utilization\n" +
            "3. Available seats\n" +
            "4. Return to main menu";
    private static String exitMessage = "Thank you for using TimeKeeper!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(welcomePrompt);
        int menuAnswer;
        do {
            System.out.println(initializeMenu);
            menuAnswer = scanner.nextInt();
            if (menuAnswer == 2) {
                System.out.println(exitMessage);
            } else if (menuAnswer == 1) {
                System.out.println(enterLabCapacity + "1:");
                int capacityOne = scanner.nextInt();
                scanner.nextLine();
                System.out.println(enterLabLocation + "1:");
                String locationOne = scanner.nextLine();
                System.out.println(enterLabCapacity + "2:");
                int capacityTwo = scanner.nextInt();
                scanner.nextLine();
                System.out.println(enterLabLocation + "2:");
                String locationTwo = scanner.nextLine();
                System.out.println(enterLabCapacity + "3:");
                int capacityThree = scanner.nextInt();
                scanner.nextLine();
                System.out.println(enterLabLocation + "3:");
                String locationThree = scanner.nextLine();

                LabManager mainLabManager = new LabManager(new Lab(capacityOne, locationOne),
                        new Lab(capacityTwo, locationTwo), new Lab(capacityThree, locationThree));

                String ongoingMenuAns;
                System.out.println(ongoingMenu);
                ongoingMenuAns = scanner.nextLine();
                if (ongoingMenuAns.equals("1")) {
                    Lab lab1 = new Lab(capacityOne, locationOne);
                    String lab1Summary = lab1.toString();
                    System.out.println(lab1Summary);
                    Lab lab2 = new Lab(capacityTwo, locationTwo);
                    String lab2Summary = lab2.toString();
                    System.out.println(lab2Summary);
                    Lab lab3 = new Lab(capacityThree, locationThree);
                    String lab3Summary = lab3.toString();
                    System.out.println(lab3Summary);
                } else if (ongoingMenuAns.equals("2")) {
                    String availabilityL1 = mainLabManager.getLabOne().listAvailabilities();
                    System.out.println("Lab One");
                    System.out.println(availabilityL1);
                    String availabilityL2 = mainLabManager.getLabTwo().listAvailabilities();
                    System.out.println("Lab Two");
                    System.out.println(availabilityL2);
                    String availabilityL3 = mainLabManager.getLabThree().listAvailabilities();
                    System.out.println("Lab Three");
                    System.out.println(availabilityL3);
                } else if (ongoingMenuAns.equals("3")) {
                    String reservationL1 = mainLabManager.getLabOne().listReservations();
                    System.out.println("Lab One");
                    System.out.println(reservationL1);
                    String reservationL2 = mainLabManager.getLabTwo().listReservations();
                    System.out.println("Lab Two");
                    System.out.println(reservationL2);
                    String reservationL3 = mainLabManager.getLabThree().listReservations();
                    System.out.print("Lab Three");
                    System.out.println(reservationL3);
                } else if (ongoingMenuAns.equals("4")) {
                    System.out.println(labLocationPrompt);
                    String locationNew = scanner.nextLine();
                    System.out.println(reservationTimePrompt);
                    String timeNew = scanner.nextLine();
                    System.out.println(reservationNamePrompt);
                    String nameNew = scanner.nextLine();
                    System.out.println(reservationEnrollmentPrompt);
                    int enrollmentNew = scanner.nextInt();
                    scanner.nextLine();
                    String reservationNew = mainLabManager.addReservation(locationNew,
                            timeNew, nameNew, enrollmentNew);
                    System.out.println(reservationNew);
                } else if (ongoingMenuAns.equals("5")) {
                    System.out.println(labLocationPrompt);
                    String locationCurr = scanner.nextLine();
                    System.out.println(reservationTimePrompt);
                    String timeCurr = scanner.nextLine();
                    String removeRes = mainLabManager.removeReservation(locationCurr, timeCurr);
                    System.out.println(removeRes);
                } else if (ongoingMenuAns.equals("6")) {
                    System.out.println(labLocationPrompt);
                    String locationMod = scanner.nextLine();
                    System.out.println(reservationTimePrompt);
                    String timeMod = scanner.nextLine();
                    System.out.println(reservationNameUpdate);
                    String nameMod = scanner.nextLine();
                    System.out.println(reservationEnrollmentUpdate);
                    int enrollmentMod = scanner.nextInt();
                    scanner.nextLine();
                    String reservationMod = mainLabManager.modifyReservation(locationMod,
                            timeMod, nameMod, enrollmentMod);
                    System.out.println(reservationMod);
                } else if (ongoingMenuAns.equals("7")) {
                    int statisticsAns;
                    do {
                        System.out.println(statisticsMenu);
                        statisticsAns = scanner.nextInt();
                        scanner.nextLine();
                        if (statisticsAns == 1) {
                            System.out.println(totalCapacity);
                        } else if (statisticsAns == 2) {
                            System.out.println(totalUtilization);
                        } else if (statisticsAns == 3) {
                            System.out.println(availableSeats);
                        } else if (statisticsAns == 4) {
                            break;
                        }
                    } while ((statisticsAns == 1) || (statisticsAns == 2) || (statisticsAns == 3));
                } else if (ongoingMenuAns.equals("8")) {
                    System.out.print(exitMessage);
                } else {
                    System.out.print(invalidInput);
                }
            } else {
                System.out.print(invalidInput);
                System.out.println(initializeMenu);
                menuAnswer = scanner.nextInt();
            }

        } while (menuAnswer != 1 || menuAnswer != 2);
    }
}