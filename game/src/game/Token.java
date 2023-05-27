package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A Token class that contains the attributes of player tokens.
 *
 * Created by:
 *
 * @author Ethel Lim
 * Modified by : Mahesh
 */
public class Token {
    private Position tokenPosition;
    private final int TOKEN_RADIUS = 22;
    private Circle token ;
    private Boolean isTokenAllow, hasPosition ;


    /**
     * constructor
     * @param color
     */
    public Token (Color color){
        this.token = new Circle(TOKEN_RADIUS, color);
        this.token.setStroke(Color.BLACK);
        this.token.setStrokeWidth(2);
        this.hasPosition= false;
        // for checking if is allow to move
        this.isTokenAllow= false;
    }

    /**
     * set Token Position
     * @param p Position
     */
    public void setTokenPosition(Position p ){
        this.tokenPosition= p;
        this.hasPosition= true ;
        tokenPosition.addToken(this);
    }

    /**
     * set isTokenAllow
     * @param b  Boolean
     */
    public void setIsTokenAllow(Boolean b ){
        this.isTokenAllow= b;
    }

    /**
     * get has position
     * @return Boolean
     */
    public Boolean getHasPosition(){
        return this.hasPosition;
    }

    /**
     * get isTokenAllow
     * @return Boolean
     */
    public Boolean getIsTokenAllow(){
        return this.isTokenAllow;
    }

    /**
     * get position
     * @return position
     */
    public Position getPosition() {
        return tokenPosition;
    }

    /**
     * get token
     * @return circle
     */
    public Circle getToken(){
        return this.token;
    }

}
