package game.Action;

import game.Position;
import game.Token;
/**
 * Place class that represent placing a token on the board
 *
 * Created by:
 *
 * @author Ethel
 */
public class Place implements Action {
    /**
     * To check the action executed by the token
     * @param token : Token
     * @param initP : initial position
     * @param newP : new position
     * @return String
     */
    public Boolean execute(Token token, Position initP, Position newP){
        System.out.println("Checking Place Action");
        Boolean b = false ;

        // token initial no position can execute
        if(!token.getHasPosition()){
            b= true ;
        }
        return b ;
    }

}
