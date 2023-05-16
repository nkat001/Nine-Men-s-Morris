package game.Action;

import game.Position;
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
     * @param position
     * @param token
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        Boolean b = false ;
        
        Circle circle = token.getToken();

        System.out.println("Click an opponent's token to remove!");
        circle.setOnMouseClicked(event -> {
            System.out.println("Token removed!");
            ((Pane) circle.getParent()).getChildren().remove(circle);

        });


        return b;
    }

}
