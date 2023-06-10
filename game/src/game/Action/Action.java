package game.Action;

import game.Position;
import game.Token;
/**
 * Action interface class to create abstract methods and allow different actions to implement
 * Created by:
 *
 * @author Nethara
 */
public interface Action {
    /**
     * To check the action executed by the token
     * @param token : Token
     * @param initP : initial position
     * @param newP : new position
     * @return String
     */
    public Boolean execute(Token token,Position initP, Position newP);
}
