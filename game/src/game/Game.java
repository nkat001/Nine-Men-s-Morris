package game;

import game.Action.Place;
import game.Action.Slide;
import game.Actor.Player;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Game class represent the main game logic
 *
 * Created by:
 *
 * @author Ethel Lim Jia Yee
 */
public class Game {

    private final Board board = Board.getInstance();

    private Mode mode ;

    public Game(Mode m){
        // double player mode or single player mode
        this.mode = m;
    }

    public void run(){
        this.mode.run();
    }

    public Board getBoard(){
        return board ;
    }

    public Mode getMode(){
        return this.mode ;
    }


}
