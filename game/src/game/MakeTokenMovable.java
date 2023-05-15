package game;

import game.Actor.Player;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class MakeTokenMovable {
    private double startx, starty, initx,inity;
    private Token token ;
    private Position initPos, finalPos;
    private Player player;

    public MakeTokenMovable(Token token, Player player ){
        this.token= token ;
        this.player= player ;
        Circle circle= token.getToken();
        makeTokenDraggable(circle);
        allowTokenReleased(circle);
    }
    
    public void makeTokenDraggable(Node node){
        node.setOnMousePressed(e ->{
            this.initPos= token.getPosition();
            initx= node.getTranslateX();
            inity= node.getTranslateY();
            startx = e.getSceneX() - node.getTranslateX();
            starty = e.getSceneY() - node.getTranslateY();
        });
        node.setOnMouseDragged(e->{
            if (this.token.getIsTokenAllow())
            {
                node.setTranslateX(e.getSceneX()- startx );
                node.setTranslateY(e.getSceneY()- starty);
            }
        });
    }

    public void allowTokenReleased(Node node){
        ArrayList<Position> pos  = Board.getInstance().getPositions();
        // for loop each position , and check if the token is within the circle
        node.setOnMouseReleased(e -> {
            if (this.token.getIsTokenAllow()) {
                double releaseX = e.getSceneX()-Board.getInstance().getGameBoard().getLayoutX() ;
                double releaseY = e.getSceneY()-Board.getInstance().getGameBoard().getLayoutY();
                Boolean isTrue = false;

                for (Position p : pos ) {
                    Circle ip = p.getIP();
                    // check if the release token within the range and is token at the position
                    if((ip.contains(releaseX, releaseY)) && (!p.getIsTokenHere())){

                        if (threeInARow(p)) {
                            System.out.println("Result as to whether there is 3 in a row: " + threeInARow(p));
                        }

                        //System.out.println("Result as to whether there is 3 in a column: " + threeInAColumn(p));
                        //System.out.println("checking at a no token position ---- ");
                        finalPos= p;
                        // check if the action executed is right
                        if(player.checkAction(this.token, initPos,finalPos))
                        {
                            if(initPos!=null){
                                initPos.removeToken();}
                            // if action is executed right
                            //System.out.println("MOVE TO A NEW POSITION ");
                            // allow move the token to the new position
                            node.setTranslateX(e.getSceneX()- startx );
                            node.setTranslateY(e.getSceneY()- starty);
                            this.token.setTokenPosition(p);
                            isTrue = true ;
                            // reset player turn
                            ResetPlayerTurn.resetPlayersTurn(player);
                        }
                        break ;
                    }
                }
                // if is not on any correct position, then set the token back its ori position.
                if(!isTrue)
                {
                    node.setTranslateX(initx);
                    node.setTranslateY(inity);
                }
            }
            else
            {
                // not allowed to move , set  it on the ori position
                node.setTranslateX(initx);
                node.setTranslateY(inity);
                System.out.println("Not allowed to moved ");
            }
        });
    }

    private boolean threeInARow(Position position) {

        boolean inARow = false;
        int inARowCounter = 0; // checker

        if (!position.getIsTokenHere()) {

            inARowCounter += 1;
            ArrayList<Position> adjListFirstPos = position.getAdjList();
            // go through the adjList of the initial position (2)
            //System.out.println("(1) In a row current: " + inARowCounter);
            for (int i = 0; i < adjListFirstPos.size(); i++) {

                // if placed in middle check if 3 in a row
//                System.out.println("adList size:" + adjListFirstPos.size());
//                if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) == 1 && adjListFirstPos.get(i).getIsTokenHere() && Math.abs(adjListFirstPos.get(i+1).getTokenNumber() - position.getTokenNumber()) == 1 && adjListFirstPos.get(i+1).getIsTokenHere()) {
//                    inARow = true;
//                    break;
//                }

                // check if the position next to the initial position is adjacent (make sure it's not vertical)
                 if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) == 1 && adjListFirstPos.get(i).getIsTokenHere()) {

                    inARowCounter += 1;
                    ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();
                    //System.out.println("(2) In a row current: " + inARowCounter);


                    // go through adjList of the second position (3)
                    for (int j = 0; j < adjListSecondPos.size(); j++) {

                        //

                        System.out.println("subtraction 3: " + Math.abs(adjListSecondPos.get(i).getTokenNumber() - adjListFirstPos.get(i).getTokenNumber()));
                        System.out.println("number number: " + adjListSecondPos.get(i).getTokenNumber());
                        System.out.println("is a token there: " + adjListSecondPos.get(i).getIsTokenHere());

                        System.out.println(Math.abs(adjListSecondPos.get(i).getTokenNumber() - adjListFirstPos.get(i).getTokenNumber()) == 1 && adjListSecondPos.get(i).getIsTokenHere());

                        //

                        if (Math.abs(adjListSecondPos.get(i).getTokenNumber() - adjListFirstPos.get(i).getTokenNumber()) == 1 && adjListSecondPos.get(i).getIsTokenHere()) {
                            inARowCounter += 1;
                            inARow = true;
                            System.out.println("(3) In a row current: " + inARowCounter);
                            break;
                        }
                    }
                }
            }
        }
        // there is no token found at the position.
        return inARow;
    }

    private boolean threeInAColumn(Position position) {

        boolean inAColumn = false;
        int inAColumnCounter = 0; // checker

        // check if the position has a token on it right now (1)
        //System.out.println("The token number is: " + position.getTokenNumber());
        //System.out.println("Is there a token at the position: " + position.getIsTokenHere());
        if (!position.getIsTokenHere()) {

            inAColumnCounter += 1;
            ArrayList<Position> adjListFirstPos = position.getAdjList();
            // go through the adjList of the initial position (2)
            System.out.println("In a column current: " + inAColumnCounter);
            for (int i = 0; i < adjListFirstPos.size(); i++) {

                // check if the position next to the initial position is vertical (and not adjacent)
                if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) > 1 && adjListFirstPos.get(i).getIsTokenHere()) {

                    inAColumnCounter += 1;
                    ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();
                    System.out.println("In a column current: " + inAColumnCounter);

                    // go through adjList of the second position (3)
                    for (int j = 0; j < adjListSecondPos.size(); j++) {

                        if (Math.abs(adjListSecondPos.get(i).getTokenNumber() - adjListFirstPos.get(i).getTokenNumber()) > 1 && adjListSecondPos.get(i).getIsTokenHere()) {
                            inAColumnCounter += 1;
                            inAColumn = true;
                            System.out.println("In a column current: " + inAColumnCounter);
                            break;
                        }
                    }
                }
            }
        }
        // there is no token found at the position.
        return inAColumn;
    }



}
