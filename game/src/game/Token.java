package game;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Token {
    private Position position;
    private final int TOKEN_RADIUS = 18;
    private Circle circle ;
    private double startx ;
    private double starty;
    private double initx;
    private double inity;


    // when create a Token,  generate the token colour
    public Token (Color color){
        this.circle = new Circle(TOKEN_RADIUS, color);
        this.circle.setStroke(Color.BLACK);
        this.circle.setStrokeWidth(2);
        makeTokenDraggable(circle);
    }

    // set token to a new position using the draggable
    public void setTokenPosition(Position p ){
        this.position= p;
        position.addToken(this);
    }

    public Position getPosition() {
        return position;
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

    public void allowTokenReleased(Node token , Circle c){
        ArrayList<Position> pos  = Board.getInstance().getPositions();

        // for loop each position , and check if the token is within the circle
        // if is , then set translate
        // if

        token.setOnMouseReleased(e -> {
            double releaseX = e.getSceneX()-Board.getInstance().getGameBoard().getLayoutX() ;
            double releaseY = e.getSceneY()-Board.getInstance().getGameBoard().getLayoutY();
            for (Position p : pos ){
                Circle ip = p.getIP();
                if(ip.contains(releaseX, releaseY)){
                    token.setTranslateX(e.getSceneX()- startx );
                    token.setTranslateY(e.getSceneY()- starty);
                }
            }
            if(c.contains(releaseX, releaseY)){
                token.setTranslateX(e.getSceneX()- startx );
                token.setTranslateY(e.getSceneY()- starty);
            }
            else{
                token.setTranslateX(initx);
                token.setTranslateY(inity);
            }
            System.out.println("Token released at: (" + releaseX + ", " + releaseY + ")");
            // Perform any desired actions with the release location
        });
    }

    public Circle getToken(){
        return this.circle;
    }

}
