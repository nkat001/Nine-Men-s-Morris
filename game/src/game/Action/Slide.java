package game.Action;

import game.Position;
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
        Boolean b = false ;
        ArrayList adjList = initP.getAdjList();
        System.out.println(adjList.get(1));
        // check the initp adj list , if the two pos is the same
        for (int i = 0 ; i< initP.getAdjList().size(); i++){
            if(adjList.get(i)== newP) {
                System.out.println("------------------------TRUE------------------TRUE--------------TRUE" );
                b= true ;
            }
        }
        if (!b){
            System.out.println("------------------------FALSE------------------FALSE--------------FALSE" );

        }

        return b;
    }
}
