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
                boolean threeFound = false;

                for (Position p : pos ) {
                    Circle ip = p.getIP();
                    // check if the release token within the range and is token at the position
                    if((ip.contains(releaseX, releaseY)) && (!p.getIsTokenHere())){

//                        if (threeInARow(p)) {
//                            System.out.println("Result as to whether there is 3 in a row: " + threeInARow(p));
//                        }
//
//                        if (threeInAColumn(p)) {
//                            System.out.println("Result as to whether there is 3 in a column: " + threeInAColumn(p));
//                        }



                        finalPos= p;
                        // check if the action executed is right

                        if (threeInAColumn(p) || threeInARow(p)) {
                            threeFound = true;
                        }
                        System.out.println("threeFound value: " + threeFound);

                        if(player.checkAction(this.token, initPos,finalPos, threeFound))
                        {
                            if(initPos!=null) {
                                initPos.removeToken();
                            }
                            // if action is executed right
                            System.out.println("MOVE TO A NEW POSITION ");
                            ResetPlayerTurn.changeTokenColor(player);
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

        // check if placed in middle:


        for (int i = 0; i < position.getAdjList().size(); i++) {

            if (Math.abs(position.getAdjList().get(i).getTokenNumber() - position.getTokenNumber()) == 1 && position.getAdjList().get(i).getIsTokenHere()) {
                inARowCounter += 1;
            }

            if (inARowCounter == 2) {
                return true;

            }

        }

        if (!position.getIsTokenHere()) {

            ArrayList<Position> adjListFirstPos = position.getAdjList();

            for (int i = 0; i < adjListFirstPos.size(); i++) {

                // check if the position next to the initial position is adjacent (make sure it's not vertical)
                 if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) == 1 && adjListFirstPos.get(i).getIsTokenHere()) {

                     int adjacentToken = adjListFirstPos.get(i).getTokenNumber();
                    ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();

                    // go through adjList of the second position (3)
                    for (int j = 0; j < adjListSecondPos.size(); j++) {

                        if (Math.abs(adjListSecondPos.get(j).getTokenNumber() - adjacentToken) == 1 && (adjListSecondPos.get(j).getIsTokenHere())) {
                            inARow = true;
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

        // check if placed in middle:


        for (int i = 0; i < position.getAdjList().size(); i++) {


            if (Math.abs(position.getAdjList().get(i).getTokenNumber() - position.getTokenNumber()) > 1 && position.getAdjList().get(i).getIsTokenHere()) {
                inAColumnCounter += 1;
            }

            if (inAColumnCounter == 2) {
                return true;

            }

        }

        if (!position.getIsTokenHere()) {

            ArrayList<Position> adjListFirstPos = position.getAdjList();

            for (int i = 0; i < adjListFirstPos.size(); i++) {

                // check if the position next to the initial position is adjacent (make sure it's not vertical)
                if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) > 1 && adjListFirstPos.get(i).getIsTokenHere()) {

                    int adjacentToken = adjListFirstPos.get(i).getTokenNumber();
                    ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();

                    // go through adjList of the second position (3)
                    for (int j = 0; j < adjListSecondPos.size(); j++) {


                        if (Math.abs(adjListSecondPos.get(j).getTokenNumber() - adjacentToken) > 1 && (adjListSecondPos.get(j).getIsTokenHere())) {
                            inAColumn = true;
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
