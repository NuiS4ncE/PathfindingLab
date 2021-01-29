package PathfindingLab.ui;

//import javax.swing.*;
//import javax.swing.text.html.ImageView;

import PathfindingLab.io.IOImg;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private double posX;
    private double posY;

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
        borderPane = new BorderPane();
        StackPane stackPane = new StackPane();

        mainScene = new Scene(stackPane, 600, 600, Color.BLACK);
        Canvas canvas = new Canvas(300, 300);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(10, 10, 300, 300);

        ImageView imageView = new ImageView("https://www.movingai.com/benchmarks/dao/arena.png");
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setOnMouseClicked(e -> {
            this.posX = e.getSceneX();
            this.posY = e.getSceneY();
            System.out.println("X: " + posX + " Y: " + posY);
            //Stage popup = new Stage();
            //popup.initOwner(primaryStage);
            //popup.show();
        });

        stackPane.getChildren().addAll(canvas, imageView);

        return mainScene;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }
}
