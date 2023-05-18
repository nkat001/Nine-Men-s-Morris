package game.Action;

import game.Position;
import game.ResetPlayerTurn;
import game.Rule;
import game.Token;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * Remove class that represent removing the token from the player
 *
 * Created by:
 *
 * @author Ethel
 */
public class Remove implements Action{
    /**
     * To check the action executed by the token
     * @param token : Token
     * @param initP : initial position
     * @param newP : new position
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        // check if the remove token is removable
        System.out.println("Checking Remove Action");

        Boolean b = true  ;
        if (initP== null){
            System.out.println("cannot remove token from player repository");
            b= false;
        }
        else if (Rule.checkPositionsHasAMIll(initP)){
            System.out.println("cannot remove token from position that has a mill ");
            b= false;
        }
        return b;
    }

}
