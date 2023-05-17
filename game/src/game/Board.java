package game;

import game.Actor.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.*;

/**
 * A Singleton Board class that create the game board
 *
 * Created by:
 *
 * @author Ethel Lim
 * Modified by : Mahesh
 */
public class Board {
    private final int CIRCLE_RADIUS = 10;
    private Group gameBoard;
    private static Board board;
    private ArrayList<Circle> ip;
    private ArrayList<Position> positions;

    /**
     * private constructor
     */
    private Board(){
        ip = new ArrayList<>();
        positions = new ArrayList<>();
        for (int i =0; i<24; i++){
            Circle circle = new Circle( CIRCLE_RADIUS, Color.WHITE);
            circle.setStroke(Color.BLACK);
            ip.add(circle);
            // create a position for each ip
            positions.add(new Position(circle));
        }
        // change board size here
        setPositionAdjList();
        gameBoard=generateBoard();
        setMillPositions();

    }


    public void setMillPositions(){
        for ( int i =0 ; i< positions.size(); i+=8){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            arr1.add(positions.get(i+1));
            arr1.add(positions.get(i+2));

            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i+7 ));
            arr2.add(positions.get(i+7-1));

            final_arr.add(arr1);
            final_arr.add(arr2);
            System.out.println(i  +"psoition what ");
            System.out.println(final_arr );
            Rule.setMillPositions(p , final_arr);
        }

        for ( int i =2 ; i< positions.size(); i+=8){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            arr1.add(positions.get(i+1));
            arr1.add(positions.get(i+2));

            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i-1 ));
            arr2.add(positions.get(i-2));

            final_arr.add(arr1);
            final_arr.add(arr2);
            Rule.setMillPositions(p , final_arr);
        }
        for ( int i =4 ; i< positions.size(); i+=8){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            arr1.add(positions.get(i+1));
            arr1.add(positions.get(i+2));

            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i-1 ));
            arr2.add(positions.get(i-2));

            final_arr.add(arr1);
            final_arr.add(arr2);
            Rule.setMillPositions(p , final_arr);
        }
        for ( int i =6 ; i< positions.size(); i+=8){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            arr1.add(positions.get(i+1));
            arr1.add(positions.get(i-6));

            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i-1 ));
            arr2.add(positions.get(i-2));

            final_arr.add(arr1);
            final_arr.add(arr2);
            Rule.setMillPositions(p , final_arr);
        }


        for ( int i =1 ; i< 8; i+=2){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            if (i == 7 ){
                arr1.add(positions.get(i-7));
                arr1.add(positions.get(i-1));
            }
            else{
                arr1.add(positions.get(i+1));
                arr1.add(positions.get(i-1));
            }
            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i+8 ));
            arr2.add(positions.get(i+8+8));

            final_arr.add(arr1);
            final_arr.add(arr2);
            Rule.setMillPositions(p , final_arr);
        }

        for ( int i =9 ; i< 16; i+=2){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            if (i == 15 ){
                arr1.add(positions.get(i-7));
                arr1.add(positions.get(i-1));
            }
            else{
                arr1.add(positions.get(i+1));
                arr1.add(positions.get(i-1));
            }
            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i+8 ));
            arr2.add(positions.get(i-8));
            final_arr.add(arr1);
            final_arr.add(arr2);
            Rule.setMillPositions(p , final_arr);
        }

        for ( int i =17 ; i< 24; i+=2){
            Position p = positions.get(i);
            ArrayList<ArrayList<Position>> final_arr= new ArrayList<>();
            ArrayList<Position> arr1= new ArrayList<>();
            if (i == 23 ){
                arr1.add(positions.get(i-7));
                arr1.add(positions.get(i-1));
            }
            else{
                arr1.add(positions.get(i+1));
                arr1.add(positions.get(i-1));
            }
            ArrayList<Position> arr2= new ArrayList<>();
            arr2.add(positions.get(i-8 ));
            arr2.add(positions.get(i-8-8));
            final_arr.add(arr1);
            final_arr.add(arr2);
            Rule.setMillPositions(p , final_arr);
        }

    }


    /**
     * create only one board instance and return the board instance
     */
    public static Board getInstance(){
        if (board == null){
            board = new Board();
        }
        return board;
    }
    public Group getGameBoard(){
        return this.gameBoard;
    }

    public void setIPLocation(){
        // inter square
        double a, b,c;
        a = 100;
        b = 370;
        c = 640;
        // outer square
        ip.get(0).setCenterX(a);
        ip.get(0).setCenterY(a);
        ip.get(1).setCenterX(b);
        ip.get(1).setCenterY(a);
        ip.get(2).setCenterX(c);
        ip.get(2).setCenterY(a);
        ip.get(3).setCenterX(c);
        ip.get(3).setCenterY(b);
        ip.get(4).setCenterX(c);
        ip.get(4).setCenterY(c);
        ip.get(5).setCenterX(b);
        ip.get(5).setCenterY(c);
        ip.get(6).setCenterX(a);
        ip.get(6).setCenterY(c);
        ip.get(7).setCenterX(a);
        ip.get(7).setCenterY(b);

        // inter square
        a = 190;
        b = 370;
        c = 550;
        ip.get(8).setCenterX(a);
        ip.get(8).setCenterY(a);
        ip.get(9).setCenterX(b);
        ip.get(9).setCenterY(a);
        ip.get(10).setCenterX(c);
        ip.get(10).setCenterY(a);
        ip.get(11).setCenterX(c);
        ip.get(11).setCenterY(b);
        ip.get(12).setCenterX(c);
        ip.get(12).setCenterY(c);
        ip.get(13).setCenterX(b);
        ip.get(13).setCenterY(c);
        ip.get(14).setCenterX(a);
        ip.get(14).setCenterY(c);
        ip.get(15).setCenterX(a);
        ip.get(15).setCenterY(b);

        a = 280;
        b = 370;
        c = 460;
        ip.get(16).setCenterX(a);
        ip.get(16).setCenterY(a);
        ip.get(17).setCenterX(b);
        ip.get(17).setCenterY(a);
        ip.get(18).setCenterX(c);
        ip.get(18).setCenterY(a);
        ip.get(19).setCenterX(c);
        ip.get(19).setCenterY(b);
        ip.get(20).setCenterX(c);
        ip.get(20).setCenterY(c);
        ip.get(21).setCenterX(b);
        ip.get(21).setCenterY(c);
        ip.get(22).setCenterX(a);
        ip.get(22).setCenterY(c);
        ip.get(23).setCenterX(a);
        ip.get(23).setCenterY(b);
    }

    public void createLine(int from , int end, ArrayList<Shape> shapes ){
        for (int i = from ; i <=end ; i++){
            Line line;
            if (i == end ){
                line = new Line(ip.get(i).getCenterX(), ip.get(i).getCenterY(), ip.get(from).getCenterX(), ip.get(from).getCenterY());
            }
            else{
                line = new Line(ip.get(i).getCenterX(), ip.get(i).getCenterY(), ip.get(i+1).getCenterX(), ip.get(i+1).getCenterY());
            }
            shapes.add(line);
        }
    }

    public void createMiddleLine(int from , int end, ArrayList<Shape> shapes ){
        Line line= new Line(ip.get(from).getCenterX(), ip.get(from).getCenterY(), ip.get(end).getCenterX(), ip.get(end).getCenterY());
        shapes.add(line);
    }

    private Group generateBoard(){
        ArrayList<Shape> shapes = new ArrayList<>();
        setIPLocation();

        createLine(0, 7, shapes);
        createLine(8, 15, shapes);
        createLine(16, 23, shapes);

        // middle line
        createMiddleLine(1, 17, shapes );
        createMiddleLine(3, 19, shapes );
        createMiddleLine(5, 21, shapes );
        createMiddleLine(7, 23, shapes );

        for (int i = 0; i < 24; i++) {shapes.add(ip.get(i));}

        Group group = new Group();
        group.getChildren().addAll(shapes);
        return group ;
    }

    private void setPositionAdjList(){
        for (int i =0; i<24 ; i++){
            Position p = positions.get(i);
            if (i ==0) {
                p.addAdjList(positions.get(i+1));
                p.addAdjList(positions.get(7));
            }
            else if(i==7){
                p.addAdjList(positions.get(0));
                p.addAdjList(positions.get(i-1));
            }
            else if(i==8){
                p.addAdjList(positions.get(i+1));
                p.addAdjList(positions.get(15));
            }
            else if(i==15){
                p.addAdjList(positions.get(8));
                p.addAdjList(positions.get(i-1));
            }
            else if(i==16){
                p.addAdjList(positions.get(i+1));
                p.addAdjList(positions.get(23));
            }
            else if(i==23){
                p.addAdjList(positions.get(16));
                p.addAdjList(positions.get(i-1));
            }
            else{
                p.addAdjList(positions.get(i-1));
                p.addAdjList(positions.get(i+1));
            }
        }
        // manually adding the straight line intersection point
        for (int i = 1; i <=7;i+=2 ){
            Position p = positions.get(i);
            p.addAdjList(positions.get(i+8));

            Position p1 = positions.get(i+8);
            p1.addAdjList(positions.get(i));
            p1.addAdjList(positions.get(i+8+8));

            Position p2 = positions.get(i+8+8);
            p2.addAdjList(positions.get(i+8));
        }
    }

    public ArrayList<Position> getPositions(){
        return this.positions;
    }
}
