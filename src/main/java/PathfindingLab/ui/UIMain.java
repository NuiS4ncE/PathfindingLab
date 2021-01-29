package PathfindingLab.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIMain extends Application {

    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("PathfindingLab");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        closeRequest(primaryStage);
    }

    public void closeRequest(Stage stage) {
        stage.setOnCloseRequest(e -> {
            System.out.println("Exiting application");
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}