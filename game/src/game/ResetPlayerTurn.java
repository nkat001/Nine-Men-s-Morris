package game;

import game.Actor.DoublePlayer;
import game.Actor.Player;
import javafx.scene.paint.Color;

public class ResetPlayerTurn {
    private static Player p1 ,p2;
    private static DoublePlayer doublePlayer;
    public static void setPlayer1(Player p){
        p1=p;
    }
    public static void setPlayer2(Player p){
        p2=p;
    }
    public static void resetPlayersTurn(Player p){
        if (p== p1){
            System.out.println("-------------------RESETING");
            p2.isPlayerTurn();
            p1.notPlayerTurn();
            doublePlayer.getP1Label().setTextFill(Color.RED);
        }
        else{
            p1.isPlayerTurn();
            p2.notPlayerTurn();
            doublePlayer.getP2Label().setTextFill(Color.RED);
        }
    }
}
