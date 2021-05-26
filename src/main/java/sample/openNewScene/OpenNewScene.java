package sample.openNewScene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class OpenNewScene {
    public static  <T> T openNewScene(String window, Button button) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        return loader.getController();
    }
    public static  <T> T showEditDialog(MouseEvent e, String window) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(window));
               try {
                   Pane page = loader.load();
                   Stage dialogStage = new Stage();
                   dialogStage.initModality(Modality.APPLICATION_MODAL);
                   Scene scene = new Scene(page);
                   dialogStage.setScene(scene);
                   dialogStage.show();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
        return loader.getController();
    }
    public static  <T> T showEditDialogTouchButton(String window) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(window));
        try {
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return loader.getController();
    }
}

