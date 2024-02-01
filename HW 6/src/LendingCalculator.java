import java.util.Scanner;

/**
 * HW-06 Challenge
 *
 * This program uses the Loan class and user input to output the details of a loan given the user's input
 *
 * @author Andrew Wu, lab section L10
 *
 * @version February 25, 2022
 *
 */

public class LendingCalculator {
    
    private static String welcomeMessage = "Welcome to the Lending Calculator!"; 
    private static String menu = "Menu";
    private static String initialMenu = "0. Quit\n1. Add a loan";
    private static String ongoingMenu = "0. Quit\n1. Modify Loan\n2. Calculate Total Cost";
    private static String exitMessage = "Thank you!";
    private static String durationMessage = "Enter the duration:";
    private static String rateMessage = "Enter the rate:";
    private static String amountMessage = "Enter the amount:"; 
    private static String amortizationPrompt = "Would you like to print the amortization schedule?"; 
    private static String amortizationMenu = "1. Yes\n2. No"; 
    private static String totalInterestMessage = "Total Cost: ";
    private static String errorMessage = "Error! Invalid input."; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(welcomeMessage);
        System.out.println(menu);
        System.out.println(initialMenu);
        int initialMenuAns = scanner.nextInt();
        while (initialMenuAns != 0 && initialMenuAns != 1) {
            System.out.println(errorMessage);
            System.out.println(menu);
            System.out.println(initialMenu);
            initialMenuAns = scanner.nextInt();
        }
        String initialMenuAnsS = "";
        initialMenuAnsS += initialMenuAns;
        if (initialMenuAnsS.equalsIgnoreCase("0")) {
            System.out.println(exitMessage);
        } else {
            System.out.println(durationMessage);
            int duration = scanner.nextInt();
            System.out.println(rateMessage);
            double rate = scanner.nextDouble();
            System.out.println(amountMessage);
            double amount = scanner.nextDouble();
            double monthlyPayment = amount * ((rate / 12) * Math.pow((1 + rate / 12),
                    duration) / (Math.pow((1 + rate / 12), duration)));
            Loan loan = new Loan(duration, rate, amount);
            System.out.println(loan.toString());

            System.out.println(menu);
            System.out.println(ongoingMenu);
            String ongoingMenuAns = scanner.nextLine();
            do {
                if (ongoingMenuAns.equalsIgnoreCase("0")) {
                    System.out.println(exitMessage);
                } else if (ongoingMenuAns.equalsIgnoreCase("1")) {
                    System.out.println(durationMessage);
                    duration = scanner.nextInt();
                    System.out.println(rateMessage);
                    rate = scanner.nextDouble();
                    System.out.println(amountMessage);
                    amount = scanner.nextDouble();
                    monthlyPayment = amount * ((rate / 12) * Math.pow((1 + rate / 12),
                        duration) / (Math.pow((1 + rate / 12), duration)));
                    loan = new Loan(duration, rate, amount);
                    System.out.println(loan.toString());
                } else {
                    System.out.println(amortizationPrompt);
                    System.out.println(amortizationMenu);
                    String amorMenuAns = scanner.nextLine();
                    double monthPayment = monthlyPayment;
                    double principal = monthPayment - rate;
                    double interest = amount * rate;
                    double remaining = amount - principal;
                    double total = amount;
                    if (amorMenuAns.equalsIgnoreCase("1")) {
                        for (int paymentNumber = 1; paymentNumber <= duration; paymentNumber++) {
                            System.out.printf("Payment: %d - Principal: %.2f - Interest: %.2f" +
                                " - Remaining: %.2f\n", paymentNumber, principal, interest, remaining);
                            total += rate;
                            System.out.printf(totalInterestMessage + "%.2f", total);
                        }
                    } else {
                        total += rate;
                        System.out.printf(totalInterestMessage + "%.2f", total);
                    }
                }
            } while (ongoingMenuAns.equalsIgnoreCase("1") || ongoingMenuAns.equalsIgnoreCase("2"));
        }

    }
}
