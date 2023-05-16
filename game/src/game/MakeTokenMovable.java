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
                        System.out.println("checking at a no token position ---- ");
                        finalPos= p;
                        // check if the action executed is right
                        if(player.checkAction(this.token, initPos,finalPos))
                        {
                            if(initPos!=null){
                                initPos.removeToken();}
                            // if action is executed right
                            System.out.println("MOVE TO A NEW POSITION ");
                            ResetPlayerTurn.changeTokenColor(player);
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
}
