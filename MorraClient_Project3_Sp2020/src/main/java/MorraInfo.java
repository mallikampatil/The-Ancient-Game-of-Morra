import java.io.Serializable;

public class MorraInfo implements Serializable {

    int p1Points, p2Points;
    int p1Plays, p2Plays;
    int p1Guess, p2Guess;
    int gameMode;
    boolean p1;
    boolean p2;

    MorraInfo (int gameMode, int p1Points, int p2Points, int p1Plays, int p2Plays, int p1Guess, int p2Guess, boolean p1, boolean p2) {

        this.gameMode = gameMode;
        this.p1Points = p1Points;
        this.p2Points = p2Points;
        this.p1Plays = p1Plays;
        this.p2Plays = p2Plays;
        this.p1Guess = p1Guess;
        this.p2Guess = p2Guess;
        this.p1 = p1;
        this.p2 = p2;

    }

    void setP1 (boolean val) {
        this.p1 = val;
    }

    void setP2 (boolean val) {
        this.p2 = val;
    }

}