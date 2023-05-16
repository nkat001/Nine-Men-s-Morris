package game;

import game.Actor.DoublePlayer;
import game.Actor.Player;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ResetPlayerTurn {
    private static Player p1 ,p2;
    public static void setPlayer1(Player p){
        p1=p;
    }
    public static void setPlayer2(Player p){
        p2=p;
    }
    public static void resetPlayersTurn(Player p){
        if (p == p1){
            System.out.println("-------------------RESETING");
        if (p== p1) {
            System.out.println("-------------------RESETTING----------------");
            p2.isPlayerTurn();
            p1.notPlayerTurn();
        }
        else{
            p1.isPlayerTurn();
            p2.notPlayerTurn();
        }
    }

    public static void changeTokenColor(Player player){
        if (player == p1) {
            DoublePlayer.player1Label().setTextFill(Color.BLACK);
            DoublePlayer.player2Label().setTextFill(Color.RED);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), DoublePlayer.player2Label());
            translateTransition.setFromY(0);
            translateTransition.setToY(5);
            translateTransition.setInterpolator(Interpolator.EASE_BOTH);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(10);
            translateTransition.play();
        }
        else {
            DoublePlayer.player1Label().setTextFill(Color.RED);
            DoublePlayer.player2Label().setTextFill(Color.BLACK);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), DoublePlayer.player1Label());
            translateTransition.setFromY(0);
            translateTransition.setToY(5);
            translateTransition.setInterpolator(Interpolator.EASE_BOTH);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(10);
            translateTransition.play();
        }
    }
}
}
