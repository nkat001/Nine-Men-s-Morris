package game.Action;

import game.Position;
import game.Token;
/**
 * Place class that represent an action
 *
 * Created by:
 *
 * @author Nethara
 */
public class Place implements Action {
    /**
     * To execute the action on the token
     * @param position
     * @param token
     * @return String
     */
    public String execute(Token token, Position position){
        token.setTokenPosition(position);
        return "The token was placed to a new position";
    }

}
