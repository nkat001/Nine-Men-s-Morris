package game;

import game.Actor.Player;
import game.Board.Board;
import game.Action.*;

public class TestBoardGame {
    public static void main(String[] args) {

        int NUM_TOKENS = 9;

        Board b =  Board.getInstance();
        b.display();


        // Create two players and construct them with their given token value
        Player player1 = new Player("Mahesh", 'x');
        Player player2 = new Player("Jisoo", 'o');

//        Token token = new Token('o');
//        Action abs= new Place();
//        token.executeAction( abs ,b.getBoardAt(0,0));
//        b.display();




        for (int x=0; x < NUM_TOKENS; x++) {

            /*
             1. How to better represent the position of the board for players instead of (0, 0) label the terminal map,
             or label the UI so players know the location

             2. Need to validate player positions, player cannot be "placing" their piece on array locations that are '-'
             placement should only be allowed on * marks on the board


             */

            // Player 1:
            Token token1 = new Token(player1.getDispChar());
            Action action1 = new Place();
            token1.executeAction(action1, b.getBoardAt(0,0));




            Token token2 = new Token(player2.getDispChar());
            Action action2 = new Place();
            token2.executeAction(action2, b.getBoardAt(0, 1));

        }


    }
}
