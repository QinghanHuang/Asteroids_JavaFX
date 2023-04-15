package com.java.asteroids.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlInfo {
    public static void load(Stage stage){

        try {
            Parent root= FXMLLoader.load(Index.class.getResource("/fxml/control_info.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
