package game.Action;

import game.Position;
import game.Rule;
import game.Token;
import java.util.ArrayList;

/**
 * Slide class that represent an action
 *
 * Created by:
 *
 * @author Nethara
 */
public class Slide implements Action{
    /**
     * To execute the action on the token
     * @param token
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        System.out.println("In slide action--------------------------");

        Boolean b = false ;
        ArrayList adjList = initP.getAdjList();
        System.out.println(adjList.get(1));
        // check the initp adj list , if the two pos is the same
        for (int i = 0 ; i< initP.getAdjList().size(); i++){
            if(adjList.get(i)== newP) {
                b= true ;
            }
        }
        if (b){
            if (Rule.checkPositionsHasAMIll(initP)){
                // this token initial has a mill so remove the positions that has a mill
                Rule.removePositionsHasAMill(initP );
            }
        }

        return b;
    }
}
