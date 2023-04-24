package game;

import game.Action.Action;
import game.Board.Board;
import game.Board.Position;

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
        position.setDispChar(this.dispChar);
    }

    public Boolean getHasPosition(){
        return hasPosition;
    }

    // ALLOWABLE ACTION
    public void executeAction(Action action , Position position ){
        action.execute(this, position);
    }

}
