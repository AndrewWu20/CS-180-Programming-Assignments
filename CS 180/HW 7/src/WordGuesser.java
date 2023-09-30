/**
 * WordGuesser
 *
 * This program is the program that allows the user to guess
 * the randomly chosen word and play the game.
 *
 * @author Andrew Wu, L10
 *
 * @version 3/10/22
 *
 */

public class WordGuesser {

    private String[][] playingField;
    private int round;
    private String solution;

    public WordGuesser(String solution) {
        this.solution = solution;
        this.round = 1;
        this.playingField = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; i++) {
                this.playingField[i][j] = "";
            }
        }
    }

    public String[][] getPlayingField() {
        return playingField;
    }

    public int getRound() {
        return round;
    }

    public String getSolution() {
        return solution;
    }

    public void setPlayingField(String[][] playingField) {
        this.playingField = playingField;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean checkGuess(String guess) {
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                this.playingField[round - 1][i] = "'" + guess.charAt(i) + "'";
            } else if (solution.contains(guess.substring(i, i + 1))) {
                this.playingField[round - 1][i] = "*" + guess.charAt(i) + "*";
            } else {
                this.playingField[round - 1][i] = "{" + guess.charAt(i) + "}";
            }
        }
        return guess.equals(this.solution);
    }

    public void printField() {
        String print = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; i++) {
                print += this.playingField[i][j] + " | ";
            }
            print += this.playingField[i][4];
            if (i < 4) {
                print += "\n";
            }
        }
        System.out.println(print);
    }
}
