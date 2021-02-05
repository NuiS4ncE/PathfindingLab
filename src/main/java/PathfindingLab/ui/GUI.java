package PathfindingLab.ui;

import javax.swing.*;
//import javax.swing.text.html.ImageView;

import PathfindingLab.algorithms.DijkstraPath;
import PathfindingLab.io.IOImg;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javax.swing.border.EtchedBorder;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private DijkstraPath dPath;
    private IOImg ioImg;


    /**
     * Constructor for the class
     * @param primStage Parameter for receiving a Stage-object
     */
    public GUI(Stage primStage) {
        this.primaryStage = primStage;
    }

    /*
    public void mainSceneGUI() throws IOException {

        frame = new JFrame();
        ioImg = new IOImg();
        frame.setSize(600, 600);
        frame.setTitle("PathfindingLab");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        String[] algorithms = {"Dijkstra"};

        JToggleButton startButton = new JToggleButton("Start");
        JToggleButton endButton = new JToggleButton("End");
        JButton runButton = new JButton("Run");
        JLabel imgLabel = new JLabel(new ImageIcon(ioImg.getBuffImg()));
        JLabel algoLabel = new JLabel("Algorithms");
        JComboBox algoBox = new JComboBox(algorithms);
        JPanel toolPanel = new JPanel();
        Map canvas;

        toolPanel.setLayout(null);
        toolPanel.setBounds(10,10,210,600);
        toolPanel.add(startButton, runButton);

        frame.setVisible(true);
    }

    */

    /**
     * Main scene method.
     * @param stageTitle Parameter for a String
     * @return Returns Scene-object.
     * @throws IOException
     */
    public Scene buildScene(String stageTitle) throws IOException {
        ioImg = new IOImg();
        dPath = new DijkstraPath();
        primaryStage.setTitle(stageTitle);
        gridPane = new GridPane();
        borderPane = new BorderPane();
        StackPane stackPane = new StackPane();


        mainScene = new Scene(stackPane, 600, 600, Color.BLACK);
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        //graphicsContext.setFill(Color.BLUE);
        //graphicsContext.fillRect(10, 10, 400, 400);

        //ImageView imageView = new ImageView("https://www.movingai.com/benchmarks/dao/arena.png");
        BufferedImage capture = ioImg.getBuffImg();
        Image image = SwingFXUtils.toFXImage(capture, null);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

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
        stackPane.setAlignment(startButton, Pos.BOTTOM_LEFT);
        stackPane.setAlignment(endButton, Pos.BOTTOM_CENTER);
        stackPane.setAlignment(exitButton, Pos.TOP_RIGHT);
        stackPane.setAlignment(runButton, Pos.BOTTOM_RIGHT);
        stackPane.getChildren().addAll(canvas, imageView, startButton, endButton, exitButton, runButton);


        imageView.setOnMouseClicked(e -> {
            if(startButton.isSelected()) {
                this.startPosX = (int)e.getSceneX();
                this.startPosY = (int)e.getSceneY();
                System.out.println("X: " + startPosX + " Y: " + startPosY);

                graphicsContext.setFill(Color.RED);
                graphicsContext.fillOval(this.startPosX, this.startPosY, 20,20);
            } else if (endButton.isSelected()) {
                this.endPosX = (int)e.getSceneX();
                this.endPosY = (int)e.getSceneY();
                System.out.println("X: " + endPosX + " Y: " + endPosY);

                graphicsContext.setFill(Color.BLUE);
                graphicsContext.fillOval(this.endPosX, this.endPosY, 20,20);

            }
        });

        runButton.setOnMouseClicked(e -> {
            try {
                dPath.DPathFind(this.startPosY, this.startPosX, this.endPosY, this.endPosX);
            } catch (IOException jk) {
                System.out.println(jk);
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
