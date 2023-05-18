package game;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import game.Actor.Player;
import javafx.scene.control.Alert;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

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
    private static Stage stage = new Stage();
    private static Home homepage = new Home();
    private  static Mode mode;


    public Rule(Mode mode) {
        this.mode = mode;
    }
    public static void setMillPositions(Position pos, ArrayList<ArrayList<Position>> arr) {
        millPositions.put(pos, arr);
    }

    /**
     *
     * checks to see if a mill exists on the board
     *
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
                    System.out.println("NOOO ADDEDD");
                    isAMill= false;
                    break ;
                }
                // if there is token , check color
                else if (pos.getToken().getToken().getFill() == color)
                {
                    System.out.println("same color so added");
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
    public static ArrayList<Position> getPosHasAMill(){
        return posHasAMill;
    }
    /**
     * Detects if a position has a mill and adds it into the list
     *
     */
    public static void addPositionHasAMill(ArrayList<Position> list ){
        ArrayList<Position> newList = new ArrayList<>(list);
        positionHasAMill.add(newList);
        System.out.println("Position that has a mill : "+positionHasAMill);
    }

    /**
     *
     *  checks tos ee if the position has a Mill
     */
    public static Boolean checkPositionsHasAMIll(Position p){
        if (p!=null && positionHasAMill.size()>0 ){
            for (ArrayList<Position> posList : positionHasAMill){
                for ( Position pos  : posList){
                    if ( pos == p ){
                        System.out.println("yess possssssssss has a millllllll");
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
     */
    public static void removePositionsHasAMill(Position p){
        Boolean isRemove = false ;
        for (ArrayList<Position> posList : positionHasAMill){
            for ( Position pos  : posList){
                if ( pos == p ){
                    System.out.println("remove the position sthat has a millllllll");
                    positionHasAMill.remove(posList);
                    isRemove= true ;
                    break ;
                }
            }
            if (isRemove){
                break ;
            }
        }
    }

    public static void setHasAMill(Boolean b){
        hasAMill=b;
    }

    public static Boolean getHasAMill(){
        return hasAMill;
    }

    /**
     *
     * Checks to see if the game has ended
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
            homepage.start(stage);
            ResetPlayerTurn.endPlayerGame();
            return true;
        }
        return false;
    }

    /**
     *
     * checks to see if there are tokens on all positions
     */
    public static Boolean checkAllTokenMillPositions(Player player){
        // if all tokens has positons in the has a mill list
        System.out.println(" IN CHECKINGGGGG ALL TOKEN MILL POSITIONS  " );
        Boolean isAllMill = true ;
        ArrayList<Token> tokens = player.getTokens();

        for ( int i =0 ; i< tokens.size() ; i++){
            Position pos = tokens.get(i).getPosition();
            System.out.println(pos);

            if( pos!= null && !checkPositionsHasAMIll(pos))
            {
                System.out.println(" NOT ALL POS HAS A MILLLLLLLLLLLLL " );
                isAllMill = false ;
                break ;
            }

        }
        return isAllMill;
    }

}
