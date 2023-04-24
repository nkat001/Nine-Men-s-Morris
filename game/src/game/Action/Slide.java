package game.Action;

import game.Board.Position;
import game.Token;

public class Slide implements Action{
    public String execute(Token token, Position position){
        token.setTokenPosition(position);
        return "The token slide to a new position";
    }
}
