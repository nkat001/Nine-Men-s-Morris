package game.Board;
import game.Status;
public abstract class PositionCharacter {
    private char dispChar;
    private Status capability ;

    public PositionCharacter(char dispChar, Status status ){
        this.dispChar= dispChar;
        this.capability= status;
    }

    public char getDispChar() {
        return dispChar;
    }
}
