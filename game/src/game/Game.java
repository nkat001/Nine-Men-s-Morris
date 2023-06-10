package game;

/**
 * Game class represent the main game logic
 * Created by:
 *
 * @author Ethel Lim Jia Yee
 */
public class Game {

    private final Board board = Board.getInstance();

    private Mode mode;

    public Game(Mode m) {
        // double player mode or single player mode
        this.mode = m;
        Rule.setMode(mode);
        // reset the game board and all positions stuff
        reset();
    }

    /**
     * run method
     * to run all the game logic
     */
    public void run() {
        this.mode.run();
    }

    /**
     * get board method
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * get mode method
     * @return Mode
     */
    public Mode getMode() {
        return this.mode;
    }

    /**
     * reset all the positions and game logic
     * @return void
     */
    public void reset(){
        // reset the position
        for (Position p : board.getPositions()){
            p.resetToken();
        }
        // reset the rule
        Rule.reset();
    }

}
