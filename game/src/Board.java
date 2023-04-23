import java.util.*;

public class Board {
    protected Position [][] board ;
    protected int width;
    protected int height;

    public Board(){
        generateBoard();
    }

    private void createBoardFromStrings(List<String> list){
        width = list.get(0).length();
        height= list.size();
        board = new Position [width][height];
        for (int x=0; x< width ;x++){
            for (int y= 0; y< height ;y++){
                char c = list.get(y).charAt(x);
                board[x][y] = new Position(x,y,c);
            }
        }
    }

    public void generateBoard(){
        List<String> gameBoard = Arrays.asList(
                "*---------*---------*",
                "|         |         |",
                "|   *-----*-----*   |",
                "|   |     |     |   |",
                "|   | *---*---* |   |",
                "|   | |       | |   |",
                "*---* *-------* *---*",
                "|   | |       | |   |",
                "|   | *---*---* |   |",
                "|   |     |     |   |",
                "|   *-----*-----*   |",
                "|         |         |",
                "*---------*---------*");
        createBoardFromStrings(gameBoard);
    }

    public Position getBoardAt(int x , int y ) {
        return this.board[x][y];
    }

    public void display(){
        for (int y= 0; y< height ;y++){
            for (int x =0; x< width;x++){
                Position l = this.getBoardAt(x,y);
                System.out.print(l.getDispChar());
            }
            System.out.println("");
        }
    }

}
