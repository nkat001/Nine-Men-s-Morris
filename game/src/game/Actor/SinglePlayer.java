package game.Actor;
import game.Mode;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class SinglePlayer implements Mode {

    private static Label p1Label, p2Label;

    public SinglePlayer(Label p1L, Label p2L){
        p1Label=p1L;
        p2Label=p2L;
    }

    /**
     * run method to run game logic
     */
    public void run(){
    }

    /**
     * get player 1 method
     */
    public Player getP1(){
        return new Player("aaa", Color.BLUE );
    }

    /**
     * get player 2 method
     */
    public Player getP2()
    {
        return new Player("bbb", Color.BLUE );

    }

    /**
     * get player 1 label method
     */
    public Label getP1Label()
    {
        return p1Label;
    }

    /**
     * get player 2 label method
     */
    public Label getP2Label(){
        return p2Label;

    }

}
