import java.util.Scanner;

/**
 * A program that prints the best major based on user inputted decisions.
 *
 * Purdue University -- CS18000 -- Spring 2022 -- Homework 04 -- Challenge
 *
 * @author Andrew Wu Purdue CS L10
 * @version February 9, 2022
 */

public class MajorDecision {
    public static final String WELCOME_MESSAGE = "Hi! Welcome to the major decider. Answer \"Yes\" or \"No\" to " +
            "our questions.";
    public static final String ALREADY_KNOW = "Do you know which major you want?";
    public static final String MATH = "Do you like math?";
    public static final String PEOPLE = "Do you like talking to people?";
    public static final String TEACHING = "Do you like teaching?";
    public static final String SCIENCES = "Do you like the sciences?";
    public static final String MONEY = "Do you think of ways to make money?";
    public static final String LANGUAGES = "Do you like languages?";
    public static final String ENGLISH = "Do you like English?";
    public static final String COMPUTERS = "Do you like using computers?";
    public static final String BUILDING = "Do you like building things?";
    public static final String GOODBYE_MESSAGE = "Thank you. Goodbye.";

    // ------------------------- DO NOT MODIFY ABOVE -------------------------

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(ALREADY_KNOW);
        String know = scanner.nextLine();
        if (know.equalsIgnoreCase("No")) {
            System.out.println(MATH);
            String math = scanner.nextLine();
            if (math.equalsIgnoreCase("Yes")) {
                System.out.println(COMPUTERS);
                String computers = scanner.nextLine();
                if (computers.equalsIgnoreCase("No")) {
                    System.out.println(BUILDING);
                    String building = scanner.nextLine();
                    if (building.equalsIgnoreCase("No")) {
                        System.out.println(SCIENCES);
                        String science = scanner.nextLine();
                        if (science.equalsIgnoreCase("No")) {
                            System.out.println("Math");
                        } else {
                            System.out.println("Natural Sciences");
                        }
                    } else {
                        System.out.println("Engineering");
                    }
                } else {
                    System.out.println("Computer Science");
                }
            } else {
                System.out.println(PEOPLE);
                String people = scanner.nextLine();
                if (people.equalsIgnoreCase("Yes")) {
                    System.out.println(TEACHING);
                    String teaching = scanner.nextLine();
                    if (teaching.equalsIgnoreCase("No")) {
                        System.out.println(SCIENCES);
                        String science = scanner.nextLine();
                        if (science.equalsIgnoreCase("No")) {
                            System.out.println(MONEY);
                            String money = scanner.nextLine();
                            if (money.equalsIgnoreCase("Yes")) {
                                System.out.println("Business");
                            } else {
                                System.out.println("Psychology");
                            }
                        } else {
                            System.out.println("Medicine");
                        }
                    } else {
                        System.out.println("Education");
                    }
                } else {
                    System.out.println(LANGUAGES);
                    String language = scanner.nextLine();
                    if (language.equalsIgnoreCase("Yes")) {
                        System.out.print(ENGLISH);
                        String english = scanner.nextLine();
                        if (english.equalsIgnoreCase("Yes")) {
                            System.out.println("English");
                        } else {
                            System.out.println("Foreign Languages");
                        }
                    } else {
                        System.out.println("Art");
                    }
                }
            }
        } else {
            System.out.println(GOODBYE_MESSAGE);
        }

    }


}
