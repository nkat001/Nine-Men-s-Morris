package game;

import game.Actor.Player;
import game.Board.Board;
import game.Action.*;

import java.util.Scanner;

public class TestBoardGame {
    public static void main(String[] args) {

        int NUM_TOKENS = 9;
        boolean GAME_END = false;

        Board b =  Board.getInstance();
        b.display();

        /*
        Can adjust this later so that player gets to choose the display character they want
         */
        Player player1 = new Player("Mahesh", '@');
        Player player2 = new Player("Jisoo", '$');

        for (int x=0; x < NUM_TOKENS; x++) {

            /*
             1. How to better represent the position of the board for players instead of (0, 0) label the terminal map,
             or label the UI so players know the location

             2. Need to validate player positions, player cannot be "placing" their piece on array locations that are '-'
             placement should only be allowed on * marks on the board
             */

            Scanner sc = new Scanner(System.in);

            // Player 1:
            Token token1 = new Token(player1.getDispChar());
            Action action1 = new Place();
            System.out.print(player1.getName() + " chooses a position to place your token: ");
            char p1InputPosition = sc.next().charAt(0);
            token1.executeAction(action1, b.findPosition(p1InputPosition));
            b.display();

            Token token2 = new Token(player2.getDispChar());
            Action action2 = new Place();
            System.out.print(player2.getName() + " chooses a position to place your token: ");
            char p2InputPosition = sc.next().charAt(0);
            token2.executeAction(action2, b.findPosition(p2InputPosition));
            b.display();

        }

        while (!GAME_END) {




        }


    }


    /*
    Separate sections of the code into methods to make it cleaner later
     */
    public void placePieces() {

    }
}
