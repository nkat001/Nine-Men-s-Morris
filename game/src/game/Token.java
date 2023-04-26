package game;

import game.Board.Board;
import game.Board.Position;
import game.Action.Action;

public class Token {
    private char dispChar ;
    private Position position;
    private Boolean hasPosition;
    private Board board = Board.getInstance();

    public Token (char dispChar){
        this.dispChar = dispChar;
        this.hasPosition = false ;
    }

    public void setTokenPosition(Position p ){
        this.hasPosition = true ;
        this.position= p;
        position.addToken(this);
    }

    public Boolean getHasPosition(){
        return hasPosition;
    }

    // ALLOWABLE ACTION
    public void executeAction(Action action , Position position ){
        action.execute(this, position);}

    public Position getPosition() {
        return position;
    }

    public char getDispChar() {
        return dispChar;
    }

    public void setDispChar(char dispChar) {
        this.dispChar = dispChar;
    }

    public void setHasPosition(Boolean hasPosition) {
        this.hasPosition = hasPosition;
    }
}
