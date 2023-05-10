package game;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Display extends Application {
    private final int CIRCLE_RADIUS = 5;

    private ArrayList<Circle> ip = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane to hold the circle and line groups
        StackPane mainPane = new StackPane();

        // create 24 circles represent the ip
        for (int i =0; i<24; i++){
            Circle circle = new Circle( CIRCLE_RADIUS, Color.WHITE);
            circle.setStroke(Color.BLACK);
            ip.add(circle);
        }
        // create outer group first
        Group board= generateBoard();

        // Add the circle and line groups to the main pane
        mainPane.getChildren().addAll(board);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        // Create a scene and add the main pane to it
        Scene scene = new Scene(mainPane, screenWidth, screenHeight);

        // Set the stage properties and show it
        primaryStage.setTitle("Nine Men's Morris");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setIPLocation(){
        // outer square
        ip.get(0).setCenterX(100);
        ip.get(0).setCenterY(100);
        ip.get(1).setCenterX(250);
        ip.get(1).setCenterY(100);
        ip.get(2).setCenterX(400);
        ip.get(2).setCenterY(100);
        ip.get(3).setCenterX(400);
        ip.get(3).setCenterY(250);
        ip.get(4).setCenterX(400);
        ip.get(4).setCenterY(400);
        ip.get(5).setCenterX(250);
        ip.get(5).setCenterY(400);
        ip.get(6).setCenterX(100);
        ip.get(6).setCenterY(400);
        ip.get(7).setCenterX(100);
        ip.get(7).setCenterY(250);

        // inter square
        double a, b,c;
        a = 150;
        b = 250;
        c = 350;
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

        a = 200;
        b = 250;
        c = 300;
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


        for (int i = 0; i < 24; i++){
            shapes.add(ip.get(i));
        }

        Group group = new Group();
        group.getChildren().addAll(shapes);

        return group ;
    }

    public static void main(String[] args) {
        launch(args);
    }

}