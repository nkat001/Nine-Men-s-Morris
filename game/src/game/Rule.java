package game;

import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Rule {
    private static ArrayList<ArrayList<Position> > positionHasAMill= new ArrayList<>();
    private static ArrayList<Position> posHasAMill= new ArrayList<>();
    private static Boolean hasAMill= false ;

    public static boolean threeInARow(Position position, Token token) {
        Boolean inARow = false;
        int inARowCounter = 0; // checker
        Paint color = token.getToken().getFill();
        posHasAMill.clear();

        // check if placed in middle:
        for (int i = 0; i < position.getAdjList().size(); i++)
        {
            if (Math.abs(position.getAdjList().get(i).getTokenNumber() - position.getTokenNumber()) == 1 && position.getAdjList().get(i).getIsTokenHere())
            {
                // if the token is the same player token
                if (position.getAdjList().get(i).getToken().getToken().getFill() == color )
                {
                    posHasAMill.add(position.getAdjList().get(i));
                    inARowCounter += 1;}
            }
            if (inARowCounter == 2)
            {
                posHasAMill.add(position);
                return true;
            }
        }

        if (!position.getIsTokenHere())
        {
            posHasAMill.clear();
            ArrayList<Position> adjListFirstPos = position.getAdjList();
            for (int i = 0; i < adjListFirstPos.size(); i++) {

                // check if the position next to the initial position is adjacent (make sure it's not vertical)
                if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) == 1 && adjListFirstPos.get(i).getIsTokenHere()) {

                    if (adjListFirstPos.get(i).getToken().getToken().getFill()!= color ){
                        inARow = false;
                        break ;
                    }
                    int adjacentToken = adjListFirstPos.get(i).getTokenNumber();
                    ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();

                    // go through adjList of the second position (3)
                    for (int j = 0; j < adjListSecondPos.size(); j++) {
                        if (Math.abs(adjListSecondPos.get(j).getTokenNumber() - adjacentToken) == 1 && (adjListSecondPos.get(j).getIsTokenHere())) {
                            // check the color of the second token
                            if (adjListSecondPos.get(j).getToken().getToken().getFill()!= color ) {
                                inARow = false;
                            }
                            else{
                                posHasAMill.add(position);
                                posHasAMill.add(adjListFirstPos.get(i));
                                posHasAMill.add(adjListSecondPos.get(j));
                                inARow = true;
                            }
                            break;
                        }
                    }
                }
            }
        }

        // there is no token found at the position.
        return inARow;
    }

    public static Boolean threeInAColumn(Position position,Token token) {
        Boolean inAColumn = false;
        int inAColumnCounter = 0;

        // checking color
        Paint color = token.getToken().getFill();
        posHasAMill.clear();
        // check if placed in middle:
        for (int i = 0; i < position.getAdjList().size(); i++) {
            if (Math.abs(position.getAdjList().get(i).getTokenNumber() - position.getTokenNumber()) > 1 && position.getAdjList().get(i).getIsTokenHere())
            {
                if (position.getAdjList().get(i).getToken().getToken().getFill()== color ){
                    posHasAMill.add(position.getAdjList().get(i));
                    inAColumnCounter += 1;
                }
            }
            if (inAColumnCounter == 2)
            {
                posHasAMill.add(position);
                System.out.println(" in a columnnn--------added pos ot the mill list " );
                return true;
            }
        }
        if (!position.getIsTokenHere()){
            posHasAMill.clear();
            ArrayList<Position> adjListFirstPos = position.getAdjList();
            for (int i = 0; i < adjListFirstPos.size(); i++) {
                // check if the position next to the initial position is adjacent (make sure it's not vertical)
                if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) > 1 && adjListFirstPos.get(i).getIsTokenHere()) {
                    if (adjListFirstPos.get(i).getToken().getToken().getFill()!= color ){
                        inAColumn = false;
                        break ;
                    }
                    int adjacentToken = adjListFirstPos.get(i).getTokenNumber();
                    ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();
                    // go through adjList of the second position (3)
                    for (int j = 0; j < adjListSecondPos.size(); j++) {
                        if (Math.abs(adjListSecondPos.get(j).getTokenNumber() - adjacentToken) > 1 && (adjListSecondPos.get(j).getIsTokenHere())) {
                            if (adjListSecondPos.get(j).getToken().getToken().getFill()== color ) {
                                posHasAMill.add(adjListFirstPos.get(i));
                                posHasAMill.add(adjListSecondPos.get(j));
                                posHasAMill.add(position);
                                System.out.println(" in a columnnn--------added pos ot the mill list " );
                                inAColumn = true;
                            }
                            else{
                                inAColumn = false;
                            }
                            break;
                        }
                    }
                }
            }
        }

        // there is no token found at the position.
        return inAColumn;
    }

    public static ArrayList<Position> getPosHasAMill(){
        return posHasAMill;
    }
    public static void addPositionHasAMill(ArrayList<Position> list ){
        System.out.println("list added ======= " + list);
        positionHasAMill.add(list);
    }

    public static Boolean checkPositionsHasAMIll(Position p){
        if (p!=null && positionHasAMill.size()>0 ){
            System.out.println("enter the checking session ");
            for (ArrayList<Position> posList : positionHasAMill){
                for ( Position pos  : posList){
                    System.out.println("loop each list ");
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
}
