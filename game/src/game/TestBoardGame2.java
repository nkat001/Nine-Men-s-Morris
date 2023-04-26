package game;

import game.Action.Place;
import game.Action.Slide;
import game.Actor.Player;
import game.Board.Board;
import game.Board.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestBoardGame2 {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Menu menu = new Menu();
        Board board =  Board.getInstance();
        Boolean gameEnd = false;

        menu.welcomeMessage();
        board.display();
        int counter = 0;


        // Setting up the players
        Player p1 = new Player(menu.getPlayerName(), '@');
        Player p2 = new Player(menu.getPlayerName(), '&');
        ArrayList<Token> p1Tokens= p1.getTokens();
        ArrayList<Token> p2Tokens= p2.getTokens();

        // place all the tokens on the board
        for (int i = 0 ; i <9; i++){
            Position p = menu.getPlayerChoice(p1.getName(), board);
            Token t=p1.getTokenAt(i);
            t.executeAction(new Place(), p);

            Position pos2 = menu.getPlayerChoice(p2.getName(), board);
            Token t2=p2.getTokenAt(i);
            t2.executeAction(new Place(), pos2);
            menu.printTitle();
            board.display();
        }

        while (counter<= 9){
            counter +=1 ;
            System.out.println("Round " +counter +":");
            menu.printTitle();

            // display the board for player choice
            board.displayPlayerTokens(p1);
            // the token index value
            int val = menu.askPlayerTokenPosition(p1.getName());
            Token t = p1Tokens.get(val);
            // the position player want to place
            Position p = menu.getPlayerChoice(p1.getName(), board);
            t.executeAction(new Slide(), p );

            // display the board for player choice
            board.displayPlayerTokens(p2);
            // the token index value
            int val2 = menu.askPlayerTokenPosition(p2.getName());
            Token t2 = p2Tokens.get(val2);
            // the position player want to place
            Position pos = menu.getPlayerChoice(p2.getName(), board);
            t2.executeAction(new Slide(), pos );

            System.out.println("Both players finish inputting tokens value");
            menu.printTitle();
            board.display();
        }
    }




}
