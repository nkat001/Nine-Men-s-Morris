package game.Board;

import game.Action.Action;
import game.Action.Place;
import game.Actor.Player;
import game.Token;

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
                "a------------------b------------------c",
                "|                  |                  |",
                "|      d-----------e-----------f      |",
                "|      |           |           |      |",
                "|      |     g-----h-----i     |      |",
                "|      |     |           |     |      |",
                "j----- k-----l           m-----n------o",
                "|      |     |           |     |      |",
                "|      |     p-----q-----r     |      |",
                "|      |           |           |      |",
                "|      s-----------t-----------u      |",
                "|                  |                  |",
                "v------------------w------------------x");
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

    public void displayPlayerTokens(Player player){
        char playerChar = player.getDispChar();
        ArrayList<Token> tokens = player.getTokens();
        ArrayList<Position> pos = new ArrayList<Position>();

        for (int i = 0; i < tokens.size(); i++){
            pos.add(tokens.get(i).getPosition());
        }
        for (int y= 0; y< height ;y++){
            for (int x =0; x< width;x++){
                Position l = this.getBoardAt(x,y);
                if (l.getDispChar()== playerChar){
                    int ind= pos.indexOf(l);
                    System.out.print(ind);
                }
                else {
                    System.out.print(l.getDispChar());
                }
            }
            System.out.println("");
        }
    }



    public Position findPosition(char positionChar) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position l = this.getBoardAt(x, y);
                if (l.getDispChar() == positionChar) {
                    return l;
                }
            }
        }
        return null;
    }


}
