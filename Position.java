public class Position {
    private int x;
    private int y;
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
}
