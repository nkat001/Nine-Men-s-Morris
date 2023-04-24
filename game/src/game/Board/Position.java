package game.Board;

public class Position {
    private int x;
    private int y;
    private Boolean isTokenHere;
    private char dispChar;

    public Position(int x , int y, char c){
        this.x= x;
        this.y=y;
        this.dispChar= c;
    }

    public  char getDispChar(){
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


}
