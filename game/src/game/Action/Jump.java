package game.Action;

import game.Position;
import game.Rule;
import game.Token;

/**
 * Jump class that represent or flying / jumping on the board
 *
 * Created by:
 * @author Ethel
 */
public class Jump implements Action{

    /**
     * To check the action executed by the token
     * @param token : Token
     * @param initP : initial position
     * @param newP : new position
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        System.out.println("Checking Jump Action");
        Boolean b = false ;

        // check if new position has a token
        if(!newP.getIsTokenHere()){
            b= true ;
        }
        if (b){
            // this token has a mill before so remove the positions that has a mill once it moved
            if (Rule.checkPositionsHasAMIll(initP)){
                Rule.removePositionsHasAMill(initP );
            }
        }
        return b;
    }
}
