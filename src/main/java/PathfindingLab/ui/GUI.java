package PathfindingLab.ui;

//import javax.swing.*;
//import javax.swing.text.html.ImageView;

import PathfindingLab.io.IOImg;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public class GUI {
/*
    private JFrame frame;
    private IOImg ioImg;
    private int width;
    private int height;
*/
    private Stage primaryStage;
    private BorderPane borderPane;
    private Scene mainScene;
    private double startPosX;
    private double startPosY;
    private double endPosX;
    private double endPosY;
    private GridPane gridPane;
    private Group root;

    /*
    public void initializeGUI() {
        width = 850;
        height = 650;
        frame = new JFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setTitle("PathfindingLab");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    } */

    public GUI(Stage primStage) {
        this.primaryStage = primStage;
    }

    public Scene buildScene(String stageTitle) {
        primaryStage.setTitle(stageTitle);
        gridPane = new GridPane();
        borderPane = new BorderPane();
        StackPane stackPane = new StackPane();
        root = new Group();

        mainScene = new Scene(stackPane, 600, 600, Color.BLACK);
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        //graphicsContext.setFill(Color.BLUE);
        //graphicsContext.fillRect(10, 10, 400, 400);

        ImageView imageView = new ImageView("https://www.movingai.com/benchmarks/dao/arena.png");
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

        ToggleButton startButton = new ToggleButton("Start");
        ToggleButton endButton = new ToggleButton("End");
        Button exitButton = new Button("Exit");

        ToggleButton[] toolsArr = {startButton, endButton};

        ToggleGroup tools = new ToggleGroup();

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        exitButton.setOnMouseClicked(e -> {
            Platform.exit();
        });
        exitButton.setPrefSize(50,40);


        //ComboBox<String> comboBox = new ComboBox<>();
        VBox buttonPane = new VBox(10);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setStyle("-fx-background-color: #999");
        buttonPane.setPrefWidth(100);

        buttonPane.getChildren().addAll(startButton, endButton);
        //topPane.getChildren().addAll(comboBox);
        stackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        stackPane.setAlignment(endButton, Pos.BOTTOM_RIGHT);
        stackPane.setAlignment(exitButton, Pos.TOP_RIGHT);
        stackPane.getChildren().addAll(canvas, imageView, startButton, endButton, exitButton);


        imageView.setOnMouseClicked(e -> {
            if(startButton.isSelected()) {
                this.startPosX = e.getSceneX();
                this.startPosY = e.getSceneY();
                System.out.println("X: " + startPosX + " Y: " + startPosY);

                graphicsContext.setFill(Color.RED);
                graphicsContext.fillOval(this.startPosX, this.startPosY, 20,20);
            } else if (endButton.isSelected()) {
                this.endPosX = e.getSceneX();
                this.endPosY = e.getSceneY();
                System.out.println("X: " + endPosX + " Y: " + endPosY);

                graphicsContext.setFill(Color.BLUE);
                graphicsContext.fillOval(this.endPosX, this.endPosY, 20,20);

            }
        });

        //borderPane.setTop(comboBox);
        //borderPane.setCenter(stackPane);
        //borderPane.setBottom(buttonPane);

        return mainScene;
    }

    public double getStartPosX() {
        return this.startPosX;
    }

    public double getPosY() {
        return this.startPosY;
    }

    public double getEndPosX() {
        return this.endPosX;
    }

    public double getEndPosY() {
        return this.endPosY;
    }
}
