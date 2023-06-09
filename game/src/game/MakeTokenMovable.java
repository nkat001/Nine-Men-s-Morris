package game;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.geometry.Point2D;
import game.Actor.Player;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;


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
        makeTokenDraggable(circle);
        allowTokenReleased(circle);
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
                Boolean checkRemove = false ;
                // check if is all tokens from the player has a mill
                if ( Rule.checkAllTokenMillPositions(player)){
                    if (initPos!= null){
                        checkRemove= true ;
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
                    checkRemove= true ;
                }

                // Check if is end game
                try {
                    Rule.endGame(player);
                    if (checkRemove && Rule.spMode){
                        initiateComputerMove();
                    }
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
                        ResetPlayerTurn.resetPlayersTurn(player);
                    }
                    // no mill then reset players turn
                    else
                    {
                        ResetPlayerTurn.changeTokenColor(player);
                        ResetPlayerTurn.resetPlayersTurn(player);

                        // rule is single player mode
                        if (Rule.spMode)
                        {
                            initiateComputerMove();
                        }
                    }
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

    public void initiateComputerMove( ){
        System.out.println("in  computer move");
        ArrayList<Position > positions = Board.getInstance().getPositions();
        int index = (int) Math.floor(Math.random() * 24);
        // get a valid random position
        while (positions.get(index).getIsTokenHere()){
            index = (int) Math.floor(Math.random() * 24);
        }


        // place the token to the new position
        ArrayList<Token> tokens = Rule.getCompTokens();

        // final position and token chosen
        Position finalPos = positions.get(index);
        Token selectedToken = tokens.get(0);
        Boolean isMoved = false  ;

        for (int i = 0 ; i< tokens.size(); i++){
            if (!tokens.get(i).getHasPosition()) {
                // retrieve attributes
                Circle ip = finalPos.getIP();
                Token tk = tokens.get(i);
                selectedToken= tk ;

                // set the token position
                // local to scene the board circle
                Point2D sceneValue = ip.localToScene(ip.getCenterX(), ip.getCenterY());
                Point2D sceneStart = tk.getToken().localToScene(tk.getToken().getTranslateX(), tk.getToken().getTranslateY());

                startx = sceneStart.getX() - tk.getToken().getTranslateX();
                starty = sceneStart.getY() - tk.getToken().getTranslateY();

                showTransition(selectedToken, tk.getToken().getTranslateX(),tk.getToken().getTranslateY(),
                        sceneValue.getX()- startx, sceneValue.getY()- starty);

                tk.getToken().setTranslateX(sceneValue.getX()- startx);
                tk.getToken().setTranslateY(sceneValue.getY()- starty);

                // update correctly
                tk.setTokenPosition(finalPos);
                isMoved= true;
                break ;

            }
        }


        // make it move
        if (!isMoved){
            if (tokens.size()==3)
            {
                int tokenInd = (int) Math.floor(Math.random() * 3);
                selectedToken = tokens.get(tokenInd);

            }
            else
            {
                while (!isMoved)
                {
                    // select random token
                    int tokenInd = (int) Math.floor(Math.random() * tokens.size());
                    // check where the token can slide to
                    Token tk = tokens.get(tokenInd);
                    // init position
                    Position initP = tk.getPosition();

                    for (int i = 0 ; i < initP.getAdjList().size(); i++)
                    {
                        Position slideP = initP.getAdjList().get(i);
                        if (!slideP.getIsTokenHere())
                        {
                            finalPos = slideP;
                            selectedToken= tk;
                            isMoved= true ;
                            break;}
                    }
                }
            }

            Circle ip = finalPos.getIP();
            Circle initIP = selectedToken.getPosition().getIP();

            if (Rule.checkPositionsHasAMIll(selectedToken.getPosition())){
                Rule.removePositionsHasAMill(selectedToken.getPosition() );
            }

            // local to scene for the circle board
            Point2D sceneValue = ip.localToScene(ip.getCenterX(), ip.getCenterY());
            // local to scene for the token position
            Point2D sceneStart = initIP.localToScene(initIP.getCenterX(), initIP.getCenterY());
            // set the start x and y value
            startx = sceneStart.getX() - selectedToken.getToken().getTranslateX();
            starty = sceneStart.getY() - selectedToken.getToken().getTranslateY();

            showTransition(selectedToken, selectedToken.getToken().getTranslateX(),selectedToken.getToken().getTranslateY(),
                    sceneValue.getX()- startx, sceneValue.getY()- starty);

            // remove init position
            selectedToken.getPosition().removeToken();
            // set token position
            selectedToken.getToken().setTranslateX(sceneValue.getX()- startx);
            selectedToken.getToken().setTranslateY(sceneValue.getY()- starty);
            selectedToken.setTokenPosition(finalPos);

        }

        // check if this move has a mill
        if ( Rule.checkPlayerHasAMill(finalPos, selectedToken))
        {
            Rule.millMessage(Rule.getCompPlayer());
            ArrayList<Token> playerTokens  = player.getTokens();
            Rule.addPositionHasAMill(Rule.getPosHasAMill());
            Token tokenRemove = null ;
            if (Rule.checkAllTokenMillPositions(player))
            {
                // all player token has a mill , select any one
                for ( int i = 0 ; i < playerTokens.size(); i ++)
                {
                    if (playerTokens.get(i).getHasPosition())
                    {
                        tokenRemove= playerTokens.get(i);
                        break ;
                    }
                }

            }
            else {
                // select one opponent token
                for ( int i = 0 ; i < playerTokens.size(); i ++)
                {
                    if (playerTokens.get(i).getHasPosition() && !Rule.checkPositionsHasAMIll(playerTokens.get(i).getPosition()))
                    {
                        tokenRemove= playerTokens.get(i);
                    }
                }
            }
            Position initPos = tokenRemove.getPosition();
            // remove any tokens selected from the player
            ((Pane) tokenRemove.getToken().getParent()).getChildren().remove(tokenRemove.getToken());
            player.removeToken(tokenRemove);
            initPos.removeToken();
            System.out.println("remove opponent token ");
        }

        // remove player token
        ResetPlayerTurn.resetPlayersTurn(Rule.getCompPlayer());
        ResetPlayerTurn.changeTokenColor(player);
        // reset the computer to player turn
    }


    public void showTransition(Token selectedToken,double startX, double startY, double endX, double endY ){
        // Create a TranslateTransition for the token
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), selectedToken.getToken());

        // Set the starting and ending positions for the animation
        transition.setFromX(startX);
        transition.setFromY(startY);
        transition.setToX(endX);
        transition.setToY(endY);

        // Set any additional animation properties
        transition.setCycleCount(1); // Play the animation once
        transition.setAutoReverse(false); // Do not reverse the animation

        // Play the animation
        transition.play();
    }

}
