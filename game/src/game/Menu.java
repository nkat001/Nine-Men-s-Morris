package game;

import game.Actor.Player;
import game.Board.Board;
import game.Board.Position;

import java.util.Scanner;

public class Menu {
    //  player name and display character
    private Scanner sc = new Scanner(System.in);

    public String getPlayerName(){
        System.out.println("Player : Enter you name :");
        String name = sc.next();
        return name;
    }

    public void  welcomeMessage(){
        System.out.println("===========Welcome to the game=============");
        System.out.println("          < Nine Men Morris >              ");
    }
    public void printTitle(){
        System.out.println("          < Nine Men Morris >              ");
    }

    public Position getPlayerChoice(String name , Board b ){
        System.out.println(name + "Player : Where do you want to place your token");
        char position = sc.next().charAt(0);
        return b.findPosition(position);
    }

    public int askPlayerTokenPosition(String name){
        System.out.println(name + " Player : Which Token do you want to move ? ");
        int val = sc.nextInt();
        return val ;
    }


}
