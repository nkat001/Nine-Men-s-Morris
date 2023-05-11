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
        // check if the position is valid -> no token on the position
        if (!position.getIsTokenHere()){
//            token.setTokenPosition(position);
            token.allowTokenReleased();
            return "The token was placed to a new position";
        }
        else {
            return "Cannot be move to the new position";
        }
    }

}
