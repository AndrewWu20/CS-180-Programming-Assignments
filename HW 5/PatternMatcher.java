import java.util.Scanner;

/**
 * Homework #5 Challenge: PatternMatcher
 *
 * A program that takes a user's input and challenges them to guess the next three numbers in the pattern
 *
 * @author Andrew Wu, lab sec L10
 *
 * @version 2/18/2022
 *
 */

public class PatternMatcher {

    public static void main(String[] args) {

        String chooseLevel = "Choose Level Difficulty:" +
                "\n1. Easy\n2. Medium\n3. Hard";
        String startNumber = "Enter a number to start the pattern:";
        String nextThree = "Enter the next 3 numbers in the pattern:";
        String congratulations = "Congrats! Your answer was correct!";
        String sorry = "Sorry! Your answer was incorrect!";
        String again = "Play Game Again? (y/n)";
        String ending = "Ending Pattern Matcher...";

        Scanner scanner = new Scanner(System.in);
        String answer = null;
        do {
            System.out.println(chooseLevel);
            String level = scanner.nextLine();
            while (!(level.equals("1") || level.equals("2") || level.equals("3"))) {
                System.out.println(chooseLevel);
                String update = scanner.nextLine();
                level = update;
            }
            if (level.equals("1")) {
                System.out.println(startNumber);
                String number = scanner.nextLine();
                System.out.println(nextThree);
                System.out.println(Integer.parseInt(number) + " " + (Integer.parseInt(number) + 3) + " " +
                        (Integer.parseInt(number) + 6) + " " + (Integer.parseInt(number) + 9));
                String answerOne = scanner.nextLine();
                String answerTwo = scanner.nextLine();
                String answerThree = scanner.nextLine();
                if (!(answerOne.equals((String.valueOf(Integer.parseInt(number) + 12))) &
                        answerTwo.equals(String.valueOf((Integer.parseInt(number) + 15))) &
                        answerThree.equals(String.valueOf((Integer.parseInt(number) + 18))))) {
                    System.out.println(sorry);
                } else {
                    System.out.println(congratulations);
                }
            } else if (level.equals("2")) {
                System.out.println(startNumber);
                String number = scanner.nextLine();
                System.out.println(nextThree);
                System.out.println(Integer.parseInt(number) + " " + (Integer.parseInt(number) * 7) + " " +
                        (Integer.parseInt(number) * 7 * 7) + " " + (Integer.parseInt(number) * 7 * 7 * 7));
                String answerOne = scanner.nextLine();
                String answerTwo = scanner.nextLine();
                String answerThree = scanner.nextLine();
                if (!(answerOne.equals((String.valueOf(Integer.parseInt(number) * 7 * 7 * 7 * 7))) &
                        answerTwo.equals(String.valueOf((Integer.parseInt(number) * 7 * 7 * 7 * 7 * 7))) &
                        answerThree.equals(String.valueOf((Integer.parseInt(number) * 7 * 7 * 7 * 7 * 7 * 7))))) {
                    System.out.println(sorry);
                } else {
                    System.out.println(congratulations);
                }
            } else {
                System.out.println(startNumber);
                String number = scanner.nextLine();
                int num = Integer.parseInt(number);
                int num1 = Integer.parseInt(number) + 1;
                int num2 = Integer.parseInt(number) + 2;
                int num3 = Integer.parseInt(number) + 3;
                int num4 = Integer.parseInt(number) + 4;
                int num5 = Integer.parseInt(number) + 5;
                int num6 = Integer.parseInt(number) + 6;
                System.out.println(nextThree);
                System.out.println((num * num * num - 1) + " " +
                        (num1 * num1 * num1 - 1) + " " +
                        (num2 * num2 * num2 - 1) + " " +
                        (num3 * num3 * num3 - 1));
                String answerOne = scanner.nextLine();
                String answerTwo = scanner.nextLine();
                String answerThree = scanner.nextLine();
                if (!(answerOne.equals(String.valueOf(num4 * num4 * num4 - 1)) &
                        answerTwo.equals((String.valueOf(num5 * num5 * num5 - 1))) &
                        answerThree.equals((String.valueOf(num6 * num6 * num6 - 1))))) {
                    System.out.println(sorry);
                } else {
                    System.out.println(congratulations);
                }
            }
            System.out.println(again);
            answer = scanner.nextLine();
        } while (answer.equalsIgnoreCase("y"));

        System.out.println(ending);
    }
}