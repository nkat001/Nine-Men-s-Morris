package game.Action;

import game.Position;
import game.Token;
/**
 * Action interface class to create abstract methods and allow different actions to implement
 *
 * Created by:
 *
 * @author Nethara
 */
public interface Action {
    /**
     * To execute the action on the token
     * @param position
     * @param token
     * @return String
     */
    public Boolean execute(Token token,Position initP, Position newP);
}
