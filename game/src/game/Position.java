package game;

import javafx.scene.shape.Circle;
import java.util.ArrayList;

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

    private ArrayList<Position> adjList;
    private Circle ip;


    /**
     * Constructor
     */
    public Position(double x , double y){
        this.x= x;
        this.y=y;
        this.isTokenHere= false ;
    }

    public Position(Circle ip){
        adjList= new ArrayList<>();
        this.ip = ip;
        this.isTokenHere= false ;
    }

    public void addAdjList(Position p){
        this.adjList.add(p);
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

    public Boolean getIsTokenHere(){
        return this.isTokenHere;
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

    public Circle getIP(){
        return ip ;
    }

}
