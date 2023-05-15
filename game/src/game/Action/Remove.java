package game.Action;

import game.Position;
import game.Token;
import javafx.scene.Parent;
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
     * @param position
     * @param token
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        Boolean b = false ;
        
        Circle circle = token.getToken();

        circle.setOnMouseClicked(event -> {
            System.out.println("Circle clicked!");
        });


        return b;
    }

}
