package game.Action;

import game.Position;
import game.Rule;
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
        System.out.println("IN JUMP ACTION CLASSSSSSSSSSSSSSSSS ");
        Boolean b = false ;
        // token initial no position can execute
        if(!newP.getIsTokenHere()){
            b= true ;
        }
        if (b){
            System.out.println("ALD JUMPPPP ");
            if (Rule.checkPositionsHasAMIll(initP)){
                // this token initial has a mill so remove the positions that has a mill
                Rule.removePositionsHasAMill(initP );
            }
        }
        return b;
    }
}
