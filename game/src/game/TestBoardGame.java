package game;

import game.Action.Action;
import game.Action.Place;
import game.Board.Board;

import java.util.Scanner;

public class TestBoardGame {
    public static void main(String[] args) {
        Board b =  Board.getInstance();
        b.display();
        Token token = new Token('o');
        Action a= new Place();
        token.executeAction(a,b.getBoardAt(0,0));
        b.display();
    }
}
