package game;

import game.Actor.Player;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import java.util.Random;

/**
 * MakeTokenMoveable class that contains logic as how to the token is interacted with and placed on the board.
 *
 * Created by:
 *
 * @author Ethel Lim Jia Yee
 * Modified by : Mahesh
 */
public class MakeTokenMovable {

    // attributes
    private double startx, starty, initx,inity;
    private Token token ;
    private Position initPos, finalPos;
    private Player player;
    private Boolean isRemoveToken= false ;

    /**
     * Constructor
     * @param  token token to move
     * @param player
     */
    public MakeTokenMovable(Token token, Player player ){
        this.token= token ;
        this.player= player ;
        Circle circle= token.getToken();

        if (player.getPlayerControlled()) {

            makeTokenDraggable(circle);
            allowTokenReleased(circle);

        } else {
            System.out.println("the player isn't a human for sure");
            dragRandomShapes();
        }

    }


    /**
     *
     * logic for allowing tokens to become draggable
     * @param node
     */
    public void makeTokenDraggable(Node node){
        // when token is pressed
        node.setOnMousePressed(e ->{
            // get the inital position of the token
            this.initPos= token.getPosition();
            initx= node.getTranslateX();
            inity= node.getTranslateY();
            startx = e.getSceneX() - node.getTranslateX();
            starty = e.getSceneY() - node.getTranslateY();

            // Check if player has a mill and the token is removable
            if((Rule.getHasAMill())&& (this.token.getIsTokenAllow())){
                // check if is all tokens from the player has a mill
                if ( Rule.checkAllTokenMillPositions(player)){
                    if (initPos!= null){
                        // remove any tokens selected from the player
                        ((Pane) node.getParent()).getChildren().remove(node);
                        player.removeToken(this.token);
                        initPos.removeToken();
                        ResetPlayerTurn.resetPlayerHasAMill(player);
                        this.isRemoveToken= true ;
                    }
                }
                // check if the remove action is valid
                else if ((player.checkAction(this.token, initPos, null, true ))){
                    // valid then remove the token
                    ((Pane) node.getParent()).getChildren().remove(node);
                    player.removeToken(this.token);
                    initPos.removeToken();
                    ResetPlayerTurn.resetPlayerHasAMill(player);
                    this.isRemoveToken= true ;
                }

                // Check if is end game
                try {
                    Rule.endGame(player);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //  set action when token is drag on the board
        node.setOnMouseDragged(e->{
            if (this.token.getIsTokenAllow() && (!Rule.getHasAMill()) && (!this.isRemoveToken) )
            {
                node.setTranslateX(e.getSceneX()- startx );
                node.setTranslateY(e.getSceneY()- starty);
            }
        });
    }



    /**
     *
     * logic for allowing tokens to become computer selected
     * @param node
     */
    public void makeTokenComputerDraggable(Node node) {

    }

    public void dragRandomShapes() {

//        // List of random positions (on board that can be moved too)
//
//        // Alternate through those positions
//
//
//
//        // Trigger the mouse press and drag events on shape1 programmatically
//        shape.fireEvent(new MouseEvent(MouseEvent.MOUSE_PRESSED, initialX, initialY, initialX, initialY, null, 0, false, false, false, false, true, false, false, false, false, false, null));

    }


    /**
     *
     * allows token to be dropped onto the board
     */
    public void allowTokenReleased(Node node){
        ArrayList<Position> pos  = Board.getInstance().getPositions();

        // set action when token is release
        node.setOnMouseReleased(e -> {
            // check is token allow to move
            if (this.token.getIsTokenAllow() && (!Rule.getHasAMill()) && (!this.isRemoveToken))
            {
                double releaseX = e.getSceneX()-Board.getInstance().getGameBoard().getLayoutX();
                double releaseY = e.getSceneY()-Board.getInstance().getGameBoard().getLayoutY();
                Boolean isTrue = false;

                // check if the release token within the range of positions
                for (Position p : pos ) {
                    Circle ip = p.getIP();
                    // check is token at the positions
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

                // if is not on any correct position, then set the token back its position.
                if(!isTrue)
                {
                    node.setTranslateX(initx);
                    node.setTranslateY(inity);
                }
                // is is a correct position
                else
                {
                    // remove token from initial position and set to new position
                    if(initPos!=null) {initPos.removeToken();}
                    this.token.setTokenPosition(finalPos);

                    // check if the player has a mill
                    if (Rule.checkPlayerHasAMill(finalPos,token)){
                        // has a mill display mill message to UI
                        Rule.millMessage(player);
                        Rule.addPositionHasAMill(Rule.getPosHasAMill());;
                        Rule.setHasAMill(true);
                    }
                    // no mill then reset players turn
                    else
                    {
                        ResetPlayerTurn.changeTokenColor(player);
                    }
                    ResetPlayerTurn.resetPlayersTurn(player);
                }
            }
            else
            {
                // not allowed to move
                node.setTranslateX(initx);
                node.setTranslateY(inity);
            }
        });
    }


}
