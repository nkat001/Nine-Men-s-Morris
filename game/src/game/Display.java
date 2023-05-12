package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.event.EventHandler;


public class Display extends Application {
    private final int CIRCLE_RADIUS = 10;
    private final int TOKEN_RADIUS = 18;

    private Group board ;
    private ArrayList<Circle> ip = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create a Pane to hold the circle and line groups
        StackPane mainPane = new StackPane();
        Label headingLabel = new Label("Nine Men's Morris");
        Font font = Font.font("Comic Sans MS", FontWeight.BOLD, 45);
        headingLabel.setFont(font);
        headingLabel.setStyle("-fx-text-fill: #CD0404;");
        StackPane.setAlignment(headingLabel, Pos.TOP_CENTER);
        headingLabel.setTranslateY(20);
        mainPane.getChildren().add(headingLabel);
        mainPane.setStyle("-fx-background-color: #FFF6C3;"); // Use any valid CSS color value

        // create the game board first
        // create 24 circles represent the ip
        for (int i =0; i<24; i++){
            Circle circle = new Circle( CIRCLE_RADIUS, Color.WHITE);
            circle.setStroke(Color.BLACK);
            ip.add(circle);
        }
        // create outer group first
        Group board= generateBoard();

        // Add the circle and line groups to the main pane
        mainPane.getChildren().add(board);

        System.out.println("hghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        double circleSceneX = ip.get(0).localToScene(ip.get(0).getBoundsInLocal()).getMaxX();
        double circleSceneY = ip.get(0).localToScene(ip.get(0).getBoundsInLocal()).getMaxY();
        System.out.println(circleSceneY);
        System.out.println(circleSceneX);



        //Player 1
        Label player1Label = new Label("Player 1");
        Font playerFont = Font.font("Arial", FontWeight.BOLD, 30);
        player1Label.setFont(playerFont);
        StackPane.setMargin(player1Label, new Insets(200));
        StackPane.setAlignment(player1Label, Pos.TOP_LEFT);
        mainPane.getChildren().add(player1Label);

        double spacing = 100.0;

        // tokens for player 1
        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle(TOKEN_RADIUS, Color.PINK);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 200, 170, 210)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_LEFT);
            makeTokenDraggable(circle);
        }

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(TOKEN_RADIUS, Color.PINK);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 200, 170, 260)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_LEFT);

        }

        // tokens for player 2
        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle(TOKEN_RADIUS, Color.MEDIUMPURPLE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 270, 170, 250)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_RIGHT);
        }

        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle(TOKEN_RADIUS, Color.MEDIUMPURPLE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 220, 170, 250)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_RIGHT);
        }

        //Player 2
        Label player2Label = new Label("Player 2");
        player2Label.setFont(playerFont);
        StackPane.setMargin(player2Label, new Insets(200));
        StackPane.setAlignment(player2Label, Pos.TOP_RIGHT);
        mainPane.getChildren().add(player2Label);


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

        this.board  = new Group();
        board.getChildren().addAll(shapes);

        return board ;
    }

    private double startx ;
    private double starty;
    public void makeTokenDraggable(Node token){
        double x = token.getTranslateX();
        double y = token.getTranslateY();


        token.setOnMousePressed(e ->{
            startx = e.getSceneX() - token.getTranslateX();
            starty = e.getSceneY() - token.getTranslateY();
        });


        token.setOnMouseDragged(e->{;
            System.out.println(startx);
            System.out.println(starty);
            token.setTranslateX(e.getSceneX()- startx );
            token.setTranslateY(e.getSceneY()- starty);
        });


        token.setOnMouseReleased(e -> {
            double releaseX = e.getSceneX()-board.getLayoutX() ;
            double releaseY = e.getSceneY()-board.getLayoutY();
            Circle c = ip.get(2);
            Shape cBarrier = new Circle(400, 100, 50);

            System.out.println("circle c is at:" + c);
            double expandedRadius = c.getRadius() + 50; // Adjust the expansion value as needed

            System.out.println("Release valueX is:" + c.getCenterX());
            System.out.println("Release valueY is:" + c.getCenterY());
            System.out.println("Does C contain inital values:" + cBarrier.contains(releaseX, releaseY));
            System.out.println("Does C contain the released values:" + cBarrier.contains(releaseX + 30, releaseY + 30));
            //c_entrance_value.setRadius(100);

            if(cBarrier.contains(releaseX + 30, releaseY + 30))
            {
                // TODO: NEED TO DO SETTING FOR WHEN TOKEN IS WITHIN BARRIER SET TOKEN TO C LOCATION RELATIVE TO BOARD.
                //                token.setTranslateX(e.getSceneX()- startx );
                System.out.println("Translated X:" + (e.getSceneX()- startx));
                System.out.println("Translated X no startX:" + (e.getSceneX()));
                //                token.setTranslateY(e.getSceneY()- starty);
                System.out.println("Translated Y:" + (e.getSceneY()- starty));
                System.out.println("Translated Y no startY:" + (e.getSceneY()));
//                                token.setTranslateX(c.getCenterX() + board.getLayoutX());
//                                System.out.println("actual board x:" + c.getCenterX() + board.getLayoutX());
//
//                                token.setTranslateY(c.getCenterY() + board.getLayoutY());
//                                System.out.println("actual board x:" + c.getCenterY() + board.getLayoutY());


                System.out.println("shhsdusbcuhdsbfciesudniuwejndiwendoiwa");
            }
            else{
                token.setTranslateX(x);
                token.setTranslateY(y);
                System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            }
            System.out.println("Token released at: (" + releaseX + ", " + releaseY + ")");
            // Perform any desired actions with the release location
        });

    }


    private boolean isPointWithinCircle(double x, double y, double cx, double cy, double r) {
        double dx = x - cx;
        double dy = y - cy;
        double distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= r * r;
    }
    public static void main(String[] args) {
        launch(args);
    }

}