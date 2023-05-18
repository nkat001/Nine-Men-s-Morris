package game.Action;

import game.Position;
import game.Rule;
import game.Token;
import java.util.ArrayList;

/**
 * Slide class that represent sliding the token on the board
 *
 * Created by:
 *
 * @author Ethel
 */
public class Slide implements Action{

    /**
     * To check the action executed by the token
     * @param token : Token
     * @param initP : initial position
     * @param newP : new position
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        System.out.println("Checking Slide Action");

        Boolean b = false ;
        ArrayList adjList = initP.getAdjList();

        // loop through the adjacent list check if is the correct position
        for (int i = 0 ; i< initP.getAdjList().size(); i++){
            if(adjList.get(i)== newP) {
                b= true ;
            }
        }
        if (b){
            // check if this position has a mill before
            if (Rule.checkPositionsHasAMIll(initP)){
                // has a mill then remove the position from the mill list
                Rule.removePositionsHasAMill(initP );
            }
        }

        return b;
    }
}
