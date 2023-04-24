package game.Action;

import game.Board.Position;
import game.Token;

public class Place implements Action {

    public String execute(Token token, Position position){
        token.setTokenPosition(position);
        return "The token was placed to a new position";
    }

}
