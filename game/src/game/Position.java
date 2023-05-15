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
    private Boolean isTokenHere;
    private Token token ;

    public ArrayList<Position> getAdjList() {
        return adjList;
    }

    private ArrayList<Position> adjList;
    private Circle ip;
    private int tokenRow;
    private int tokenColumn;


    /**
     * Constructor
     */
    public Position(Circle ip){
        adjList= new ArrayList<>();
        this.ip = ip;
        this.isTokenHere= false;
        this.tokenRow = 0;
        this.tokenColumn = 0;
    }



    public void addAdjList(Position p){
        this.adjList.add(p);
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

    public Boolean getTokenHere() {
        return isTokenHere;
    }

    public Token getToken() {
        return token;
    }

    public Circle getIP(){
        return ip ;
    }

    public int getTokenRow() {
        return tokenRow;
    }

    public void setTokenRow(int tokenRow) {
        this.tokenRow = tokenRow;
    }

    public int getTokenColumn() {
        return tokenColumn;
    }

    public void setTokenColumn(int tokenColumn) {
        this.tokenColumn = tokenColumn;
    }
}
