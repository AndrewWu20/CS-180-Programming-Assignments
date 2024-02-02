limport java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PlayGame
 *
 * Program contains program to carry out the game of battleship
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class PlayGame {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File playerOneShip = new File("src/ShipPositionsPlayerOne.txt");
        String[][] oneArray = new String[10][14];
        FileReader oneShip = new FileReader(playerOneShip);
        BufferedReader oneShipBfr = new BufferedReader(oneShip);
        int oneI = 0;
        while (true) {
            String line = oneShipBfr.readLine();
            if (line == null)
                break;
            for (int j = 0; j < 14; j++) {
                oneArray[oneI][j] = String.valueOf(line.charAt(j));
            }
            oneI++;
        }
        oneShipBfr.close();
        File playerTwoShip = new File("src/ShipPositionsPlayerTwo.txt");
        String[][] twoArray = new String[10][14];
        FileReader twoShip = new FileReader(playerTwoShip);
        BufferedReader twoShipBfr = new BufferedReader(twoShip);
        int twoI = 0;
        while (true) {
            String line = twoShipBfr.readLine();
            if (line == null)
                break;
            for (int j = 0; j < 14; j++) {
                twoArray[twoI][j] = String.valueOf(line.charAt(j));
            }
            twoI++;
        }
        twoShipBfr.close();
        System.out.println("Hello, Welcome to Battleship!");
        System.out.println("Please enter a Game mode:\n1. Automated\n2. Active");
        String modeAnswer = scanner.nextLine();
        int oneHits = 0;
        int twoHits = 0;
        int numTurns = 0;
        int winningPlayer = 0;
        int losingPlayerHits = 0;
        String boardPatternOne = "";
        String boardPatternTwo = "";
        GameLog gameLog = new GameLog(winningPlayer, losingPlayerHits, numTurns,
                boardPatternOne, boardPatternTwo);
        int hitCountTopOne = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 14; j++) {
                if (oneArray[i][j].equals("H")) {
                    hitCountTopOne++;
                }
            }
        }
        int hitCountMidOne = 0;
        for (int i = 3; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                if (oneArray[i][j].equals("H")) {
                    hitCountMidOne++;
                }
            }
        }
        int hitCountBotOne = 0;
        for (int i = 7; i < 10; i++) {
            for (int j = 0; j < 14; j++) {
                if (oneArray[i][j].equals("H")) {
                    hitCountBotOne++;
                }
            }
        }
        int hitCountTopTwo = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 14; j++) {
                if (twoArray[i][j].equals("H")) {
                    hitCountTopTwo++;
                }
            }
        }
        int hitCountMidTwo = 0;
        for (int i = 3; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                if (twoArray[i][j].equals("H")) {
                    hitCountMidTwo++;
                }
            }
        }
        int hitCountBotTwo = 0;
        for (int i = 7; i < 10; i++) {
            for (int j = 0; j < 14; j++) {
                if (twoArray[i][j].equals("H")) {
                    hitCountBotTwo++;
                }
            }
        }
        if (hitCountTopOne >= 9) {
            gameLog.setBoardPatternOne("Top Heavy");
        } else if (hitCountMidOne >= 9) {
            gameLog.setBoardPatternOne("Middle Heavy");
        } else if (hitCountBotOne >= 9) {
            gameLog.setBoardPatternOne("Bottom Heavy");
        } else {
            gameLog.setBoardPatternOne("Scattered");
        }
        if (hitCountTopTwo >= 9) {
            gameLog.setBoardPatternTwo("Top Heavy");
        } else if (hitCountMidTwo >= 9) {
            gameLog.setBoardPatternTwo("Middle Heavy");
        } else if (hitCountBotTwo >= 9) {
            gameLog.setBoardPatternTwo("Bottom Heavy");
        } else {
            gameLog.setBoardPatternTwo("Scattered");
        }
        if (modeAnswer.equals("1")) {
            ArrayList<Integer> playOneR = new ArrayList<>();
            ArrayList<Integer> playOneC = new ArrayList<>();
            ArrayList<Integer> playTwoR = new ArrayList<>();
            ArrayList<Integer> playTwoC = new ArrayList<>();
            System.out.println("Enter the filename with the game data:");
            String fileName = scanner.nextLine();
            File gameOne = new File(fileName);
            BufferedReader bfrThree = new BufferedReader(new FileReader(gameOne));
            String letter = bfrThree.readLine();
            while (letter != null) {
                int valueASCIIOne = letter.toUpperCase().charAt(0);
                valueASCIIOne = valueASCIIOne - 65;
                playOneR.add(valueASCIIOne);
                letter = bfrThree.readLine();
                playOneC.add(Integer.parseInt(letter) - 1);
                letter = bfrThree.readLine();
                int valueASCIITwo = letter.toUpperCase().charAt(0);
                valueASCIITwo = valueASCIITwo - 65;
                playTwoR.add(valueASCIITwo);
                letter = bfrThree.readLine();
                playTwoC.add(Integer.parseInt(letter) - 1);
                numTurns++;
                letter = bfrThree.readLine();
            }
            bfrThree.close();
            gameLog.setNumTurns(numTurns);
            for (int i = 0; i < numTurns; i++) {
                int curOneRow = playOneR.get(i);
                int curOneCol = playOneC.get(i);
                int curTwoRow = playTwoR.get(i);
                int curTwoCol = playTwoC.get(i);
                if (twoArray[curOneRow][curOneCol].equals("H")) {
                    oneHits++;
                    twoArray[curOneRow][curOneCol] = ("M");
                }
                if (oneArray[curTwoRow][curTwoCol].equals("H")) {
                    twoHits++;
                    oneArray[curTwoRow][curTwoCol] = ("M");
                }
            }
            if (oneHits == 17) {
                System.out.println("Enemy fleet destroyed. Congratulations player 1!");
                gameLog.setWinningPlayer(1);
                gameLog.setLosingPlayerHits(twoHits);
                FileWriter logGame = new FileWriter("GameLog.txt");
                BufferedWriter bfrLog = new BufferedWriter(logGame);
                bfrLog.write(gameLog.toString());
                bfrLog.close();
            }
            if (twoHits == 17) {
                System.out.println("Enemy fleet destroyed. Congratulations player 2!");
                gameLog.setWinningPlayer(2);
                gameLog.setLosingPlayerHits(oneHits);
                FileWriter logGame = new FileWriter("GameLog.txt");
                BufferedWriter bfrLog = new BufferedWriter(logGame);
                bfrLog.write(gameLog.toString());
                bfrLog.close();
            }
        }


        if (modeAnswer.equals("2")) {
            while (oneHits < 17 && twoHits < 17) {
                numTurns++;
                System.out.println("Player 1 - Enter a row letter from A - J");
                String playerOneRow = scanner.nextLine();
                System.out.println("Player 1 - Enter a column number from 1 - 14");
                int playerOneCol = scanner.nextInt() - 1;
                scanner.nextLine();
                int curOneRow = playerOneRow.toUpperCase().charAt(0);
                curOneRow = curOneRow - 65;
                if (twoArray[curOneRow][playerOneCol].equals("H")) {
                    oneHits++;
                    twoArray[curOneRow][playerOneCol] = "M";
                    System.out.println("Value: H");
                } else {
                    System.out.println("Value: M");
                }
                if (oneHits == 17) {
                    break;
                }
                System.out.println("Player 2 - Enter a row letter from A - J");
                String playerTwoRow = scanner.nextLine();
                System.out.println("Player 2 - Enter a column number from 1 - 14");
                int playerTwoCol = scanner.nextInt() - 1;
                scanner.nextLine();
                int curTwoRow = playerTwoRow.toUpperCase().charAt(0);
                curTwoRow = curTwoRow - 65;
                if (oneArray[curTwoRow][playerTwoCol].equals("H")) {
                    twoHits++;
                    oneArray[curTwoRow][playerTwoCol] = "M";
                    System.out.println("Value: H");
                } else {
                    System.out.println("Value: M");
                }
                if (twoHits == 17) {
                    break;
                }
            }
            gameLog.setNumTurns(numTurns);
            if (oneHits == 17) {
                System.out.println("Enemy fleet destroyed. Congratulations player 1!");
                gameLog.setWinningPlayer(1);
                gameLog.setLosingPlayerHits(twoHits);
                FileWriter logGame = new FileWriter("GameLog.txt");
                BufferedWriter bfrLog = new BufferedWriter(logGame);
                bfrLog.write(gameLog.toString());
                bfrLog.close();
            }
            if (twoHits == 17) {
                System.out.println("Enemy fleet destroyed. Congratulations player 2!");
                gameLog.setWinningPlayer(2);
                gameLog.setLosingPlayerHits(oneHits);
                FileWriter logGame = new FileWriter("GameLog.txt");
                BufferedWriter bfrLog = new BufferedWriter(logGame);
                bfrLog.write(gameLog.toString());
                bfrLog.close();
            }
        }
    }
}

