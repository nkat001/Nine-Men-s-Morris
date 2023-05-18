package game;

import game.Actor.DoublePlayer;
import game.Actor.Player;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    private DoublePlayer doublePlayer;

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

            // when pressed , check if player has a mill and the token is removable
            if((Rule.getHasAMill())&& (this.token.getIsTokenAllow()) &&(player.checkAction(this.token, initPos, null, true ))){
                System.out.println("Token removed!");
                ((Pane) node.getParent()).getChildren().remove(node);
                player.removeToken(this.token);
                initPos.removeToken();
                // check if is end game
                try {
                    if (Rule.endGame(player)){
                        // this player lose
                        //
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                ResetPlayerTurn.resetPlayerHasAMill(player);
            }

        });
        node.setOnMouseDragged(e->{
            if (this.token.getIsTokenAllow() && (!Rule.getHasAMill()) )
            {
                node.setTranslateX(e.getSceneX()- startx );
                node.setTranslateY(e.getSceneY()- starty);
            }
        });
    }
    public void allowTokenReleased(Node node){
        ArrayList<Position> pos  = Board.getInstance().getPositions();
        node.setOnMouseReleased(e -> {
            if (this.token.getIsTokenAllow() && (!Rule.getHasAMill()))
            {
                double releaseX = e.getSceneX()-Board.getInstance().getGameBoard().getLayoutX();
                double releaseY = e.getSceneY()-Board.getInstance().getGameBoard().getLayoutY();
                Boolean isTrue = false;

                for (Position p : pos ) {
                    Circle ip = p.getIP();
                    // check if the release token within the range and is token at the position
                    if((ip.contains(releaseX, releaseY)) && (!p.getIsTokenHere()))
                    {
                        finalPos= p;
                        if(player.checkAction(this.token, initPos,finalPos, false ))
                        {
                            // allow move the token to the new position
                            node.setTranslateX(e.getSceneX()- startx );
                            node.setTranslateY(e.getSceneY()- starty);
                            isTrue = true;
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
                else
                {
                    if(initPos!=null) {initPos.removeToken();}
                    // set the token position , add the token to the position
                    this.token.setTokenPosition(finalPos);
                    // check the release token is what
                    if (Rule.checkPlayerHasAMill(finalPos,token)){
                        millMessage();
                        System.out.println("detected HAS A MILL");
                        Rule.addPositionHasAMill(Rule.getPosHasAMill());;
                        Rule.setHasAMill(true);
                    }
                    else
                    {
                        ResetPlayerTurn.changeTokenColor(player);
                    }
                    ResetPlayerTurn.resetPlayersTurn(player);

                }
            }
            else
            {
                // not allowed to move , set  it on the ori position
                node.setTranslateX(initx);
                node.setTranslateY(inity);
            }
        });
    }

    public void millMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String s1 = player.getName();
        alert.setTitle("Mill Formed");
        alert.setHeaderText(null);
        alert.setContentText(player.getName() + " has a mill");
        alert.showAndWait();
    }

}
