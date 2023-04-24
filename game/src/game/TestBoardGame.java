package game;

import game.Board.Board;
import game.Action.*;

public class TestBoardGame {
    public static void main(String[] args) {
        Board b =  Board.getInstance();
        b.display();
        Token token = new Token('o');
        Action abs= new Place();
        token.executeAction( abs ,b.getBoardAt(0,0));
        b.display();
    }
}
