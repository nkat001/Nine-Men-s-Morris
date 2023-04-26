package game;

import game.Actor.Player;
import game.Board.Board;
import game.Action.*;
import game.Board.Position;

import java.util.ArrayList;
import java.util.Scanner;

public class TestBoardGame {

    Board b =  Board.getInstance();

    public static void main(String[] args) {

        int NUM_TOKENS = 3;
        boolean GAME_END = false;

        Scanner sc = new Scanner(System.in);
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


            // Player 1:
            Token token1 = new Token(player1.getDispChar());
            Action action1 = new Place();
            System.out.print(player1.getName() + " chooses a position to place your token: ");
            char p1InputPosition = sc.next().charAt(0);
            token1.executeAction(action1, b.findPosition(p1InputPosition));
            player1.addToken(token1);
            b.display();

            Token token2 = new Token(player2.getDispChar());
            Action action2 = new Place();
            System.out.print(player2.getName() + " chooses a position to place your token: ");
            char p2InputPosition = sc.next().charAt(0);
            token2.executeAction(action2, b.findPosition(p2InputPosition));
            player2.addToken(token2);
            b.display();

            System.out.println(x);
        }

        while (!GAME_END) {


            TestBoardGame testBoardGame = new TestBoardGame();
            testBoardGame.changeToPlayerView(player1.getTokens());
            System.out.println("woof");
            b.display();

            System.out.print(player1.getName() + " chooses a position to place your token: ");
            char p1InputPosition = sc.next().charAt(0);





        }


    }


    /*
    Separate sections of the code into methods to make it cleaner later
     */
    public void placePieces() {

    }

    public void changeToPlayerView(ArrayList<Token> tokens) {

        for (int i = 0; i < tokens.size(); i++) {

            // get position of token stored at index 'i' in tokens list
            Position tokenPosition = tokens.get(i).getPosition();
            // change position to number view and set hasPosition to false in order to swap back
            tokens.get(i).setDispChar(Integer.toString(i).charAt(0));
            tokens.get(i).setHasPosition(false);

            // get position of token stored at index 'i' in tokens list
            Action action1 = new Place();
            tokens.get(i).executeAction(action1, tokenPosition);
        }
    }

    public void revertPlayerView() {

    }
}
