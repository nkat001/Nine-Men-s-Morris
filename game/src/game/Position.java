package game;

import javafx.scene.shape.Circle;
import java.util.ArrayList;

/**
 * Position class that represent each position in the game board
 * Created by:
 *
 * @author Ethel Lim
 */
public class Position {

    /**
     * initialise the attributes
     */
    private Boolean isTokenHere;
    private Token token ;
    private ArrayList<Position> adjList;
    private Circle ip;

    /**
     * constructor
     * @param ip : intersection point on the board
     */
    public Position(Circle ip){
        System.out.println(this);
        adjList= new ArrayList<>();
        this.ip = ip;
        this.isTokenHere= false;
    }

    /**
     * add adjacent positions to the list
     * @param p : position
     */
    public void addAdjList(Position p){
        this.adjList.add(p);
    }

    /**
     * get adjacent positions list
     * @return ArrayList<Position>
     */
    public ArrayList<Position> getAdjList() {
        return adjList;
    }

    /**
     * get is token here
     * @return Boolean
     */
    public Boolean getIsTokenHere(){
        return this.isTokenHere;
    }
    public void resetToken(){
        this.isTokenHere = false ;
    }

    /**
     * add token instance to the position
     * @param t : token
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

    /**
     * get token
     * @return Token
     */
    public Token getToken() {
        return token;
    }

    /**
     * get Intersection Point
     * @return Circle
     */
    public Circle getIP(){
        return ip ;
    }

}
