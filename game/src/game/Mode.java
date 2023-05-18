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
    // is double player or single player
    public void run();

    public Player getP1();

    public Player getP2();

    public Label getP1Label();

    public Label getP2Label();

}
