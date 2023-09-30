import java.util.Scanner;

/**
 * WordGame
 *
 * This program allows the user to input a number of words to play a guessing game.
 * The game is for the user to guess the word chosen by the random seed the user inputted.
 *
 * @author Andrew Wu, L10
 *
 * @version 3/10/22
 *
 */

public class WordGame {

    public static String welcome = "Ready to play?";
    public static String yesNo = "1.Yes\n2.No";
    public static String noPlay = "Maybe next time!";
    public static String currentRoundLabel = "Current Round: ";
    public static String enterGuess = "Please enter a guess!";
    public static String winner = "You got the answer!";
    public static String outOfGuesses = "You ran out of guesses!";
    public static String solutionLabel = "Solution: ";
    public static String incorrect = "That's not it!";
    public static String keepPlaying = "Would you like to make another guess?";
    public static String enterWords = "Please enter a comma-separated list of words";
    public static String enterSeed = "Please enter a seed for the random number generator";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(enterWords);
        String list = scanner.nextLine();

        int wordNumber = 1;
        for (int i = 0; i < list.length(); i++) {
            if (list.charAt(i) == ',') {
                wordNumber++;
            }
        }

        String[] wordLibrary = new String[wordNumber];
        int indOne = 0;
        int indTwo = 0;
        int word = 0;
        for (int i = 0; i < list.length(); i++) {
            if (list.charAt(i) == ',' | i == list.length() - 1) {
                indTwo = i;
                if (i == list.length() - 1) {
                    indTwo = list.length();
                }
                wordLibrary[word] = list.substring(indOne, indTwo);
                indOne = indTwo + 1;
                word++;
            }
        }

        System.out.println(enterSeed);
        int seed = scanner.nextInt();
        scanner.nextLine();

        WordLibrary library = new WordLibrary(wordLibrary, seed);
        library.cleanLibrary();

        int ready = 0;
        int another = 0;
        int round = 1;
        String product = "";

        do {
            product = library.chooseWord();
            WordGuesser game = new WordGuesser(product);
            System.out.println(welcome);
            System.out.println(yesNo);
            ready = seed;
            scanner.nextLine();
            another = 1;

            if (ready == 1) {
                do {
                    if (game.getRound() > 1) {
                        System.out.println(keepPlaying);
                        System.out.println(yesNo);
                        another = scanner.nextInt();
                        scanner.nextLine();
                    }
                    if (another == 1) {
                        System.out.println(currentRoundLabel + game.getRound());
                        game.printField();
                        System.out.println(enterGuess);
                        String guess = scanner.nextLine();
                        boolean noYes = game.checkGuess(guess);
                        if (noYes) {
                            System.out.println(winner);
                            game.printField();
                            another = 2;
                        } else if (game.getRound() < 5) {
                            System.out.println(incorrect);
                            game.setRound(game.getRound() + 1);
                        } else if (game.getRound() == 5) {
                            System.out.println(outOfGuesses);
                            System.out.println(solutionLabel + product);
                            game.printField();
                            another = 2;
                        }
                    } else if (another == 2) {
                        game.printField();
                    }
                } while (another != 2);
            }
        } while (ready != 2);
        System.out.println(noPlay);
    }
}
