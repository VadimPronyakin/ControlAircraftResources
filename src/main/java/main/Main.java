package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlFiles/sample.fxml"));
        primaryStage.setTitle("Контроль работ и ресурсов");
        primaryStage.setScene(new Scene(root, 950, 600));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/images/Programlogo.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
