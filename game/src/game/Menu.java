package game;

import java.util.Scanner;

/**
 * menu
 *
 * Created by:
 *
 * @author Ethel Lim Jia Yee
 */
public class Menu {

    private Scanner sc = new Scanner(System.in);

    /**
     * Ask for player name
     * @return The name of the player
     */
    public String getPlayerName(){
        System.out.println("Player : Enter you name :");
        String name = sc.next();
        return name;
    }

    /**
     * printing welcome message
     */
    public void  welcomeMessage(){
        System.out.println("===========Welcome to the game=============");
        System.out.println("          < Nine Men Morris >              ");
    }

    /**
     * printing game title
     */
    public void printTitle(){
        System.out.println("          < Nine Men Morris >              ");
    }

    /**
     * get player move token position
     * @return Position
     */
    public Position getPlayerChoice(String name , Board b ){
        System.out.println(name + "Player : Where do you want to place your token");
        char position = sc.next().charAt(0);
        return b.findPosition(position);
    }

    /**
     * ask player which token to move
     * @return int
     */
    public int askPlayerTokenPosition(String name){
        System.out.println(name + " Player : Which Token do you want to move ? ");
        int val = sc.nextInt();
        return val ;
    }


}
