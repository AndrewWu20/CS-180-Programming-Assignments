import java.util.Random;

/**
 * WordLibrary
 *
 * This program contains the list of words that the program can
 * choose from to play the game
 *
 * @author Andrew Wu, L10
 *
 * @version 3/10/22
 *
 */

public class WordLibrary {

    private String[] library;
    private int seed;
    private Random random;

    public WordLibrary(String[] library, int seed) {
        this.library = library;
        this.seed = seed;
        this.random = new Random(seed);
        cleanLibrary();
    }

    public void cleanLibrary() {
        int count = 0;

        for (int i = 0; i < this.library.length; i++) {
            if (this.library[i].length() == 5) {
                count++;
            }
        }

        String [] nextLibrary = new String[count];
        int index = 0;
        for (int i = 0; i < this.library.length; i++) {
            if (this.library[i].length() == 5) {
                nextLibrary[index] = this.library[i];
                index++;
            }
        }
        this.library = nextLibrary;
    }

    public String chooseWord() {
        return library[random.nextInt(library.length)];
    }

    public int getSeed() {
        return seed;
    }

    public String[] getLibrary() {
        return library;
    }

    public void setLibrary(String[] library) {
        this.library = library;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
