package game;

import game.Actor.Player;
import game.Actor.SinglePlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Rule class that contains methods to determine if a Mill is found
 *
 * Created by:
 *
 * @author Ethel Lim Jia Yee
 * Modified by : Mahesh
 */
public class Rule {
    private static HashMap<Position, ArrayList<ArrayList<Position>>> millPositions = new HashMap<>();
    private static ArrayList<ArrayList<Position>> positionHasAMill = new ArrayList<>();
    private static ArrayList<Position> posHasAMill = new ArrayList<>();
    private static Boolean hasAMill = false;
    private static Mode mode;
    public static Boolean spMode= false ;
    private static Stage tryStage ;
    private static SinglePlayer sp ;

    public static void setTryStage(Stage t){
        tryStage = t ;
    }
    /**
     * initiate computer move on the board if is a single player mode
     */
    public static void initiateCompMove(){
        sp.initiateComputerMove();
    }

    /**
     * setting the single player mode
     */
    public static void setSp(SinglePlayer mode){
        sp = mode;
    }

    /**
     * set the mill positions of the game board
     * @param pos : Position
     * @param arr : ArrayList<ArrayList<Position>>
     */
    public static void setMillPositions(Position pos, ArrayList<ArrayList<Position>> arr) {
        millPositions.put(pos, arr);
    }

    /**
     * set the game mode
     * @param m : mode
     */
    public static void setMode(Mode m){mode = m;}
    public static void setSpMode(Boolean m){spMode = m;}

    /**
     * checks to see if a mill exists on the board
     * @param token
     * @param position
     */
    public static Boolean checkPlayerHasAMill(Position position, Token token){
        ArrayList<ArrayList<Position> > posList = millPositions.get(position);
        Paint color = token.getToken().getFill();
        System.out.println("first color : "+color);
        Boolean isAMill= false ;
        int counter;
        for ( ArrayList<Position> posL : posList )
        {
            posHasAMill.clear();
            counter =0 ;
            for (Position pos:posL)
            {
                // if there is no token and not same color
                if(!pos.getIsTokenHere() )
                {
                    // break the list , check other list
                    isAMill= false;
                    break ;
                }
                // if there is token , check color
                else if (pos.getToken().getToken().getFill() == color)
                {
                    posHasAMill.add(pos);
                    counter+=1;
                }
            }
            if (counter == 2){
                posHasAMill.add(position);
                isAMill= true ;
                break ;
            }
        }
        return isAMill;
    }

    /**
     * get the positions that formed a mill
     * @return ArrayList<Position>
     */
    public static ArrayList<Position> getPosHasAMill(){
        return posHasAMill;
    }
    /**
     * Detects if a position has a mill and adds it into the list
     * @param list of positions
     */
    public static void addPositionHasAMill(ArrayList<Position> list ){
        ArrayList<Position> newList = new ArrayList<>(list);
        positionHasAMill.add(newList);
    }

    /**
     *
     *  checks to see if the position has a Mill
     * @param p : position
     */
    public static Boolean checkPositionsHasAMIll(Position p){
        if (p!=null && positionHasAMill.size()>0 ){
            for (ArrayList<Position> posList : positionHasAMill){
                for ( Position pos  : posList){
                    if ( pos == p ){
                        return true ;
                    }
                }
            }
        }
        return false ;
    }

    /**
     *
     * removes the position that has a mill
     * @param p : position
     */
    public static void removePositionsHasAMill(Position p){
        Boolean isRemove = false ;
        for (ArrayList<Position> posList : positionHasAMill){
            for ( Position pos  : posList){
                if ( pos == p ){
                    positionHasAMill.remove(posList);
                    isRemove= true ;
                    break;
                }
            }
            if (isRemove){
                break ;
            }
        }
    }

    /**
     * set has a mill
     * @param b Boolean
     */
    public static void setHasAMill(Boolean b){
        hasAMill=b;
    }

    /**
     * get if the player has a mill
     * @return Boolean
     */
    public static Boolean getHasAMill(){
        return hasAMill;
    }

    /**
     *
     * Checks to see if the game has ended
     * @param player
     */
    public static Boolean endGame(Player player) throws Exception {
        if (player.getTokenSize() == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner");
            alert.setHeaderText(null);

            if (mode.getP1()==player) {
                alert.setContentText(mode.getP2().getName() + " won the game!");
            }
            else {
                alert.setContentText(mode.getP1().getName() + " won the game!");
            }
            alert.showAndWait();
            Home home = new Home();
            home.start(tryStage);
            ResetPlayerTurn.endPlayerGame();
            return true;
        }
        return false;
    }

    /**
     * checks is all tokens from the player has a mill
     * @param player
     */
    public static Boolean checkAllTokenMillPositions(Player player){
        // if all tokens has positons in the has a mill list
        Boolean isAllMill = true ;
        ArrayList<Token> tokens = player.getTokens();

        for ( int i =0 ; i< tokens.size() ; i++){
            Position pos = tokens.get(i).getPosition();
            System.out.println(pos);
            if( pos!= null && !checkPositionsHasAMIll(pos))
            {
                isAllMill = false ;
                break ;
            }

        }
        return isAllMill;
    }

    /**
     * display mill message on the UI
     * @param player
     */
    public static void millMessage(Player player){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mill Formed");
        alert.setHeaderText(null);
        alert.setContentText(player.getName() + " has a mill");
        alert.showAndWait();
    }

    public static void reset(){
        positionHasAMill.clear();
        posHasAMill.clear();
    }
}
