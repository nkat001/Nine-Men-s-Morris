package game;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Token {
    private Position tokenPosition;
    private final int TOKEN_RADIUS = 18;
    private Circle token ;
    private double startx ;
    private double starty;
    private double initx;
    private double inity;


    // when create a Token,  generate the token colour
    public Token (Color color){
        this.token = new Circle(TOKEN_RADIUS, color);
        this.token.setStroke(Color.BLACK);
        this.token.setStrokeWidth(2);
        makeTokenDraggable(token);
        allowTokenReleased();
    }

    // set token to a new position using the draggable
    public void setTokenPosition(Position p ){
        this.tokenPosition= p;
        tokenPosition.addToken(this);
    }

    public Position getPosition() {
        return tokenPosition;
    }

    public void makeTokenDraggable(Node token){
        initx= token.getTranslateX();
        inity= token.getTranslateY();

        token.setOnMousePressed(e ->{
            startx = e.getSceneX() - token.getTranslateX();
            starty = e.getSceneY() - token.getTranslateY();
        });
        token.setOnMouseDragged(e->{;
            token.setTranslateX(e.getSceneX()- startx );
            token.setTranslateY(e.getSceneY()- starty);
        });
    }

    public void allowTokenReleased(){
        ArrayList<Position> pos  = Board.getInstance().getPositions();
        // for loop each position , and check if the token is within the circle
        token.setOnMouseReleased(e -> {
            double releaseX = e.getSceneX()-Board.getInstance().getGameBoard().getLayoutX() ;
            double releaseY = e.getSceneY()-Board.getInstance().getGameBoard().getLayoutY();
            Boolean isTrue = false;

            for (Position p : pos ){
                Circle ip = p.getIP();
                // check if the release token within the range and is token at the position
                if((ip.contains(releaseX, releaseY)) && (!p.getIsTokenHere())){
                    this.tokenPosition= p ;
                    p.addToken(this);
                    token.setTranslateX(e.getSceneX()- startx );
                    token.setTranslateY(e.getSceneY()- starty);
                    isTrue = true ;
                    break ;
                }
            }
            // if is not set , then set the token back its ori position.
            if(!isTrue){
                token.setTranslateX(initx);
                token.setTranslateY(inity);
            }
            System.out.println("Token released at: (" + releaseX + ", " + releaseY + ")");
        });
    }

    public Circle getToken(){
        return this.token;
    }

}
