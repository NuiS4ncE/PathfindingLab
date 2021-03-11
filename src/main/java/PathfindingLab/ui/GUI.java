package PathfindingLab.ui;

import javax.swing.*;
//import javax.swing.text.html.ImageView;

import PathfindingLab.algorithms.AStar;
import PathfindingLab.algorithms.DijkstraPath;
import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class GUI {

    private JFrame frame;
    private Stage primaryStage;
    private BorderPane borderPane;
    private Scene mainScene;
    private int startPosX;
    private int startPosY;
    private int endPosX;
    private int endPosY;
    private GridPane gridPane;
    //private DijkstraPath dPath;
    private IOImg ioImg;
    private int wantedHeight;
    private int wantedWidth;
    private Image img;
    private Group root;
    private int runValue;
    //private AStar aStar;


    /**
     * Constructor for the class
     *
     * @param primStage Parameter for receiving a Stage-object
     */
    public GUI(Stage primStage) {
        this.primaryStage = primStage;
    }


    /**
     * Main scene method.
     *
     * @param stageTitle Parameter for a String
     * @return Returns Scene-object.
     * @throws IOException
     */
    public Scene buildScene(String stageTitle) throws IOException {
        ioImg = new IOImg();
        //dPath = new DijkstraPath();
        //aStar = new AStar();
        primaryStage.setTitle(stageTitle);
        gridPane = new GridPane();
        borderPane = new BorderPane();
        root = new Group();

        wantedHeight = 800;
        wantedWidth = 800;

        Canvas canvas = new Canvas(wantedWidth, wantedHeight);
        //Canvas drawCanvas = new Canvas(600, 600);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();


        //BufferedImage buffImg = ioImg.getBuffImg();
        //Image image = SwingFXUtils.toFXImage(buffImg, null);
        //WritableImage refittedImage = new WritableImage(image.getPixelReader(), )

        //graphicsContext.drawImage(image, 0, 0);

        ToggleButton startButton = new ToggleButton("Start");
        ToggleButton endButton = new ToggleButton("End");
        Button exitButton = new Button("Exit");
        Button runButton = new Button("Run");
        ToggleButton[] toolsArr = {startButton, endButton};


        ToggleGroup tools = new ToggleGroup();

        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        ComboBox<String> comboFilter = comboBoxFilter();
        //comboFilter.setPadding(new Insets(5));

        comboFilter.setOnAction((e) -> {
            if (comboFilter.getValue().equals("Dijkstra")) {
                runValue = 1;
                System.out.println("runValue: " + runValue);
            }
            if (comboFilter.getValue().equals("AStar")) {
                runValue = 2;
                System.out.println("runValue: " + runValue);
            }
            if (comboFilter.getValue().equals("IDAStar")) {
                runValue = 3;
                System.out.println("runValue: " + runValue);
            }
            if (comboFilter.getValue().equals("All")) {
                runValue = 4;
                System.out.println("runValue: " + runValue);
            }
        });

        runButton.setOnMouseClicked((f) -> {
            try {
                if (runValue == 1) {
                    System.out.println("Dijkstra selected");
                    DijkstraPath dPath = new DijkstraPath();
                    dPath.DPathFind(ioImg.getFullMap(), this.startPosX, this.startPosY, this.endPosX, this.endPosY, 0);
                    drawPathDijkstra(dPath);
                    //drawVisitedDijkstra(canvas);
                    //dPath.clearRoute();
                }
                if (runValue == 2) {
                    System.out.println("AStar selected");
                    AStar aStar = new AStar();
                    aStar.aStarFind(ioImg.getFullMap(), this.startPosX, this.startPosY, this.endPosX, this.endPosY, 0);
                    drawPathAStar(aStar);
                    //aStar.clearRoute();
                }
            } catch (IOException jk) {
                System.out.println(jk);
            }
        });

        Button openButton = new Button("Open");
        Button clearButton = new Button("Clear");

        exitButton.setOnMouseClicked(e -> {
            Platform.exit();
        });

        VBox buttonPane = new VBox(10);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setStyle("-fx-background-color: #999");
        buttonPane.setPrefWidth(100);

        buttonPane.getChildren().addAll(openButton, comboFilter, startButton, endButton, runButton, clearButton, exitButton);

        canvas.setOnMouseClicked(e -> {
            if (startButton.isSelected()) {
                this.startPosX = (int) e.getX();
                this.startPosY = (int) e.getY();
                System.out.println("X: " + startPosX + " Y: " + startPosY);

                graphicsContext.setFill(Color.RED);
                graphicsContext.fillOval(-10 + this.startPosX, -10 + this.startPosY, 10, 10);
            } else if (endButton.isSelected()) {
                this.endPosX = (int) e.getX();
                this.endPosY = (int) e.getY();
                System.out.println("X: " + endPosX + " Y: " + endPosY);

                graphicsContext.setFill(Color.BLUE);
                graphicsContext.fillOval(-10 + this.endPosX, -10 + this.endPosY, 10, 10);

            }
        });

        clearButton.setOnMouseClicked((e) -> {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.startPosX = 0;
            this.startPosY = 0;
            this.endPosX = 0;
            this.startPosY = 0;

            startButton.setSelected(false);
            endButton.setSelected(false);
            borderPane.setCenter(canvas);
        });

        openButton.setOnAction((e) -> {
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    InputStream inputStream = new FileInputStream(file);
                    img = new Image(inputStream, wantedWidth, wantedHeight, false, true);
                    System.out.println(file);
                    ioImg.setBuffImg(file, wantedWidth, wantedHeight);
                    ioImg.setMap();
                    graphicsContext.drawImage(img, 0, 0);
                } catch (IOException exception) {
                    System.out.println(exception);
                }
            }
        });

        //root.getChildren().addAll(canvas, drawCanvas);
        mainScene = new Scene(borderPane, 1000, 1000, Color.BLACK);
        borderPane.setRight(buttonPane);
        borderPane.setCenter(canvas);

        return mainScene;
    }

    public ComboBox<String> comboBoxFilter() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Dijkstra");
        comboBox.getItems().add("AStar");
        comboBox.getItems().add("IDAStar");
        comboBox.setEditable(false);
        comboBox.setValue("");

        return comboBox;
    }

    public void drawPathDijkstra(DijkstraPath dPath) {
        Canvas canvas1 = drawPoints();
        ArrayList<Node> pathAL = dPath.printRoute();
        for (int i = 0; i < pathAL.size(); i++) {
            canvas1.getGraphicsContext2D().lineTo(pathAL.get(i).getX(), pathAL.get(i).getY());
            canvas1.getGraphicsContext2D().stroke();
        }
        pathAL.clear();
        //drawVisitedDijkstra(canvas1, dPath);
        borderPane.setCenter(canvas1);
    }

    public Canvas drawPoints() {
        Canvas canvas1 = new Canvas(wantedWidth, wantedHeight);
        GraphicsContext graphicsContext1 = canvas1.getGraphicsContext2D();
        graphicsContext1.drawImage(img, 0, 0);
        graphicsContext1.setFill(Color.RED);
        graphicsContext1.fillOval(-10 + this.startPosX, -10 + this.startPosY, 10, 10);
        graphicsContext1.setFill(Color.BLUE);
        graphicsContext1.fillOval(-10 + this.endPosX, -10 + this.endPosY, 10, 10);
        return canvas1;
    }

    public void drawPathAStar(AStar aStar) {
        Canvas canvas2 = drawPoints();
        ArrayList<Node> pathAL = aStar.printRoute();
        for (int i = 0; i < pathAL.size(); i++) {
            canvas2.getGraphicsContext2D().lineTo(pathAL.get(i).getX(), pathAL.get(i).getY());
            canvas2.getGraphicsContext2D().stroke();
        }
        pathAL.clear();
        borderPane.setCenter(canvas2);
        //System.out.println(pathAL.toString());
    }

    public void drawVisitedDijkstra(Canvas canvas, DijkstraPath dPath) {
        ArrayList<Node> visitedNodes = dPath.printVisitedNodes();
        for (int i = 0; i < visitedNodes.size(); i++) {
            canvas.getGraphicsContext2D().fillRect(visitedNodes.get(i).getX(), visitedNodes.get(i).getY(), 0.75, 0.75);
            canvas.getGraphicsContext2D().setFill(Color.RED);
        }
    }

}
