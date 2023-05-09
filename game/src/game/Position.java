package game;

import game.Token;

/**
 * Position class that represent each position in the game board
 *
 * Created by:
 *
 * @author Ethel Lim
 */
public class Position {
    /**
     * initialise the attributes
     */
    private double x;
    private double y;
    private Boolean isTokenHere;
    private Token token ;

    /**
     * Constructor
     */
    public Position(double x , double y){
        this.x= x;
        this.y=y;
        this.isTokenHere= false ;
    }

    /**
     * get x
     */
    public double getX(){
        return x ;
    }

    /**
     * get y
     */
    public double getY(){
        return y ;
    }

    /**
     * add token instance to the position
     */
    public void addToken(Token t){
        isTokenHere= true ;
        token = t;}

    /**
     * remove token instance from the position
     */
    public void removeToken(){
        token = null ;
        isTokenHere= false;
    }

}
