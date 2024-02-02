import java.lang.Math;

/**
 * HW-06 Challenge
 *
 * This class houses all commands and the calculations used in the LendingCalculator class
 *
 * @author Andrew Wu, lab section L10
 *
 * @version February 25, 2022
 *
 */

public class Loan {
    private int duration;
    private double rate;
    private double amount;
    private double monthlyPayment;

    public Loan(int duration, double rate, double amount) {
        this.duration = duration;
        this.rate = rate;
        this.amount = amount;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }

    public double calculateMonthlyPayment() {
        monthlyPayment = amount * ((rate / 12) * Math.pow((1 + rate / 12), duration))
                / ((Math.pow((1 + rate / 12), duration) - 1));
        return monthlyPayment;
    }

    public double calculateTotalCost(boolean output) {
        double monthPayment = monthlyPayment;
        double principal = monthPayment - rate;
        double interest = amount * rate;
        double remaining = amount - principal;
        double total = amount;
        for (int paymentNumber = 1; paymentNumber <= duration; paymentNumber++) {
            System.out.printf("Payment: %d - Principal: %.2f - Interest: %.2f - Remaining:" +
                    " %.2f\n", paymentNumber, principal, interest, remaining);
            total += rate;
        }
        return (total);
    }

    @Override
    public String toString() {
        return  String.format("Amount: %.2f - Rate: %.2f - Duration: %d" +
                " - Payment: %.2f", amount, rate, duration, monthlyPayment);
    }
}
