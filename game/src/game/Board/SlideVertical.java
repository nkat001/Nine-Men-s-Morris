package game.Board;

import game.Status;

public class SlideVertical  extends PositionCharacter {
    public SlideVertical(){
        super('|', Status.TOKEN_SLIDE);
    }
}
