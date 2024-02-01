import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * MapNavigator
 *
 * This program initializes a map with an x in the center and
 * moves the x based on movements by players
 *
 * @author Andrew Wu, L10
 *
 * @version 4/1/22
 *
 */

public class MapNavigator extends Thread{
    private static int currentRow;
    private static int currentColumn;
    private static int moveNumber;
    private static boolean started;
    private static char[][] map;
    private int playerNumber;
    private String fileName;
    private static final Object FINALOBJ = new Object();

    public MapNavigator(int playerNumber, String fileName) {
        this.playerNumber = playerNumber;
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        try {
            MapNavigator[] MapNavigators = {new MapNavigator(1, "PlayerOneMoves.txt"),
                    new MapNavigator(2, "PlayerTwoMoves.txt")};
            for (int i = 0; i < MapNavigators.length; i++) {
                MapNavigators[i].start();
            }
            for (int i = 0; i < MapNavigators.length; i++) {
                MapNavigators[i].join();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printInitialMap() {
        System.out.println("Welcome! Initial Map:");
        map[4][4] = 'X';
        currentColumn = 4;
        currentRow = 4;
        map = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = ' ';
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + '|');
            }
        }
    }

    public void fileReader() throws IOException {
        ArrayList<Integer> playerMoves = new ArrayList<>();
        File pOneMoves = new File("PlayerOneMoves.txt");
        FileReader PlayerOneMoves = new FileReader(pOneMoves);
        BufferedReader bfrOne = new BufferedReader(PlayerOneMoves);
        String numberOne = bfrOne.readLine();
        while (numberOne != null) {
            playerMoves.add(Integer.parseInt(numberOne));
            numberOne = bfrOne.readLine();
        }
        bfrOne.close();
        File pTwoMoves = new File("PlayerTwoMoves.txt");
        FileReader PlayerTwoMoves = new FileReader(pTwoMoves);
        BufferedReader bfrTwo = new BufferedReader(PlayerTwoMoves);
        String numberTwo = bfrTwo.readLine();
        while (numberTwo != null) {
            playerMoves.add(Integer.parseInt(numberTwo));
            numberTwo = bfrTwo.readLine();
        }
        bfrTwo.close();

    }

    public void run() {
        if (!started) {
            printInitialMap();
            started = true;
        }
        try {
            fileReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        moveNumber = 0;
        File moves = new File(fileName);
        FileReader positionMoves = null;
        try {
            positionMoves = new FileReader(moves);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bfrMoves = new BufferedReader(positionMoves);
        while (true) {
            String line = null;
            try {
                line = bfrMoves.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            switch (line) {
                case "1" -> {
                    currentRow = currentRow - 1;
                    map[currentRow][currentColumn] = 'X';
                    map[currentRow + 1][currentColumn] = ' ';
                    moveNumber = moveNumber + 1;
                    System.out.println("Move number: " + moveNumber);
                    System.out.println("Player: " + playerNumber);
                    System.out.println("Move: Left");
                }
                case "2" -> {
                    currentRow = currentRow + 1;
                    map[currentRow][currentColumn] = 'X';
                    map[currentRow - 1][currentColumn] = ' ';
                    moveNumber = moveNumber + 1;
                    System.out.println("Move number: " + moveNumber);
                    System.out.println("Player: " + playerNumber);
                    System.out.println("Move: Right");
                }
                case "3" -> {
                    currentColumn = currentColumn + 1;
                    map[currentRow][currentColumn] = 'X';
                    map[currentRow][currentColumn - 1] = ' ';
                    moveNumber = moveNumber + 1;
                    System.out.println("Move number: " + moveNumber);
                    System.out.println("Player: " + playerNumber);
                    System.out.println("Move: Up");
                }
                case "4" -> {
                    currentColumn = currentColumn - 1;
                    map[currentRow][currentColumn] = 'X';
                    map[currentRow][currentColumn + 1] = ' ';
                    moveNumber = moveNumber + 1;
                    System.out.println("Move number: " + moveNumber);
                    System.out.println("Player: " + playerNumber);
                    System.out.println("Move: Down");
                }
                default -> System.out.println("Error, invalid input!");
            }
        }
    }
}


// Did not find a way to synchronize the program.





