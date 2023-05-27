package game;

import game.Actor.Player;
import javafx.scene.control.Label;

/**
 * Mode class represent the mode that player choose
 *
 * Created by:
 *
 * @author Ethel Lim Jia Yee
 */
public interface Mode {

    /**
     * run method to run game logic
     */
    public void run();

    /**
     * get player 1 method
     */
    public Player getP1();

    /**
     * get player 2 method
     */
    public Player getP2();

    /**
     * get player 1 label method
     */
    public Label getP1Label();

    /**
     * get player 2 label method
     */
    public Label getP2Label();

}
