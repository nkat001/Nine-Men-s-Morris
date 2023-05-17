package game.Action;

import game.Position;
import game.Token;

/**
 * Action interface class to create abstract methods and allow different actions to implement
 * <p>
 * Created by: Ethel
 *
 * @author Nethara
 */
public interface Action {
    /**
     * Abstract method to execute the action on the token
     *
     * @param token is the token to execute on the board
     * @return Boolean
     */
    public Boolean execute(Token token, Position initP, Position newP);
}
