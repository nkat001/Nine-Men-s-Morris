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
 * Remove class that represent an action
 *
 * Created by:
 *
 * @author Nethara
 */
public class Remove implements Action{
    /**
     * To execute the action on the token

     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        // check if the remove token is removable
        System.out.println("In REMOVE action--------------------------");

        Boolean b = true  ;
        if (initP== null){
            System.out.println("cannot remove from player repos");
            b= false;
        }
        else if (Rule.checkPositionsHasAMIll(initP)){
            System.out.println("cannot remove token from position that has a mill ");
            b= false;
        }
        return b;
    }

}
