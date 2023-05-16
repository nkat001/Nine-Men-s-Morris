package game.Action;

import game.Position;
import game.Token;
/**
 * Place class that represent an action
 *
 * Created by:
 *
 * @author Nethara
 */
public class Place implements Action {
    /**
     * To execute the action on the token
     * @param token
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        Boolean b = false ;
        // token initial no position can execute
        if(!token.getHasPosition()){

            b= true ;
        }


        return b ;
    }

}
