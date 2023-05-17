package game.Action;

import game.Position;
import game.Token;

/**
 * Place class that represent an action
 * <p>
 * Created by: Ethel
 *
 * @author Nethara
 */
public class Place implements Action {
    /**
     * To execute the action on the token
     *
     * @param token is the token to execute on the board
     * @return Boolean
     */
    public Boolean execute(Token token, Position initP, Position newP) {
        Boolean b = false;
        // token initial no position can execute
        if (!token.getHasPosition()) {
            b = true;
        }
        return b;
    }

}
