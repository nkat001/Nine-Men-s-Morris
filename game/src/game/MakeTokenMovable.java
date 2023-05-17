package game;

import game.Actor.Player;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        // if the previous player has a mill

        node.setOnMouseClicked( e->{
            if(ResetPlayerTurn.getHasAMill()){
                System.out.println("Token removed!");
                if(player.checkAction(this.token, initPos, null, true )){
                    ((Pane) node.getParent()).getChildren().remove(node);
                    player.removeToken(this.token);
                    initPos.removeToken();
                    ResetPlayerTurn.resetPlayerHasAMill(player);
                }
            }
        });

        node.setOnMousePressed(e ->{
            this.initPos= token.getPosition();
            initx= node.getTranslateX();
            inity= node.getTranslateY();
            startx = e.getSceneX() - node.getTranslateX();
            starty = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e->{
            if (this.token.getIsTokenAllow() && (!ResetPlayerTurn.getHasAMill()) )
            {
                node.setTranslateX(e.getSceneX()- startx );
                node.setTranslateY(e.getSceneY()- starty);
            }
        });
    }

    public void allowTokenReleased(Node node){
        ArrayList<Position> pos  = Board.getInstance().getPositions();

        node.setOnMouseReleased(e -> {
            if (this.token.getIsTokenAllow() && (!ResetPlayerTurn.getHasAMill()))
            {
                double releaseX = e.getSceneX()-Board.getInstance().getGameBoard().getLayoutX();
                double releaseY = e.getSceneY()-Board.getInstance().getGameBoard().getLayoutY();
                Boolean isTrue = false;
                Boolean threeFound = false;

                for (Position p : pos ) {
                    Circle ip = p.getIP();
                    // check if the release token within the range and is token at the position
                    if((ip.contains(releaseX, releaseY)) && (!p.getIsTokenHere()))
                    {
                        finalPos= p;

                        if(player.checkAction(this.token, initPos,finalPos, false ))
                        {
                            if(initPos!=null)
                            {
                                initPos.removeToken();
                            }
                            // allow move the token to the new position
                            node.setTranslateX(e.getSceneX()- startx );
                            node.setTranslateY(e.getSceneY()- starty);
                            this.token.setTokenPosition(p);
                            isTrue = true;

                            System.out.println("in a columnnnnnnnnnnnnnnnnnnnnn"+threeInAColumn(p));
                            System.out.println("in a rowwwwwwwwwwwwwwwwwwwwwwwww"+threeInARow(p));

                            // check if the action executed is right
                            if (threeInAColumn(p) || threeInARow(p))
                            {
                                System.out.println("detected three  pieces");
                                // three found then is player turn again to choose one opponent player to remove
                                ResetPlayerTurn.setPlayerHasAMill(true);
                                ResetPlayerTurn.resetPlayersTurn(player);
                            }
                            else{
                                // reset player turn
                                ResetPlayerTurn.resetPlayersTurn(player);
                                ResetPlayerTurn.changeTokenColor(player);
                            }

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
        Boolean inARow = false;
        int inARowCounter = 0; // checker
        Paint color = this.token.getToken().getFill();
        System.out.println(color);


        // check if placed in middle:
        for (int i = 0; i < position.getAdjList().size(); i++)
        {
            if (Math.abs(position.getAdjList().get(i).getTokenNumber() - position.getTokenNumber()) == 1 && position.getAdjList().get(i).getIsTokenHere())
            {
                System.out.println(position.getAdjList().get(i).getToken().getToken().getFill());
                // if the token is the same player token
                if (position.getAdjList().get(i).getToken().getToken().getFill() == color )
                {
                    inARowCounter += 1;
                }
            }
            if (inARowCounter == 2)
            {
                System.out.println("======================= 2 in a row" );
                return true;
            }
        }

        ArrayList<Position> adjListFirstPos = position.getAdjList();
        for (int i = 0; i < adjListFirstPos.size(); i++)
        {
            // check if the position next to the initial position is adjacent (make sure it's not vertical)
             if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) == 1 && adjListFirstPos.get(i).getIsTokenHere())
             {
                 int adjacentToken = adjListFirstPos.get(i).getTokenNumber();
                ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();
                // go through adjList of the second position (3)
                for (int j = 0; j < adjListSecondPos.size(); j++)
                {
                    if (Math.abs(adjListSecondPos.get(j).getTokenNumber() - adjacentToken) == 1 && (adjListSecondPos.get(j).getIsTokenHere()))
                    {
                        if (adjListSecondPos.get(j).getToken().getToken().getFill()!=color){
                            inARow = false;
                        }
                        else{
                            System.out.println(adjListSecondPos.get(j).getToken().getToken().getFill());
                            System.out.println(color);
                            System.out.println(" samew colourrrrrrrrrrrrrrrrrrrrrrrrrrr");
                            inARow = true;
                        }
                        break ;
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
        Paint color = this.token.getToken().getFill();
        System.out.println(color + " ------------------------------------------");

        // check if placed in middle:
        for (int i = 0; i < position.getAdjList().size(); i++) {
            if (Math.abs(position.getAdjList().get(i).getTokenNumber() - position.getTokenNumber()) > 1 && position.getAdjList().get(i).getIsTokenHere())
            {
                if (position.getAdjList().get(i).getToken().getToken().getFill()== color ){
                    inAColumnCounter += 1;
                }
            }
            if (inAColumnCounter == 2)
            {
                return true;
            }
        }

        ArrayList<Position> adjListFirstPos = position.getAdjList();
        for (int i = 0; i < adjListFirstPos.size(); i++)
        {
            // check if the position next to the initial position is adjacent (make sure it's not vertical)
            if (Math.abs(adjListFirstPos.get(i).getTokenNumber() - position.getTokenNumber()) ==3 && adjListFirstPos.get(i).getIsTokenHere())
            {
                int adjacentToken = adjListFirstPos.get(i).getTokenNumber();
                ArrayList<Position> adjListSecondPos = adjListFirstPos.get(i).getAdjList();
                // go through adjList of the second position (3)
                for (int j = 0; j < adjListSecondPos.size(); j++)
                {
                    if (Math.abs(adjListSecondPos.get(j).getTokenNumber() - adjacentToken) ==3 && (adjListSecondPos.get(j).getIsTokenHere()))
                    {
                        if ( adjListSecondPos.get(j).getToken().getToken().getFill()== color){
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
        // there is no token found at the position.
        return inAColumn;
    }
}
