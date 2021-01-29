package PathfindingLab.ui;

import javax.swing.*;
import PathfindingLab.io.IOImg;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class GUI {

    private JFrame frame;
    private IOImg ioImg;
    private int width;
    private int height;

    private Stage primaryStage;
    private BorderPane borderPane;
    private Scene mainScene;

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
    }

    public GUI(Stage primStage) {
        this.primaryStage = primStage;
    }

    public Scene buildScene(String stageTitle) {
        primaryStage.setTitle(stageTitle);
        borderPane = new BorderPane();

        mainScene = new Scene(borderPane);

        return mainScene;
    }
}
