package PathfindingLab.ui;

import javax.swing.*;
//import javax.swing.text.html.ImageView;

import PathfindingLab.algorithms.AStar;
import PathfindingLab.algorithms.DijkstraPath;
import PathfindingLab.algorithms.IDAStar;
import PathfindingLab.io.IOImg;
import PathfindingLab.utils.MyList;
import PathfindingLab.utils.Node;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    private Node routeFinal, visitedNode;
    private MyList<Node> routeNodes, visitedNodes;
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
        primaryStage.setTitle(stageTitle);
        gridPane = new GridPane();
        borderPane = new BorderPane();
        root = new Group();

        wantedHeight = 800;
        wantedWidth = 800;

        Canvas canvas = new Canvas(wantedWidth, wantedHeight);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

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

        comboFilter.setOnAction((e) -> {
            if (comboFilter.getValue().equals("Dijkstra")) {
                runValue = 1;
                //System.out.println("runValue: " + runValue);
            }
            if (comboFilter.getValue().equals("AStar")) {
                runValue = 2;
                //System.out.println("runValue: " + runValue);
            }
            if (comboFilter.getValue().equals("IDAStar")) {
                runValue = 3;
                //System.out.println("runValue: " + runValue);
            }
            if (comboFilter.getValue().equals("All")) {
                runValue = 4;
                //System.out.println("runValue: " + runValue);
            }
        });

        runButton.setOnMouseClicked((f) -> {
            try {
                if (runValue == 1) {
                    //System.out.println("Dijkstra selected");
                    DijkstraPath dPath = new DijkstraPath();
                    dPath.DPathFind(ioImg.getFullMap(), this.startPosX, this.startPosY, this.endPosX, this.endPosY, 0);
                    drawPathDijkstra(dPath, new Node(this.startPosX, this.startPosY, 0));
                    //drawVisitedDijkstra(canvas);
                    //dPath.clearRoute();
                }
                if (runValue == 2) {
                    //System.out.println("AStar selected");
                    AStar aStar = new AStar();
                    aStar.aStarFind(ioImg.getFullMap(), this.startPosX, this.startPosY, this.endPosX, this.endPosY, 0);
                    drawPathAStar(aStar, new Node(this.startPosX, this.startPosY, 0));
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
                //System.out.println("X: " + startPosX + " Y: " + startPosY);

                graphicsContext.setFill(Color.RED);
                graphicsContext.fillOval(-10 + this.startPosX, -10 + this.startPosY, 10, 10);
            } else if (endButton.isSelected()) {
                this.endPosX = (int) e.getX();
                this.endPosY = (int) e.getY();
                //System.out.println("X: " + endPosX + " Y: " + endPosY);

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

    /**
     * Method for ComboBox drop-down list
     *
     * @return returns ComboBox with listed algorithms
     */
    public ComboBox<String> comboBoxFilter() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Dijkstra");
        comboBox.getItems().add("AStar");
        comboBox.getItems().add("IDAStar");
        comboBox.setEditable(false);
        comboBox.setValue("");

        return comboBox;
    }

    /**
     * Method for drawing the of Dijkstra
     *
     * @param dPath     DijkstraPath type parameter for use of class methods
     * @param startNode Node type parameter for the starting Node
     */
    public void drawPathDijkstra(DijkstraPath dPath, Node startNode) {
        Canvas canvas1 = drawPoints();
        MyList<Node> pathAL = printRoute(dPath, null, null, startNode);
        for (int i = 0; i < pathAL.size(); i++) {
            canvas1.getGraphicsContext2D().lineTo(pathAL.get(i).getX(), pathAL.get(i).getY());
            canvas1.getGraphicsContext2D().stroke();
        }
        pathAL.clear();
        //drawVisitedDijkstra(canvas1, dPath);
        borderPane.setCenter(canvas1);
    }

    /**
     * Method to draw starting and end points set by the user
     *
     * @return returns canvas with drawn points
     */
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

    /**
     * Method for drawing the path of AStar
     *
     * @param aStar     AStar type parameter for use of class methods
     * @param startNode Node type parameter for use of starting Node
     */
    public void drawPathAStar(AStar aStar, Node startNode) {
        Canvas canvas2 = drawPoints();
        MyList<Node> pathAL = printRoute(null, aStar, null, startNode);
        for (int i = 0; i < pathAL.size(); i++) {
            canvas2.getGraphicsContext2D().lineTo(pathAL.get(i).getX(), pathAL.get(i).getY());
            canvas2.getGraphicsContext2D().stroke();
        }
        pathAL.clear();
        borderPane.setCenter(canvas2);
    }

    /**
     * Method for drawing visited Nodes
     *
     * @param canvas    JavaFX Canvas type parameter for using GraphicsContext2D to draw visited Nodes
     * @param dPath     DijkstraPath type parameter for use of class methods
     * @param startNode Node type parameter for the starting Node
     */
    public void drawVisitedDijkstra(Canvas canvas, DijkstraPath dPath, Node startNode) {
        MyList<Node> visitedNodes = printVisitedNodes(dPath, null, null, startNode);
        for (int i = 0; i < visitedNodes.size(); i++) {
            canvas.getGraphicsContext2D().fillRect(visitedNodes.get(i).getX(), visitedNodes.get(i).getY(), 0.75, 0.75);
            canvas.getGraphicsContext2D().setFill(Color.RED);
        }
    }

    /**
     * Method for getting route Nodes
     *
     * @param dijkstraPath DijkstraPath type parameter for possible use of dijkstra
     * @param aStar        AStar type parameter for possible use of astar
     * @param idaStar      IDAStar type parameter for possible use of idastar
     * @param startNode    Node type parameter for the starting Node
     * @return returns MyList type list of route Nodes
     */
    public MyList<Node> printRoute(DijkstraPath dijkstraPath, AStar aStar, IDAStar idaStar, Node startNode) {
        if (runValue == 1) {
            routeFinal = dijkstraPath.getRouteFinal();
            routeNodes = dijkstraPath.getRoute();
        } else if (runValue == 2) {
            routeFinal = aStar.getRouteFinal();
            routeNodes = aStar.getRoute();
        } else if (runValue == 3) {
            routeFinal = idaStar.getRouteFinal();
            routeNodes = idaStar.getRoute();
        }
        if (routeFinal != null) {
            while (routeFinal != startNode) {
                if (routeFinal.getPrevNode() == null) break;
                routeNodes.add(routeFinal.getPrevNode());
                routeFinal = routeFinal.getPrevNode();
            }
        } else {
            System.out.println("Route not found! " + routeNodes.toString());
        }

        return routeNodes;
    }

    /**
     * Method for getting visited Nodes
     *
     * @param dijkstraPath DijkstraPath type parameter for possible use of dijkstra
     * @param aStar        AStar type parameter for possible use of astar
     * @param idaStar      IDAStar type parameter for possible use of idastar
     * @param startNode    Node type parameter for the starting Node
     * @return returns MyList type list of visited Nodes
     */
    public MyList printVisitedNodes(DijkstraPath dijkstraPath, AStar aStar, IDAStar idaStar, Node startNode) {
        if (runValue == 1) {
            visitedNode = dijkstraPath.getVisitedNode();
            visitedNodes = dijkstraPath.getVisitedNodes();
        } else if (runValue == 2) {
            visitedNode = aStar.getVisitedNode();
            visitedNodes = aStar.getVisitedNodes();
        } else if (runValue == 3) {
            visitedNode = idaStar.getVisitedNode();
            visitedNodes = idaStar.getVisitedNodes();
        }
        while (visitedNode != startNode) {
            if (routeFinal.getPrevNode() == null) break;
            visitedNodes.add(visitedNode.getPrevNode());
            visitedNode = visitedNode.getPrevNode();
        }
        return visitedNodes;
    }

    /**
     * Method for clearing the route ArrayList
     */
    public void clearRoute() {
        if (routeFinal != null) {
            routeFinal.clearNode();
            routeFinal = null;
            routeNodes.clear();
        } else {
            System.out.println("Clearing of failed!");
        }
    }


}
