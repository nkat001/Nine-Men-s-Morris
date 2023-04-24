package game.Action;

import game.Board.Position;
import game.Token;

public class Jump implements Action{
    public String execute(Token token, Position position){
        token.setTokenPosition(position);
        return "The Token Jumped to a new position";
    }
}
