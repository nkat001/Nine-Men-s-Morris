package game;

import game.Board.Board;
import game.Board.Position;

public class Token {
    private char dispChar ;
    private Position position;
    private Board board = Board.getInstance();

    public Token (char dispChar){
        this.dispChar = dispChar;
    }

    public void setTokenPosition(Position p ){
        this.position= p;
        position.setDispChar(this.dispChar);
    }
}
