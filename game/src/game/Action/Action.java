package game.Action;

import game.Board.Position;
import game.Token;

public interface Action {
    public String execute(Token token, Position position);
}
