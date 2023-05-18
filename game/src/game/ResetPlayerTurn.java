package game;

import game.Actor.DoublePlayer;
import game.Actor.Player;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Rule class used to organise player turns
 *
 * Created by:
 *
 * @author Ethel Lim
 */
public class ResetPlayerTurn {
    private static Mode mode ;
    private static Player p1,p2;

    /**
     * set the game mode
     * @param m : mode
     */
    public static void setMode(Mode m){
        mode = m ;
    }

    /**
     * set player 1
     * @param p : player
     */
    public static void setPlayer1(Player p){
        p1=p;
    }

    /**
     * set player 2
     * @param p : player
     */
    public static void setPlayer2(Player p){
        p2=p;
    }

    /**
     * reset player turn
     * @param p : player
     */
    public static void resetPlayersTurn(Player p){
        if (p== p1) {
            p2.isPlayerTurn();
            p1.notPlayerTurn();
        }
        else{
            p1.isPlayerTurn();
            p2.notPlayerTurn();
        }
    }

    /**
     * reset player has a mill turn
     * @param p : player
     */
    public static void resetPlayerHasAMill(Player p){
        Rule.setHasAMill(false);
        if (p== p1) {
            p1.isPlayerTurn();
            p2.notPlayerTurn();
            changeTokenColor(p2);
        }
        else{
            p2.isPlayerTurn();
            p1.notPlayerTurn();
            changeTokenColor(p1);
        }
    }

    /**
     *
     * changes colour of the tokens
     * @param player : player
     */
    public static void changeTokenColor(Player player){
        if (player == p1)
        {
            mode.getP1Label().setTextFill(Color.LIGHTGREEN);
            mode.getP2Label().setTextFill(Color.WHITE);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), mode.getP2Label());
            translateTransition.setFromY(0);
            translateTransition.setToY(5);
            translateTransition.setInterpolator(Interpolator.EASE_BOTH);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(10);
            translateTransition.play();
        }
        else
        {
            mode.getP1Label().setTextFill(Color.WHITE);
            mode.getP2Label().setTextFill(Color.LIGHTGREEN);

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), mode.getP1Label());
            translateTransition.setFromY(0);
            translateTransition.setToY(5);
            translateTransition.setInterpolator(Interpolator.EASE_BOTH);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(10);
            translateTransition.play();
        }
    }

    /**
     *
     * end game
\\
     */

    public static void endPlayerGame(){
        p1.notPlayerTurn();
        p2.notPlayerTurn();
    }


}

