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
    private int x;
    private int y;
    private Boolean isTokenHere;
    private char dispChar;
    private Token token ;

    /**
     * Constructor
     */
    public Position(int x , int y, char c){
        this.x= x;
        this.y=y;
        this.dispChar= c;
        this.isTokenHere= false ;
    }

    /**
     * get the position diplay character
     */
    public  char getDispChar(){
        if (isTokenHere){
            return token.getDispChar();
        }
        return dispChar;
    }

    /**
     * get x
     */
    public int getX(){
        return x ;
    }

    /**
     * get y
     */
    public int getY(){
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
