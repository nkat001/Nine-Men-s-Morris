package game.Action;

import game.Position;
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
     * @param position
     * @param token
     * @return String
     */
    public String execute(Token token, Position position){
        token.setTokenPosition(position);
        return "The Token Jumped to a new position";
    }
}
