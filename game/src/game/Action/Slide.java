package game.Action;

import game.Position;
import game.Token;

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
     * @param position
     * @param token
     * @return String
     */
    public String execute(Token token, Position position){
        Position initP= token.getPosition();
        initP.removeToken();
        token.setTokenPosition(position);
        return "The token slide to a new position";
    }
}
