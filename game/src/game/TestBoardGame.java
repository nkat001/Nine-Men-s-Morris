package game;

import game.Board.Board;

public class TestBoardGame {
    public static void main(String[] args) {
        Board b =  Board.getInstance();
        b.display();
        Token token = new Token('o');
        token.setTokenPosition(0,0);
        b.display();

    }
}
