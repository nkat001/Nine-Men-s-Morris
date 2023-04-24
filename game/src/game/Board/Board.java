package game.Board;

import java.util.*;

public class Board {
    private Position [][] gameBoard ;
    private int width;
    private int height;
    private IntersectionPoint ip;
    private static Board board ;

    private Board(){
        generateBoard();
//        ip= IntersectionPoint.getInstance();
    }

    public static Board getInstance(){
        if (board == null){
            board = new Board();
        }
        return board;
    }

    private void createBoardFromStrings(List<String> list){
        width = list.get(0).length();
        height= list.size();
        gameBoard = new Position [width][height];
        for (int x=0; x< width ;x++){
            for (int y= 0; y< height ;y++){
                char c = list.get(y).charAt(x);
                gameBoard[x][y] = new Position(x,y,c);
//                ip.addListIP(gameBoard[x][y]);
            }
        }
    }

    public void generateBoard(){
        List<String> gameBoard = Arrays.asList(
                "*-------*-------*",
                "|       |       |",
                "| *-----*-----* |",
                "| |     |     | |",
                "| | *---*---* | |",
                "| | |       | | |",
                "*-* *-------* *-*",
                "| | |       | | |",
                "| | *---*---* | |",
                "| |     |     | |",
                "| *-----*-----* |",
                "|       |       |",
                "*-------*-------*");
        createBoardFromStrings(gameBoard);
    }

    public Position getBoardAt(int x , int y ) {
        return this.gameBoard[x][y];
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
