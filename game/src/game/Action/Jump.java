package game.Action;

import game.Position;
import game.Token;

/**
 * Jump class that represent an action
 *
 * Created by:
 *
 * @author Nethara
 */
public class Jump implements Action{

    /**
     * To execute the action on the token
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        Boolean b = false ;
        // token initial no position can execute
        if(!newP.getIsTokenHere()){
            b= true ;
        }
        return b;
    }
}
