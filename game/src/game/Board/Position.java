package game.Board;

import game.Token;

public class Position {
    private int x;
    private int y;
    private Boolean isTokenHere;
    private char dispChar;
    private Token token ;

    public Position(int x , int y, char c){
        this.x= x;
        this.y=y;
        this.dispChar= c;
        this.isTokenHere= false ;
    }

    public  char getDispChar(){
        if (isTokenHere){
            return token.getDispChar();
        }
        return dispChar;
    }
    public int getX(){
        return x ;
    }

    public int getY(){
        return y ;
    }

    public void setDispChar(char c){
        this.dispChar = c;
    }
    public void addToken(Token t){
        isTokenHere= true ;
        token = t;}

    public void removeToken(){
        token = null ;
        isTokenHere= false;
    }

}
