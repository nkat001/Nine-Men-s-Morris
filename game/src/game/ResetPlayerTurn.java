package game;

import game.Actor.Player;

public class ResetPlayerTurn {
    private static Player p1 ,p2;
    public static void setPlayer1(Player p){
        p1=p;
    }
    public static void setPlayer2(Player p){
        p2=p;
    }
    public static void resetPlayersTurn(Player p){
        if (p== p1){
            System.out.println("-------------------RESETTING----------------");
            p2.isPlayerTurn();
            p1.notPlayerTurn();
        }
        else{
            p1.isPlayerTurn();
            p2.notPlayerTurn();
        }

    }

}
