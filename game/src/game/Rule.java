package game;

import game.Actor.Player;
import javafx.scene.control.Alert;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;

public class Rule {
    private static HashMap <Position , ArrayList<ArrayList<Position> > > millPositions  = new HashMap<>();
    private static ArrayList<ArrayList<Position> > positionHasAMill= new ArrayList<>();
    private static ArrayList<Position> posHasAMill= new ArrayList<>();
    private static Boolean hasAMill= false ;

    public static void setMillPositions(Position pos, ArrayList<ArrayList<Position>> arr ){
        millPositions.put(pos, arr );
    }
    public static Boolean checkPlayerHasAMill(Position position, Token token){
        ArrayList<ArrayList<Position> > posList = millPositions.get(position);
        System.out.println("position = " + position + ", token = " + token);
        System.out.println(posList);
        Paint color = token.getToken().getFill();
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
                else if (pos.getToken().getToken().getFill() == color)
                {
                    System.out.println("addddedddddddddddddddddddddd");
                    posHasAMill.add(pos);
                    counter+=1;
                }
            }
            if (counter == 2){
                System.out.println("counter =======================    2");
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
    public static void addPositionHasAMill(ArrayList<Position> list ){
        ArrayList<Position> newList = new ArrayList<>(list);
        positionHasAMill.add(newList);
        System.out.println("Position that has a mill : "+positionHasAMill);
    }
    public static Boolean checkPositionsHasAMIll(Position p){
        if (p!=null && positionHasAMill.size()>0 ){
            System.out.println("enter the checking session ");
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

    public static Boolean endGame(Player player){
        if (player.getTokenSize() == 2)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner");
            alert.setHeaderText(null);
            alert.setContentText(player.getName() + " won the game!");
            alert.showAndWait();
            ResetPlayerTurn.endPlayerGame();
            return true ;
        }
        else {

        }
        return false ;
    }

}
