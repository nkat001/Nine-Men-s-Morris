package game.Actor;

import game.*;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;


/**
 * @author Ethel
 *
 * modified by: Nethara
 */
public class SinglePlayer implements Mode {

    private static final Font playerFont = Font.font("Arial", FontWeight.BOLD, 30);
    private Player p1, p2;
    private static Label p1Label, p2Label;

    /**
     * Constructor
     * @param p1Name player 1 name
     * @param p2Name player 2 name
     */
    public SinglePlayer(String p1Name, String p2Name) {
        // create player instances
        p1 = new Player(p1Name, Color.PINK);
        p2 = new Player(p2Name, Color.BLUE );
        // set the computer player
        Rule.setSpMode(true);
        Rule.setSp(this);
        // setting the UI for double player
        BackgroundFill backgroundFill = new BackgroundFill(Color.DARKBLUE, new CornerRadii(7), null);
        Background background = new Background(backgroundFill);

        // set p1 label in the screen
        if (!p1.getName().equals("")) {
            p1Label = new Label(p1.getName());
            p1Label.setTextFill(Color.WHITE);
        } else {
            p1Label = new Label("Player 1");
            p1Label.setTextFill(Color.WHITE);
        }
        p1Label.setFont(playerFont);
        p1Label.setBackground(background);
        StackPane.setMargin(p1Label, new Insets(200));
        StackPane.setAlignment(p1Label, Pos.TOP_LEFT);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), p1Label);
        translateTransition.setFromY(0);
        translateTransition.setToY(5);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(10);
        translateTransition.play();

        // set p2 label in the screen
        p2Label = new Label("Computer");
        p2Label.setTextFill(Color.LIGHTGREEN);
        p2Label.setFont(playerFont);
        p2Label.setBackground(background);
        StackPane.setMargin(p2Label, new Insets(200));
        StackPane.setAlignment(p2Label, Pos.TOP_RIGHT);
    }

    /**
     * run method
     * allow the game to start running the logic
     */
    public void run() {
        // set the initial game status
        ResetPlayerTurn.setMode(this);
        ResetPlayerTurn.setPlayer1(p1);
        ResetPlayerTurn.setPlayer2(p2);
        p1.isPlayerTurn();
        p2.notPlayerTurn();
    }

    /**
     * initiate computer move method
     * a method to trigger the computer to move a random token to a random place
     */
    public void initiateComputerMove(){
        p2.notPlayerTurn();
        // start x and start y value
        double startx , starty;
        // get the list of positions
        ArrayList<Position> positions = Board.getInstance().getPositions();
        // select a random index for position
        int index = (int) Math.floor(Math.random() * 24);
        while (positions.get(index).getIsTokenHere()){
            index = (int) Math.floor(Math.random() * 24);
        }

        // get the list of tokens from computer player
        ArrayList<Token> tokens = p2.getTokens();

        // store the final position and the selected token
        Position finalPos = positions.get(index);
        Token selectedToken = tokens.get(0);

        // check if is moved
        Boolean isMoved = false  ;

        // do place actions first
        for (int i = 0 ; i< tokens.size(); i++){
            if (!tokens.get(i).getHasPosition()) {
                // retrieve attributes
                Circle ip = finalPos.getIP();
                Token tk = tokens.get(i);
                selectedToken= tk ;

                // local the position and token scene
                Point2D sceneValue = ip.localToScene(ip.getCenterX(), ip.getCenterY());
                Point2D sceneStart = tk.getToken().localToScene(tk.getToken().getTranslateX(), tk.getToken().getTranslateY());

                // store the start x and y values
                startx = sceneStart.getX() - tk.getToken().getTranslateX();
                starty = sceneStart.getY() - tk.getToken().getTranslateY();

                // make transition
                showTransition(selectedToken, tk.getToken().getTranslateX(),tk.getToken().getTranslateY(),
                        sceneValue.getX()- startx, sceneValue.getY()- starty);

                // update the token position correctly
                tk.setTokenPosition(finalPos);
                isMoved= true;
                break ;
            }
        }

        // if not placed action , do slide action or jump action
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

            // get the ihe circle
            Circle ip = finalPos.getIP();
            Circle initIP = selectedToken.getPosition().getIP();
            // check if this position has a mill
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
            // do transition
            showTransition(selectedToken, selectedToken.getToken().getTranslateX(),selectedToken.getToken().getTranslateY(),
                    sceneValue.getX()- startx, sceneValue.getY()- starty);


            // remove the initial position
            selectedToken.getPosition().removeToken();
            // set token position and value
            selectedToken.setTokenPosition(finalPos);
        }

        // check if this move has a mill
        if ( Rule.checkPlayerHasAMill(finalPos, selectedToken))
        {
            // output mill message
            Rule.millMessage(p2);
            ArrayList<Token> playerTokens  = p1.getTokens();
            Rule.addPositionHasAMill(Rule.getPosHasAMill());
            Token tokenRemove = null ;
            if (Rule.checkAllTokenMillPositions(p1))
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
            // remove any tokens selected from the player
            fadeOutTransition(tokenRemove);
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            ResetPlayerTurn.resetPlayersTurn(p2);
            ResetPlayerTurn.changeTokenColor(p2);
        });
        pause.play();

    }

    /**
     * show transition of the computer move
     * a method to show transition of the computer move
     */
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
        transition.setOnFinished( event -> {
            selectedToken.getToken().setTranslateX(endX);
            selectedToken.getToken().setTranslateY(endY);
        });
        // Play the animation
        transition.play();
    }


    /**
     * fade transition of the computer move
     * a method to show transition of the computer move
     */
    public void  fadeOutTransition(Token tokenRemove){
        Position initPos = tokenRemove.getPosition();
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2.1), tokenRemove.getToken());
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setOnFinished(event -> {
            // Remove the token from its parent node after the animation completes
            ((Pane) tokenRemove.getToken().getParent()).getChildren().remove(tokenRemove.getToken());
            p1.removeToken(tokenRemove);
            initPos.removeToken();
        });
        fadeOutTransition.play();
    }
    /**
     * get player 1
     */
    public Player getP1() {
        return p1;
    }
    /**
     * get player 2
     */
    public Player getP2() {
        return p2;
    }

    /**
     * get player 1 label on UI
     */
    public Label getP1Label() {
        return p1Label;
    }

    /**
     * get player 1 label on UI
     */
    public Label getP2Label() {
        return p2Label;
    }

}
