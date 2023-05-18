package game;

import game.Action.Action;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Token {
    private Position tokenPosition;
    private final int TOKEN_RADIUS = 22;
    private Circle token ;
    private Boolean isTokenAllow, hasPosition ;

    // when create a Token,  generate the token colour
    public Token (Color color){
        this.token = new Circle(TOKEN_RADIUS, color);
        this.token.setStroke(Color.BLACK);
        this.token.setStrokeWidth(2);
        this.hasPosition= false;
        // for checking if is this token chosen to move and check if is allow
        this.isTokenAllow= false;

    }

    // set token to a new position using the draggable
    public void setTokenPosition(Position p ){
        this.tokenPosition= p;
        this.hasPosition= true ;
        tokenPosition.addToken(this);
    }
    public void setIsTokenAllow(Boolean b ){
        this.isTokenAllow= b;
    }

    public Boolean getHasPosition(){
        return this.hasPosition;
    }

    public Boolean getIsTokenAllow(){
        return this.isTokenAllow;
    }

    public Position getPosition() {
        return tokenPosition;
    }

    public Circle getToken(){
        return this.token;
    }

}
