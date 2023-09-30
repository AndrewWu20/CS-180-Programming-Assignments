/**
 * GameLog
 *
 * Program contains of the game once the game finishes
 *
 * @author Andrew Wu, L10
 *
 * @version 3/23/22
 *
 */

public class GameLog {
    private int winningPlayer;
    private int losingPlayerHits;
    private int numTurns;
    private String boardPatternOne;
    private String boardPatternTwo;

    public GameLog(int winningPlayer, int losingPlayerHits, int numTurns,
                   String boardPatternOne, String boardPatternTwo) {
        this.winningPlayer = winningPlayer;
        this.losingPlayerHits = losingPlayerHits;
        this.numTurns = numTurns;
        this.boardPatternOne = boardPatternOne;
        this.boardPatternTwo = boardPatternTwo;
    }

    public int getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(int winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public int getLosingPlayerHits() {
        return losingPlayerHits;
    }

    public void setLosingPlayerHits(int losingPlayerHits) {
        this.losingPlayerHits = losingPlayerHits;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }

    public String getBoardPatternOne() {
        return boardPatternOne;
    }

    public void setBoardPatternOne(String boardPatternOne) {
        this.boardPatternOne = boardPatternOne;
    }

    public String getBoardPatternTwo() {
        return boardPatternTwo;
    }

    public void setBoardPatternTwo(String boardPatternTwo) {
        this.boardPatternTwo = boardPatternTwo;
    }

    @Override
    public String toString() {
        String battleLog = "";
        if (winningPlayer == 1) {
            battleLog = "Battleship Game Log:" +
                    "\nWinning Player: Player " + winningPlayer +
                    "\nHits: 17 - " + losingPlayerHits +
                    "\nNumber of Turns To Win: " + numTurns +
                    "\nPlayer 1 Board Pattern: " + boardPatternOne +
                    "\nPlayer 2 Board Pattern: " + boardPatternTwo + "\n";
        } else {
            battleLog = "Battleship Game Log:" +
                    "\nWinning Player: Player " + winningPlayer +
                    "\nHits: " + losingPlayerHits + " - 17" +
                    "\nNumber of Turns To Win: " + numTurns +
                    "\nPlayer 1 Board Pattern: " + boardPatternOne +
                    "\nPlayer 2 Board Pattern: " + boardPatternTwo + "\n";
        }
        return battleLog;
    }
}
