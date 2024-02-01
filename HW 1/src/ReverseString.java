import java.nio.charset.CharacterCodingException;
import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String question = null;
        do {
            System.out.println("Enter a string:");
            String input = scanner.nextLine();

            for (int i = input.length() - 1; i >= 0; i--) {
                System.out.print(input.charAt(i));
            }

            System.out.println("\nAgain?");
            question = scanner.nextLine();
        } while (question.equals("Y"));

    }
}
